/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tpintegrador.bazar.service;

import com.tpintegrador.bazar.dto.VentaDTO;
import com.tpintegrador.bazar.model.Cliente;
import com.tpintegrador.bazar.model.Producto;
import com.tpintegrador.bazar.model.Venta;
import com.tpintegrador.bazar.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{
    
    @Autowired
    IVentaRepository ventaRepo;

    @Override
    public void saveVenta(Venta venta, List<Producto> listaProductos) {
        
        Venta v = this.checkTotalVenta(venta, listaProductos);
        ventaRepo.save(v);
    }

    @Override
    public List<Venta> getVentas() {
        
        List<Venta> listaVentas = ventaRepo.findAll();
        return listaVentas;
        
    }

    @Override
    public Venta getVenta(Long idVenta) {
        Venta venta = ventaRepo.findById(idVenta).orElse(null);
        return venta;
    }

    @Override
    public void deleteVenta(Long idVenta) {
        ventaRepo.deleteById(idVenta);
    }

//    @Override
//    public void editVenta(Long idOriginal, Long idNueva, LocalDate fechaNueva, Double nuevoTotal, List<Producto> listaProductos, Cliente nuevoCliente) {
//        
//        Venta venta = this.getVenta(idOriginal);
//        venta.setIdVenta(idNueva);
//        venta.setFechaVenta(fechaNueva);
//        venta.setTotal(nuevoTotal);
//        venta.setListaProductos(listaProductos);  
//        venta.setUnCliente(nuevoCliente);
//        
//        this.saveVenta(venta);
//    }
    
    public Venta checkTotalVenta(Venta venta, List<Producto> listaProductos)
    {
        //calcular total
        Double total = 0.0;
        
        for(Producto p : listaProductos)
        {
            total += p.getCosto();
        }
        
        venta.setTotal(total);
        return venta;
    }

    @Override
    public List<Producto> getVentaItems(Long idVenta) {
        
        Venta venta = this.getVenta(idVenta);
        List<Producto> listaItems = venta.getListaProductos();
        return listaItems;
    }

    @Override
    public String getVentasDia(LocalDate fecha) {
       
        List <Venta> listaVentas = this.getVentas();
        
        int cantidadVentas=0;
        Double montoTotal=0.0;
        
        for(Venta v:listaVentas)
        {
            if(v.getFechaVenta().isEqual(fecha))
            {
                cantidadVentas+=1;
                montoTotal+=v.getTotal();
            }
        }
        
        return "En la fecha "+fecha+" tuvimos "+cantidadVentas+" ventas por un total de "+ montoTotal;
    }

    @Override
    public VentaDTO getMaximaVenta() {
        
        List<Venta> listaVentas = this.getVentas();
        Double totalMaximo = 0.0;
        Venta ventaMaxima = new Venta();
        VentaDTO ventaDTO = new VentaDTO();
        int cant = 0;
        for(Venta venta:listaVentas)
        {
            if(venta.getTotal()>totalMaximo)
            {
                totalMaximo = venta.getTotal();
                ventaMaxima = venta;
            }
            
            cant++;
        }
        
        ventaDTO.setIdVenta(ventaMaxima.getIdVenta());
        ventaDTO.setTotal(ventaMaxima.getTotal());
        ventaDTO.setCantidadItems(cant);
        ventaDTO.setNombreCliente(ventaMaxima.getUnCliente().getNombre());
        ventaDTO.setApellidoCliente(ventaMaxima.getUnCliente().getApellido());
        
        return ventaDTO;
    }

    @Override
    public void saveVenta(Venta venta) {
        ventaRepo.save(venta);
    }

    @Override
    public Venta editVenta(Venta venta, List<Producto> listaProductos) {
        
        Venta v = this.checkTotalVenta(venta, listaProductos);
        this.saveVenta(v);
        return this.getVenta(v.getIdVenta());
    }
    
}
