package com.example.demo.controller;

import com.example.demo.dto.Req;
import com.example.demo.dto.UserResponse;
import com.example.demo.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ApiController {

    private final RestTemplateService restTemplateService;
    //  @Autowired 를 사용하는 방식에서 최근에는 생성자 주입방식을 선호 한다고한다.

    public ApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/hello")
    public String getHello(){
        return restTemplateService.hello();
    }

    @GetMapping("/user")
    public UserResponse getUser(){
        return restTemplateService.user();
    }

    @GetMapping("/user-query")
    public UserResponse getUserQuery(){
        return restTemplateService.userAddQuery();
    }

    @PostMapping("/post")
    public UserResponse post(){
        return restTemplateService.post();
    }

    @PostMapping("/post-exchange")
    public UserResponse postExchange(){
        return restTemplateService.exchange();
    }

    @PostMapping("/post-generic-exchange")
    public Req<UserResponse> postGenericExchange(){
        return restTemplateService.genericExchange();
    }

}
