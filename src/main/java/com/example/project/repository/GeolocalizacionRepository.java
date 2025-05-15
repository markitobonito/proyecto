package com.example.project.repository;

import com.example.project.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GeolocalizacionRepository extends JpaRepository<Geolocalizacion, Integer> {
    Optional<Geolocalizacion> findByCoordinadorAndFechaAndEstado(Usuarios coordinador, Date fecha, EstadoGeo estado);
    List<Geolocalizacion> findByCoordinadorOrderByFechaDesc(Usuarios coordinador);
}