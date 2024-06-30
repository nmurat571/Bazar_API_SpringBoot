/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tpintegrador.bazar.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class VentaDTO {
    
    private Long idVenta;
    private Double total;
    private int cantidadItems;
    private String nombreCliente;
    private String apellidoCliente;

    public VentaDTO() {
    }

    public VentaDTO(Long idVenta, Double total, int cantidadItems, String nombreCliente, String apellidoCliente) {
        this.idVenta = idVenta;
        this.total = total;
        this.cantidadItems = cantidadItems;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
    }
    
}
