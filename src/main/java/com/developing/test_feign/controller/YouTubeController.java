package com.developing.test_feign.controller;

import com.developing.test_feign.feign.YouTubeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YouTubeController {

    @Autowired
    private YouTubeClient youTubeClient;

    @Value("${api.key}")
    private String key;

    @GetMapping("/youtube")
    public String getVideo() {
        String id = "7lCDEYXw3mM";
        String part = "snippet,statistics";
        String fields = "items(snippet,statistics)";
        return youTubeClient.getVideo(id, key, part, fields);
    }
}
