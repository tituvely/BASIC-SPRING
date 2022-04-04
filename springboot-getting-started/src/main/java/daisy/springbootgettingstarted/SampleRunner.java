package daisy.springbootgettingstarted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    @Autowired
    DaisyProperties daisyProperties;

    @Autowired
    private String hello;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("=============");
        System.out.println("hello = " + hello);
        System.out.println("name = " + daisyProperties.getName());
        System.out.println("fullname = " + daisyProperties.getFullName());
        System.out.println("=============");
    }
}
