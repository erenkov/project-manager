package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Tag(name = "Управление проектами")
@RequestMapping("/projects")
@Controller
public class ProjectsController {
    Logger logger = LoggerFactory.getLogger(ProjectsController.class);
    @Autowired
    private ProjectService projectService;

    @Autowired
    private TeamService teamService;

    @Operation(summary = "Получить страницу с проектами")
    @GetMapping()
    public String getProjectsPage(Model model, Principal principal) {
        model.addAttribute("projectNames", projectService.getListOfAllProjectNames());
        model.addAttribute("userName", principal.getName());
        logger.trace(principal.getName() + " accessed the projects page");
        return "projects";
    }

    @Operation(summary = "Получить страницу создания нового проекта")
    @GetMapping("/create")
    public String getNewProjectPage(Model model) {
        model.addAttribute("newProject", new ProjectRequestDto());
        model.addAttribute("teamsList", teamService.findAllTeamNames());
        model.addAttribute("projectStatusList", projectService.getListOfAllProjectStatus());
            return "create-project";
    }

    @Operation(summary = "Создать новый проект")
    @PostMapping("/create")                                 //TODO: Validation
    public String createProject(@ModelAttribute("newProject") ProjectRequestDto projectRequestDto) {

        projectService.addProject(projectRequestDto);
        logger.trace(projectRequestDto.getName() + " has been created");
        return "redirect:/projects";
    }

    @Operation(summary = "Получить страницу редактирования проекта")
    @GetMapping("/edit/{prName}")
    public String getEditProjectPage(@PathVariable(value = "prName") String prName, Model model) {

        model.addAttribute("project",  projectService.findByName(prName));
        model.addAttribute("teamsList", teamService.findAllTeamNames());
        model.addAttribute("projectStatusList", projectService.getListOfAllProjectStatus());

        return "edit-project";
    }

    @Operation(summary = "Редактировать проект")
    @PostMapping("/edit/{prName}")                  //TODO: Validation
    public String editProject(@PathVariable(value = "prName") String prName,
                              @ModelAttribute("project") ProjectRequestDto projectRequestDto) {
        projectRequestDto.setName(prName);              //todo: Плохое решение
        projectService.editProject(projectRequestDto);  //todo: return dto?
        logger.debug(prName + " project has been created");
        return "redirect:/projects";
    }
}
