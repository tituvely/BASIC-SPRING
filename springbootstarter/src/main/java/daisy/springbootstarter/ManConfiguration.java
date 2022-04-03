package daisy.springbootstarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Man man() {
        Man man = new Man();
        man.setAge(20);
        man.setName("Daisy");
        return man;
    }
}
