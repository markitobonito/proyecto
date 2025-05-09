package com.example.project.repository;

import com.example.project.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservasRepository extends JpaRepository<Reserva, Integer> {
    @Query(value="select sum(costo) as total from reserva where estado=1;",nativeQuery=true)
    int ingresoTotal();
    @Query(value="    select sum(costo) as total from reserva where estado=1 and extract(MONTH FROM curdate())=extract(MONTH FROM fecha) and extract(YEAR FROM curdate())=extract(YEAR FROM fecha);",nativeQuery=true)
    int ingresoMensual();
}
