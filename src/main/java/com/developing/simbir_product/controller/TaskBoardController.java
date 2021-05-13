package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.entity.TaskStatus;
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

import java.security.Principal;
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
    @GetMapping("/{prName}")
    public String getBoardPage(@PathVariable("prName") String projectName, Model model) {
        // Запрос страницы с доской проекта
        model.addAttribute("listBacklogTasks", taskService.findTasksByStatus(projectName, TaskStatus.BACKLOG));
        model.addAttribute("listInProgressTasks", taskService.findTasksByStatus(projectName, TaskStatus.IN_PROGRESS));
        model.addAttribute("listDoneTasks", taskService.findTasksByStatus(projectName, TaskStatus.DONE));
        model.addAttribute("currentRelease", releaseService.getCurrentRelease(projectName));
        model.addAttribute("teamName", projectService.findByName(projectName).getTeamName());
        model.addAttribute("currentProject", projectService.findByName(projectName));
        return "task-board";
    }

    @Operation(summary = "Получить страницу с задачей")
    @GetMapping("/task/{id}")
    public String getTaskPage(@PathVariable("id") String id, Model model) {
        //if (id == -1) => создать пустую задачу и отобразить на редактирование
        // Получить страницу с запрашиваемой задачей
        model.addAttribute("task", taskService.getById(UUID.fromString(id)));
        model.addAttribute("releaseList", releaseService.getAllReleasesByProject(taskService.getTaskById(UUID.fromString(id)).getProjectId()));
        return "task-details";
    }

    @Operation(summary = "Получить страницу создания новой задачи")
    @GetMapping("/create")
    public String getNewTaskPage(@RequestParam("projectName") String projectName, Model model, Principal principal) {
        model.addAttribute("newTask", new TaskRequestDto());
        model.addAttribute("teamName", projectService.findByName(projectName).getTeamName());
        model.addAttribute("taskStatus", taskService.getListOfTaskStatus());
        model.addAttribute("taskType", taskService.getListOfTaskTypes());
        model.addAttribute("listUsers", userService.getListOfAllUsers());
        model.addAttribute("currentRelease", releaseService.getCurrentRelease(projectName));
        model.addAttribute("releaseList", releaseService.getAllReleasesByProject(projectService.getProjectEntity(projectName)));
        model.addAttribute("currentUser", userService.findByEmail(principal.getName()));
        return "create-task";
    }

    @Operation(summary = "Создать новую задачу")
    @PostMapping("/create")
    public ModelAndView saveNewTask(@ModelAttribute("task") TaskRequestDto newTask) {
        ModelAndView modelAndView = new ModelAndView("redirect:/board");
//        modelAndView.addObject("newTask",taskService.addTask(newTask));
        taskService.addTask(newTask);
        modelAndView.setStatus(HttpStatus.CREATED);
        return modelAndView;
    }

    @Operation(summary = "Редактирование задачи")
    @PostMapping("/task")
    public ModelAndView editTask(@ModelAttribute("task") TaskRequestDto editTask) {
        ModelAndView modelAndView = new ModelAndView("redirect:/board");
        modelAndView.addObject("editTask",taskService.editTask(editTask));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @Operation(summary = "Удаление задачи")
    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") String id) {
        taskService.deleteById(UUID.fromString(id));
        return ResponseEntity.ok("redirect:/board");
    }
}
