package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.controller.Dto.UserResponseDto;
import com.developing.simbir_product.entity.Role;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.TeamService;
import com.developing.simbir_product.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Tag(name = "Управление проектами")
@RequestMapping("/projects")
@Controller
public class ProjectsController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private TeamService teamService;

    @Autowired
    UserService userService;

    @Operation(summary = "Получить страницу с проектами")
    @GetMapping()
    public String getProjectsPage(Model model, Principal principal, Authentication authentication) {
        UserResponseDto user = userService.findByEmail(principal.getName());
        model.addAttribute("userName", String.format("%s %s", user.getFirstName(),user.getLastName()));

        if (authentication.getAuthorities().contains(Role.ROLE_ADMIN)) {
            model.addAttribute("projectNames", projectService.getListOfAllProjectNames());
        } else {
            model.addAttribute("projectNames", projectService.getListOfAllProjectNamesByTeam(user.getTeam()));
        }
        return "projects";
    }

    @Operation(summary = "Получить страницу создания нового проекта")
    @GetMapping("/create")
    public String getNewProjectPage(Model model) {
        model.addAttribute("newProject", new ProjectRequestDto());
        model.addAttribute("teamList", teamService.findAllTeamNames());
        model.addAttribute("projectStatusList", projectService.getListOfAllProjectStatus());
            return "create-project";
    }

    @Operation(summary = "Создать новый проект")
    @PostMapping("/create")                                 //TODO: Validation
    public String createProject(@ModelAttribute("newProject") ProjectRequestDto projectRequestDto) {

        projectService.addProject(projectRequestDto);
        return "redirect:/projects";
    }

    @Operation(summary = "Получить страницу редактирования проекта")
    @GetMapping("/edit/{prName}")
    public String getEditProjectPage(@PathVariable("prName") String prName, Model model) {

        model.addAttribute("project",  projectService.findByName(prName));
        model.addAttribute("teamList", teamService.findAllTeamNames());
        model.addAttribute("projectStatusList", projectService.getListOfAllProjectStatus());

        return "edit-project";
    }

    @Operation(summary = "Редактировать проект")
    @PostMapping("/edit/{prName}")                  //TODO: Validation
    public String editProject(@PathVariable("prName") String prName,
                              @ModelAttribute("project") ProjectRequestDto projectRequestDto) {
        projectRequestDto.setName(prName);              //todo: Обдумать решение
        projectService.editProject(projectRequestDto);  //todo: return dto?
        return "redirect:/projects";
    }
}
