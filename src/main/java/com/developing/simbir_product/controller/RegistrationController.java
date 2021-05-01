package com.developing.simbir_product.controller;

import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.service.impl.UserEntityServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

//todo @RequestMapping("/") => перенаправление в зависимости от роли
@Tag(name = "Авторизация")
@RestController
public class RegistrationController {

    @Autowired
    UserEntityServiceImpl userEntityService;

    @Operation(summary = "Получить страницу регистрации")
    @GetMapping(value = "/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @Operation(summary = "Зарегистрировать пользователя")
    @PostMapping(value = "/registration")
    public String registerUser(@Valid UserEntity user, Model model, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }
        if (!userEntityService.addUser(user)) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }
}
