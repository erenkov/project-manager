package com.developing.simbir_product.clients;

import com.developing.simbir_product.controller.Dto.WikiMatchCheckRequestDto;
import com.developing.simbir_product.controller.Dto.WikipediaMathCheckResponseDto;
import com.developing.simbir_product.controller.Dto.WikipediaTitleResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = "wikipedia", url = "https://en.wikipedia.org/api/rest_v1")
public interface WikipediaClient {

    @GetMapping("/page/title/{title}")
    WikipediaTitleResponseDto getTitle(@PathVariable("title") String title);

    @PostMapping("/media/math/check/{type}")
    WikipediaMathCheckResponseDto mathCheck(@PathVariable("type") String type, @RequestBody WikiMatchCheckRequestDto body);
}
