package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.controller.Dto.ProjectResponseDto;
import com.developing.simbir_product.service.impl.ProjectServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Tag(name = "Управление проектами")
@RequestMapping("/projects")
@Controller
public class ProjectsController {

    @Autowired
    ProjectServiceImpl projectService;

    @Operation(summary = "Получить страницу с проектами")
    @GetMapping()
    public String getProjectsPage() {
        // Возвращаем страницу с проектами
        return "projects";
    }

    @Operation(summary = "Получить страницу создания нового проекта")
    @GetMapping("/create")
    public String getNewProjectPage() {
        // Запрос страницы для создания нового проекта
        return "create-project";
    }

    @Operation(summary = "Создать новый проект")
    @PostMapping("/create")
    public String createProject(ProjectRequestDto projectRequestDto, Model model) {
        ProjectResponseDto projectResponseDto;
        projectResponseDto = projectService.addProject(projectRequestDto);

        return "board/{projectResponseDto.getName()}"; //Перейти на доску
    }

    @Operation(summary = "Получить страницу редактирования проекта")
    @GetMapping("/edit")
    public String getEditProjectPage() {
        // Запрос страницы для редактирования проекта
        return "edit-project";
    }

    @Operation(summary = "Редактировать проект")
    @PostMapping("/edit")
    public ResponseEntity<String> editProject() {
        // Редактируем новый проект
        return null; //Перейти на доску
    }
}
