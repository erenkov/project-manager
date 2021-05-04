package com.developing.simbir_product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Tag(name = "Управление проектами")
@RequestMapping("/projects")
@Controller
public class ProjectsController {

    @Operation(summary = "Получить страницу с проектами")
    @GetMapping()
    public ResponseEntity<String> getProjectsPage() {
        // Возвращаем страницу с проектами
        return null;
    }

    @Operation(summary = "Получить страницу создания нового проекта")
    @GetMapping("/create")
    public ResponseEntity<String> getNewProjectPage() {
        // Запрос страницы для создания нового проекта
        return null;
    }

    @Operation(summary = "Создать новый проект")
    @PostMapping("/create")
    public ResponseEntity<String> createProject() {
        // Создаём новый проект
        return null; //Перейти на доску
    }

    @Operation(summary = "Получить страницу редактирования проекта")
    @GetMapping("/edit")
    public ResponseEntity<String> getEditProjectPage() {
        // Запрос страницы для редактирования проекта
        return null;
    }

    @Operation(summary = "Редактировать проект")
    @PostMapping("/edit")
    public ResponseEntity<String> editProject() {
        // Редактируем новый проект
        return null; //Перейти на доску
    }
}
