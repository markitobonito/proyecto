package com.example.project.controller;
import com.example.project.entity.Usuarios;
import com.example.project.repository.EstadoUsuRepository;
import com.example.project.repository.RolRepository;
import com.example.project.repository.UsuariosRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/vecino")
public class VecinoController {
    final UsuariosRepository usuariosRepository;
    final RolRepository rolRepository;
    final EstadoUsuRepository estadoRepository;// Declaración correcta
    // Constructor con inyección de dependencia
    public VecinoController(UsuariosRepository usuariosRepository,
                            RolRepository rolRepository,
                            EstadoUsuRepository estadoRepository) {
        this.usuariosRepository = usuariosRepository;
        this.rolRepository      = rolRepository;
        this.estadoRepository   = estadoRepository;
    }
    @GetMapping("/")
    public String principal(Model model) {
        List<Usuarios> usuarios = usuariosRepository.findAll(); // Obtiene todos los usuarios
        model.addAttribute("usuarios", usuarios); // Pasa la lista a la vista
        return "vecino/principal"; // Usa la misma vista para mostrar todos los DNIs
    }



}
