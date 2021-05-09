package com.developing.simbir_product.controller;

import io.swagger.v3.oas.annotations.Operation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Operation(summary = "Получить страницу логина")
    @GetMapping("/login")
    public String login() {
        logger.debug("Login controller has been accessed");
        return "login";
    }

}
