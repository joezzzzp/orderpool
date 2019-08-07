package com.zzz.orderpool.component;

import org.springframework.stereotype.Component;

/**
 * orderNo generator
 * @author zzz
 * @date 2019/8/7 10:02
 **/
@Component
public interface OrderNoGenerator {

    /**
     * get next order number
     * @return orderNo
     */
    String nextOrderNo();

    /**
     * prefix
     * @return 订单前缀
     */
    default String getPrefix() {
        return "BD";
    }
}
