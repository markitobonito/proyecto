package com.example.project.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "EstadoMensaje")
public class EstadoMensaje {
    @Id
    private int idEstadoMensaje;

    private String estado;
}
