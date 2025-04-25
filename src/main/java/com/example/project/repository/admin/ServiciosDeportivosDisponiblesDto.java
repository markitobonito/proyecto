package com.example.project.repository.admin;

import org.springframework.stereotype.Repository;


public interface ServiciosDeportivosDisponiblesDto {
    Integer getId();            // r.idReserva AS ID
    String getTipo();           // e.tipo AS Tipo
    String getLugar();          // l.lugar AS Lugar
    Double getCosto();          // r.costo AS Costo
    String getEstadoEspacio();  // ee.estado AS EstadoEspacio
}
