package com.challenge.forohub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.challenge.forohub.domain.usuario.Usuario;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.springframework.security.config.Elements.JWT;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("ForoHub")
                    .withSubject(usuario.getCorreoEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Error al generar token", e);
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("Token no proporcionado");
        }
        DecodedJWT verifier = null;
        try {
            // Usamos JWT.require para crear un verificador de token
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // Creamos el algoritmo
            DecodedJWT decodedJWT = JWT.require(algorithm) // Llamamos a require con el algoritmo
                    .withIssuer("ForoHub") // El emisor del token
                    .build() // Construimos el verificador
                    .verify(token); // Verificamos el token

            return decodedJWT.getSubject(); // Esto devuelve el "subject" del token (el login o usuario)
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error al verificar el token", exception);
        }
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(20).toInstant(ZoneOffset.UTC);
    }

}
