package com.example.project.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Espacio")
public class Espacio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idEspacio", nullable = false)
    private int idEspacio;
    @Column(name="nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name="idLugar")
    private Lugar idLugar;

    @ManyToOne
    @JoinColumn(name="idEstadoEspacio")
    private EstadoEspacio idEstadoEspacio;

    @Column(name="tipo")
    private String tipo;
}
