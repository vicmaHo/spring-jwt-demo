package com.vichernandez.demo_jwt.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults; // importacion de la pantalla de login
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

// Clase que  contendra la cadena de filtros de seguridad
@Configuration // Indica que es una clase de configuración, que tendra metodos anotados con Bean, para ser cargados en el contenedor (configuracion de objetos requeridos en la aplicacion)
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // Metodo de seguridad, contiene toda la cadena de filtros que se van a ejecutar
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // retornamos http, siempre y cuando pase por la cadena de filtos que se configurara a continuación
        return http
                    // Proteccion cross, medida de seguridad que se usa para agregar a las solicitudes una atenticacion de tipo csrf, la desabilitamos ya que se trabajara con JWT
                    .csrf(csrf -> 
                        csrf
                            .disable())
            .authorizeHttpRequests(authRequest ->
                authRequest
                    .requestMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated()
                    )
                .formLogin(withDefaults())
                .build();
    }
}
