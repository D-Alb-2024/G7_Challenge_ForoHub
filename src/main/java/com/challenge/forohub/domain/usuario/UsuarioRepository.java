package com.challenge.forohub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository
        extends JpaRepository <Usuario, Long>{

    Optional<Usuario> findByCorreoEmail(String correoEmail);
    Optional<Usuario> findByNombre(String nombre);

    // Verificar si existe un usuario por su correo electrónico (para validaciones)
    boolean existsByCorreoEmail(String correoEmail);

    // Verificar si existe un usuario por su nombre de usuario (login)
    boolean existsByNombre(String nombre);
}
