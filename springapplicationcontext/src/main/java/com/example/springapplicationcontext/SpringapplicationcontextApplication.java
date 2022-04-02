package com.example.springapplicationcontext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringapplicationcontextApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringapplicationcontextApplication.class, args);
    }

}
