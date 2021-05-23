package com.developing.simbir_product.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @Operation(summary = "Получить страницу логина")
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
