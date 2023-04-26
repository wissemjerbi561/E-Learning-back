package com.example.springbootkeycloak.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public static final String Apprenant = "APPRENANT";
    public static final String Tuteur_Academique = "TUTEUR_ACADEMIQUE";

    public static final String Tuteur_Professionnel = "TUTEUR_PROFESSIONNEL";
    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET,"/test/tuteur-academique","/test/tuteur-academique/**").hasRole(Tuteur_Academique)
                .requestMatchers(HttpMethod.GET, "/test/tuteur-professionnel","/test/tuteur-professionnel/**").hasRole(Tuteur_Professionnel)
                .requestMatchers(HttpMethod.GET, "/test/apprenant","/test/apprenant/**").hasRole(Apprenant)
                .anyRequest().authenticated();
        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

}