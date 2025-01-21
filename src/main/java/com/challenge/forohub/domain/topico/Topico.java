package com.challenge.forohub.domain.topico;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode (of = "id")
public class Topico {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String idUsuario;
    @Column(nullable = false, unique = true)
    private String titulo;
    @Column(nullable = false, unique = true)
    private String mensaje;
    @Column(nullable = false)
    private LocalDateTime fechaDeCreacion;
    @Column(nullable = false)
    private String autor;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    @Column(nullable = false)
    private String nombreDeCurso;
    @Column(nullable = false)
    private Integer respuestas;

    public Topico (){}

    public Topico(DatosRegistroTopico datosRegistroTopico) {

        this.idUsuario = datosRegistroTopico.idUsuario();
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fechaDeCreacion = datosRegistroTopico.fechaDeCreacion();
        this.autor = datosRegistroTopico.autor();
        this.status = Status.fromString(datosRegistroTopico.status().toString());  // Convertir status al enum Status
        this.nombreDeCurso = datosRegistroTopico.nombreDeCurso();
        this.respuestas = datosRegistroTopico.respuestas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNombreDeCurso() {
        return nombreDeCurso;
    }

    public void setNombreDeCurso(String nombreDeCurso) {
        this.nombreDeCurso = nombreDeCurso;
    }

    public Integer getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Integer respuestas) {
        this.respuestas = respuestas;
    }
}
