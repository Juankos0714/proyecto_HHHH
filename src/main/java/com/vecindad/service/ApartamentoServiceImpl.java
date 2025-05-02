package com.vecindad.service;

import com.vecindad.model.Apartamento;
import com.vecindad.repository.ApartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApartamentoServiceImpl implements ApartamentoService {

    private static final int COSTO_PISCINA_POR_ADULTO = 2000;
    private static final int COSTO_JUEGOS = 5000;
    private static final int COSTO_ZONAS_SOCIALES = 10000;
    private static final int COSTO_ASEO = 15000;
    private static final double PORCENTAJE_DESCUENTO = 0.20;
    private static final int COSTO_FIJO = 50000;

    @Autowired
    private ApartamentoRepository apartamentoRepository;

    @Override
    public List<Apartamento> obtenerTodosApartamentos() {
        return apartamentoRepository.findAll();
    }

    @Override
    public Apartamento obtenerApartamentoPorNumero(String numero) {
        return apartamentoRepository.findById(numero).orElse(null);
    }

    @Override
    public List<Apartamento> crearDatosIniciales() {
        List<Apartamento> apartamentos = new ArrayList<>();

        apartamentos.add(new Apartamento("102", "María", "Ana", 2, 2));
        apartamentos.add(new Apartamento("303", "José", "José", 3, 2));
        apartamentos.add(new Apartamento("202", "Jesús", "Beto", 1, 0));
        apartamentos.add(new Apartamento("203", "David", "Carlos", 4, 0));
        apartamentos.add(new Apartamento("302", "Daniel", "Daniel", 3, 0));
        apartamentos.add(new Apartamento("103", "Moisés", "Federico", 1, 1));
        apartamentos.add(new Apartamento("401", "Eustaquio", "Zulia", 1, 2));
        apartamentos.add(new Apartamento("402", "Sacarías", "Sacarías", 2, 1));
        apartamentos.add(new Apartamento("403", "Mateo", "", 0, 0));
        apartamentos.add(new Apartamento("501", "Marcos", "Gonzalo", 2, 1));
        apartamentos.add(new Apartamento("502", "Jesús", "", 0, 0));

        apartamentoRepository.saveAll(apartamentos);
        
        return apartamentos;
    }

    @Override
    public void calcularCostos() {
        List<Apartamento> apartamentos = apartamentoRepository.findAll();
        
        for (Apartamento apto : apartamentos) {
            boolean estaOcupado = apto.getInquilino() != null && !apto.getInquilino().isEmpty();

            if (estaOcupado) {

                apto.setCostoPiscina(apto.getNumAdultos() * COSTO_PISCINA_POR_ADULTO);

                if (apto.getNumNinos() > 0) {
                    apto.setCostoJuegos(COSTO_JUEGOS);
                } else {
                    apto.setCostoJuegos(0);
                }

                apto.setCostoZonasSociales(COSTO_ZONAS_SOCIALES);

                String piso = apto.getNumero().substring(0, 1);
                if (piso.equals("1") || piso.equals("2")) {
                    apto.setCostoAseo(COSTO_ASEO);
                } else {
                    apto.setCostoAseo(0);
                }

                int subtotal = apto.getCostoPiscina() + apto.getCostoJuegos() +
                        apto.getCostoZonasSociales() + apto.getCostoAseo();
                apto.setSubtotal(subtotal);

                if (apto.getDueno().equals(apto.getInquilino())) {
                    apto.setDescuento((int)(subtotal * PORCENTAJE_DESCUENTO));
                } else {
                    apto.setDescuento(0);
                }
                apto.setTotalPagar(COSTO_FIJO + subtotal - apto.getDescuento());
            } else {
                apto.setCostoPiscina(0);
                apto.setCostoJuegos(0);
                apto.setCostoZonasSociales(0);
                apto.setCostoAseo(0);
                apto.setSubtotal(0);
                apto.setDescuento(0);
                apto.setTotalPagar(COSTO_FIJO);
            }
        }
        
        apartamentoRepository.saveAll(apartamentos);
    }

    @Override
    public void guardarApartamento(Apartamento apartamento) {
        apartamentoRepository.save(apartamento);
    }

    @Override
    public void guardarTodosApartamentos(List<Apartamento> apartamentos) {
        apartamentoRepository.saveAll(apartamentos);
    }
}