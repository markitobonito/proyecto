package com.example.project.controller;

import com.example.project.entity.Espacio;
import com.example.project.entity.Geolocalizacion;
import com.example.project.repository.EspacioRepository;
import com.example.project.repository.GeolocalizacionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/coordinador")
public class CoordinadorController {

    private final EspacioRepository espacioRepository;
    private final GeolocalizacionRepository geolocalizacionRepository;

    public CoordinadorController(EspacioRepository espacioRepository, GeolocalizacionRepository geolocalizacionRepository) {
        this.espacioRepository = espacioRepository;
        this.geolocalizacionRepository = geolocalizacionRepository;
    }

    @GetMapping("/perfil")
    public String perfil(Model model) {
        List<Espacio> espacios = espacioRepository.findAll();
        model.addAttribute("espacios", espacios);
        return "coordinador/coordinador-perfil";
    }

    @GetMapping("/asistencia")
    public String asistencia(Model model) {
        List<Geolocalizacion> asistencias = geolocalizacionRepository.findAll();
        model.addAttribute("asistencias", asistencias);
        return "coordinador/coordinador-asistencia";
    }
}