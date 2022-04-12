package daisy.springbootgettingstarted;

import daisy.springbootgettingstarted.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class SpringbootGettingStartedApplication {

    @Autowired
    MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootGettingStartedApplication.class);
        app.run(args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            Account account = new Account();
            account.setName("daisy");
            account.setEmail("daisy@spring.com");

            mongoTemplate.insert(account);
        };
    }

}
