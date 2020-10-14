package com.atguigu.springcloud.controller;

import cn.hutool.core.util.ObjectUtil;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author luzern
 */
@RestController
@Slf4j
public class OrderController {

    private static final String url = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping(value = "consumer/payment/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(url+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping(value = "consumer/payment/createForEntity")
    public CommonResult createForEntity(Payment payment){
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(url+"/payment/create",payment,CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(444,"操作失败！");
        }
    }

    @GetMapping(value = "consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(url+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult getPaymentForEntity(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(url + "/payment/get/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(444,"操作失败！");
        }
    }

    @GetMapping(value = "consumer/payment/getLB")
    public String getPaymentLB(){
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (ObjectUtil.isNull(serviceInstanceList)){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instance(serviceInstanceList);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/LB",String.class);
    }

}
