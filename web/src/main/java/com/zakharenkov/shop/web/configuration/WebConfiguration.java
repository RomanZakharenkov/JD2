package com.zakharenkov.shop.web.configuration;

import com.zakharenkov.shop.database.configuration.DatabaseConfiguration;
import com.zakharenkov.shop.service.configuration.ServiceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.zakharenkov.shop.web")
@EnableTransactionManagement
@Import({ServiceConfiguration.class, DatabaseConfiguration.class})
public class WebConfiguration {
}
