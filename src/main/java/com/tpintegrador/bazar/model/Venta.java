/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tpintegrador.bazar.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Venta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idVenta;
    private LocalDate fechaVenta;
    private Double total;
    @JoinTable(name = "rel_venta_prod",
                joinColumns = @JoinColumn(name = "fk_venta", nullable = false),
                inverseJoinColumns = @JoinColumn(name = "fk_prod", nullable = false))
    @ManyToMany
    private List<Producto> listaProductos;
    @ManyToOne
    @JoinColumn(name ="idCliente")
    private Cliente unCliente;

    public Venta() {
    }

    public Venta(Long idVenta, LocalDate fechaVenta, Double total, List<Producto> listaProductos, Cliente unCliente) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.listaProductos = listaProductos;  
        this.unCliente = unCliente;
    }
    
    
}
