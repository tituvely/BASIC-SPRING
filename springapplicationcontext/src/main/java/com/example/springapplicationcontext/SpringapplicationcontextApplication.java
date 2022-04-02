package com.example.springapplicationcontext;

import com.example.out.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class SpringapplicationcontextApplication {

    @Autowired
    MyService myService;

    public static void main(String[] args) {
        var app = new SpringApplication(SpringapplicationcontextApplication.class);
        app.addInitializers(new ApplicationContextInitializer<GenericApplicationContext>() {
            @Override
            public void initialize(GenericApplicationContext ctx) {
                ctx.registerBean(MyService.class);
            }
        });
        app.run(args);
    }

}
