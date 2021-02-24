package com.itmo.mibsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collections;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.itmo.mibsystem.dao"})
public class MibSystemApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MibSystemApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "15415"));
        app.run(args);
    }
}
