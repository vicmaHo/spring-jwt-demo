package com.vichernandez.demo_jwt.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vichernandez.demo_jwt.Auth.dto.AuthResponse;
import com.vichernandez.demo_jwt.Auth.dto.LoginRequest;
import com.vichernandez.demo_jwt.Auth.dto.RegisterRequest;

import lombok.RequiredArgsConstructor;


// Controlador expuesto sin necesidad de autenticación, para temas de registro y login en una plataforma
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {

        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping
    public String getMethodName() {
        return "Hello from public access endpoint!";
    }
    
    

}
