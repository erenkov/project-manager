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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
        modelAndView.addObject("projectName", releaseRequestDto.getProjectName());
        if (releaseService.addRelease(releaseRequestDto)) {
            modelAndView.setViewName("redirect:/releases");
            modelAndView.setStatus(HttpStatus.CREATED);
        } else {
            modelAndView.setViewName("redirect:/releases/create");
            modelAndView.setStatus(HttpStatus.CONFLICT);
            modelAndView.addObject("newRelease", releaseRequestDto);
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
                                    @Valid @ModelAttribute("release") ReleaseRequestDto releaseRequestDto) {
        releaseRequestDto.setId(releaseId);

        ModelAndView modelAndView = new ModelAndView();
        if (releaseService.editRelease(releaseRequestDto)) {
            modelAndView.addObject("projectName", releaseRequestDto.getProjectName());
            modelAndView.setViewName("redirect:/releases");
            modelAndView.setStatus(HttpStatus.OK);
        } else {
            modelAndView.setViewName("redirect:/releases/edit/" + releaseId);
            modelAndView.setStatus(HttpStatus.CONFLICT);
        }
        return modelAndView;
    }

    @ExceptionHandler(BindException.class)
    private ModelAndView handleValidationException(Exception e, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("create-release", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("FieldErrors", bindingResult.getFieldErrors());
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        return modelAndView;
    }
}
