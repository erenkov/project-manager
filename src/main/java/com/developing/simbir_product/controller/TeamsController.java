package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.TeamRequestDto;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.exception.TeamAlreadyExistException;
import com.developing.simbir_product.service.TeamService;
import com.developing.simbir_product.utils.BindingUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Tag(name = "Управление командами")
@RequestMapping(value = "/teams")
@Controller
public class TeamsController {

    @Autowired
    private BindingUtils bindingUtils;
    @Autowired
    private TeamService teamService;

    @Operation(summary = "Получить страницу c информацией о командах")
    @GetMapping
    public ModelAndView getTeamsPage(@RequestParam(value = "errorMessage", required = false) Optional<String> errorMessage) {
        ModelAndView modelAndView = new ModelAndView("teams", HttpStatus.OK);
        errorMessage.ifPresent(modelAndView::addObject);
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
        teamService.addTeamDto(teamRequestDto);
        return new ModelAndView("redirect:/teams", HttpStatus.CREATED);
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
        teamService.editTeam(teamRequestDto);
        return new ModelAndView("redirect:/teams");
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(TeamAlreadyExistException.class)
    private ModelAndView handleCreateException(TeamAlreadyExistException e) {
        ModelAndView modelAndView = new ModelAndView("create-team");
        modelAndView.addObject("newTeam", e.getTeam());
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    @ExceptionHandler(NotFoundException.class)
    private ModelAndView handleEditException(NotFoundException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/teams");
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    private ModelAndView handleValidationException(BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        bindingUtils.addErrorsToModel(bindingResult, modelAndView);
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
