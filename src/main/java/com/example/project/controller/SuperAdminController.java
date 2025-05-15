package com.example.project.controller;


import com.example.project.repository.ReservasRepository;
import com.example.project.repository.superadmin.SuperAdminRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
        model.addAttribute("ingresoTotalEnLinea", reservasRepository.ingresoTotalEnLinea());
        model.addAttribute("ingresoTotalEnBanco", reservasRepository.ingresoTotalEnBanco());
        /*model.addAttribute("ingresoMes", reservasRepository.ingresoMensual());*/
        List<String> listaMeses = new ArrayList<>();
        List<Integer> listCantidadesMes = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            listCantidadesMes.add(reservasRepository.ingresoMensual(i));
            switch (reservasRepository.intofmonth(i)){
                case 1:
                    listaMeses.add("Ene");
                    break;
                case 2:
                    listaMeses.add("Feb");
                    break;
                case 3:
                    listaMeses.add("Mar");
                    break;
                case 4:
                    listaMeses.add("Abr");
                    break;
                case 5:
                    listaMeses.add("May");
                    break;
                case 6:
                    listaMeses.add("Jun");
                    break;
                case 7:
                    listaMeses.add("Jul");
                    break;
                case 8:
                    listaMeses.add("Ago");
                    break;
                case 9:
                    listaMeses.add("Sep");
                    break;
                case 10:
                    listaMeses.add("Oct");
                    break;
                case 11:
                    listaMeses.add("Nov");
                    break;
                case 12:
                    listaMeses.add("Dic");
                    break;
            }
        }
        model.addAttribute("meses", listaMeses);
        model.addAttribute("cantidadesMes", listCantidadesMes);
        List<String> listaDias = new ArrayList<>();
        List<Integer> listaCantidadesBanco = new  ArrayList<>();
        List<Integer> listaCantidadesEnLinea = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            listaCantidadesBanco.add(reservasRepository.ingresoDiarioEnBanco(i));
            listaCantidadesEnLinea.add(reservasRepository.ingresoDiarioEnLinea(i));
            switch (reservasRepository.intofday(i)){
                case 1:
                    listaDias.add("Dom");
                    break;
                case 2:
                    listaDias.add("Lun");
                    break;
                case 3:
                    listaDias.add("Mar");
                    break;
                case 4:
                    listaDias.add("Mié");
                    break;
                case 5:
                    listaDias.add("Jue");
                    break;
                case 6:
                    listaDias.add("Vie");
                    break;
                case 7:
                    listaDias.add("Sáb");
                    break;
            }
        }
        model.addAttribute("dias", listaDias);
        model.addAttribute("cantidadesBanco", listaCantidadesBanco);
        model.addAttribute("cantidadesEnLinea", listaCantidadesEnLinea);

        return "/superadmin/casa";
    }

    @PostMapping(value = "/en")
    public String habilita(@RequestParam("id") int id,@RequestParam("st") int st){
        superAdminRepository.actualizarEstadoUsuario(id, st);
        return "redirect:/superadmin";
    }
}
