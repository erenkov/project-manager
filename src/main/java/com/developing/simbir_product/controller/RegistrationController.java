package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.exception.UserAlreadyExistException;
import com.developing.simbir_product.service.TeamService;
import com.developing.simbir_product.service.UserService;
import com.developing.simbir_product.utils.BindingUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Tag(name = "Регистрация")
@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private BindingUtils bindingUtils;

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
        userService.addUser(newUser);
        logger.debug("Created the user with credentials: {}", newUser);
        return new ModelAndView("redirect:/login");
    }

    private ModelAndView getRegistrationModel(UserRequestDto userRequestDto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        modelAndView.addObject("newUser", userRequestDto);
        modelAndView.addObject("teamNames", teamService.getListOfAllTeamNames());
        modelAndView.addObject("roles", userService.getListOfAllRoles());
        return modelAndView;
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistException.class)
    private ModelAndView handleCreateException(UserAlreadyExistException e) {
        UserRequestDto newUser = e.getUser();
        ModelAndView modelAndView = getRegistrationModel(newUser);
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    private ModelAndView handleValidationException(BindingResult bindingResult) {
        ModelAndView modelAndView = getRegistrationModel((UserRequestDto) bindingResult.getModel().get("newUser"));
        bindingUtils.addErrorsToModel(bindingResult, modelAndView);
        return modelAndView;
    }
}
