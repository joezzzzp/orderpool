package com.zzz.orderpool.component;

import com.zzz.orderpool.params.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zzz
 * @date 2019/8/6 16:24
 **/
@Component
public class OrderGenerator {

    private static final String PREFIX = "BD";

    private static volatile OrderGenerator instance = null;

    private static AtomicInteger count = new AtomicInteger(0);

    private OrderGenerator() {
        //empty implementation
    }

    @Bean
    public static OrderGenerator get() {
        if (instance == null) {
            synchronized (OrderGenerator.class) {
                if (instance == null) {
                    instance = new OrderGenerator();
                }
            }
        }
        return instance;
    }

    public Order newOrder() {
        int no = count.getAndIncrement();
        String orderNo = String.format("%s%11d", PREFIX, no).replace(" ", "0");
        Order newOrder = new Order();
        newOrder.setOrderNo(orderNo);
        return newOrder;
    }
}
