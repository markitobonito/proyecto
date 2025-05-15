package com.example.project.repository;

import com.example.project.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeolocalizacionRepository extends JpaRepository<Geolocalizacion, Integer> {
    List<Geolocalizacion> findByCoordinadorOrderByFechaDesc(Usuarios coordinador);
    Optional<Geolocalizacion> findTopByCoordinadorAndHoraFinIsNullOrderByFechaDesc(Usuarios coordinador);
}