package com.zakharenkov.shop.service.configuration;

import com.zakharenkov.shop.database.configuration.DatabaseConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.zakharenkov.shop.service")
@EnableTransactionManagement
@Import({DatabaseConfiguration.class, ServiceConfiguration.class})
public class ServiceConfiguration {
}
