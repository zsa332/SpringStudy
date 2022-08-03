package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)//    권한체크를 실행하겠다.

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(//    USER 권한을 가진 user2 생성
                        User.builder()
                                .username("user2")
                                .password(passwordEncoder().encode("2222"))
                                .roles("USER")
                )
                .withUser(//    ADMIN 권한을 가진 admin 생성
                        User.builder()
                                .username("admin")
                                .password(passwordEncoder().encode("3333"))
                                .roles("ADMIN")
                );


    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests((request) ->
                request.antMatchers("/").permitAll()//  permitAll()을 이용해 모든 사용자에게 열어준다.
                        .anyRequest().authenticated()// 그외에는 모두 권한 검사사
        );
        http.formLogin();
        http.httpBasic();
    }
}
