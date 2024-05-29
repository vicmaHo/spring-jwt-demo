package com.vichernandez.demo_jwt.User;


import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints= {@UniqueConstraint(columnNames={"username"})})
public class User implements UserDetails{ //agrego la implementacion de userdetails para trabajar con la autenticacion, gracias a los metodos que brinda

    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false)
    String username;

    String lastname;

    String firstname;

    String country;

    String password;

    @Enumerated(EnumType.STRING)
    Rol role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name()))); // lista que contiene un unico objeto, representa la autoridad otorgada al usuario
    }

    // Existen otros metodos de la interface UserDetails, pero no se usaran ya que se estara trabajando con un token
    // y este token contendra la informacion necesaria, por tanto estas funciones no seran necesarias


}
