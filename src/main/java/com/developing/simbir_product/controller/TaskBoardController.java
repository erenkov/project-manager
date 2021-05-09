package com.developing.simbir_product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Управление задачами")
@RequestMapping("/board")
@Controller
public class TaskBoardController {
    Logger logger = LoggerFactory.getLogger(TaskBoardController.class);
    @Operation(summary = "Получить страницу с доской проекта")
    @GetMapping
    public String getBoardPage() {
        // Запрос страницы с доской проекта
        return "task-board";
    }

    @Operation(summary = "Получить страницу с задачей")
    @GetMapping("/task/{id}")
    public String getTaskPage() {
        //if (id == -1) => создать пустую задачу и отобразить на редактирование
        // Получить страницу с запрашиваемой задачей
        return "task-details";
    }

    @Operation(summary = "Получить страницу создания новой задачи")
    @GetMapping("/create")
    public String getNewTaskPage() {
        // Запрос страницы с доской проекта
        return "create-task";
    }

    @Operation(summary = "Получить страницу создания новой задачи")
    @PostMapping("/create")
    public ResponseEntity<String> saveNewTask() {
        // Запрос страницы с доской проекта
        return null;
    }

    @Operation(summary = "Редактирование задачи")
    @PostMapping("/task")
    public ResponseEntity<String> editTask() {
        // Редактировать задачу
        return null; //boardPage
    }

    @Operation(summary = "Удаление задачи")
    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteTask() {
        // Удалить задачу
        return null; //boardPage
    }
}
