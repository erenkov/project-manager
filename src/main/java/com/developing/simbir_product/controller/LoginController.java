package com.developing.simbir_product.controller;

import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.service.impl.UserEntityServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    UserEntityServiceImpl userEntityService;
    @Operation(summary = "Получить страницу логирования")
    @GetMapping
    public String getLoginPage() {
        return "login";
    }

    @Operation(summary = "Проверка логина")
    @PostMapping
    public String loginUser(UserEntity user, Model model) {
        if (userEntityService.findByEmail(user.getLogin()).getEmail() != null) {
            model.addAttribute("message", "User exists!");
            return "login";
        }
        return "registration";
    }
}
