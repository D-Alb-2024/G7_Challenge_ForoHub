package com.challenge.forohub.domain.topico;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {

    ABIERTO,
    CERRADO;

    @JsonCreator
    public static Status fromString(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El estado no puede ser nulo o vacío");
        }
        return Status.valueOf(value.trim().toUpperCase());
    }

}
