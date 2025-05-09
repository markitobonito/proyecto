package com.example.project.repository;

import com.example.project.dto.EspacioDto;
import com.example.project.entity.Espacio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspacioRepository extends JpaRepository<Espacio, Integer> {

    @Query(value = """
        SELECT 
            e.idEspacio AS IdEspacio,
            e.nombre AS Nombre,
            l.lugar AS Lugar,
            ee.estado AS Estado,
            e.tipo AS Tipo
        FROM Espacio e
        JOIN Lugar l ON e.idLugar = l.idLugar
        JOIN EstadoEspacio ee ON e.idEstadoEspacio = ee.idEstadoEspacio
        """, nativeQuery = true)
    List<EspacioDto> obtenerEspacios();
}

@Modifying
@Query("UPDATE Espacio e SET e.idEstadoEspacio = :estadoId WHERE e.idEspacio = :espacioId")
void actualizarEstado(Integer espacioId, Integer estadoId);