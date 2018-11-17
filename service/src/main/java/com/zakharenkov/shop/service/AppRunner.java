package com.zakharenkov.shop.service;

import com.zakharenkov.shop.service.configuration.ServiceConfiguration;
import com.zakharenkov.shop.service.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

public class AppRunner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ServiceConfiguration.class);
        ProductService productService = context.getBean("productService", ProductService.class);
        Set<String> allBrand = productService.getAllBrand();
        allBrand.forEach(System.out::println);
    }

}
