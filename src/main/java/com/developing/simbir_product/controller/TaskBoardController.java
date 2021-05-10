package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.controller.Dto.TaskResponseDto;
import com.developing.simbir_product.entity.TaskStatus;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Tag(name = "Управление задачами")
@RequestMapping("/board")
@Controller
public class TaskBoardController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ReleaseService releaseService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Operation(summary = "Получить страницу с доской проекта")
    @GetMapping
    public String getBoardPage(@RequestParam("projectName") String projectName, Model model) {
        // Запрос страницы с доской проекта
        model.addAttribute("listBacklogTasks", taskService.findTasksByStatus(projectName, TaskStatus.BACKLOG));
        model.addAttribute("listInProgressTasks", taskService.findTasksByStatus(projectName, TaskStatus.IN_PROGRESS));
        model.addAttribute("listDoneTasks", taskService.findTasksByStatus(projectName, TaskStatus.DONE));
        model.addAttribute("currentRelease", releaseService.getCurrentRelease(projectName));
        model.addAttribute("teamName", projectService.findByName(projectName).getTeamName());
        return "task-board";
    }

    @Operation(summary = "Получить страницу с задачей")
    @GetMapping("/task/{id}")
    public String getTaskPage(@PathVariable("id") String id, Model model) {
        //if (id == -1) => создать пустую задачу и отобразить на редактирование
        // Получить страницу с запрашиваемой задачей
        model.addAttribute("task", taskService.getById(UUID.fromString(id)));
        return "task-details";
    }

    @Operation(summary = "Получить страницу создания новой задачи")
    @GetMapping("/create")
    public String getNewTaskPage(@RequestParam("projectName") String projectName, Model model) {
        model.addAttribute("newTask", new TaskRequestDto());
        model.addAttribute("teamName", projectService.findByName(projectName).getTeamName());
        model.addAttribute("taskStatus", taskService.getListOfTaskTypes());
        model.addAttribute("listUsers", userService.getListOfAllUsers());
        return "create-task";
    }

    @Operation(summary = "Получить страницу создания новой задачи")
    @PostMapping("/create")
    public ModelAndView saveNewTask(@ModelAttribute("task") TaskRequestDto taskRequestDto) {
        ModelAndView modelAndView = new ModelAndView("redirect:/board");
        taskService.addTask(taskRequestDto);
        modelAndView.setStatus(HttpStatus.CREATED);
        return modelAndView;
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
