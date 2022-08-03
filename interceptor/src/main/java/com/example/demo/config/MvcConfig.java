package com.example.demo.config;

import com.example.demo.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor//  final 로 생성된 객체들을 생성자에서 주입받을수 있게 해준다.
public class MvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*");//   addPathPatterns("","")를 이용하여 특정 url만 interceptor을 적용할 수 있다.
        // registry.addInterceptor();   추가로 interceptor을 추가할 수 도 있다.
    }
}
