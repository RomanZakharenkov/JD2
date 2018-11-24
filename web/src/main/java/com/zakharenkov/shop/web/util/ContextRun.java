package com.zakharenkov.shop.web.util;

import com.zakharenkov.shop.web.configuration.WebConfig;
import lombok.experimental.UtilityClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@UtilityClass
public class ContextRun {

    public static AnnotationConfigApplicationContext getContext() {
        return new AnnotationConfigApplicationContext(WebConfig.class);
    }
}
