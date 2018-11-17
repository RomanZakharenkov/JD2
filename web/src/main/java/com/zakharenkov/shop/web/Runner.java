package com.zakharenkov.shop.web;

import com.zakharenkov.shop.web.configuration.WebConfiguration;
import lombok.Getter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Getter
public class Runner {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfiguration.class);
}
