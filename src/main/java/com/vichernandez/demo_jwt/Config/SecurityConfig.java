package com.vichernandez.demo_jwt.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vichernandez.demo_jwt.Jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

// Clase que  contendra la cadena de filtros de seguridad
@Configuration // Indica que es una clase de configuración, que tendra metodos anotados con Bean, para ser cargados en el contenedor (configuracion de objetos requeridos en la aplicacion)
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final AuthenticationProvider authProvider;

    // Metodo de seguridad, contiene toda la cadena de filtros que se van a ejecutar
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // retornamos http, siempre y cuando pase por la cadena de filtos que se configurara a continuación
        return http
                    // Proteccion cross, medida de seguridad que se usa para agregar a las solicitudes una atenticacion de tipo csrf, la desabilitamos ya que se trabajara con JWT
                    .csrf(csrf -> 
                        csrf
                            .disable())
            // .cors(cors -> cors.disable())
            .authorizeHttpRequests(authRequest ->
                authRequest
                    .requestMatchers(HttpMethod.GET).permitAll()
                    .requestMatchers(HttpMethod.OPTIONS).permitAll()
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated()
                    )
            .headers(headers -> headers
                    .frameOptions(frameOptions -> frameOptions.disable())
                )
                    // desabilito las sesions
                .sessionManagement(sessionManager ->
                    sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)        
                .build();
    }

}