package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.service.TeamService;
import com.developing.simbir_product.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getRegistrationPage(Model model) {
        model.addAttribute("newUser", new UserEntity());
        model.addAttribute("teamNames", teamService.getListOfAllTeamNames());
        model.addAttribute("roles", userService.getListOfAllRoles());
        return "registration";
    }

    @Operation(summary = "Зарегистрировать пользователя")
    @PostMapping
    public ModelAndView registerUser(@Valid @ModelAttribute("newUser") UserRequestDto newUser) {
        ModelAndView modelAndView = new ModelAndView();
        if (!userService.addUser(newUser)) {
            modelAndView.setViewName("registration");
            modelAndView.setStatus(HttpStatus.CONFLICT);
            modelAndView.addObject("userError", "User exists!");
        } else {
            modelAndView.setViewName("redirect:/login");
            modelAndView.setStatus(HttpStatus.CREATED);
        }
        return modelAndView;
    }

    @ExceptionHandler(BindException.class)
    private ModelAndView handleValidationException(Exception e, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("registration", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("FieldErrors", bindingResult.getFieldErrors());
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        return modelAndView;
    }
}
