package IPL;

import IPL.controller.IPLScorer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created on 4/8/2019 8:15 PM.
 *
 * @author Aayush Shrivastava
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = IPLScorer.class)
public class IPLApplication {
    public static void main(String[] args) {
        SpringApplication.run(IPLApplication.class, args);
    }
}
