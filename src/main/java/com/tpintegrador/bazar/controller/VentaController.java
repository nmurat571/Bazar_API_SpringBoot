/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tpintegrador.bazar.controller;

import com.tpintegrador.bazar.dto.VentaDTO;
import com.tpintegrador.bazar.model.Cliente;
import com.tpintegrador.bazar.model.Producto;
import com.tpintegrador.bazar.model.Venta;
import com.tpintegrador.bazar.service.IProductoService;
import com.tpintegrador.bazar.service.IVentaService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaController {
    
    @Autowired
    IVentaService ventaServ;
    @Autowired
    IProductoService productoServ;
    
    @PostMapping("/venta/crear")
    public String saveVenta(@RequestBody Venta venta)
    {
        List<Producto> listaCompleta = productoServ.getProductosVenta(venta);
        if(productoServ.checkStockVenta(venta))
        {
            ventaServ.saveVenta(venta,listaCompleta);
            return "Costo: " + venta.getTotal() + " fecha: "+ venta.getFechaVenta();
        }
        else
        {
            return "Stock faltante para concretar la venta";
        }
        
    }
    
    @GetMapping("/venta/listar")
    public List<Venta> getVentas()
    {
        List<Venta> listaVentas = new ArrayList<>();
        listaVentas = ventaServ.getVentas();
        return listaVentas;
    }
    
    @GetMapping("/venta/traer")
    public Venta getVenta(@RequestParam Long idVenta)
    {
        Venta venta = ventaServ.getVenta(idVenta);
        return venta;
    }
    
    @DeleteMapping("/venta/borrar")
    public String deleteVenta(@RequestParam Long idVenta)
    {
        ventaServ.deleteVenta(idVenta);
        return "Venta eliminada";
    }
    
//    @PutMapping("/venta/editar/{idOriginal}")
//    public String editVenta(@PathVariable Long idOriginal,
//                           @RequestParam(required = false, name = "idNueva") Long idNueva,
//                           @RequestParam(required = false, name = "fechaNueva") LocalDate fechaNueva,
//                           @RequestParam(required = false, name = "totalNuevo") Double totalNuevo,
//                           @RequestParam(required = false, name = "listaNueva") List<Producto> listaNueva,
//                           @RequestParam(required = false, name = "clienteNuevo") Cliente clienteNuevo)
//    {
//        ventaServ.editVenta(idOriginal, idNueva, fechaNueva, totalNuevo, listaNueva,  clienteNuevo);
//        return "La venta se edito correctamente";
//    }
    
    @PutMapping("/venta/editar")
    public Venta editVenta(@RequestBody Venta venta)
    {
        List<Producto> listaCompleta = productoServ.getProductosVenta(venta);
        Venta editada = ventaServ.editVenta(venta,listaCompleta);
        return editada;
    }
    
    @GetMapping("/venta/items")
    public List<Producto> getItemsVenta(@RequestParam Long idVenta)
    {
        List<Producto> listaItems = ventaServ.getVentaItems(idVenta);
        return listaItems;
    }
    
    @GetMapping("/venta/diaria")
    public String getTotalFecha(@RequestParam LocalDate fecha)
    {
        String ventaDiaria = ventaServ.getVentasDia(fecha);
        return ventaDiaria;
    }
    
    @GetMapping("/venta/maxima")
    public VentaDTO getMaxima()
    {
        VentaDTO maxima = ventaServ.getMaximaVenta();
        return maxima;
    }
}
