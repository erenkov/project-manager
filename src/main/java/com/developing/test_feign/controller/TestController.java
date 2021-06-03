package com.developing.test_feign.controller;

import com.developing.test_feign.Dto.RequestDto;
import com.developing.test_feign.feign.TestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestClient testClient;

    @GetMapping("/test")
    public String testPostRequest() {
        RequestDto requestDto = new RequestDto(57, "Ivan Ivanov", 1, 25.00d);
        return testClient.testPostRequest(requestDto);
    }
}
