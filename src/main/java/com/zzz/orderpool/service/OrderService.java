package com.zzz.orderpool.service;

import com.zzz.orderpool.params.Order;
import org.springframework.stereotype.Service;

/**
 * @author zzz
 * @date 2019/8/6 15:38
 **/
@Service
public interface OrderService {

    /**
     * add new order
     * @param newOrder new order entity
     * @return order entity with order no
     */
    Order add(Order newOrder);

    /**
     * remove order
     * @param orderNo order no
     * @return removing result
     */
    boolean remove(String orderNo);

    /**
     * get first order in queue
     * @return the order entity
     */
    Order takeOne() throws InterruptedException;
}
