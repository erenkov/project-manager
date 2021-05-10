package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.service.TeamService;
import com.developing.simbir_product.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @Operation(summary = "Получить страницу регистрации")
    @GetMapping
    public String getRegistrationPage(Model model) {
        model.addAttribute("newUser", new UserRequestDto());
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
            logger.debug("User with email {} exists", newUser.getEmail());
        } else {
            modelAndView.setViewName("redirect:/login");
            modelAndView.setStatus(HttpStatus.CREATED);
            logger.debug("Created the user with credentials: {}", newUser.getEmail(), newUser.getFirstName(), newUser.getLastName(),
                    newUser.getRole());
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
