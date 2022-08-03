package com.example.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // all args 생성자
public class User {
    private String name;
    private int age;
}
