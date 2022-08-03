package com.example.demo.service;

import com.example.demo.dto.Req;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    //  http://localhost/api/server/hello
    //  response

    /*  GET Method  */
    public String hello(){

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .encode()
                .build()
                .toUri();
        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();//   http method를 지원해준다.
//        String result = restTemplate.getForObject(uri, String.class);// getForObject를 통해 원하는 방식으로 response을 받을 수 있다.

        ResponseEntity<String> result = restTemplate.getForEntity(uri,String.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }

    public UserResponse user(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user")
                .encode()
                .build()
                .toUri();
        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri,UserResponse.class);


        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }

    public UserResponse userAddQuery(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user-query")
                .queryParam("name","전우치")//queryParam을 통해 parameter을 추가할 수 있다.
                .queryParam("age","20")
                .encode()
                .build()
                .toUri();
        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri,UserResponse.class);


        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }

    /*  POST Method  */
    public UserResponse post(){
        //  http://localhost:9090/api/server/user/{userId}/name/{userName}

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")// , 를 통해 추가해주면 순서대로 매칭이 된다.
                .toUri();
        System.out.println(uri);

        //  http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(12);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri, req, UserResponse.class);// postForEntity(uri, body, responseType)
        //  ResponseEntity<String> response = restTemplate.postForEntity(uri, req, String.class);// 초기에는 무엇을 보내줄지 모르기 때문에 String type 으로 받아서 확인해 볼 수 있다.


        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();
    }

    public UserResponse exchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user-exchange/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")// , 를 통해 추가해주면 순서대로 매칭이 된다.
                .toUri();
        System.out.println(uri);

        //  http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(12);

        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "asdf")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response =restTemplate.exchange(requestEntity,UserResponse.class);

        return response.getBody();
    }

    public Req<UserResponse> genericExchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user-generic-exchange/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")// , 를 통해 추가해주면 순서대로 매칭이 된다.
                .toUri();
        System.out.println(uri);

        UserRequest userRequest = new UserRequest();
        userRequest.setName("steve");
        userRequest.setAge(12);

        Req req = new Req<UserRequest>();
        req.setHeader(
                new Req.Header()
        );
        req.setResBody(
                userRequest
        );

        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "asdf")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Req<UserResponse>> response = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<Req<UserResponse>>(){});
        // generic 은 .class를 붙일 수 없기 때문에 new ParameterizedTypeReference<>(){}를 이용

        return response.getBody();
    }
}
