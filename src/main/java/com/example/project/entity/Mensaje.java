package com.example.project.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
@Getter
@Setter
@Entity
@Table(name = "Mensaje")
public class Mensaje {
    @Id
    private int idMensaje;
    private Timestamp horaEnvio;
    private int transmisor;
    private int receptor;
    private String mensaje;
    private int estado;
}
