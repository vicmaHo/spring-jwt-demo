
package com.vichernandez.demo_jwt.Auth;

import org.springframework.stereotype.Service;

import com.vichernandez.demo_jwt.Auth.dto.AuthResponse;
import com.vichernandez.demo_jwt.Auth.dto.LoginRequest;
import com.vichernandez.demo_jwt.Auth.dto.RegisterRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AuthService {

    public AuthResponse login(LoginRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    public AuthResponse register(RegisterRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

}
