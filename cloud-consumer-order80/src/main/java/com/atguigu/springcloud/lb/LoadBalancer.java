package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author luzern
 */
@Component
public interface LoadBalancer {

    /**
     * 根据ServiceInstance集合获取其中一个ServiceInstance（负载均衡）
     * @param serviceInstanceList
     * @return
     */
    ServiceInstance instance (List<ServiceInstance> serviceInstanceList);

}
