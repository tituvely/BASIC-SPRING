package com.example.springapplicationcontext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/app.properties")
public class SpringapplicationcontextApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringapplicationcontextApplication.class, args);
    }

}
