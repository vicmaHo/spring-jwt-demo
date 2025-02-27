
package com.vichernandez.demo_jwt.Jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {

    private static final String SECRET_KEY = "11132E4312312F123123123P12312345R543434209G73G6219836475J"; 
    
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
            .builder()
            .claims(extraClaims)
            .subject(user.getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis()+1000*60*24))
            .signWith(getKey())
            .compact();

    }

    private Key getKey() {
        //la secret key la llevamos a base 64
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes); // nos permite crear una nueva instancia de la secret key
    }

    String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject); // aca se aloja el username
    }

    boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);

        // System.out.println(getClaim(token, Claims::getSubject));
        // System.out.println("Token valido: ");
        // System.out.println(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); // se valida el token a traves de los metodos auxiliares comprobando la fecha de expiracio y el username
    }

    public Claims getAllClaims(String token) {
        SecretKey key = (SecretKey) getKey();
        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
        return claimsJws.getPayload();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token) {

        // System.out.println(getClaim(token, Claims::getExpiration));

        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
