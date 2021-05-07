package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.controller.Dto.ProjectResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.service.impl.ProjectServiceImpl;
import com.developing.simbir_product.service.impl.TeamServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Tag(name = "Управление проектами")
@RequestMapping("/projects")
@Controller
public class ProjectsController {

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    TeamServiceImpl teamService;


    @Operation(summary = "Получить страницу с проектами")
    @GetMapping()
    public String getProjectsPage(Model model) {
        // Посылает на фронт лист проектов. Фронт должен обрабатывать
        // Сделал через repository, мб через сервис
        List<ProjectEntity> projects = projectService.findAll(); //Maybe use Iterable<> instead of List<>
        model.addAttribute("projects", projects);
        return "projects";
    }

    @Operation(summary = "Получить страницу создания нового проекта")
    @GetMapping("/create")
    public String getNewProjectPage() {

        return "create-project";
    }

    @Operation(summary = "Создать новый проект")
    @PostMapping("/create") //TODO: Validation
    public String createProject(@ModelAttribute("project") ProjectRequestDto projectRequestDto) {
        // @ModelAttribute парсит формы и создает объект с уже заполненными значениями
        // name у форм должны совпадать с полями объекта
        // если не работает - сделать через @RequestParam
        projectService.addProject(projectRequestDto);

        return "redirect:/projects";
    }

    // TODO: Вместо ID в URL придумать что-то новое
    // Данный метод передачи id возможно не работает.
    @Operation(summary = "Получить страницу редактирования проекта")
    @GetMapping("/edit/{id}")
    public String getEditProjectPage(@PathVariable(value = "id") String id, Model model) {

        UUID uid = UUID.fromString(id);
        ProjectResponseDto project = projectService.getById(uid);
        List<String> teamsList = teamService.findAll();
        model.addAttribute("project", project);
        model.addAttribute("teamsList", teamsList);
        // На странице данные должны быть распарсены в виде:
        // form с уже имеющимися значениями, на которые можно кликнуть и изменить
        return "edit-project";
    }


    @Operation(summary = "Редактировать проект")
    @PostMapping("/edit/{id}") //TODO: Validation
    public String editProject(@ModelAttribute("project") ProjectRequestDto projectRequestDto) {
        projectService.editProject(projectRequestDto);
        return "redirect:/projects";
    }
}
