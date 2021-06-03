package com.developing.test_feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TestFeignApp {

    public static void main(String[] args) {
        SpringApplication.run(TestFeignApp.class, args);
    }

}
