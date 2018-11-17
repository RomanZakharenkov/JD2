package com.zakharenkov.shop.web;

import com.zakharenkov.shop.web.configuration.WebConfiguration;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@UtilityClass
public class Runner {

//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfiguration.class);
//        System.out.println();
//    }

    public static AnnotationConfigApplicationContext getContext() {
        return new AnnotationConfigApplicationContext(WebConfiguration.class);
    }
}
