package com.vecindad.controller;

import com.vecindad.model.Apartamento;
import com.vecindad.service.ApartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApartamentoRestController {

    @Autowired
    private ApartamentoService apartamentoService;

    @GetMapping("/apartamentos")
    public List<Apartamento> obtenerTodosApartamentos() {
        return apartamentoService.obtenerTodosApartamentos();
    }

    @GetMapping("/apartamentos/{numero}")
    public ResponseEntity<Apartamento> obtenerApartamentoPorNumero(@PathVariable String numero) {
        Apartamento apartamento = apartamentoService.obtenerApartamentoPorNumero(numero);
        if (apartamento != null) {
            return ResponseEntity.ok(apartamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/apartamentos")
    public ResponseEntity<Apartamento> crearApartamento(@RequestBody Apartamento apartamento) {
        apartamentoService.guardarApartamento(apartamento);
        return ResponseEntity.ok(apartamento);
    }

    @PutMapping("/apartamentos/{numero}")
    public ResponseEntity<Apartamento> actualizarApartamento(
            @PathVariable String numero,
            @RequestBody Apartamento apartamento) {
        
        Apartamento existente = apartamentoService.obtenerApartamentoPorNumero(numero);
        if (existente != null) {
            apartamento.setNumero(numero);
            apartamentoService.guardarApartamento(apartamento);
            return ResponseEntity.ok(apartamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/apartamentos/{numero}")
    public ResponseEntity<Void> eliminarApartamento(@PathVariable String numero) {
        Apartamento existente = apartamentoService.obtenerApartamentoPorNumero(numero);
        if (existente != null) {
            apartamentoService.guardarApartamento(null);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/calcular-costos")
    public ResponseEntity<String> calcularCostos() {
        apartamentoService.calcularCostos();
        return ResponseEntity.ok("Costos calculados correctamente");
    }

    @PostMapping("/crear-datos-iniciales")
    public ResponseEntity<List<Apartamento>> crearDatosIniciales() {
        List<Apartamento> apartamentos = apartamentoService.crearDatosIniciales();
        apartamentoService.calcularCostos();
        return ResponseEntity.ok(apartamentos);
    }
}