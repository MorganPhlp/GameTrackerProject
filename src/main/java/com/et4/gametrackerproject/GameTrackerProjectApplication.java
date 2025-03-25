package com.et4.gametrackerproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GameTrackerProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameTrackerProjectApplication.class, args);
    }

}
