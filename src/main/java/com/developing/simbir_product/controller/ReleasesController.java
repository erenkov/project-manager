package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.ReleaseService;
import com.developing.simbir_product.utils.Converter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Tag(name = "Управление релизами")
@RequestMapping(value = "/releases")
@Controller
public class ReleasesController {

    @Autowired
    private ReleaseService releaseService;

    @Autowired
    private ProjectService projectService;

    @Operation(summary = "Получить страницу c информацией о релизах")
    @GetMapping
    public ModelAndView getReleasePage(@RequestParam String projectName) {
        ModelAndView modelAndView = new ModelAndView("releases", HttpStatus.OK);
        ProjectEntity projectEntity = projectService.getProjectEntity(projectName);
        modelAndView.addObject("releases", releaseService.getAllReleasesByProject(projectEntity));
        return modelAndView;
    }

    @Operation(summary = "Получить страницу создания нового релиза")
    @GetMapping("/create")
    public ModelAndView getNewReleasePage(@RequestParam String projectName) {
        ModelAndView modelAndView = new ModelAndView("create-release", HttpStatus.OK);
        ReleaseRequestDto releaseRequestDto = new ReleaseRequestDto();
        releaseRequestDto.setProjectName(projectName);
        modelAndView.addObject("newRelease", releaseRequestDto);
        return modelAndView;
    }

    @Operation(summary = "Создать релиз")
    @PostMapping("/create")
    public ModelAndView createRelease(@Valid @ModelAttribute("newRelease") ReleaseRequestDto releaseRequestDto) {
        ModelAndView modelAndView = new ModelAndView();
        if (releaseService.addRelease(releaseRequestDto)) {
            modelAndView.setViewName("redirect:/releases?projectName=" + releaseRequestDto.getProjectName());
            modelAndView.setStatus(HttpStatus.CREATED);
        } else {
            modelAndView.setViewName("create-release");
            modelAndView.setStatus(HttpStatus.CONFLICT);
            modelAndView.addObject("newRelease", releaseRequestDto);
            modelAndView.addObject("releaseError", "Release already exist");
        }
        return modelAndView;
    }

    @Operation(summary = "Получить страницу редактирования релиза")
    @GetMapping("/edit/{releaseId}")
    public ModelAndView getEditReleasePage(@PathVariable("releaseId") String releaseId) {
        ModelAndView modelAndView = new ModelAndView("edit-release", HttpStatus.OK);
        modelAndView.addObject("release", releaseService.getById(Converter.getUuidFromString(releaseId)));
        return modelAndView;
    }

    @Operation(summary = "Редактировать информацию о релизе")
    @PostMapping("/edit/{releaseId}")
    public ModelAndView editRelease(@PathVariable("releaseId") String releaseId,
                                    @Valid @ModelAttribute("release") ReleaseRequestDto releaseRequestDto,
                                    HttpServletRequest httpServletRequest) {
        releaseRequestDto.setId(releaseId);

        ModelAndView modelAndView = new ModelAndView();
        if (releaseService.editRelease(releaseRequestDto)) {
            modelAndView.setViewName("redirect:/releases?projectName=" + releaseRequestDto.getProjectName());
            modelAndView.setStatus(HttpStatus.OK);
        } else {
            modelAndView.setViewName("redirect:/create-release?projectName=" + releaseRequestDto.getProjectName());
            modelAndView.setStatus(HttpStatus.CONFLICT);
            modelAndView.addObject("releaseError", "Failed to save new values");
        }
        return modelAndView;
    }

    @ExceptionHandler(BindException.class)
    private ModelAndView handleValidationException(Exception e, BindingResult bindingResult) {
        //todo имя view
        ModelAndView modelAndView = new ModelAndView("releaseViewName", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("FieldErrors", bindingResult.getFieldErrors());
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        return modelAndView;
    }
}
