package com.vichernandez.demo_jwt.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {

    // JPA nos provee metodos para un crud basico
    // Si deseamos metodos especificos podemos usar querymethods
    //querymethod para buscar por nombre de usuario en la base de datos
    Optional<Users> findByUsername(String username);

}
