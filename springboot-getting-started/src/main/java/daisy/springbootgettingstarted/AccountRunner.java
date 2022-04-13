package daisy.springbootgettingstarted;

import daisy.springbootgettingstarted.account.Account;
import daisy.springbootgettingstarted.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountRunner implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account daisy = accountService.createAccount("daisy", "1234");
    }
}
