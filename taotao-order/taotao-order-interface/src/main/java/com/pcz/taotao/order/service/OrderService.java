package com.pcz.taotao.order.service;

import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.order.pojo.OrderInfo;

/**
 * @author picongzhi
 */
public interface OrderService {
    /**
     * 创建订单
     *
     * @param orderInfo 订单信息
     * @return TaotaoResult
     */
    TaotaoResult createOrder(OrderInfo orderInfo);
}
