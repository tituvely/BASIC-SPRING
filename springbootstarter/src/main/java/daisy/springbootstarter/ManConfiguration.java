package daisy.springbootstarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ManProperties.class)
public class ManConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Man man(ManProperties properties) {
        Man man = new Man();
        man.setAge(properties.getAge());
        man.setName(properties.getName());
        return man;
    }
}
