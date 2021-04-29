package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.controller.Dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Авторизация")
@RequestMapping("/")
@RestController
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
    public ResponseEntity<UserResponseDto> loginUser(UserRequestDto userRequestDto) {
        // Запрашиваем проверку логина
        return ResponseEntity.ok().body(new UserResponseDto());
    }

    @Operation(summary = "Получить страницу регистрации")
    @GetMapping(value = "/registration")
    public ResponseEntity<String> getRegistrationPage() {
        // Возвращаем страницу с полями для регистрации
        return null;
    }

    @Operation(summary = "Зарегистрировать пользователя")
    @PostMapping(value = "/registration")
    public ResponseEntity<UserResponseDto> registerUser(UserRequestDto userRequestDto) {
        // Регистрируем нового пользователя
        return ResponseEntity.ok().body(new UserResponseDto());
    }
}
