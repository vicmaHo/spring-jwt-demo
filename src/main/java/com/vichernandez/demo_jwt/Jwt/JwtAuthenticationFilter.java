package com.vichernandez.demo_jwt.Jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    // extendemos de onceperrequestfilter ya que esta es la que se usa para crear filtros personalizados,
    // con ello garantizamos que el filtro se ejecute solo una vez por cada solicitud
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String token = getTokenFromRequest(request);
        final String username;

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        username = jwtService.getUsernameFromToken(token);

        // si el username no es null y ademas no este en el security contex holder se busca en la base de datos
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // validamos si el token es valido
            if (jwtService.isTokenValid(token, userDetails)) {
                // si es valido actualizamos el security contex holder
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // System.out.println("Datos del token");
        // System.out.println(token);
        // System.out.println(username);
        // System.out.println("--------------");

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
