package com.example.project.controller;

import com.example.project.entity.*;
import com.example.project.repository.ActividadRepository;
import com.example.project.repository.UsuariosRepository;
import com.example.project.repository.GeolocalizacionRepository;
import com.example.project.repository.EstadoGeoRepository;
import com.example.project.repository.EspacioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Controller
@RequestMapping("/coordinador")
public class CoordinadorController {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private EspacioRepository espacioRepository;

    @Autowired
    private GeolocalizacionRepository geolocalizacionRepository;

    @Autowired
    private EstadoGeoRepository estadoGeoRepository;

    @GetMapping("/perfil")
    public String showCoordinadorProfile(Model model, HttpSession session) {
        Usuarios coordinador = (Usuarios) session.getAttribute("loggedUser");
        if (coordinador == null || !"Coordinador".equals(coordinador.getRol().getRol())) {
            return "redirect:/login";
        }

        List<Actividad> actividades = actividadRepository.findByUsuarioOrderByFechaDesc(coordinador);
        model.addAttribute("coordinador", coordinador);
        model.addAttribute("actividades", actividades);
        return "coordinador-perfil-2";
    }

    @GetMapping("/espacios")
    public String showEspacios(Model model, HttpSession session) {
        Usuarios coordinador = (Usuarios) session.getAttribute("loggedUser");
        if (coordinador == null || !"Coordinador".equals(coordinador.getRol().getRol())) {
            return "redirect:/login";
        }

        List<Espacio> espacios = espacioRepository.findAll();
        model.addAttribute("coordinador", coordinador);
        model.addAttribute("espacios", espacios);
        return "coordinador-tabla-espacios2";
    }

    @PostMapping("/updateEstado")
    public String updateEstado(@RequestParam("idEspacio") int idEspacio, @RequestParam("nuevoEstado") String nuevoEstado, Model model, HttpSession session) {
        Usuarios coordinador = (Usuarios) session.getAttribute("loggedUser");
        if (coordinador == null || !"Coordinador".equals(coordinador.getRol().getRol())) {
            return "redirect:/login";
        }

        Espacio espacio = espacioRepository.findById(idEspacio).orElse(null);
        if (espacio != null) {
            EstadoEspacio estadoEspacio = new EstadoEspacio();
            estadoEspacio.setEstado(nuevoEstado);
            espacio.setIdEstadoEspacio(estadoEspacio);
            espacioRepository.save(espacio);
        }

        return "redirect:/coordinador/espacios";
    }

    @PostMapping("/addObservacion")
    public String addObservacion(@RequestParam("idEspacio") int idEspacio, @RequestParam("observacion") String observacion, Model model, HttpSession session) {
        Usuarios coordinador = (Usuarios) session.getAttribute("loggedUser");
        if (coordinador == null || !"Coordinador".equals(coordinador.getRol().getRol())) {
            return "redirect:/login";
        }

        Espacio espacio = espacioRepository.findById(idEspacio).orElse(null);
        if (espacio != null) {
            String observacionesExistentes = espacio.getObservaciones() != null ? espacio.getObservaciones() : "";
            espacio.setObservaciones(observacionesExistentes + "\n" + observacion + " (Agregado el " + java.time.LocalDate.now() + ")");
            espacioRepository.save(espacio);
        }

        return "redirect:/coordinador/espacios";
    }

    @GetMapping("/asistencia")
    public String showAsistencia(Model model, HttpSession session) {
        Usuarios coordinador = (Usuarios) session.getAttribute("loggedUser");
        if (coordinador == null || !"Coordinador".equals(coordinador.getRol().getRol())) {
            return "redirect:/login";
        }

        List<Geolocalizacion> asistencias = geolocalizacionRepository.findByCoordinadorOrderByFechaDesc(coordinador);
        model.addAttribute("coordinador", coordinador);
        model.addAttribute("asistencias", asistencias);
        return "coordinador-asistencia-2";
    }

    @PostMapping("/marcarAsistencia")
    public String marcarAsistencia(@RequestParam("latitud") String latitud,
                                   @RequestParam("longitud") String longitud,
                                   @RequestParam(value = "observacion", required = false) String observacion,
                                   HttpSession session) {
        Usuarios coordinador = (Usuarios) session.getAttribute("loggedUser");
        if (coordinador == null || !"Coordinador".equals(coordinador.getRol().getRol())) {
            return "redirect:/login";
        }

        // Check if there's an open attendance record (no exit time)
        Optional<Geolocalizacion> openAttendance = geolocalizacionRepository.findTopByCoordinadorAndHoraFinIsNullOrderByFechaDesc(coordinador);

        if (openAttendance.isPresent()) {
            Geolocalizacion asistencia = openAttendance.get();
            asistencia.setHoraFin(Time.valueOf(LocalTime.now()));
            EstadoGeo estadoCompleto = estadoGeoRepository.findByEstado("Asistencia Completa")
                    .orElseGet(() -> {
                        EstadoGeo nuevoEstado = new EstadoGeo();
                        nuevoEstado.setEstado("Asistencia Completa");
                        return estadoGeoRepository.save(nuevoEstado);
                    });
            asistencia.setEstado(estadoCompleto);
            geolocalizacionRepository.save(asistencia);
        } else {
            // Mark entry
            Geolocalizacion asistencia = new Geolocalizacion();
            asistencia.setFecha(Date.valueOf(LocalDate.now()));
            asistencia.setHoraInicio(Time.valueOf(LocalTime.now()));
            asistencia.setCoordinador(coordinador);
            asistencia.setLugarExacto(latitud + "," + longitud);
            asistencia.setObservacion(observacion);

            Espacio espacio = espacioRepository.findAll().stream().findFirst().orElse(null);
            asistencia.setEspacio(espacio);

            EstadoGeo estadoEntrada = estadoGeoRepository.findByEstado("Entrada Registrada")
                    .orElseGet(() -> {
                        EstadoGeo nuevoEstado = new EstadoGeo();
                        nuevoEstado.setEstado("Entrada Registrada");
                        return estadoGeoRepository.save(nuevoEstado);
                    });
            asistencia.setEstado(estadoEntrada);
            geolocalizacionRepository.save(asistencia);
        }

        return "redirect:/coordinador/asistencia";
    }

}