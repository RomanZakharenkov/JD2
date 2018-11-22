package com.zakharenkov.shop.database.config;

import com.zakharenkov.shop.database.configuration.DatabaseConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DatabaseConfiguration.class)
public class TestConfiguration {

}
