package com.sometimes.sometimesbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SometimesBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SometimesBeApplication.class, args);
    }

}
