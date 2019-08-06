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
     * 新增订单
     * @return 新增的订单实体
     */
    Order add();

    /**
     * 移除订单
     * @param orderNo 订单编号
     * @return 移除的订单实体
     */
    boolean remove(String orderNo);

    /**
     * 获取最新订单
     * @return 获取的订单实体
     */
    Order takeOne() throws InterruptedException;
}
