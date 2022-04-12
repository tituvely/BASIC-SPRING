package daisy.springbootgettingstarted;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootGettingStartedApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootGettingStartedApplication.class);
        app.run(args);
    }

}
