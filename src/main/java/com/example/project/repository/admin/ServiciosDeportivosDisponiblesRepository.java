package com.example.project.repository.admin;

import com.example.project.entity.Espacio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiciosDeportivosDisponiblesRepository extends JpaRepository<Espacio, Integer> {

    @Query(value = """
        SELECT 
            r.idReserva AS id,
            e.tipo AS tipo,
            l.lugar AS lugar,
            r.costo AS costo,
            ee.estado AS estadoEspacio
        FROM 
            Reserva r
        JOIN Espacio e ON r.espacio = e.idEspacio
        JOIN Lugar l ON e.idLugar = l.idLugar
        JOIN EstadoEspacio ee ON e.idEstadoEspacio = ee.idEstadoEspacio
        WHERE ee.estado = 'Activo'
    """, nativeQuery = true)
    List<ServiciosDeportivosDisponiblesDto> listarServiciosActivos();
}
