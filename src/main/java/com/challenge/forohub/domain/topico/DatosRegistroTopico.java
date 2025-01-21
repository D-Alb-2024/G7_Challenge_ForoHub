package com.challenge.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public record DatosRegistroTopico(

        @NotBlank
        String idUsuario,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        LocalDateTime fechaDeCreacion,
        @NotBlank
        String autor,
        @NotNull
        Status status,
        @NotBlank
        String nombreDeCurso,
        @NotNull
        @PositiveOrZero
        Integer respuestas

) {
}
