package com.example.project.controller;
import com.example.project.entity.Usuarios;
import com.example.project.repository.UsuariosRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/")
public class HomeController {
    final UsuariosRepository usuariosRepository;  // Declaración correcta
    // Constructor con inyección de dependencia
    public HomeController(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }
    @GetMapping("/")
    public String mostrarTodosUsuarios(Model model) {
        List<Usuarios> usuarios = usuariosRepository.findAll(); // Obtiene todos los usuarios
        model.addAttribute("usuarios", usuarios); // Pasa la lista a la vista
        return "hola"; // Usa la misma vista para mostrar todos los DNIs
    }
}
