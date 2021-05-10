package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.service.TeamService;
import com.developing.simbir_product.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Tag(name = "Регистрация")
@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @Operation(summary = "Получить страницу регистрации")
    @GetMapping
    public ModelAndView getRegistrationPage() {
        return getRegistrationModel(new UserRequestDto());
    }

    @Operation(summary = "Зарегистрировать пользователя")
    @PostMapping
    public ModelAndView registerUser(@Valid @ModelAttribute("newUser") UserRequestDto newUser) {
        ModelAndView modelAndView;
        if (userService.addUser(newUser)) {
            modelAndView = new ModelAndView("redirect:/login", HttpStatus.CREATED);
        } else {
            modelAndView = getRegistrationModel(newUser);
            modelAndView.setStatus(HttpStatus.CONFLICT);
            modelAndView.addObject("userError", "User exists!");
        }
        return modelAndView;
    }

    private ModelAndView getRegistrationModel(UserRequestDto userRequestDto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        modelAndView.addObject("newUser", userRequestDto);
        modelAndView.addObject("teamNames", teamService.getListOfAllTeamNames());
        modelAndView.addObject("roles", userService.getListOfAllRoles());
        return modelAndView;
    }

    @ExceptionHandler(BindException.class)
    private ModelAndView handleValidationException(Exception e, BindingResult bindingResult) {
        ModelAndView modelAndView = getRegistrationModel((UserRequestDto) bindingResult.getModel().get("newUser"));
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        modelAndView.addObject("FieldErrors", bindingResult.getFieldErrors());
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        return modelAndView;
    }
}
