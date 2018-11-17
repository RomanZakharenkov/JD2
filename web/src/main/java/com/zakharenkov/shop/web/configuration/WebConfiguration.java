package com.zakharenkov.shop.web.configuration;

import com.zakharenkov.shop.database.configuration.DatabaseConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.zakharenkov.shop.web")
@EnableTransactionManagement
@Import(DatabaseConfiguration.class)
public class WebConfiguration {
}
