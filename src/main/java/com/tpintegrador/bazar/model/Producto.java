/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tpintegrador.bazar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idProducto; 
    private String nombre;
    private String marca;
    private Double costo;
    private Double stock;
    @JsonIgnore
    @ManyToMany (mappedBy = "listaProductos")
    private List<Venta> listaVentas;

    public Producto() {
    }

    public Producto(Long idProducto, String nombre, String marca, Double costo, Double stock, List<Venta> listaVentas) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.stock = stock;
        this.listaVentas = listaVentas;
    }

    
    
}
