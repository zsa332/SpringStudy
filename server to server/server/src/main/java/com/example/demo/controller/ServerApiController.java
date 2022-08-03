package com.example.demo.controller;

import com.example.demo.dto.Req;
import com.example.demo.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

@RestController
@RequestMapping("/api/server")
@Slf4j
public class ServerApiController {

    //  https://openapi.naver.com/v1/search/local.json
    //  ?query=%EC%B9%98%ED%82%A8%EC%A7%
    //  &display=10
    //  &start=1
    //  &sort=random
    //  Naver Open Api에 대한 설명은 https://developers.naver.com/docs/serviceapi/search/blog/blog.md#%EB%B8%94%EB%A1%9C%EA%B7%B8 에 나와있다.
    @GetMapping("/naver")
    public String naver() throws UnsupportedEncodingException {
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query", "치킨집")
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "random")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", "u2za6uc_L439vkBcavi7")
                .header("X-Naver-Client-Secret", "WC28_UJqY1")
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        log.info("uri : {}", uri);
        log.info("result : {}",result);

        return result.getBody();
    }

    @GetMapping("/hello")
    public String hello(){
        return "server hello";
    }

    @GetMapping("/user")
    public User user(){
        User user = new User();

        user.setName("홍길동");
        user.setAge(10);

        return user;
    }

    @GetMapping("/user-query")
    public User userQuery(@RequestParam String name, @RequestParam int age){
        User user =new User();

        user.setName(name);
        user.setAge(age);

        return user;
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public User post(@RequestBody User user, @PathVariable int userId, @PathVariable String userName){
        log.info("userId : {}, userName : {}", userId, userName);
        log.info("client req : {}", user);

        return user;
    }

    @PostMapping("/user-exchange/{userId}/name/{userName}")
    public User postExchange(@RequestBody User user, @PathVariable int userId, @PathVariable String userName, @RequestHeader("x-authorization") String authorization, @RequestHeader("custom-header") String customHeader){
        log.info("userId : {}, userName : {}", userId, userName);
        log.info("authorization : {}, customHeader : {}", authorization, customHeader);
        log.info("client req : {}", user);

        return user;
    }

    @PostMapping("/user-generic-exchange/{userId}/name/{userName}")
    public Req<User> postGenericExchange(
//            HttpEntity<String> entity,//    request 를 String 으로 받아서 확인 해볼수있다.
            @RequestBody Req<User> user,
            @PathVariable int userId,
            @PathVariable String userName,
            @RequestHeader("x-authorization") String authorization,
            @RequestHeader("custom-header") String customHeader
    ){
//        log.info("req : {}", entity.getBody());
        log.info("userId : {}, userName : {}", userId, userName);
        log.info("authorization : {}, customHeader : {}", authorization, customHeader);
        log.info("client req : {}", user);

        Req<User> response = new Req<>();
        response.setHeader(
                new Req.Header()
        );
        response.setResBody(user.getResBody());
        return response;
    }
}
