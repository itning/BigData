package top.itning.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SentinelApplication {

    public static void main(String[] args) {
        System.setProperty("csp.sentinel.dashboard.server", "localhost:8080");
        System.setProperty("csp.sentinel.log.dir", "sentinel_log");

        SpringApplication.run(SentinelApplication.class, args);
    }

}
