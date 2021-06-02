package com.developing.simbir_product.controller;

import com.developing.simbir_product.feign.YouTubeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class YouTubeController {

    @Autowired
    YouTubeClient youTubeClient;

    @GetMapping("/youtube")
    public List<Object> getVideo() {
        return youTubeClient.getVideo();
    }
}
