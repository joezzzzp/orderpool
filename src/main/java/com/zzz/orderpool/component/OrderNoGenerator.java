package com.zzz.orderpool.component;

import org.springframework.stereotype.Component;

/**
 * orderNo生成器
 * @author zzz
 * @date 2019/8/7 10:02
 **/
@Component
public interface OrderNoGenerator {

    /**
     * 获取下一个OrderNo
     * @return orderNo
     */
    String nextOrderNo();

    /**
     * 订单前缀
     * @return 订单前缀
     */
    default String getPrefix() {
        return "BD";
    }
}
