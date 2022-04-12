package daisy.springbootgettingstarted;

import daisy.springbootgettingstarted.account.Account;
import daisy.springbootgettingstarted.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootGettingStartedApplication {

    @Autowired
    AccountRepository accountRepository;

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

            accountRepository.insert(account);
        };
    }

}
