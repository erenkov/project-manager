package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@Tag(name = "Управление проектами")
@RequestMapping("/projects")
@Controller
public class ProjectsController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TeamService teamService;

    @Operation(summary = "Получить страницу с проектами")
    @GetMapping()
    public String getProjectsPage(Model model, Principal principal) {
        model.addAttribute("projectNames", projectService.getListOfAllProjectNames());
        model.addAttribute("userName", principal.getName());
        return "projects";
    }

    @Operation(summary = "Получить страницу создания нового проекта")
    @GetMapping("/create")
    public ModelAndView getNewProjectPage() {
        ModelAndView modelAndView = getProjectsModel();
        modelAndView.setViewName("create-project");
        modelAndView.addObject("newProject", new ProjectRequestDto());
        return modelAndView;
    }

    @Operation(summary = "Создать новый проект")
    @PostMapping("/create")
    public ModelAndView createProject(@Valid @ModelAttribute("newProject") ProjectRequestDto projectRequestDto) {
        ModelAndView modelAndView;
        if (projectService.addProject(projectRequestDto)) {
            modelAndView = new ModelAndView("redirect:/projects", HttpStatus.CREATED);
        } else {
            modelAndView = getProjectsModel();
            modelAndView.setViewName("create-project");
            modelAndView.setStatus(HttpStatus.CONFLICT);
            modelAndView.addObject("newProject", projectRequestDto);
            modelAndView.addObject("projectError", "Project exists!");
        }
        return modelAndView;
    }

    @Operation(summary = "Получить страницу редактирования проекта")
    @GetMapping("/edit/{prName}")
    public ModelAndView getEditProjectPage(@PathVariable("prName") String prName) {
        ModelAndView modelAndView = getProjectsModel();
        modelAndView.setViewName("edit-project");
        modelAndView.addObject("project", projectService.findByName(prName));
        return modelAndView;
    }

    @Operation(summary = "Редактировать проект")
    @PostMapping("/edit/{prName}")
    public String editProject(@PathVariable("prName") String prName,
                              @Valid @ModelAttribute("project") ProjectRequestDto projectRequestDto) {
        projectRequestDto.setName(prName);              //todo: Плохое решение
        projectService.editProject(projectRequestDto);  //todo: return dto?
        return "redirect:/projects";
    }

    private ModelAndView getProjectsModel() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teamList", teamService.findAllTeamNames());
        modelAndView.addObject("projectStatusList", projectService.getListOfAllProjectStatus());
        return modelAndView;
    }

    @ExceptionHandler(BindException.class)
    private ModelAndView handleValidationException(Exception e, BindingResult bindingResult) {
        ModelAndView modelAndView = getProjectsModel();
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        modelAndView.addObject("FieldErrors", bindingResult.getFieldErrors());
        modelAndView.addObject("GlobalErrors", bindingResult.getGlobalErrors());
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        List<String> pathSegments = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPathSegments();
        if ("create".equals(pathSegments.get(1))) {
            modelAndView.setViewName("create-project");
            modelAndView.addObject("newProject", bindingResult.getModel().get("newProject"));
        } else {
            modelAndView.setViewName("edit-project");
            modelAndView.addObject("project", bindingResult.getModel().get("project"));
        }
        return modelAndView;
    }
}
