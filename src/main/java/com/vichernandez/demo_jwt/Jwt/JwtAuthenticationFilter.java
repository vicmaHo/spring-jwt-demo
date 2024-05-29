package com.vichernandez.demo_jwt.Jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // extendemos de onceperrequestfilter ya que esta es la que se usa para crear filtros personalizados,
    // con ello garantizamos que el filtro se ejecute solo una vez por cada solicitud
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String token = getTokenFromRequest(request);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        // Obtenemos el token de la cabecera del request
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // verificamos la palabra bearer, despues de esta se encuentra nuestro token
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;

    }
}
