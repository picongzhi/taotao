package com.pcz.taotao.order.service.impl;

import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.mapper.TbOrderItemMapper;
import com.pcz.taotao.mapper.TbOrderMapper;
import com.pcz.taotao.mapper.TbOrderShippingMapper;
import com.pcz.taotao.order.pojo.OrderInfo;
import com.pcz.taotao.order.service.OrderService;
import com.pcz.taotao.order.service.jedis.JedisClient;
import com.pcz.taotao.pojo.TbOrderItem;
import com.pcz.taotao.pojo.TbOrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author picongzhi
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Value("${ORDER_ID_GEN_KEY}")
    private String ORDER_ID_GEN_KEY;
    @Value("${ORDER_ID_BEGIN_VALUE}")
    private String ORDER_ID_BEGIN_VALUE;
    @Value("${ORDER_ITEM_GEN_KEY}")
    private String ORDER_ITEM_GEN_KEY;

    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;
    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;
    @Autowired
    private JedisClient jedisClient;

    @Override
    public TaotaoResult createOrder(OrderInfo orderInfo) {
        if (!jedisClient.exists(ORDER_ID_GEN_KEY)) {
            jedisClient.set(ORDER_ID_GEN_KEY, ORDER_ID_BEGIN_VALUE);
        }

        String orderId = jedisClient.incr(ORDER_ID_GEN_KEY).toString();
        orderInfo.setOrderId(orderId);
        orderInfo.setPostFee("0");
        // 状态：1.未付款，2.已付款，3.未发货，4.已发货，5.交易成功，6.交易关闭
        orderInfo.setStatus(1);
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        tbOrderMapper.insert(orderInfo);

        List<TbOrderItem> tbOrderItemList = orderInfo.getOrderItems();
        tbOrderItemList.forEach(tbOrderItem -> {
            tbOrderItem.setOrderId(orderId);
            tbOrderItemMapper.insert(tbOrderItem);
        });

        TbOrderShipping tbOrderShipping = orderInfo.getOrderShipping();
        tbOrderShipping.setOrderId(orderId);
        tbOrderShipping.setCreated(new Date());
        tbOrderShipping.setUpdated(new Date());
        tbOrderShippingMapper.insert(tbOrderShipping);

        return TaotaoResult.ok(orderId);
    }
}
