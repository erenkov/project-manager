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

    private String id = "7lCDEYXw3mM";

    @Value("${api.key}")
    private String key;

    private String part = "snippet,statistics";

    private String fields = "items(snippet,statistics)";

    @GetMapping("/youtube")
    public String getVideo() {
        return youTubeClient.getVideo(id, key, part, fields);
    }
}
