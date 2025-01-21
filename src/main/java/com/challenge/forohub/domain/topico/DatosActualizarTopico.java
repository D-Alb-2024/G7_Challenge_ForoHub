package com.challenge.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarTopico(

        @NotBlank
        String mensaje,
        Status status

) {
}
