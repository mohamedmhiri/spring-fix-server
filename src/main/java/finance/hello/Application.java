package hello;

import quickfix.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args)  throws ConfigError {
        SpringApplication.run(Application.class, args);

    }
}
