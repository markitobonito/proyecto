package com.example.project.controller;

import com.example.project.repository.ReservasRepository;
import com.example.project.repository.superadmin.SuperAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/superadmin")
public class SuperAdminController {

    @Autowired
    SuperAdminRepository superAdminRepository;

    @Autowired
    ReservasRepository reservasRepository;

    @GetMapping(value = {"", "/home"})
    public String home(Model model) {
        model.addAttribute("cantidadActivos", superAdminRepository.cantidadDeEspaciosPorIdEstado(1));
        model.addAttribute("cantidadEnMantenimiento", superAdminRepository.cantidadDeEspaciosPorIdEstado(2));
        model.addAttribute("cantidadPrestados", superAdminRepository.cantidadDeEspaciosPorIdEstado(3));
        model.addAttribute("cantidadCerrados", superAdminRepository.cantidadDeEspaciosPorIdEstado(4));
        model.addAttribute("cantidadTotal", superAdminRepository.count());
        model.addAttribute("listaUsuarios", superAdminRepository.findAll());
        model.addAttribute("listaReservas", reservasRepository.findAll());
        model.addAttribute("totalReservas", reservasRepository.count());
        model.addAttribute("ingresoTotal", reservasRepository.ingresoTotal());
        model.addAttribute("ingresoMes", reservasRepository.ingresoMensual());
        return "/superadmin/casa";
    }

}
