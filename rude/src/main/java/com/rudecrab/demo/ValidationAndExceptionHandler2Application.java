package com.rudecrab.demo;

import com.rudecrab.demo.config.FileProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileProperties.class
})
public class ValidationAndExceptionHandler2Application {

    public static void main(String[] args) {
        SpringApplication.run(ValidationAndExceptionHandler2Application.class, args);
    }

}
