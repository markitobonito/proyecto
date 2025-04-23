package com.example.project.controller;
import com.example.project.entity.Usuarios;
import com.example.project.repository.EstadoUsuRepository;
import com.example.project.repository.RolRepository;
import com.example.project.repository.UsuariosRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/Usuarios")
public class HomeController {
    final UsuariosRepository usuariosRepository;
    final RolRepository rolRepository;
    final EstadoUsuRepository estadoRepository;// Declaración correcta
    // Constructor con inyección de dependencia
    public HomeController(UsuariosRepository usuariosRepository,
     RolRepository rolRepository,
                          EstadoUsuRepository estadoRepository) {
        this.usuariosRepository = usuariosRepository;
        this.rolRepository      = rolRepository;
        this.estadoRepository   = estadoRepository;
    }
    @GetMapping("/")
    public String mostrarTodosUsuarios(Model model) {
        List<Usuarios> usuarios = usuariosRepository.findAll(); // Obtiene todos los usuarios
        model.addAttribute("usuarios", usuarios); // Pasa la lista a la vista
        return "hola"; // Usa la misma vista para mostrar todos los DNIs
    }
    @PostMapping("/")
    public String logueo(@RequestParam String username,
                         @RequestParam String password,
                         Model model,
                         HttpSession session
    ) {
        // Intentar por correo
        Optional<Usuarios> opt = usuariosRepository.findByCorreoAndContrasena(username, password);
        // Si no, intentar por DNI (si es numérico)
        if (opt.isEmpty()) {
            try {
                int dni = Integer.parseInt(username);
                opt = usuariosRepository.findByDniAndContrasena(dni, password);
            } catch (NumberFormatException e) { }
        }

        if (opt.isPresent()) {
            Usuarios user = opt.get();
            session.setAttribute("loggedUser", user);

            // Redirigir según el rol
            String rolName = user.getRol().getRol();
            switch (rolName) {
                case "admininstrador":   return "redirect:/administrador/dashboard";
                case "vecino": return "redirect:/vecino/home";
                case "superadmin": return "redirect:/superadmin/home";
                case "coordinador":return "redirect:/superadmin/coordinador";
                default:
                    return "redirect:/";
            }
        }

        // Falló autenticación
        model.addAttribute("error", "Credenciales inválidas");
        return "login";

    }

    @GetMapping("/registro")
    public String mostrarFormRegistro(Model model) {
        model.addAttribute("usuario", new Usuarios());
        return "registro";        // tu registro.html
    }
    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute Usuarios usuario) {
        // fijamos rol y estado por defecto:
        usuario.setRol( rolRepository.findByRol("vecino") );
        usuario.setEstado( estadoRepository.findByNombre("activo") );
        usuariosRepository.save(usuario);
        return "redirect:/login";  // lo lleva a tu login.html
    }

}
