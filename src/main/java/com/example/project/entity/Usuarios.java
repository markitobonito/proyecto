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
    @Column(name = "idUsuarios", nullable = false)
    private int idUsuarios;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "dni")
    private int dni;

    @Column(name = "correo")
    private String correo;

    @Column(name = "contrasena")
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "rol")
    private Rol rol;

    @ManyToOne
    @JoinColumn(name="estado")
    private EstadoUsu estado;
}
