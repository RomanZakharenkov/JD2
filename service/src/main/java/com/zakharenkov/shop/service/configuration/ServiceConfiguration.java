package com.zakharenkov.shop.service.configuration;

import com.zakharenkov.shop.database.configuration.DatabaseConfiguration;
import com.zakharenkov.shop.service.aspect.FirstAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"com.zakharenkov.shop.service.service", "com.zakharenkov.shop.service.converter"})
@EnableTransactionManagement
@Import(DatabaseConfiguration.class)
public class ServiceConfiguration {

}
