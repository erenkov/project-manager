package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.controller.Dto.UserResponseDto;
import com.developing.simbir_product.entity.Role;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.exception.ProjectAlreadyExistException;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.TeamService;
import com.developing.simbir_product.service.UserService;
import com.developing.simbir_product.utils.BindingUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


@Tag(name = "{projectsController.tag}")
@RequestMapping("/projects")
@Controller
public class ProjectsController {

    @Autowired
    private BindingUtils bindingUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TeamService teamService;

    @Operation(summary = "{projectsController.getProjectsPage.operation}")
    @GetMapping()
    public String getProjectsPage(Model model, Principal principal, Authentication authentication,
                                  @RequestParam(value = "errorMessage", required = false) Optional<String> errorMessage) {
        errorMessage.ifPresent(model::addAttribute);
        UserResponseDto user = userService.findByEmail(principal.getName());
        if (user == null) {
            return "redirect:/logout";
        }
        model.addAttribute("userName", String.format("%s %s", user.getFirstName(), user.getLastName()));
        if (authentication.getAuthorities().contains(Role.ROLE_ADMIN)) {
            model.addAttribute("projectNames", projectService.getListOfAllProjectNames());
        } else {
            model.addAttribute("projectNames", projectService.getListOfAllProjectNamesByTeam(user.getTeam()));
        }
        return "projects";
    }

    @Operation(summary = "{projectsController.getNewProjectPage.operation}")
    @GetMapping("/create")
    public ModelAndView getNewProjectPage() {
        ModelAndView modelAndView = getProjectsModel();
        modelAndView.setViewName("create-project");
        modelAndView.addObject("newProject", new ProjectRequestDto());
        return modelAndView;
    }

    @Operation(summary = "{projectsController.createProject.operation}")
    @PostMapping("/create")
    public ModelAndView createProject(@Valid @ModelAttribute("newProject") ProjectRequestDto projectRequestDto) {
        projectService.addProject(projectRequestDto);
        return new ModelAndView("redirect:/projects", HttpStatus.CREATED);
    }

    @Operation(summary = "{projectsController.getEditProjectPage.operation}")
    @GetMapping("/edit/{prName}")
    public ModelAndView getEditProjectPage(@PathVariable("prName") String prName) {
        ModelAndView modelAndView = getProjectsModel();
        modelAndView.setViewName("edit-project");
        modelAndView.addObject("project", projectService.findByName(prName));
        return modelAndView;
    }

    @Operation(summary = "{projectsController.editProject.operation}")
    @PostMapping("/edit/{prName}")
    public ModelAndView editProject(@PathVariable("prName") String prName,
                                    @Valid @ModelAttribute("project") ProjectRequestDto projectRequestDto) {
        projectRequestDto.setName(prName);
        projectService.editProject(projectRequestDto);
        return new ModelAndView("redirect:/projects");
    }

    private ModelAndView getProjectsModel() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teamList", teamService.getListOfAllTeamNames());
        modelAndView.addObject("projectStatusList", projectService.getListOfAllProjectStatus());
        return modelAndView;
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ProjectAlreadyExistException.class)
    private ModelAndView handleCreateException(ProjectAlreadyExistException e) {
        ModelAndView modelAndView = getProjectsModel();
        modelAndView.setViewName("create-project");
        modelAndView.addObject("newProject", e.getProject());
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    @ExceptionHandler(NotFoundException.class)
    private ModelAndView handleEditException(NotFoundException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/projects");
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    private ModelAndView handleValidationException(BindingResult bindingResult, Locale locale) {
        ModelAndView modelAndView = getProjectsModel();
        List<String> pathSegments = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPathSegments();
        if ("create".equals(pathSegments.get(1))) {
            modelAndView.setViewName("create-project");
            modelAndView.addObject("newProject", bindingResult.getModel().get("newProject"));
        } else {
            modelAndView.setViewName("edit-project");
            modelAndView.addObject("project", bindingResult.getModel().get("project"));
        }
        bindingUtils.addErrorsToModel(bindingResult, modelAndView, locale);
        return modelAndView;
    }
}
