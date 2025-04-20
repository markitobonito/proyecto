package com.example.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "EstadoGeo")
public class EstadoGeo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstadoGeo;

    private String estado;
}
