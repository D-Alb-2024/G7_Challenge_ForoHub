package com.challenge.forohub.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratarErrores {

//    Manejo de errores para recursos no encontrados (404)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404() {
        return ResponseEntity.notFound().build();
    }

    // Manejo de errores de validación de argumentos (400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors()
                .stream()
                .map(DatosErrorValidacion::new)
                .toList();
        return ResponseEntity.badRequest().body(errores);
    }

    // Manejo de errores genéricos no controlados (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarError500(Exception ex) {
        var error = new DatosErrorGenerico("Error inesperado", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }

    // Registro para errores de validación
    private record DatosErrorValidacion(String campo, String mensaje) {
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    // Registro para errores genéricos
    private record DatosErrorGenerico(String titulo, String detalle) {
    }

}
