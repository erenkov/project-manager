package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.service.TeamService;
import com.developing.simbir_product.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Tag(name = "Управление профилем")
@RequestMapping(value = "/profile")
@Controller
public class UserProfileController {

    Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @Operation(summary = "Получить страницу c профилем пользователя")
    @GetMapping
    public String getProfile(Model model, Principal principal) {
        model.addAttribute("currentUser", userService.findByEmail(principal.getName()));
        model.addAttribute("teamNames", teamService.getListOfAllTeamNames());
        model.addAttribute("roles", userService.getListOfAllRoles());
        logger.trace("{} has accessed the profile page", principal.getName());
        return "profile";
    }

    @Operation(summary = "Получить страницу c профилем пользователя")
    @PostMapping
    public String updateProfile(@ModelAttribute("currentUser") UserRequestDto currentUser, Model model, Principal principal) {
        currentUser.setEmail(principal.getName());
        model.addAttribute("currentUser", userService.editUser(currentUser));
        return "redirect:/profile";
    }
}
