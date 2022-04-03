package daisy.springbootgettingstarted;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SampleListener implements ApplicationListener<ApplicationStartingEvent> {

    // ApplicationContext가 만들어지기 전이기 때문에,
    // Listener를 Bean으로 등록해도 설정이 먹지 않는다.
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        System.out.println("=======================");
        System.out.println("Application is starting");
        System.out.println("=======================");
    }
}
