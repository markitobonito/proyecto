package com.example.project.repository.coordinador;

import com.example.project.dto.MiCuentaCoordinadorDto;
import com.example.project.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiCuentaCoordinadorRepository extends JpaRepository<Usuarios, Integer> {

    @Query(value = """
        SELECT 
            u.nombres AS Nombre,
            u.apellidos AS Apellidos,
            eu.estado AS Estado,
            r.rol AS Rol,
            u.dni AS Dni,
            u.correo AS Email
        FROM Usuarios u
        JOIN Rol r ON u.rol = r.idRol
        JOIN EstadoUsu eu ON u.estado = eu.idEstado
        WHERE r.rol = 'Coordinador'
        """, nativeQuery = true)
    List<MiCuentaCoordinadorDto> obtenerCoordinadores();
}