
package com.vichernandez.demo_jwt.Auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vichernandez.demo_jwt.Auth.dto.AuthResponse;
import com.vichernandez.demo_jwt.Auth.dto.LoginRequest;
import com.vichernandez.demo_jwt.Auth.dto.RegisterRequest;
import com.vichernandez.demo_jwt.Jwt.JwtService;
import com.vichernandez.demo_jwt.User.Role;
import com.vichernandez.demo_jwt.User.Users;
import com.vichernandez.demo_jwt.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    public AuthResponse login(LoginRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    public AuthResponse register(RegisterRequest request) {
        Users user = Users.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .country(request.getCountry())
            .role(Role.USER)
            .build();

        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
    }

}
