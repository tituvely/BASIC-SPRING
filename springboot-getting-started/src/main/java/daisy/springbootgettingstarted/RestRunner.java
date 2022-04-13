package daisy.springbootgettingstarted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Component
public class RestRunner implements ApplicationRunner {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        RestTemplate restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofMinutes(3))
                .build();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String helloResult = restTemplate.getForObject("http://localhost:8080/hello", String.class);
        System.out.println(helloResult);
        String myResult = restTemplate.getForObject("http://localhost:8080/my", String.class);
        System.out.println(myResult);

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
