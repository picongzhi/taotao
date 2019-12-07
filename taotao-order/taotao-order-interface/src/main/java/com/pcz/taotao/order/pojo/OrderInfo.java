package com.pcz.taotao.order.pojo;

import com.pcz.taotao.pojo.TbOrder;
import com.pcz.taotao.pojo.TbOrderItem;
import com.pcz.taotao.pojo.TbOrderShipping;

import java.io.Serializable;
import java.util.List;

/**
 * @author picongzhi
 */
public class OrderInfo extends TbOrder implements Serializable {
    private List<TbOrderItem> orderItems;
    private TbOrderShipping orderShipping;

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
