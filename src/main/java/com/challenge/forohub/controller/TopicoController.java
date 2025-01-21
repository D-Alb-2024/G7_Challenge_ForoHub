package com.challenge.forohub.controller;

import com.challenge.forohub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<String> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        // Verificar si ya existe un tópico con el mismo título
        if (topicoRepository.existsByTitulo(datosRegistroTopico.titulo())) {
            return new ResponseEntity<>("El título ya existe.", HttpStatus.BAD_REQUEST);
        }

        // Verificar si ya existe un tópico con el mismo mensaje
        if (topicoRepository.existsByMensaje(datosRegistroTopico.mensaje())) {
            return new ResponseEntity<>("El mensaje ya existe.", HttpStatus.BAD_REQUEST);
        }

        // Crear y guardar el tópico si no existen duplicados
        Topico topico = new Topico(datosRegistroTopico);
        topicoRepository.save(topico);
        return new ResponseEntity<>("Tópico registrado exitosamente", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> listarTopicos(Pageable pageable) {
        // Usar el repositorio con paginación
        Page<DatosRespuestaTopico> topicos = topicoRepository.findAll(pageable)
                .map(topico -> new DatosRespuestaTopico(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaDeCreacion()));

        // Retornar la respuesta con el código de estado HTTP 200 OK y los datos
        return new ResponseEntity<>(topicos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {

        // Verificar si el tópico existe en la base de datos
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isEmpty()) {
            return new ResponseEntity<>("Tópico no encontrado.", HttpStatus.NOT_FOUND);
        }

        // Obtener el tópico actual
        Topico topico = optionalTopico.get();

        // Actualizar los campos
        topico.setMensaje(datosActualizarTopico.mensaje());
        topico.setStatus(datosActualizarTopico.status());

        // Guardar los cambios en la base de datos
        topicoRepository.save(topico);

        return new ResponseEntity<>("Tópico actualizado exitosamente.", HttpStatus.OK);
    }

    // Método para eliminar un tópico por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTopico(@PathVariable Long id) {

        // Verificar si el tópico existe en la base de datos
        Optional<Topico> optionalTopico = topicoRepository.findById(id);

        // Si no existe, devolver un error 404
        if (optionalTopico.isEmpty()) {
            return new ResponseEntity<>("Tópico no encontrado.", HttpStatus.NOT_FOUND);
        }

        // Eliminar el tópico de la base de datos
        topicoRepository.deleteById(id);

        // Confirmar que el tópico fue eliminado
        return new ResponseEntity<>("Tópico eliminado exitosamente.", HttpStatus.NO_CONTENT);
    }

}
