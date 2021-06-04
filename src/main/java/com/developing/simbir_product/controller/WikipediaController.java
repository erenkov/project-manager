package com.developing.simbir_product.controller;


import com.developing.simbir_product.clients.WikipediaClient;
import com.developing.simbir_product.controller.Dto.WikiMatchCheckRequestDto;
import com.developing.simbir_product.controller.Dto.WikipediaMathCheckResponseDto;
import com.developing.simbir_product.controller.Dto.WikipediaTitleResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wiki")
public class WikipediaController {

    @Autowired
    WikipediaClient wikipediaClient;

    @GetMapping("/{title}")
    public WikipediaTitleResponseDto getTitle(@PathVariable("title") String title){
        return wikipediaClient.getTitle(title);
    }

    @GetMapping("/type/{type}")
    public WikipediaMathCheckResponseDto mathCheck(@PathVariable("type") String type, @RequestParam("param") String param){
        WikiMatchCheckRequestDto pojoBody = new WikiMatchCheckRequestDto();
        pojoBody.setQ(param);
        return wikipediaClient.mathCheck(type, pojoBody);
    }
}
