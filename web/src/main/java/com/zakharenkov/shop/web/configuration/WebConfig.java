package com.zakharenkov.shop.web.configuration;

import com.zakharenkov.shop.service.configuration.ServiceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"com.zakharenkov.shop.web"})
@EnableWebMvc
@Import(value = {ServiceConfiguration.class, ThymeleafConfig.class})
public class WebConfig {

}
