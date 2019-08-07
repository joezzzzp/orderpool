package com.zzz.orderpool.component;

import com.zzz.orderpool.enumerate.BusinessErrorEnum;
import com.zzz.orderpool.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zzz
 * @date 2019/8/7 10:46
 **/
@Component
public class MultiOrderNoGenerator implements OrderNoGenerator{

    private static final String ORDER_NO_COUNT_KEY = "order:number:count";

    private StringRedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String nextOrderNo() {
        Long orderNoCount = redisTemplate.opsForValue().increment(ORDER_NO_COUNT_KEY);
        if (orderNoCount != null) {
            return String.format("%s%11d", getPrefix(), orderNoCount).replace(" ", "0");
        }
        throw BusinessException.build(BusinessErrorEnum.GENERATE_ORDER_NO_FAILED);
    }
}
