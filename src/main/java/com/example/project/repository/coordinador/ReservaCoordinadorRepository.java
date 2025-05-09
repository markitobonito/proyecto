package com.example.project.repository.coordinador;

import com.example.project.dto.ReservaCoordinadorDto;
import com.example.project.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaCoordinadorRepository extends JpaRepository<Reserva, Integer> {

    @Query(value = """
        SELECT 
            r.idReserva AS IdReserva,
            r.fecha AS Fecha,
            r.horaInicio AS HoraInicio,
            r.horaFin AS HoraFin,
            r.costo AS Costo,
            e.nombre AS Espacio,
            u.nombres AS NombreVecino,
            u.apellidos AS ApellidoVecino,
            er.estado AS Estado
        FROM Reserva r
        JOIN Usuarios u ON r.vecino = u.idUsuarios
        JOIN Espacio e ON r.espacio = e.idEspacio
        JOIN EstadoReserva er ON r.estado = er.idEstadoReserva
        WHERE r.coordinador = :coordinadorId
        """, nativeQuery = true)
    List<ReservaCoordinadorDto> obtenerReservasPorCoordinador(Integer coordinadorId);
}