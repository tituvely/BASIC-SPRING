package com.example.springapplicationcontext;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MyEventHandler {

    @EventListener
    @Async
    public void handle(MyEvent event) {
        System.out.println(Thread.currentThread());
        System.out.println("이벤트를 받았다. 데이터는 " + event.getData());
    }
}
