package com.example.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "Reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReserva;

    private Time horaInicio;
    private Time horaFin;
    private Date fecha;
    private int coordinador;
    private Double costo;
    private int vecino;
    private int estado;
    private int espacio;

    @Lob
    private byte[] captura;

    private Timestamp momentoReserva;
}
