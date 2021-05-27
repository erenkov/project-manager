package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.exception.ReleaseDatesException;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.ReleaseService;
import com.developing.simbir_product.utils.BindingUtils;
import com.developing.simbir_product.utils.Converter;
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
import org.springframework.web.util.UriUtils;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;


@Tag(name = "Управление релизами")
@RequestMapping(value = "/releases/{projectName}")
@Controller
public class ReleasesController {

    @Autowired
    BindingUtils bindingUtils;

    @Autowired
    private ReleaseService releaseService;

    @Autowired
    private ProjectService projectService;

    @Operation(summary = "Получить страницу c информацией о релизах")
    @GetMapping
    public ModelAndView getReleasePage(@PathVariable String projectName,
                                       @RequestParam(value = "errorMessage", required = false) Optional<String> errorMessage) {
        ModelAndView modelAndView = new ModelAndView("releases", HttpStatus.OK);
        errorMessage.ifPresent(modelAndView::addObject);
        ProjectEntity projectEntity = projectService.getProjectEntity(projectName);
        modelAndView.addObject("releases", releaseService.getAllReleasesByProject(projectEntity));
        return modelAndView;
    }

    @Operation(summary = "Получить страницу создания нового релиза")
    @GetMapping("/create")
    public ModelAndView getNewReleasePage(@PathVariable String projectName) {
        ModelAndView modelAndView = new ModelAndView("create-release", HttpStatus.OK);
        ReleaseRequestDto releaseRequestDto = new ReleaseRequestDto();
        releaseRequestDto.setProjectName(projectName);
        modelAndView.addObject("release", releaseRequestDto);
        return modelAndView;
    }

    @Operation(summary = "Создать релиз")
    @PostMapping("/create")
    public ModelAndView createRelease(@PathVariable String projectName,
                                      @Valid @ModelAttribute("release") ReleaseRequestDto releaseRequestDto) {
        releaseService.addRelease(releaseRequestDto);
        return new ModelAndView("redirect:/releases/" + UriUtils.encode(projectName, StandardCharsets.UTF_8));
    }

    @Operation(summary = "Получить страницу редактирования релиза")
    @GetMapping("/edit/{releaseId}")
    public ModelAndView getEditReleasePage(@PathVariable String projectName,
                                           @PathVariable("releaseId") String releaseId) {
        ModelAndView modelAndView = new ModelAndView("edit-release", HttpStatus.OK);
        modelAndView.addObject("release", releaseService.getById(Converter.getUuidFromString(releaseId)));
        return modelAndView;
    }

    @Operation(summary = "Редактировать информацию о релизе")
    @PostMapping("/edit/{releaseId}")
    public ModelAndView editRelease(@PathVariable String projectName,
                                    @PathVariable("releaseId") String releaseId,
                                    @Valid @ModelAttribute("release") ReleaseRequestDto releaseRequestDto) {
        releaseRequestDto.setId(releaseId);
        releaseService.editRelease(releaseRequestDto);
        return new ModelAndView("redirect:/releases/" + UriUtils.encode(projectName, StandardCharsets.UTF_8));
    }


    private ModelAndView getCurrentView() {
        ModelAndView modelAndView = new ModelAndView();
        List<String> pathSegments = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPathSegments();
        if ("create".equals(pathSegments.get(2))) {
            modelAndView.setViewName("create-release");
        } else {
            modelAndView.setViewName("edit-release");
        }
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    @ExceptionHandler(NotFoundException.class)
    private ModelAndView handleEditException(NotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("redirect:../");
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReleaseDatesException.class)
    private ModelAndView handleDatesException(ReleaseDatesException e) {
        ModelAndView modelAndView = getCurrentView();
        modelAndView.addObject("release", e.getRelease());
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    private ModelAndView handleValidationException(BindingResult bindingResult) {
        ModelAndView modelAndView = getCurrentView();
        modelAndView.addObject("release", bindingResult.getModel().get("release"));
        bindingUtils.addErrorsToModel(bindingResult, modelAndView);
        return modelAndView;
    }
}
