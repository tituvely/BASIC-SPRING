package daisy.springbootgettingstarted;

import daisy.springbootstarter.Man;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootGettingStartedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootGettingStartedApplication.class, args);
    }

    @Bean
    public Man man() {
        Man man = new Man();
        man.setName("titu");
        man.setAge(26);
        return man;
    }
}
