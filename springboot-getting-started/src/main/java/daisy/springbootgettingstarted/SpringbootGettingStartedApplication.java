package daisy.springbootgettingstarted;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootGettingStartedApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootGettingStartedApplication.class);
        app.run(args);
    }

    @Bean
    public WebClientCustomizer webClientCustomizer() {
        return webClientBuilder -> webClientBuilder.baseUrl("http://localhost:8080").build();
    }
}
