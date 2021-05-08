package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.controller.Dto.ProjectResponseDto;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.TeamService;
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
    private ProjectService projectService;

    @Autowired
    private TeamService teamService;

    @Operation(summary = "Получить страницу с проектами")
    @GetMapping()
    public String getProjectsPage(Model model) {
        model.addAttribute("projectNames", projectService.getListOfAllProjectNames());
        return "projects";
    }

    @Operation(summary = "Получить страницу создания нового проекта")
    @GetMapping("/create")
    public String getNewProjectPage(Model model) {
        model.addAttribute("newProject", new ProjectRequestDto());
        model.addAttribute("projectStatus", projectService.getListOfAllProjectStatus());
            return "create-project";
    }

    @Operation(summary = "Создать новый проект")
    @PostMapping("/create")                                 //TODO: Validation
    public String createProject(@ModelAttribute("newProject") ProjectRequestDto projectRequestDto) {

        projectService.addProject(projectRequestDto);

        return "redirect:/projects";
    }

//    @Operation(summary = "Создать новый проект")
//    @PostMapping("/create")                                 //TODO: Validation
//    public String createProject(@ModelAttribute("newProject") ProjectRequestDto projectRequestDto,
//                                @RequestParam(value = "strStartDate", required = false) String strStartDate,
//                                @RequestParam(value = "strEstFinishDate" , required = false) String strEstFinishDate) {
//        //TODO: Костыль
//        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        LocalDate ldStart = LocalDate.parse(strStartDate, DATEFORMATTER);
////        projectRequestDto.setStartDate(LocalDateTime.of(ldStart, LocalDateTime.now().toLocalTime()));
//        projectRequestDto.setStartDate(ldStart.atStartOfDay());
//
//        LocalDate ldFinish = LocalDate.parse(strEstFinishDate, DATEFORMATTER);
//        projectRequestDto.setEstFinishDate(ldFinish.atStartOfDay());
////        projectRequestDto.setEstFinishDate(LocalDateTime.of(ldFinish, LocalDateTime.now().toLocalTime()));
//
//
//        projectService.addProject(projectRequestDto);
//
//        return "redirect:/projects";
//    }

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
