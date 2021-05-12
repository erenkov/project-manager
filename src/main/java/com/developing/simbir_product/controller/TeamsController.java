package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.TeamRequestDto;
import com.developing.simbir_product.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;


@Tag(name = "Управление командами")
@RequestMapping(value = "/teams")
@Controller
public class TeamsController {

    @Autowired
    private TeamService teamService;

    @Operation(summary = "Получить страницу c информацией о командах")
    @GetMapping
    public ModelAndView getTeamsPage() {
        ModelAndView modelAndView = new ModelAndView("teams", HttpStatus.OK);
        modelAndView.addObject("teamNames", teamService.getListOfAllTeamNames());
        return modelAndView;
    }

    @Operation(summary = "Получить страницу создания новой команды")
    @GetMapping("/create")
    public ModelAndView getNewTeamPage() {
        ModelAndView modelAndView = new ModelAndView("create-team", HttpStatus.OK);
        modelAndView.addObject("newTeam", new TeamRequestDto());
        return modelAndView;
    }

    @Operation(summary = "Создать команду")
    @PostMapping("/create")
    public ModelAndView createTeam(@Valid @ModelAttribute("newTeam") TeamRequestDto teamRequestDto) {
        ModelAndView modelAndView = new ModelAndView();
        if (teamService.addTeamDto(teamRequestDto)) {
            modelAndView.setViewName("redirect:/teams");
            modelAndView.setStatus(HttpStatus.CREATED);
        } else {
            modelAndView.setViewName("create-team");
            modelAndView.setStatus(HttpStatus.CONFLICT);
            modelAndView.addObject("teamError", "Team already exist");
        }
        return modelAndView;
    }

    @Operation(summary = "Получить страницу редактирования команды")
    @GetMapping("/edit/{teamName}")
    public ModelAndView getEditTeamPage(@PathVariable("teamName") String teamName) {
        ModelAndView modelAndView = new ModelAndView("edit-team", HttpStatus.OK);
        modelAndView.addObject("team", teamService.findDtoByName(teamName));
        return modelAndView;
    }

    @Operation(summary = "Редактировать информацию о команде")
    @PostMapping("/edit/{teamName}")
    public ModelAndView editTeam(@PathVariable("teamName") String teamName,
                                 @Valid @ModelAttribute("team") TeamRequestDto teamRequestDto) {
        teamRequestDto.setName(teamName);

        ModelAndView modelAndView = new ModelAndView();
        if (teamService.editTeam(teamRequestDto)) {
            modelAndView.setViewName("redirect:/teams");
            modelAndView.setStatus(HttpStatus.OK);
        } else {
            modelAndView.setViewName("edit-team");
            modelAndView.setStatus(HttpStatus.CONFLICT);
            modelAndView.addObject("team", teamRequestDto);
            modelAndView.addObject("teamError", "Failed to save new values");
        }
        return modelAndView;
    }


    @ExceptionHandler(BindException.class)
    private ModelAndView handleValidationException(Exception e, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        modelAndView.addObject("FieldErrors", bindingResult.getFieldErrors());
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        List<String> pathSegments = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPathSegments();
        if ("create".equals(pathSegments.get(1))) {
            modelAndView.setViewName("create-team");
            modelAndView.addObject("newTeam", bindingResult.getModel().get("newTeam"));
        } else {
            modelAndView.setViewName("edit-team");
            modelAndView.addObject("team", bindingResult.getModel().get("team"));
        }
        return modelAndView;
    }
}
