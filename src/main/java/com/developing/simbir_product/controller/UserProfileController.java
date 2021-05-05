package com.developing.simbir_product.controller;

import com.developing.simbir_product.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Tag(name = "Управление профилем")
@RequestMapping(value = "/profile")
@Controller
public class UserProfileController {

    @Autowired
    UserServiceImpl userService;

    @Operation(summary = "Получить страницу c профилем пользователя")
    @GetMapping
    public String getProfile(Model model, Principal principal) {
        model.addAttribute("currentUser", userService.findByEmail(principal.getName()));
        return "profile";
    }
}
