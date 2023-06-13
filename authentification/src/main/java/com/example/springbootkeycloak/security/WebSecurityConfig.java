package com.example.springbootkeycloak.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

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
        http.csrf().disable().cors().configurationSource(corsConfigurationSource());

        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/test/tuteur-academique", "/test/tuteur-academique/**").hasRole(Tuteur_Academique)
                .requestMatchers(HttpMethod.GET, "/test/tuteur-professionnel", "/test/tuteur-professionnel/**").hasRole(Tuteur_Professionnel)
                .requestMatchers(HttpMethod.GET, "/test/apprenant", "/test/apprenant/**").hasRole(Apprenant)
                .requestMatchers(HttpMethod.GET, "/swagger-ui/index.html", "/swagger-ui/**","/swagger-ui/index.html/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/auth/signin", "/api/auth/signin/**", "/auth/**", "/api/**").permitAll()
                .anyRequest().permitAll();
        return http.build();
    }


    CorsConfigurationSource corsConfigurationSource() {
        final var configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader("*");
        configuration.setAllowCredentials(true);
        configuration.addAllowedMethod("GET");
        configuration.addAllowedMethod("POST");
        configuration.addAllowedMethod("PUT");
        configuration.addAllowedMethod("DELETE");

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}