package com.example.springapplicationcontext;

import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class AnotherHandler {

    @EventListener
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void handle(MyEvent myEvent) {
        System.out.println(Thread.currentThread());
        System.out.println("Another " + myEvent.getData());
    }
}
