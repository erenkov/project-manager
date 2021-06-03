package com.developing.test_feign.feign;

import com.developing.test_feign.Dto.RequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "test-service",
        url = "${test.api.url}"
)
public interface TestClient {

    @PostMapping("/echo/post/json")
    String testPostRequest(@RequestBody RequestDto requestDto);

}
