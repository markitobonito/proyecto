package com.example.project.repository.admin;

import com.example.project.entity.Espacio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspacioRepository extends JpaRepository<Espacio, Integer> {
    long countByTipo(String tipo);
}
