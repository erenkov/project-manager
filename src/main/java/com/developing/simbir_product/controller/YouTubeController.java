package com.developing.simbir_product.controller;

import com.developing.simbir_product.feign.YouTubeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YouTubeController {

    @Autowired
    YouTubeClient youTubeClient;

    String id ="7lCDEYXw3mM";
    String key="AIzaSyAm2LbrFj0NZ7CxL2lgaGUKLtqT3jd8JNE";
    String part="snippet,statistics";
    String fields="items(snippet,statistics)";

    @GetMapping("/youtube")
    public String getVideo() {
        return youTubeClient.getVideo(id,key,part,fields);
    }
}
