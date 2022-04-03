package daisy.springbootgettingstarted;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class Argument {

    public Argument(ApplicationArguments arguments) {
        System.out.println("foo: " + arguments.containsOption("foo"));
        System.out.println("bar: " + arguments.containsOption("bar"));
    }
}
