package com.developing.simbir_product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Tag(name = "{loginController.tag}")
@Controller
public class LoginController {

    @Operation(summary = "{loginController.login.operation}")
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
