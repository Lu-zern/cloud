package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author luzern
 */
@RestController
@Slf4j
public class OrderController {

    private static final String url = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "consumer/payment/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(url+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping(value = "consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(url+"/payment/get/"+id,CommonResult.class);
    }

}
