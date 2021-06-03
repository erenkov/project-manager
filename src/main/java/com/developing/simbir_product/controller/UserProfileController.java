package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.service.TeamService;
import com.developing.simbir_product.service.UserService;
import com.developing.simbir_product.utils.BindingUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Locale;


@Tag(name = "{userProfileController.tag}")
@RequestMapping(value = "/profile")
@Controller
public class UserProfileController {

    private final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    private BindingUtils bindingUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MessageSource messageSource;


    @Operation(summary = "{userProfileController.getProfile.operation}")
    @GetMapping
    public ModelAndView getProfile(Principal principal) {
        ModelAndView modelAndView = getUserProfileModel();
        modelAndView.addObject("currentUser", userService.findByEmail(principal.getName()));
        logger.info(messageSource.getMessage("userProfileController.getProfile.logger",
                new String[]{principal.getName()}, Locale.getDefault()));
        return modelAndView;
    }

    @Operation(summary = "{userProfileController.updateProfile.operation}")
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


    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    @ExceptionHandler(NotFoundException.class)
    private ModelAndView handleEditException(NotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("redirect:/logout");
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    private ModelAndView handleValidationException(BindingResult bindingResult, Locale locale) {
        ModelAndView modelAndView = getUserProfileModel();
        modelAndView.addObject("currentUser", bindingResult.getModel().get("currentUser"));
        bindingUtils.addErrorsToModel(bindingResult, modelAndView, locale);
        return modelAndView;
    }
}
