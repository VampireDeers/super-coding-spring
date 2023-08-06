package com.github.supercodingspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SuperCodingSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(SuperCodingSpringApplication.class, args);
    }

}
