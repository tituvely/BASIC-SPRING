package com.example.springapplicationcontext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringapplicationcontextApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringapplicationcontextApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

}
