package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.controller.Dto.TaskResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.exception.TaskDatesException;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.ReleaseService;
import com.developing.simbir_product.service.TaskReleaseHistoryService;
import com.developing.simbir_product.service.TaskService;
import com.developing.simbir_product.service.UserService;
import com.developing.simbir_product.utils.BindingUtils;
import com.developing.simbir_product.utils.Converter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Tag(name = "Управление задачами")
@RequestMapping("/board/{projectName}")
@Controller
public class TaskBoardController {

    @Autowired
    private BindingUtils bindingUtils;
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
    public ModelAndView getBoardPage(@PathVariable("projectName") String projectName,
                                     @RequestParam(value = "errorMessage", required = false) Optional<String> errorMessage) {
        ModelAndView modelAndView = new ModelAndView("task-board", HttpStatus.OK);
        errorMessage.ifPresent(modelAndView::addObject);
        modelAndView.addObject("listAllTasks", taskService.getAllProjectTasks(projectName));
        modelAndView.addObject("currentRelease", releaseService.getCurrentRelease(projectName));
        modelAndView.addObject("teamName", projectService.findByName(projectName).getTeamName());
        modelAndView.addObject("currentProject", projectService.findByName(projectName));
        return modelAndView;
    }

    @Operation(summary = "Получить страницу с задачей")
    @GetMapping("/task/{id}")
    public ModelAndView getTaskPage(@PathVariable("projectName") String projectName,
                                    @PathVariable("id") String id) {
        ModelAndView modelAndView = getTaskModel(projectName);
        modelAndView.setViewName("task-details");
        TaskResponseDto task = taskService.getByStringId(id);
        modelAndView.addObject("task", task);
        modelAndView.addObject("currentRelease", taskReleaseHistoryService.getCurrentReleaseDtoByTask(taskService.getTaskEntityById(id)));
        modelAndView.addObject("currentUser", userService.findByAssigneeName(task.getAssigneeName()));
        return modelAndView;
    }

    @Operation(summary = "Получить страницу создания новой задачи")
    @GetMapping("/create")
    public ModelAndView getNewTaskPage(@PathVariable("projectName") String projectName, Principal principal) {
        ModelAndView modelAndView = getTaskModel(projectName);
        modelAndView.setViewName("create-task");
        modelAndView.addObject("task", new TaskRequestDto());
        modelAndView.addObject("currentRelease", releaseService.getCurrentRelease(projectName));
        modelAndView.addObject("currentUser", userService.findByEmail(principal.getName()));
        return modelAndView;
    }

    @Operation(summary = "Создать новую задачу")
    @PostMapping("/create")
    public ModelAndView saveNewTask(@Valid @ModelAttribute("task") TaskRequestDto task,
                                    @PathVariable("projectName") String projectName) {
        ModelAndView modelAndView = new ModelAndView("redirect:/board/{projectName}");
        task.setProjectName(projectName);
        taskService.addTask(task);
        modelAndView.setStatus(HttpStatus.CREATED);
        return modelAndView;
    }

    @Operation(summary = "Редактирование задачи")
    @PostMapping("/task/{id}")
    public ModelAndView editTask(@Valid @ModelAttribute("task") TaskRequestDto editTask,
                                 @PathVariable("projectName") String projectName,
                                 @PathVariable("id") String id) {
        editTask.setId(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/board/{projectName}");
        modelAndView.addObject("editTask", taskService.editTask(editTask));
        return modelAndView;
    }

    @Operation(summary = "Удаление задачи")
    @PostMapping("/task/delete/{id}")
    public ModelAndView deleteTask(@PathVariable("id") String id, @PathVariable("projectName") String projectName) {
        taskService.deleteById(Converter.getUuidFromString(id));
        return new ModelAndView("redirect:/board/" + UriUtils.encode(projectName, StandardCharsets.UTF_8));
    }

    private ModelAndView getTaskModel(String projectName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("taskStatusList", taskService.getListOfTaskStatus());
        modelAndView.addObject("taskTypeList", taskService.getListOfTaskTypes());
        String teamName = projectService.findByName(projectName).getTeamName();
        modelAndView.addObject("listUsers", userService.getListOfUsersByTeamName(teamName));
        ProjectEntity projectEntity = projectService.getProjectEntity(projectName);
        modelAndView.addObject("releaseList", releaseService.getAllReleasesByProject(projectEntity));
        return modelAndView;
    }

    private ModelAndView getCurrentView(Principal principal, TaskRequestDto taskRequestDto, ModelAndView modelAndView) {
        List<String> pathSegments = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPathSegments();
        if ("create".equals(pathSegments.get(2))) {
            modelAndView.setViewName("create-task");
            modelAndView.addObject("projectName", taskRequestDto.getProjectName());
            modelAndView.addObject("currentUser", userService.findByEmail(principal.getName()));
        } else {
            modelAndView.setViewName("task-details");
            modelAndView.addObject("currentUser", userService.findByAssigneeName(taskRequestDto.getAssigneeName()));
        }
        String release = taskRequestDto.getRelease();
        if (release != null) {
            modelAndView.addObject("currentRelease", releaseService.getByStringId(release));
        }
        modelAndView.addObject("task", taskRequestDto);
        return modelAndView;
    }


    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    @ExceptionHandler(NotFoundException.class)
    private ModelAndView handleEditException(NotFoundException e) {
        List<String> pathSegments = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPathSegments();
        ModelAndView modelAndView = new ModelAndView();
        if (pathSegments.size() == 2) {
            modelAndView.setViewName("redirect:/projects");
        } else if (pathSegments.size() == 4) {
            modelAndView.setViewName("redirect:/board/" + UriUtils.encode(pathSegments.get(1), StandardCharsets.UTF_8));
        }
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TaskDatesException.class)
    private ModelAndView handleDatesException(TaskDatesException e, Principal principal) {
        TaskRequestDto task = e.getTask();
        ModelAndView modelAndView = getTaskModel(task.getProjectName());
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        return getCurrentView(principal, task, modelAndView);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    private ModelAndView handleValidationException(BindingResult bindingResult, Principal principal) {
        TaskRequestDto taskRequestDto = (TaskRequestDto) bindingResult.getModel().get("task");
        ModelAndView modelAndView = getTaskModel(taskRequestDto.getProjectName());
        modelAndView.addObject("task", taskRequestDto);
        bindingUtils.addErrorsToModel(bindingResult, modelAndView);
        return getCurrentView(principal, taskRequestDto, modelAndView);
    }
}
