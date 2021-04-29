package com.developing.simbir_product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Управление проектами")
@RequestMapping(value = "/projects")
@RestController
public class ProjectsController {

    @Operation(summary = "Получить страницу с проектами")
    @GetMapping(value = "")
    public ResponseEntity<String> getProjectsPage() {
        // Возвращаем страницу с проектами
        return null;
    }

    @Operation(summary = "Получить страницу создания нового проекта")
    @GetMapping(value = "/create")
    public ResponseEntity<String> getNewProjectPage() {
        // Запрос страницы для создания нового проекта
        return null;
    }

    @Operation(summary = "Создать новый проект")
    @PostMapping(value = "/create")
    public ResponseEntity<String> createProject() {
        // Создаём новый проект
        return null; //Перейти на доску
    }

    @Operation(summary = "Получить страницу редактирования проекта")
    @GetMapping(value = "/edit")
    public ResponseEntity<String> getEditProjectPage() {
        // Запрос страницы для редактирования проекта
        return null;
    }

    @Operation(summary = "Редактировать проект")
    @PostMapping(value = "/edit")
    public ResponseEntity<String> editProject() {
        // Редактируем новый проект
        return null; //Перейти на доску
    }
}
