package io.github.youthred.goc.testapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TestApiApp {

    public static void main(String[] args) {
        SpringApplication.run(TestApiApp.class, args);
    }
}
