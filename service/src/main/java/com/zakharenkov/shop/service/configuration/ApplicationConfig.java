package com.zakharenkov.shop.service.configuration;

import com.zakharenkov.shop.service.aspect.FirstAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.zakharenkov.shop.service.service")
@EnableAspectJAutoProxy
public class ApplicationConfig {

    @Bean
    public FirstAspect firstAspect() {
        return new FirstAspect();
    }
}
