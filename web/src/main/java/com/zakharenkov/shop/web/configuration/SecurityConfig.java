package com.zakharenkov.shop.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(new CharacterEncodingFilter(UTF_8.name()), CsrfFilter.class);

        http
                .authorizeRequests()
                    .antMatchers("/user")
                        .hasAnyAuthority("ADMIN")
                    .anyRequest()
                        .permitAll()
                .and()
                    .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/start", true)
                .and()
                    .httpBasic()
                .and()
                    .logout()
                .and()
                    .csrf().disable();
        http.userDetailsService(userDetailsService);
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println();
    }
}
