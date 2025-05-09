package com.example.project.repository.superadmin;

import com.example.project.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SuperAdminRepository extends JpaRepository<Usuarios, Integer> {
    @Query(value="select count(*) from espacio where idEstadoEspacio= ?1",nativeQuery=true)
    int cantidadDeEspaciosPorIdEstado(int idEstadoEspacio);
}
