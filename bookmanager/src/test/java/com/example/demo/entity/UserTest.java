package com.example.demo.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void test(){
        User user = new User();
        user.setEmail("asdf@naver.com");
        user.setName("steve");
        user.setCreateAt(LocalDateTime.now());
        user.setUpdateAt(LocalDateTime.now());

        //  AllArgsConstructor
        User user1 = new User(null, "steve", "asdf@naver.com", LocalDateTime.now(), LocalDateTime.now());

        //  RequiredArgsConstructor
        User user2 = new User("steve", "asdf@naver.com");

        //  builder
        User user3 = User.builder()
                .name("steve")
                .email("asdf@naver.com")
                .build();

        System.out.println("user : " + user.toString());
    }

}