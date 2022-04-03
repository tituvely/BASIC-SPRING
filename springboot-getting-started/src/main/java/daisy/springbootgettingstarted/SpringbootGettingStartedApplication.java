package daisy.springbootgettingstarted;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootGettingStartedApplication {

    public static void main(String[] args) {
        // 따라서 명시적으로, Listener를 등록해주어야 함
        SpringApplication app = new SpringApplication(SpringbootGettingStartedApplication.class);
        app.run(args);
    }

}
