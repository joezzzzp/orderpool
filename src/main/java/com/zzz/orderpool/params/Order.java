package com.zzz.orderpool.params;

import org.springframework.util.StringUtils;

/**
 * @author zzz
 * @date 2019/8/6 15:52
 **/

public class Order {

    /**
     * 订单编号
     */
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Order) {
            Order order = (Order) obj;
            if (!StringUtils.isEmpty(orderNo) && !StringUtils.isEmpty(order.getOrderNo())) {
                return orderNo.equals(order.getOrderNo());
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return orderNo.hashCode();
    }
}
