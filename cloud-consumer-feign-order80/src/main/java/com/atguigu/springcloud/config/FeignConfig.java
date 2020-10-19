package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author luzern
 */
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return  Logger.Level.FULL;
    }
}
