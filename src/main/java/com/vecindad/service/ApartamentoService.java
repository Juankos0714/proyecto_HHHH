package com.vecindad.service;

import com.vecindad.model.Apartamento;
import java.util.List;

public interface ApartamentoService {
    
    List<Apartamento> obtenerTodosApartamentos();
    
    Apartamento obtenerApartamentoPorNumero(String numero);
    
    List<Apartamento> crearDatosIniciales();
    
    void calcularCostos();
    
    void guardarApartamento(Apartamento apartamento);
    
    void guardarTodosApartamentos(List<Apartamento> apartamentos);
}