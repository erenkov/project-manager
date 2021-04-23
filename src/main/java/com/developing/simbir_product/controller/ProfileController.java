package com.developing.simbir_product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Управление профилем")
@RequestMapping(value = "/profile")
@RestController
public class ProfileController {
    @Operation(summary = "Получить страницу c профилем пользователя")
    @GetMapping(value = "")
    public ResponseEntity<String> getProfile() {
        return null;
    }
}
