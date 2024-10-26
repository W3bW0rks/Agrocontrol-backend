package com.agrocontrol.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AgroControlBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgroControlBackendApplication.class, args);
    }
 
}
