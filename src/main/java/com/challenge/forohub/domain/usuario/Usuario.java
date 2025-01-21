package com.challenge.forohub.domain.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String correoEmail;

    @Column(nullable = false)
    private String contraseña;

    @Column(nullable = false)
    private String perfil;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoEmail() {
        return correoEmail;
    }

    public void setCorreoEmail(String correoEmail) {
        this.correoEmail = correoEmail;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }


    // Métodos relacionados con UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Aquí puedes agregar las autoridades del usuario según tu implementación
        // En este caso, solo se retorna un perfil como autoridad
        return List.of(() -> perfil);
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return correoEmail;  // El correo es el username que se usará en la autenticación
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Si tienes lógica de expiración, puedes modificar esto
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Si tienes lógica de bloqueo de cuentas, puedes modificar esto
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Si tienes lógica de expiración de credenciales, puedes modificar esto
    }

    @Override
    public boolean isEnabled() {
        return true;  // Si tienes lógica para habilitar/deshabilitar usuarios, puedes modificar esto
    }



}
