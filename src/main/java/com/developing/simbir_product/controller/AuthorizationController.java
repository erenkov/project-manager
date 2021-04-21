package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.LoginDto;
import com.developing.simbir_product.controller.Dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class AuthorizationController {
//todo @RequestMapping("/") => перенаправление в зависимости от роли

    @Operation(summary = "Получить страницу логирования")
    @GetMapping(value = "/login")
    public ResponseEntity<String> getLoginPage() {
        //Возвращаем страницу с вводом логина и пароля
        return null;
    }

    @Operation(summary = "Проверка логина")
    @PostMapping(value = "/login")
    public ResponseEntity<LoginDto> loginUser(LoginDto loginDto) {
        // Запрашиваем проверку логина
        return ResponseEntity.ok().body(new LoginDto());
    }

    @Operation(summary = "Получить страницу регистрации")
    @GetMapping(value = "/registration")
    public ResponseEntity<String> getRegistrationPage() {
        // Возвращаем страницу с полями для регистрации
        return null;
    }

    @Operation(summary = "Зарегистрировать пользователя")
    @PostMapping(value = "/registration")
    public ResponseEntity<LoginDto> registerUser(UserDto userDto) {
        // Регистрируем нового пользователя
        return ResponseEntity.ok().body(new LoginDto());
    }
}
