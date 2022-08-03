package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AppConfig {

    @Bean("async-thread")
    public Executor asyncThread(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(100);//
        threadPoolTaskExecutor.setCorePoolSize(10);//  queue가 꽉차면 10개씩 증가
        threadPoolTaskExecutor.setQueueCapacity(10);// 10개의 queue가 주어짐
        threadPoolTaskExecutor.setThreadNamePrefix("Async-");

        return threadPoolTaskExecutor;
    }
}
