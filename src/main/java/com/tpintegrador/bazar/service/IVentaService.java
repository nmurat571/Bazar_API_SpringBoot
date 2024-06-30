/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tpintegrador.bazar.service;

import com.tpintegrador.bazar.dto.VentaDTO;
import com.tpintegrador.bazar.model.Cliente;
import com.tpintegrador.bazar.model.Producto;
import com.tpintegrador.bazar.model.Venta;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author natal
 */
public interface IVentaService {
    
    public void saveVenta(Venta venta, List<Producto>listaProductosCompleta);
    public void saveVenta(Venta venta);
    public List<Venta> getVentas();
    public Venta getVenta(Long idVenta);
    public void deleteVenta(Long idVenta);
//    public void editVenta(Long idOriginal, Long idNueva, LocalDate fechaNueva,
//                         Double nuevoTotal, List<Producto>listaProductos,
//                         Cliente nuevoCliente);//
    public Venta editVenta(Venta venta, List<Producto>listaItemsCompleta);
    public List<Producto> getVentaItems(Long idVenta);
    public String getVentasDia(LocalDate fecha);
    public VentaDTO getMaximaVenta();
}
