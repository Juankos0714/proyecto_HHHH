package com.vecindad.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apartamento {
    
    @Id
    private String numero;
    private String dueno;
    private String inquilino;
    private int numAdultos;
    private int numNinos;
    private int costoPiscina;
    private int costoJuegos;
    private int costoZonasSociales;
    private int costoAseo;
    private int subtotal;
    private int descuento;
    private int totalPagar;
    
    public Apartamento(String numero, String dueno, String inquilino, int numAdultos, int numNinos) {
        this.numero = numero;
        this.dueno = dueno;
        this.inquilino = inquilino != null ? inquilino : "";
        this.numAdultos = numAdultos;
        this.numNinos = numNinos;
        this.costoPiscina = 0;
        this.costoJuegos = 0;
        this.costoZonasSociales = 0;
        this.costoAseo = 0;
        this.subtotal = 0;
        this.descuento = 0;
        this.totalPagar = 0;
    }
}