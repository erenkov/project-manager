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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;


@Tag(name = "Управление профилем")
@RequestMapping(value = "/profile")
@Controller
public class UserProfileController {

    private final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @Operation(summary = "Получить страницу c профилем пользователя")
    @GetMapping
    public ModelAndView getProfile(Principal principal) {
        ModelAndView modelAndView = getUserProfileModel();
        modelAndView.addObject("currentUser", userService.findByEmail(principal.getName()));
        logger.trace("{} has accessed the profile page", principal.getName());
        return modelAndView;
    }

    @Operation(summary = "Получить страницу c профилем пользователя")
    @PostMapping
    public String updateProfile(@Valid @ModelAttribute("currentUser") UserRequestDto currentUser,
                                Model model, Principal principal) {
        currentUser.setEmail(principal.getName());
        model.addAttribute("currentUser", userService.editUser(currentUser));
        return "redirect:/profile";
    }

    private ModelAndView getUserProfileModel() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        modelAndView.addObject("teamNames", teamService.getListOfAllTeamNames());
        modelAndView.addObject("roles", userService.getListOfAllRoles());
        return modelAndView;
    }

    @ExceptionHandler(BindException.class)
    private ModelAndView handleValidationException(Exception e, BindingResult bindingResult) {
        ModelAndView modelAndView = getUserProfileModel();
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        modelAndView.addObject("currentUser", bindingResult.getModel().get("currentUser"));
        modelAndView.addObject("FieldErrors", bindingResult.getFieldErrors());
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        return modelAndView;
    }
}
