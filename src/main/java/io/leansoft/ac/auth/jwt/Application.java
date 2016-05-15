package io.leansoft.ac.auth.jwt;

import io.leansoft.ac.auth.jwt.config.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Configuration
@Import(WebConfiguration.class)
public class Application {
    public static void main(String[] args) {
        final ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }
}
