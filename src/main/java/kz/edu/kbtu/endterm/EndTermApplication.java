package kz.edu.kbtu.endterm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class EndTermApplication {

    public static void main(String[] args) {
        SpringApplication.run(EndTermApplication.class, args);
    }

}
