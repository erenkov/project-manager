package com.developing.simbir_product;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.UserEntityService;
import com.developing.simbir_product.service.impl.ProjectServiceImpl;
import com.developing.simbir_product.service.impl.UserEntityServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimbirProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimbirProductApplication.class, args);

    }

}