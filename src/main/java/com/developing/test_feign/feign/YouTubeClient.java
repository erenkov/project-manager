package com.developing.test_feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "youtube-service",
        url = "${api.url}"
)
public interface YouTubeClient {

    @GetMapping("/videos")
    String getVideo(@RequestParam("id") String id, @RequestParam("key") String key, @RequestParam("part") String part, @RequestParam("fields") String fields);

}