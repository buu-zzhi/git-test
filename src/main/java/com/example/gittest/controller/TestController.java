package com.example.gittest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Hello Springboot";
    }

    @GetMapping("/test2")
    public String test2() {
        return "Hello mybatis";
    }

    @GetMapping("/test3")
    public String test3() {
        return "HPL";
    }
}
