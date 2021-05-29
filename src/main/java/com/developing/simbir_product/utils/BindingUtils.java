package com.developing.simbir_product.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;
import java.util.stream.Collectors;


@Component
public class BindingUtils {

    @Autowired
    private MessageSource messageSource;

    public void addErrorsToModel(BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.addObject("FieldErrors", bindingResult.getFieldErrors().stream()
                .map(error -> messageSource.getMessage(error, Locale.ROOT))
                .sorted()
                .collect(Collectors.toList()));
        modelAndView.addObject("GlobalErrors", bindingResult.getGlobalErrors().stream()
                .map(error -> messageSource.getMessage(error, Locale.ROOT))
                .sorted()
                .collect(Collectors.toList()));
    }
}
