package com.example.project.controller;

import com.example.project.entity.*;
import com.example.project.repository.EstadoUsuRepository;
import com.example.project.repository.RolRepository;
import com.example.project.repository.UsuariosRepository;
import com.example.project.repository.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EstadoUsuRepository estadoUsuRepository;


    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private MiCuentaAdminRepository adminRepository;

    @Autowired
    private ServiciosDeportivosDisponiblesRepository serviciosRepositoryDisponible;

    @Autowired
    private ServiciosDeportivosRepository serviciosRepository;

    @Autowired
    private EspacioRepository espacioRepository;

    @Autowired
    private EstadoEspacioRepository estadoEspacioRepository;

    @Autowired
    private LugarRepository lugarRepository;

    @Autowired
    private ListaCoordinadoresRepository listaCoordinadoresRepository;

    // Mostrar servicios deportivos activos
    @GetMapping("/servicios_deportivos")
    public String mostrarServicios(Model model) {
        List<ServiciosDeportivosDto> serviciosdeportivos = serviciosRepository.listarServicios();
        model.addAttribute("serviciosdeportivos", serviciosdeportivos);
        return "admin/servicios_deportivos";
    }

    // Mostrar servicios deportivos disponibles
    @GetMapping("/servicios_disponible")
    public String mostrarServiciosActivos(Model model) {
        List<ServiciosDeportivosDisponiblesDto> servicios = serviciosRepositoryDisponible.listarServiciosActivos();
        model.addAttribute("servicios", servicios);
        return "admin/servicios_disponible";
    }

    // Mostrar datos del administrador
    @GetMapping("/mi_cuenta")
    public String mostrarMiCuentaAdmin(Model model) {
        List<MiCuentaAdminDto> admins = adminRepository.obtenerAdmins();
        model.addAttribute("admins", admins);
        return "admin/mi_cuenta";
    }

    @GetMapping("/agregar_servicios")
    public String mostrarFormularioEspacio(Model model) {
        model.addAttribute("espacio", new Espacio());
        List<EstadoEspacio> estados = estadoEspacioRepository.findAll();
        model.addAttribute("estados", estados);
        return "admin/agregar_servicios";
    }

    @PostMapping("/agregar_servicios")
    public String crearEspacio(
            @ModelAttribute Espacio espacio,
            @RequestParam("nombreLugar") String nombreLugar,
            RedirectAttributes redirectAttributes) {

        // Crear y guardar el nuevo Lugar
        Lugar nuevoLugar = new Lugar();
        nuevoLugar.setNombreLugar(nombreLugar);
        lugarRepository.save(nuevoLugar);

        // Asociar el lugar al espacio
        espacio.setIdLugar(nuevoLugar);

        // Guardar el espacio
        espacioRepository.save(espacio);

        redirectAttributes.addFlashAttribute("mensaje", "Espacio creado exitosamente");
        return "redirect:/admin/servicios_disponible";
    }


    @GetMapping("/lista_coordinadores")
    public String listarCoordinadores(Model model) {
        List<ListaCoordinadoresDto> coordinadores = listaCoordinadoresRepository.listarCoordinadores();
        model.addAttribute("coordinadores", coordinadores);  // Agregar los coordinadores al modelo
        return "admin/lista_coordinadores";  // Nombre de la vista Thymeleaf (coordinadores.html)
    }


    @GetMapping("/crear_coordinador")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("coordinador", new Usuarios());
        return "admin/crear_coordinador";
    }

    @PostMapping("/crear_coordinador")
    public String registrarNuevoCoordinador(@ModelAttribute("coordinador") Usuarios coordinador) {
        Rol rolCoordinador = rolRepository.findById(Integer.valueOf(2))
                .orElseThrow(() -> new RuntimeException("Rol con ID 2 no encontrado"));

        EstadoUsu estadoActivo = estadoUsuRepository.findById(Integer.valueOf(1))
                .orElseThrow(() -> new RuntimeException("Estado con ID 1 no encontrado"));

        coordinador.setRol(rolCoordinador);
        coordinador.setEstado(estadoActivo);

        usuariosRepository.save(coordinador);
        return "redirect:/admin/lista_coordinadores";
    }



}