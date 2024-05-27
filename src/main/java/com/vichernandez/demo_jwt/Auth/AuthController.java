package com.vichernandez.demo_jwt.Auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

// Controlador expuesto sin necesidad de autenticación
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping(value = "login")
    public String login() {
        return "login endpoint";
    }

    @PostMapping(value = "register")
    public String register( ) {
        return "register endpoint";
    }
    

}
