package com.example.project.controller;

import com.example.project.dto.EspacioDto;
import com.example.project.entity.Geolocalizacion;
import com.example.project.repository.EspacioRepository;
import com.example.project.repository.GeolocalizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/coordinador/espacios")
public class EspacioController {

    @Autowired
    private EspacioRepository espacioRepository;

    @Autowired
    private GeolocalizacionRepository geolocalizacionRepository;

    @GetMapping
    public String mostrarEspacios(Model model) {
        List<EspacioDto> espacios = espacioRepository.obtenerEspacios();
        model.addAttribute("espacios", espacios);
        return "coordinador-tabla-espacios";
    }

    @GetMapping("/cambiarEstado")
    public String cambiarEstado(@RequestParam("idEspacio") Integer idEspacio, @RequestParam("estado") String estado) {
        Integer idEstadoEspacio = mapEstadoToId(estado); 
        espacioRepository.actualizarEstado(idEspacio, idEstadoEspacio);
        return "redirect:/coordinador/espacios";
    }

    @PostMapping("/agregarObservacion")
    public String agregarObservacion(@RequestParam("idEspacio") Integer idEspacio, 
                                    @RequestParam("observacion") String observacion,
                                    Authentication authentication) {
        Geolocalizacion geo = new Geolocalizacion();
        geo.setEspacio(idEspacio);
        geo.setCoordinador(obtenerIdCoordinador(authentication));
        geo.setFecha(LocalDate.now());
        geo.setHoraInicio(LocalTime.now());
        geo.setObservacion(observacion);
        geo.setEstado(1);
        geolocalizacionRepository.save(geo);
        return "redirect:/coordinador/espacios";
    }

    private Integer mapEstadoToId(String estado) {
        return switch (estado) {
            case "Disponible" -> 1;
            case "Ocupado" -> 2;
            case "Mantenimiento" -> 3;
            default -> 1;
        };
    }

    private Integer obtenerIdCoordinador(Authentication authentication) {

        return 1;
    }
}