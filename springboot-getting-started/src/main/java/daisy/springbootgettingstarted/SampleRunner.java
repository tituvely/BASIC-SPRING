package daisy.springbootgettingstarted;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(SampleRunner.class);

    @Autowired
    DaisyProperties daisyProperties;

    @Autowired
    private String hello;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("=============");
        logger.info("hello = " + hello);
        logger.info("name = " + daisyProperties.getName());
        logger.info("fullname = " + daisyProperties.getFullName());
        logger.info("=============");
    }
}
