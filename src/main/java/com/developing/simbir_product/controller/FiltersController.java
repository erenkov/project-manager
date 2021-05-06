package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Tag(name = "Управление задачами")
@RequestMapping("/board/filters")
@Controller
public class FiltersController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Получить страницу с фильтрами")
    @GetMapping
    public ResponseEntity<String> getFilterPage() {
        return ResponseEntity.ok("task-filters");
    }

    @Operation(summary = "Преминить фильтр")
    @PostMapping
    public ResponseEntity<String> applyFilter(TaskRequestDto taskRequestDto, Model model, Principal principal) {
        model.addAttribute("filteredTasks", taskService.getTasksByFilter(taskRequestDto, principal));
        return ResponseEntity.ok("task-filters");
    }
}
