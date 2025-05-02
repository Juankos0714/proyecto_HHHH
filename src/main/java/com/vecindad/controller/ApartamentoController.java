package com.vecindad.controller;

import com.vecindad.model.Apartamento;
import com.vecindad.service.ApartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ApartamentoController {

    @Autowired
    private ApartamentoService apartamentoService;

    @GetMapping("/")
    public String index(Model model) {
        // Verificar si hay datos y, si no, crear datos iniciales
        List<Apartamento> apartamentos = apartamentoService.obtenerTodosApartamentos();
        if (apartamentos.isEmpty()) {
            apartamentos = apartamentoService.crearDatosIniciales();
            apartamentoService.calcularCostos();
        }
        
        model.addAttribute("apartamentos", apartamentos);
        return "index";
    }

    @GetMapping("/administrar")
    public String administrar(Model model) {
        List<Apartamento> apartamentos = apartamentoService.obtenerTodosApartamentos();
        model.addAttribute("apartamentos", apartamentos);
        return "administrar";
    }

    @GetMapping("/base-datos")
    public String baseDatos(Model model) {
        List<Apartamento> apartamentos = apartamentoService.obtenerTodosApartamentos();
        model.addAttribute("apartamentos", apartamentos);
        return "base-datos";
    }

    @PostMapping("/calcular-costos")
    @ResponseBody
    public String calcularCostos() {
        apartamentoService.calcularCostos();
        return "Costos calculados correctamente";
    }

    @PostMapping("/guardar-apartamento")
    @ResponseBody
    public String guardarApartamento(@RequestBody Apartamento apartamento) {
        apartamentoService.guardarApartamento(apartamento);
        return "Apartamento guardado correctamente";
    }

    @GetMapping("/api/apartamentos")
    @ResponseBody
    public List<Apartamento> obtenerApartamentosApi() {
        return apartamentoService.obtenerTodosApartamentos();
    }

    @PostMapping("/crear-datos-iniciales")
    @ResponseBody
    public String crearDatosIniciales() {
        apartamentoService.crearDatosIniciales();
        apartamentoService.calcularCostos();
        return "Datos iniciales creados correctamente";
    }
}