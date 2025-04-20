package com.example.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "EstadoGeo")
public class EstadoGeo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idEstadoGeo", nullable = false)
    private int idEstadoGeo;

    @Column(name="estado")
    private String estado;
}
