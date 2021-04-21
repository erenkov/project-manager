package com.developing.simbir_product.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class TaskBoardController {
    @Operation(summary = "Получить страницу с доской проекта")
    @GetMapping(value = "/board")
    public ResponseEntity<String> getBoardPage() {
        // Запрос страницы с доской проекта
        return null;
    }

    @Operation(summary = "Получить страницу с задачей")
    @GetMapping(value = "/task/{id}")
    public ResponseEntity<String> getTaskPage() {
        //if (id == -1) => создать пустую задачу и отобразить на редактирование
        // Получить страницу с запрашиваемой задачей
        return null;
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
    public ResponseEntity<String> getFilterPage() {
        return null;
    }

    @Operation(summary = "Преминить фильтр")
    @PostMapping(value = "/filter")
    public ResponseEntity<String> applyFilter() {
        return null;
    }
}
