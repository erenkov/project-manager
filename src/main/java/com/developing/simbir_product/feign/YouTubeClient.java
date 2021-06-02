package com.developing.simbir_product.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(
        name = "youtube-service",
        url = "https://www.googleapis.com/youtube/v3/"
)
public interface YouTubeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/videos?id=7lCDEYXw3mM&key=AIzaSyAm2LbrFj0NZ7CxL2lgaGUKLtqT3jd8JNE&part=snippet,statistics&fields=items(id,snippet,statistics)")
    List<Object> getVideo();

}