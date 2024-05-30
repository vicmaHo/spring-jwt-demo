package com.vichernandez.demo_jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping(value = "/test")
    public String method() {
        
        System.out.println("Accedo");
        return "Welcome from protected endpoint";
    }
}
