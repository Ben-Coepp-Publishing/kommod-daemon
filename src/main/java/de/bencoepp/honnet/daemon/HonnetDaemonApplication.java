package de.bencoepp.honnet.daemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HonnetDaemonApplication {

    public static void main(String[] args) {
        SpringApplication.run(HonnetDaemonApplication.class, args);
    }

}
