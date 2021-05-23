package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.controller.Dto.TaskResponseDto;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.ReleaseService;
import com.developing.simbir_product.service.TaskReleaseHistoryService;
import com.developing.simbir_product.service.TaskService;
import com.developing.simbir_product.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.UUID;


@Tag(name = "Управление задачами")
@RequestMapping("/board/{projectName}")
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

    @Autowired
    private TaskReleaseHistoryService taskReleaseHistoryService;

    @Operation(summary = "Получить страницу с доской проекта")
    @GetMapping
    public String getBoardPage(@PathVariable("projectName") String projectName, Model model) {
        // Запрос страницы с доской проекта
        model.addAttribute("listAllTasks", taskService.getAllProjectTasks(projectName));
        model.addAttribute("currentRelease", releaseService.getCurrentRelease(projectName));
        model.addAttribute("teamName", projectService.findByName(projectName).getTeamName());
        model.addAttribute("currentProject", projectService.findByName(projectName));
        return "task-board";
    }

    @Operation(summary = "Получить страницу с задачей")
    @GetMapping("/task/{id}")
    public String getTaskPage(@PathVariable("projectName") String projectName,
                              @PathVariable("id") String id,
                              Model model,
                              Principal principal) {
        TaskResponseDto task = taskService.getById(UUID.fromString(id));
        model.addAttribute("task", task);
        model.addAttribute("taskStatusList", taskService.getListOfTaskStatus());
        model.addAttribute("taskTypeList", taskService.getListOfTaskTypes());
        model.addAttribute("listUsers",
                userService.getListOfUsersByTeamName(projectService.findByName(projectName).getTeamName()));
        model.addAttribute("currentRelease",
                taskReleaseHistoryService.getCurrentReleaseDtoByTask(taskService.getTaskEntityById(id)));
        model.addAttribute("releaseList",
                releaseService.getAllReleasesByProject(projectService.getProjectEntity(projectName)));
        model.addAttribute("currentUser", userService.findByAssigneeName(task.getAssigneeName()));
        return "task-details";
    }

    @Operation(summary = "Получить страницу создания новой задачи")
    @GetMapping("/create")
    public String getNewTaskPage(@PathVariable("projectName") String projectName, Model model, Principal principal) {
        model.addAttribute("newTask", new TaskRequestDto());
        model.addAttribute("taskStatus", taskService.getListOfTaskStatus());
        model.addAttribute("taskType", taskService.getListOfTaskTypes());
        model.addAttribute("listUsers", userService.getListOfUsersByTeamName(projectService.findByName(projectName).getTeamName()));
        model.addAttribute("currentRelease", releaseService.getCurrentRelease(projectName));
        model.addAttribute("releaseList", releaseService.getAllReleasesByProject(projectService.getProjectEntity(projectName)));
        model.addAttribute("currentUser", userService.findByEmail(principal.getName()));
        model.addAttribute("projectName", projectName);
        return "create-task";
    }

    @Operation(summary = "Создать новую задачу")
    @PostMapping("/create")
    public ModelAndView saveNewTask(@ModelAttribute("newTask") TaskRequestDto newTask,
                                    @PathVariable("projectName") String projectName) {
        ModelAndView modelAndView = new ModelAndView("redirect:/board/{projectName}");
        newTask.setProjectName(projectName);
        taskService.addTask(newTask);
        modelAndView.setStatus(HttpStatus.CREATED);
        return modelAndView;
    }

    @Operation(summary = "Редактирование задачи")
    @PostMapping("/task/{id}")
    public ModelAndView editTask(@ModelAttribute("task") TaskRequestDto editTask,
                                 @PathVariable("projectName") String projectName,
                                 @PathVariable("id") String id) {
        editTask.setId(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/board/{projectName}");
        modelAndView.addObject("editTask", taskService.editTask(editTask));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @Operation(summary = "Удаление задачи")
    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") String id,
                                             @PathVariable("projectName") String projectName) {
        taskService.deleteById(UUID.fromString(id));
        return ResponseEntity.ok("redirect:/board/{projectName}");
    }
}
