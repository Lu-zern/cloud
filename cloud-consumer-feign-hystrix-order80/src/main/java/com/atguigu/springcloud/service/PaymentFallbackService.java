package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author luzern
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{

    @Override
    public String paymentInfoOk(Integer id) {
        return "----PaymentFallbackService fall back paymentInfoOk,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return "----PaymentFallbackService fall back paymentInfoTimeout,o(╥﹏╥)o";
    }
}
