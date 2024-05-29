package com.vichernandez.demo_jwt.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    // JPA nos provee metodos para un crud basico
    // Si deseamos metodos especificos podemos usar querymethods
    //querymethod para buscar por nombre de usuario en la base de datos
    Optional<User> findByUsername(String username);

}
