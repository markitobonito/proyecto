package com.example.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "EstadoUsu")
public class EstadoUsu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idEstado", nullable = false)
    private int idEstado;

    @Column(name="estado")
    private String estado;
}
