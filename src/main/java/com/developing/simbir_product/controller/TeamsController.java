package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.TeamRequestDto;
import com.developing.simbir_product.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Tag(name = "Управление командами")
@RequestMapping(value = "/teams")
@Controller
public class TeamsController {
    Logger logger = LoggerFactory.getLogger(TeamsController.class);
    @Autowired
    private TeamService teamService;


    @Operation(summary = "Получить страницу c информацией о команде")
    @GetMapping
    public ModelAndView getTeamPage(@RequestParam String teamName) {
        ModelAndView modelAndView = new ModelAndView("teamViewName", HttpStatus.OK);
        modelAndView.addObject("team", teamService.findByName(teamName));
        return modelAndView;
    }

    @Operation(summary = "Создать команду")
    @PostMapping
    public ModelAndView createTeam(@Valid @ModelAttribute("newTeam") TeamRequestDto teamRequestDto) {
        ModelAndView modelAndView = new ModelAndView();
        if (!teamService.addTeamDto(teamRequestDto)) {
            modelAndView.setViewName("teamViewName");
            modelAndView.setStatus(HttpStatus.CONFLICT);
            modelAndView.addObject("teamError", "Team already exist");
        } else {
            modelAndView.setViewName("redirect:/teams");
            modelAndView.setStatus(HttpStatus.CREATED);
        }
        return modelAndView;
    }

    @Operation(summary = "Редактировать информацию о команде")
    @PatchMapping
    public ModelAndView editTeam(@Valid @ModelAttribute("team") TeamRequestDto teamRequestDto) {
        ModelAndView modelAndView = new ModelAndView();
        if (!teamService.editTeamDto(teamRequestDto)) {
            modelAndView.setViewName("teamViewName");
            modelAndView.setStatus(HttpStatus.CONFLICT);
            modelAndView.addObject("teamError", "Failed to save new values");
        } else {
            modelAndView.setViewName("redirect:/teams");
            modelAndView.setStatus(HttpStatus.OK);
        }
        return modelAndView;
    }


    //TODO: Рассмотреть возможность выноса обобщенной версии такого обработчика для всех контроллеров
    //      в ParentErrorController
    @ExceptionHandler(BindException.class)
    private ModelAndView handleValidationException(Exception e, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("releaseViewName", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("FieldErrors", bindingResult.getFieldErrors());
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        return modelAndView;
    }
}
