package daisy.springbootgettingstarted;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringbootGettingStartedApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootGettingStartedApplication.class);
        app.run(args);
    }

    @Bean
    public RestTemplateCustomizer restTemplateCustomizer() {
        return new RestTemplateCustomizer() {
            @Override
            public void customize(RestTemplate restTemplate) {
                restTemplate
                        .setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            }
        };
    }
}
