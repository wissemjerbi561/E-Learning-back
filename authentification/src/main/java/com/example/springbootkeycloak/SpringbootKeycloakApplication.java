package com.example.springbootkeycloak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringbootKeycloakApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootKeycloakApplication.class, args);
    }


}
