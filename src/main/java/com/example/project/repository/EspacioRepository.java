package com.example.project.repository;

import com.example.project.entity.Espacio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspacioRepository extends JpaRepository<Espacio, Integer> {
}