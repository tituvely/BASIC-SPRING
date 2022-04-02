package com.example.springapplicationcontext;

import com.example.out.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

import java.util.function.Supplier;

@SpringBootApplication
public class SpringapplicationcontextApplication {

    @Autowired
    MyService myService;

    public static void main(String[] args) {
        var app = new SpringApplication(SpringapplicationcontextApplication.class);
        app.addInitializers((ApplicationContextInitializer<GenericApplicationContext>) ctx -> {
            ctx.registerBean(MyService.class);
            ctx.registerBean(ApplicationRunner.class, new Supplier<ApplicationRunner>() {
                @Override
                public ApplicationRunner get() {
                    return new ApplicationRunner() {
                        @Override
                        public void run(ApplicationArguments args) throws Exception {
                            System.out.println("Function Bean Definition");
                        }
                    };
                }
            });
        });
        app.run(args);
    }

}
