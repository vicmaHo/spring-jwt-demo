package com.vichernandez.demo_jwt.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Integer> {

    // JPA nos provee metodos para un crud basico
    // Si deseamos metodos especificos podemos usar querymethods
    //querymethod para buscar por nombre de usuario en la base de datos
    Optional<Users> findByUsername(String username);


    // Consulta especifica para modificar usuario, JPA realiza la implementacion de esta consulta
    @Modifying()
    @Query("update Users u set u.firstname=:firstname, u.lastname=:lastname, u.country=:country where u.id = :id")
    void updateUser(@Param(value = "id") Integer id,   @Param(value = "firstname") String firstname, @Param(value = "lastname") String lastname , @Param(value = "country") String country);

}
