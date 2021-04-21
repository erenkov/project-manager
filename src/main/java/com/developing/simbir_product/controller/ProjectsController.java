package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.LoginDto;
import com.developing.simbir_product.controller.Dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class ProjectsController {
    @Operation(summary = "Зарегистрировать пользователя")
    @PostMapping(value = "/registration")
    public ResponseEntity<LoginDto> registerUser(UserDto userDto) {
        // Регистрируем нового пользователя
        return ResponseEntity.ok().body(new LoginDto());
    }

    @Operation(summary = "Получить страницу с проектами")
    @GetMapping(value = "/projects")
    public ResponseEntity<String> getProjectsPage() {
        // Возвращаем страницу с проектами
        return null;
    }

    @Operation(summary = "Получить страницу создания нового проекта")
    @GetMapping(value = "/projects/create")
    public ResponseEntity<String> getNewProjectPage() {
        // Запрос страницы для создания нового проекта
        return null;
    }

    @Operation(summary = "Создать новый проект")
    @PostMapping(value = "/projects/create")
    public ResponseEntity<String> createProject() {
        // Создаём новый проект
        return null; //Перейти на доску
    }

    @Operation(summary = "Получить страницу редактирования проекта")
    @GetMapping(value = "/projects/edit")
    public ResponseEntity<String> getEditProjectPage() {
        // Запрос страницы для редактирования проекта
        return null;
    }

    @Operation(summary = "Редактировать проект")
    @PostMapping(value = "/projects/edit")
    public ResponseEntity<String> editProject() {
        // Редактируем новый проект
        return null; //Перейти на доску
    }
}
