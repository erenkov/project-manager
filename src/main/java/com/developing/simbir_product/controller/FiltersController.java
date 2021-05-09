package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@Tag(name = "Управление задачами")
@RequestMapping("/board/filters")
@Controller
public class FiltersController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Получить страницу с фильтрами")
    @GetMapping
    public ModelAndView getFilterPage() {
        return new ModelAndView("task-filters", HttpStatus.OK);
    }

    @Operation(summary = "Преминить фильтр")
    @PostMapping
    public ModelAndView applyFilter(TaskRequestDto taskRequestDto, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("task-filters", HttpStatus.OK);
        modelAndView.addObject("filteredTasks", taskService.getTasksByFilter(taskRequestDto, principal));
        return modelAndView;
    }
}
