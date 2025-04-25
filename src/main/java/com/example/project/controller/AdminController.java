package com.example.project.controller;

import com.example.project.repository.admin.*;
import com.example.project.repository.admin.ServiciosDeportivosDisponiblesDto;
import com.example.project.repository.admin.ServiciosDeportivosDisponiblesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private MiCuentaAdminRepository adminRepository;

    @Autowired
    private ServiciosDeportivosDisponiblesRepository serviciosRepositoryDisponible;

    @Autowired
    private ServiciosDeportivosRepository ServiciosRepository;

    // Mostrar servicios deportivos activos
    @GetMapping("/admin/servicios_deportivos")
    public String mostrarServicios(Model model) {
        List<ServiciosDeportivosDto> serviciosdeportivos = ServiciosRepository.listarServicios();
        model.addAttribute("serviciosdeportivos", serviciosdeportivos);
        return "admin/servicios_deportivos"; // Asegúrate de tener esta vista en templates/admin
    }



    // Mostrar servicios deportivos activos
    @GetMapping("/admin/servicios_disponible")
    public String mostrarServiciosActivos(Model model) {
        List<ServiciosDeportivosDisponiblesDto> servicios = serviciosRepositoryDisponible.listarServiciosActivos();
        model.addAttribute("servicios", servicios);
        return "admin/servicios_disponible"; // Asegúrate de tener esta vista en templates/admin
    }

    // Mostrar datos del administrador
    @GetMapping("/admin/mi_cuenta")
    public String mostrarMiCuentaAdmin(Model model) {
        List<MiCuentaAdminDto> admins = adminRepository.obtenerAdmins();
        model.addAttribute("admins", admins);
        return "admin/mi_cuenta";
    }
}
