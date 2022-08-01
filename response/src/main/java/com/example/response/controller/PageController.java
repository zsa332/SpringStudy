package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }

    // RequestEntity
    //  PageController 에서는 되도록이면 api Controller을 이용하지 않음
    @ResponseBody
    @GetMapping("/user")
    public User user(){
        var user = new User();

        user.setName("steve");
        user.setAddress("ksnu");
        return user;
    }
}
