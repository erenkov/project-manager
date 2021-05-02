package com.developing.simbir_product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Управление задачами")
@RequestMapping(value = "/board")
@Controller
public class TaskBoardController {
    @Operation(summary = "Получить страницу с доской проекта")
    @GetMapping(value = "")
    public String getBoardPage() {
        // Запрос страницы с доской проекта
        return "task-board";
    }

    @Operation(summary = "Получить страницу с задачей")
    @GetMapping(value = "/task/{id}")
    public String getTaskPage() {
        //if (id == -1) => создать пустую задачу и отобразить на редактирование
        // Получить страницу с запрашиваемой задачей
        return "task-details";
    }

    @Operation(summary = "Редактирование задачи")
    @PostMapping(value = "/task")
    public ResponseEntity<String> editTask() {
        // Редактировать задачу
        return null; //boardPage
    }

    @Operation(summary = "Удаление задачи")
    @DeleteMapping(value = "/task/{id}")
    public ResponseEntity<String> deleteTask() {
        // Удалить задачу
        return null; //boardPage
    }

    @Operation(summary = "Получить страницу с фильтрами")
    @GetMapping(value = "/filter")
    public String getFilterPage() {
        return "task-filters";
    }

    @Operation(summary = "Преминить фильтр")
    @PostMapping(value = "/filter")
    public ResponseEntity<String> applyFilter() {
        return null;
    }
}
