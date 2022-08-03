package com.example.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/api/user/*")
//  특정 url에만 filter를 적용하고 자하면 @WebFilter을 사용 Application에서 @ServletComponentScan를 추가하면 된다.
//  모두 적용하기 위해서는 @Component를 사용하면 된다.
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //  전처리
//        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
//        위의 방법을 이용하게 되면 controller에서 request를 읽을 수 없다.

        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest)request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse)response);


        chain.doFilter(httpServletRequest, httpServletResponse);//chain 을 기준으로 전처리 후처리를 할 수 있다.

        // 후처리
        String url = httpServletRequest.getRequestURI();

        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("request url : {}, request body : {}", url, reqContent);

        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();

        httpServletResponse.copyBodyToResponse();// response을 읽는 과정에서 데이터를 모두 읽어버려 다시 copy를 해주어야 한다.

        log.info("response status : {}, responseBody : {}", httpStatus, resContent);

    }
}
