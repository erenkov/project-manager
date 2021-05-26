package com.developing.simbir_product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String greeting() {
        return "redirect:/projects";
    }
}
