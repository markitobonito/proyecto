package com.example.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name="Usuarios")
public class Usuarios {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="idUsuarios", nullable = false)
    private int idUsuarios;

    @Column(name="nombres")
    private String nombres;

    @Column(name="apellidos")
    private String apellidos;

    @Column(name="dni")
    private int dni;

    @Column(name="correo")
    private String correo;

    @Column(name="contrasena")
    private String contrasena;

    @Column(name="rol", nullable = false)
    private int rol;

    @Column(name="estado", nullable = false)
    private int estado;
}
