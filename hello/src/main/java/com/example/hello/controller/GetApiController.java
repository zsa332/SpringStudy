package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path = "/hello")
    public String hello(){
        return "get Hello";
    }

    /*
        RequesstMapping는 GetMappig 와 달리 method = RequestMethod.Get 형식으로
        요청 방식을 지정해 주어야한다.

        GET / POST / PUT / DELETE
    */

    @RequestMapping(path = "/hi", method = RequestMethod.GET)
    public String hi(){
        return "hi";
    }

    //  http://localhost:9090/api/get/path-variable/{spring-boot}
    //  http://localhost:9090/api/get/path-variable/{spring}
    //  http://localhost:9090/api/get/path-variable/{java}
    @GetMapping("/path-variable/{name}")
    public String pathVariable(@PathVariable String name){
        System.out.println("PathVariable : " + name);
        return name;
    }

    /*
        위 처럼 {name}와 parameter 의 @PathVariable String name 의 이름을 맞춰 주어야 하지만
        경우에 따라서 parameter 의 이름을 다르게 지정해 줄 수도 있다 그럴 땐 아래와 같이 수정하면 된다.
    */
    @GetMapping("/path-variable-second/{id}")
    public String pathVariableSecond(@PathVariable(name = "id") String pathName){
        System.out.println("PathVariable : " + pathName);
        return pathName;
    }

    /*
        search?q=naver
        &ei=_2jFYvO_JsmK2roPrb6dkAc
        &ved=0ahUKEwizu5rri-T4AhVJhVYBHS1fB3IQ4dUDCA4
        &uact=5
        &oq=naver
        &gs_lcp=Cgdnd3Mtd2l6EAMyCwgAEIAEELEDEIMBMgsIABCABBCxAxCDATILCAAQgAQQsQMQgwEyCwgAEIAEELEDEIMBMggIABCABBCxAzIFCAAQgAQyCAgAEIAEELEDMgsIABCABBCxAxCDATILCAAQgAQQsQMQgwEyCwgAEIAEELEDEIMBOhEILhCABBCxAxCDARDHARDRAzoECAAQAzoOCC4QgAQQxwEQ0QMQ1AI6DgguEIAEELEDEIMBENQCSgQIQRgASgQIRhgAUABYvQdglgloAXABeACAAXuIAb0FkgEDMC42mAEAoAEBwAEB
        &sclient=gws-wiz

        google에 네이버를 검색 했을때

        ?key=value&key2=value2
    */
    //  http://localhost:9090/api/get/query-param?user=steve&email=steve@gmail.com&age=30
    @GetMapping(path = "query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){
        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println();

            sb.append(entry.getKey()+" "+entry.getValue()+"\n");
        });

        return sb.toString();
    }

    //  parameter로 설정하는 방법
    @GetMapping("query-param02")
    public String queryParam02(@RequestParam String name, @RequestParam String email, @RequestParam int age){

        return name+" "+email+" "+age;
    }

    //  실무에서 가장 많이 사용되는 방법이라고 합니다
    //  객체를 만들어서 query parameter가 바로 맵핑이 되도록 만드는 방법
    @GetMapping("query-param03")
    public String queryParam03(UserRequest userRequest){
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }
}
