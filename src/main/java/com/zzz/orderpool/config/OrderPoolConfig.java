package com.zzz.orderpool.config;

import com.zzz.orderpool.service.OrderService;
import com.zzz.orderpool.service.impl.MultiOrderService;
import com.zzz.orderpool.service.impl.SimpleOrderService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @author zzz
 * @date 2019/8/7 11:37
 **/
@Configuration
public class OrderPoolConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public OrderService getOrderService() {
        String mode = applicationContext.getEnvironment().getProperty("orderPool.mode");
        if (StringUtils.isEmpty(mode) || "simple".equalsIgnoreCase(mode)) {
            return new SimpleOrderService();
        }
        if ("multi".equalsIgnoreCase(mode)) {
            return new MultiOrderService();
        }
        return new SimpleOrderService();
    }
}
