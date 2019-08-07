package com.zzz.orderpool.controller;

import com.zzz.orderpool.enumerate.BusinessErrorEnum;
import com.zzz.orderpool.exception.BusinessException;
import com.zzz.orderpool.params.Order;
import com.zzz.orderpool.params.Response;
import com.zzz.orderpool.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zzz
 * @date 2019/8/6 15:42
 **/

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public Response<Order> addOrder(@RequestBody Order newOrder) {
        Order generatedOrder = orderService.add(newOrder);
        if (generatedOrder != null) {
            return Response.success(generatedOrder);
        }
        throw BusinessException.build(BusinessErrorEnum.FULL_ORDER_POOL);
    }

    @DeleteMapping("/remove/{orderNo}")
    public Response<Boolean> removeOrder(@PathVariable String orderNo) {
        if (orderService.remove(orderNo)) {
            return Response.success();
        }
        throw BusinessException.build(BusinessErrorEnum.NO_SUCH_ORDER);
    }

    @GetMapping("/takeOne")
    public Response<Order> takeOne() throws InterruptedException {
        Order latestOrder;
        try {
            latestOrder = orderService.takeOne();
        } catch (InterruptedException e) {
            logger.error("获取订单时意外中断", e);
            throw e;
        }
        if (latestOrder == null) {
            throw BusinessException.build(BusinessErrorEnum.NO_ORDER);
        }
        return Response.success(latestOrder);
    }
}
