package com.example.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@Entity
@Table(name = "Geolocalizacion")
public class Geolocalizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGeolocalizacion;

    private Date fecha;
    private Time horaInicio;
    private Time horaFin;
    private int coordinador;
    private int espacio;
    private String lugarExacto;
    private String observacion;
    private int estado;
}
