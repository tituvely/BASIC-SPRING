package daisy.springbootgettingstarted;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        Thread.sleep(5000L);
        return "hello";
    }

    @GetMapping("/my")
    public String my() throws InterruptedException {
        Thread.sleep(3000L);
        return "my";
    }
}
