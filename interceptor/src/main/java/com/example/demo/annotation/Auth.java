package com.example.demo.annotation;

import java.lang.annotation.*;

//  Custom Annotation
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Auth {

}
