package daisy.springbootgettingstarted;

import daisy.springbootgettingstarted.account.Account;
import daisy.springbootgettingstarted.account.AccountRepository;
import daisy.springbootgettingstarted.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Neo4jRunner implements ApplicationRunner {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setEmail("daisy@spring.com");
        account.setName("titu");

        Role role = new Role();
        role.setName("admin");
        account.getRoles().add(role);

        accountRepository.save(account);
        System.out.println("finished");
    }
}
