package com.developing.simbir_product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Управление профилем")
@RequestMapping(value = "/profile")
@Controller
public class UserProfileController {

    @Operation(summary = "Получить страницу c профилем пользователя")
    @GetMapping
    public String getProfile() {
        return "profile";
    }
}
