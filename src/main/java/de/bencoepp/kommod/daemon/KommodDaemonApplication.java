package de.bencoepp.kommod.daemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableScheduling
public class KommodDaemonApplication {

    public static void main(String[] args) {
        SpringApplication.run(KommodDaemonApplication.class, args);
    }

}
