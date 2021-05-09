package com.developing.simbir_product.controller;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;
import com.developing.simbir_product.service.ReleaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Tag(name = "Управление релизами")
@RequestMapping(value = "/releases")
@Controller
public class ReleasesController {

    @Autowired
    private ReleaseService releaseService;


    @Operation(summary = "Получить страницу c информацией о релизе")
    @GetMapping
    public ModelAndView getReleasePage(@RequestParam String releaseName) {
        ModelAndView modelAndView = new ModelAndView("releaseViewName", HttpStatus.OK);
        modelAndView.addObject("release", releaseService.findByName(releaseName));
        return modelAndView;
    }

    @Operation(summary = "Создать релиз")
    @PostMapping
    public ModelAndView createTeam(@Valid @ModelAttribute("newRelease") ReleaseRequestDto releaseRequestDto) {
        ModelAndView modelAndView = new ModelAndView();
        if (!releaseService.addRelease(releaseRequestDto)) {
            modelAndView.setViewName("releaseViewName");
            modelAndView.setStatus(HttpStatus.CONFLICT);
            modelAndView.addObject("releaseError", "Release already exist");
        } else {
            modelAndView.setViewName("redirect:/releases");
            modelAndView.setStatus(HttpStatus.CREATED);
        }
        return modelAndView;
    }

    @Operation(summary = "Редактировать информацию о релизе")
    @PatchMapping
    public ModelAndView editTeam(@Valid @ModelAttribute("release") ReleaseRequestDto releaseRequestDto) {
        ModelAndView modelAndView = new ModelAndView();
        if (!releaseService.editRelease(releaseRequestDto)) {
            modelAndView.setViewName("releaseViewName");
            modelAndView.setStatus(HttpStatus.CONFLICT);
            modelAndView.addObject("teamError", "Failed to save new values");
        } else {
            modelAndView.setViewName("redirect:/teams");
            modelAndView.setStatus(HttpStatus.OK);
        }
        return modelAndView;
    }

    //TODO: Рассмотреть возможность выноса обобщенной версии такого обработчика для всех контроллеров
    //      в ParentErrorController
    @ExceptionHandler(BindException.class)
    private ModelAndView handleValidationException(Exception e, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("releaseViewName", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("FieldErrors", bindingResult.getFieldErrors());
        modelAndView.addObject("errorMessage", e.getLocalizedMessage());
        return modelAndView;
    }
}
