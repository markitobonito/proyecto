package com.example.project.dto;

public interface ReservaCoordinadorDto {
    Integer getIdReserva();
    String getFecha();
    String getHoraInicio();
    String getHoraFin();
    Double getCosto();
    String getEspacio();
    String getNombreVecino();
    String getApellidoVecino();
    String getEstado();
}