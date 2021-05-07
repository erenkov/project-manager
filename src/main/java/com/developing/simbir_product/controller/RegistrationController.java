package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Tag(name = "Авторизация")
@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    UserServiceImpl userService;

    @Operation(summary = "Получить страницу регистрации")
    @GetMapping
    public String getRegistrationPage() {
        return "registration";
    }

    @Operation(summary = "Зарегистрировать пользователя")
    @PostMapping
    public ModelAndView registerUser(@Valid UserRequestDto user) {
        ModelAndView modelAndView = new ModelAndView();
        if (!userService.addUser(user)) {
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
