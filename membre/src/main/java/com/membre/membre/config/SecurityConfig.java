package com.membre.membre.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public  class  SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests()
                .requestMatchers("/api/member/member/create","/api/member/member/ajoutMember")
                .permitAll()
                .anyRequest()
                .authenticated();
        http.csrf().disable()
        .oauth2ResourceServer()
        .jwt();        return http
        .build();    }}
