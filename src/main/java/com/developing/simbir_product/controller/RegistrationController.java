package com.developing.simbir_product.controller;

import com.developing.simbir_product.dto.UserDto;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.service.UserEntityService;
import com.developing.simbir_product.service.impl.UserEntityServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Авторизация")
@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    UserEntityServiceImpl userEntityService;

    @Operation(summary = "Получить страницу регистрации")
    @GetMapping
    public String getRegistrationPage() {
        return "registration";
    }

    @Operation(summary = "Зарегистрировать пользователя")
    @PostMapping
    public String registerUser(UserDto user, Model model) {
        if(user.getUsername().isEmpty() && user.getPassword().isEmpty()){
            model.addAttribute("regError", "er");
            return "registration";
        }
        if (!userEntityService.addUser(user)) {
            model.addAttribute("userError", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }
}
