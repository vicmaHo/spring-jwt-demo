package com.vichernandez.demo_jwt.Demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

// Controlador con endpoints protegidos por autenticación
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class DemoController {

    @PostMapping(value = "test")
    public String test() {
        return "test secure endpoint";
    }
    
}
