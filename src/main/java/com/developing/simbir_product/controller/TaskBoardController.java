package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.TaskResponseDto;
import com.developing.simbir_product.entity.TaskStatus;
import com.developing.simbir_product.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Управление задачами")
@RequestMapping(value = "/board")
@Controller
public class TaskBoardController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Получить страницу с доской проекта")
    @GetMapping
    public String getBoardPage(@RequestParam("projectName") String projectName, Model model) {
        // Запрос страницы с доской проекта
        model.addAttribute("listBacklogTasks", taskService.findTasksByStatus(projectName, TaskStatus.BACKLOG));
        model.addAttribute("listInProgressTasks", taskService.findTasksByStatus(projectName, TaskStatus.IN_PROGRESS));
        model.addAttribute("listDoneTasks", taskService.findTasksByStatus(projectName, TaskStatus.DONE));


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

    @Operation(summary = "Получить страницу с фильтрами")
    @GetMapping("/filters")
    public String getFilterPage() {
        return "task-filters";
    }

    @Operation(summary = "Преминить фильтр")
    @PostMapping("/filters")
    public ResponseEntity<String> applyFilter() {
        return null;
    }
}
