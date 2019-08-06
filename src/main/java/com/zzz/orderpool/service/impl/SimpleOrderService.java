package com.zzz.orderpool.service.impl;

import com.zzz.orderpool.component.OrderGenerator;
import com.zzz.orderpool.params.Order;
import com.zzz.orderpool.service.OrderService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author zzz
 * @date 2019/8/6 16:09
 **/

@Service
public class SimpleOrderService implements OrderService, InitializingBean {

    private BlockingQueue<Order> orderPool;

    @Value("${simpleOrderPoolSize:1000}")
    private int poolSize;

    @Value("${timeOut:5000}")
    private long timeOutMilliSecond;

    private OrderGenerator orderGenerator;

    @Override
    public void afterPropertiesSet() throws Exception {
        orderPool = new ArrayBlockingQueue<>(poolSize);
    }

    @Autowired
    public void setOrderGenerator(OrderGenerator orderGenerator) {
        this.orderGenerator = orderGenerator;
    }

    @Override
    public Order add() {
        Order newOrder = orderGenerator.newOrder();
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
