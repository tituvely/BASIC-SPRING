package daisy.springactuatoradmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SpringActuatorAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringActuatorAdminApplication.class, args);
    }

}
