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
    
    public void calcularTotales() {
        this.subtotal = costoPiscina + costoJuegos + costoZonasSociales + costoAseo;
        this.totalPagar = subtotal - descuento;
    }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public Persona getInquilino() { return inquilino; }
    public void setInquilino(Persona inquilino) { this.inquilino = inquilino; }

    public int getNumAdultos() { return numAdultos; }
    public void setNumAdultos(int numAdultos) { this.numAdultos = numAdultos; }

    public int getNumNinos() { return numNinos; }
    public void setNumNinos(int numNinos) { this.numNinos = numNinos; }

    public int getCostoJuegos() { return costoJuegos; }
    public void setCostoJuegos(int costoJuegos) { this.costoJuegos = costoJuegos; }

    public int getCostoZonasSociales() { return costoZonasSociales; }
    public void setCostoZonasSociales(int costoZonasSociales) { this.costoZonasSociales = costoZonasSociales; }

    public int getCostoAseo() { return costoAseo; }
    public void setCostoAseo(int costoAseo) { this.costoAseo = costoAseo; }

    public int getCostoPiscina() { return costoPiscina; }
    public void setCostoPiscina(int costoPiscina) { this.costoPiscina = costoPiscina; }

    public int getSubtotal() { return subtotal; }
    public void setSubtotal(int subtotal) { this.subtotal = subtotal; }

    public int getDescuento() { return descuento; }
    public void setDescuento(int descuento) { this.descuento = descuento; }

    public int getTotalPagar() { return totalPagar; }
    public void setTotalPagar(int totalPagar) { this.totalPagar = totalPagar; }

    public Persona getDueno() { return dueno; }
    public void setDueno(Persona dueno) { this.dueno = dueno; }

    
}