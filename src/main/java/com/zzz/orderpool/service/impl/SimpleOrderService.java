package com.zzz.orderpool.service.impl;

import com.zzz.orderpool.component.OrderNoGenerator;
import com.zzz.orderpool.params.Order;
import com.zzz.orderpool.service.OrderService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author zzz
 * @date 2019/8/6 16:09
 **/
public class SimpleOrderService implements OrderService, InitializingBean {

    private BlockingQueue<Order> orderPool;

    @Value("${simpleOrderPoolSize}")
    private int poolSize;

    @Value("${timeOut}")
    private long timeOutMilliSecond;

    private OrderNoGenerator orderNoGenerator;

    @Override
    public void afterPropertiesSet() {
        orderPool = new ArrayBlockingQueue<>(poolSize);
    }

    @Autowired
    @Qualifier(value = "simpleOrderNoGenerator")
    public void setOrderNoGenerator(OrderNoGenerator orderNoGenerator) {
        this.orderNoGenerator = orderNoGenerator;
    }

    @Override
    public Order add(Order newOrder) {
        String newOrderOr = orderNoGenerator.nextOrderNo();
        newOrder.setOrderNo(newOrderOr);
        if (orderPool.add(newOrder)) {
            return newOrder;
        }
        return null;
    }

    @Override
    public boolean remove(String orderNo) {
        Order needRemovedOrder = new Order();
        needRemovedOrder.setOrderNo(orderNo);
        return orderPool.remove(needRemovedOrder);
    }

    @Override
    public Order takeOne() throws InterruptedException {
        return orderPool.poll(timeOutMilliSecond, TimeUnit.MILLISECONDS);
    }

}
