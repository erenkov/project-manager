package com.developing.simbir_product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class SimbirProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimbirProductApplication.class, args);
    }

}
