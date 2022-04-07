package daisy.springbootgettingstarted;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("name", "daisy");
        return "hello";
    }
}
