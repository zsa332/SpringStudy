package com.example.post.controller;

import com.example.post.dto.PostRequestDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {
    /*
        JSON
        string : value
        number : value
        boolean : value
        object : value { }
        array : value [ ]

        {
            "phone_number" : "010-1234-1234",
            "age" : 10,
            "isAgree" : false,
            "account" : {
                "email" : "steve@gmail.com"
                "password" : "1234"
            }
        }
    */
    @PostMapping("/post")
    public void post(@RequestBody Map<String, Object> requestData){
        requestData.forEach((key, value) -> {
            System.out.println("key : " + key);
            System.out.println("value : " + value);
        });
    }

    @PostMapping("/post-second")
    public void post02(@RequestBody PostRequestDto requestData){
        System.out.println(requestData);
    }
}
