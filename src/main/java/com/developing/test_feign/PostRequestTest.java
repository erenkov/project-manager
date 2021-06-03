package com.developing.test_feign;

import com.developing.test_feign.Dto.RequestDto;
import com.developing.test_feign.feign.TestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostRequestTest {

    @Autowired
    private TestClient testClient;

    @Test
    void postRequestShouldReturnTrue(){
        RequestDto requestDto = new RequestDto(78912, "Jason Sweet", 1, 18.00d);
        Assertions.assertTrue(testClient.testPostRequest(requestDto).contains("true"));
        System.out.println(testClient.testPostRequest(requestDto));
    }


}
