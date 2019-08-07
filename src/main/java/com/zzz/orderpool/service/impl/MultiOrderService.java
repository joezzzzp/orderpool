package com.zzz.orderpool.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzz.orderpool.component.OrderNoGenerator;
import com.zzz.orderpool.params.Order;
import com.zzz.orderpool.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zzz
 * @date 2019/8/7 10:34
 **/
public class MultiOrderService implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(MultiOrderService.class);

    private static final String QUEUE_KEY = "order:queue:key";

    private static final String CONTENT_KEY_PREFIX = "order:content:";

    private StringRedisTemplate redisTemplate;

    private OrderNoGenerator orderNoGenerator;

    private ObjectMapper objectMapper;

    @Value("${timeOut:1000}")
    private long timeOutMilliSecond;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    @Qualifier("multiOrderNoGenerator")
    public void setOrderNoGenerator(OrderNoGenerator orderNoGenerator) {
        this.orderNoGenerator = orderNoGenerator;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Order add(Order newOrder) {
        String nextOrderNo = orderNoGenerator.nextOrderNo();
        newOrder.setOrderNo(nextOrderNo);
        try {
            redisTemplate.opsForList().leftPush(QUEUE_KEY, nextOrderNo);
            redisTemplate.opsForValue().set(CONTENT_KEY_PREFIX + nextOrderNo, objectMapper.writeValueAsString(newOrder));
        } catch (JsonProcessingException e) {
            logger.error("convert object to json failed", e);
            return null;
        }
        return newOrder;
    }

    @Override
    public boolean remove(String orderNo) {
        Long count = redisTemplate.opsForList().remove(QUEUE_KEY, 1, orderNo);
        if (count == null || count < 1) {
            return false;
        }
        redisTemplate.delete(CONTENT_KEY_PREFIX + orderNo);
        return true;
    }

    @Override
    public Order takeOne() {
        String orderNo = redisTemplate.opsForList().rightPop(QUEUE_KEY, timeOutMilliSecond, TimeUnit.MILLISECONDS);
        if (StringUtils.isEmpty(orderNo)) {
            return null;
        }
        String jsonString = redisTemplate.opsForValue().get(CONTENT_KEY_PREFIX + orderNo);
        if (!StringUtils.isEmpty(jsonString)) {
            try {
                return objectMapper.readValue(jsonString, Order.class);
            } catch (IOException e) {
                logger.error("convert json to object failed", e);
                return null;
            }
        }
        return null;
    }
}
