package daisy.springbootgettingstarted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    @Autowired
    DaisyProperties daisyProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("=============");
        System.out.println("name = " + daisyProperties.getName());
        System.out.println("name = " + daisyProperties.getAge());
        System.out.println("=============");
    }
}
