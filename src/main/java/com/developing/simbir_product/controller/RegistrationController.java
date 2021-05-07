package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Tag(name = "Авторизация")
@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Получить страницу регистрации")
    @GetMapping
    public String getRegistrationPage() {
        return "registration";
    }

    @Operation(summary = "Зарегистрировать пользователя")
    @PostMapping
    public String registerUser(UserRequestDto user, Model model) {
        if (user.getEmail().isEmpty()
                || user.getPassword().isEmpty()
                || user.getFirstName().isEmpty()
                || user.getLastName().isEmpty()) {

                model.addAttribute("regError", "er");
            return "registration";
        }
        if (!userService.addUser(user)) {
            model.addAttribute("userError", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }
}
