package com.zzz.orderpool.component;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zzz
 * @date 2019/8/6 16:24
 **/
@Component
public class SimpleOrderNoGenerator implements OrderNoGenerator{

    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public String nextOrderNo() {
        int no = count.incrementAndGet();
        return String.format("%s%11d", getPrefix(), no).replace(" ", "0");
    }
}
