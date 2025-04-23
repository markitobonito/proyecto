package com.example.project.repository;

import com.example.project.entity.EstadoUsu;

public interface EstadoUsuRepository {
    EstadoUsu findByNombre(String nombre);
}
