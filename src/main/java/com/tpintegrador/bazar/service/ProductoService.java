/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tpintegrador.bazar.service;

import com.tpintegrador.bazar.model.Producto;
import com.tpintegrador.bazar.model.Venta;
import com.tpintegrador.bazar.repository.IProductoRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{
    
    @Autowired
    IProductoRepository productoRepo;

    @Override
    public void saveProducto(Producto produ) {
        productoRepo.save(produ);
    }

    @Override
    public List<Producto> getProductos() {
        List<Producto> listaProdu = productoRepo.findAll();
        return listaProdu;
    }

    @Override
    public Producto getProducto(Long idProducto) {
        
        Producto produ = productoRepo.findById(idProducto).orElse(null);
        return produ;
    }

    @Override
    public void deleteProducto(Long idProducto) {
        
        productoRepo.deleteById(idProducto);
        
    }

    @Override
    public Producto editProducto(Long idOriginal, Long IdNueva, String nombreNuevo, String marcaNueva, Double costoNuevo, Double stockNuevo) {
        
        Producto produ = this.getProducto(idOriginal);
        produ.setIdProducto(IdNueva);
        produ.setNombre(nombreNuevo);
        produ.setMarca(marcaNueva);
        produ.setCosto(costoNuevo);
        produ.setStock(stockNuevo);
        
        this.saveProducto(produ);
        return this.getProducto(IdNueva);
    }
    
    public Producto editProducto(Producto producto)
    {
        this.saveProducto(producto);
        return this.getProducto(producto.getIdProducto());
    }
    
    public boolean checkStockVenta(Venta venta)
    {
        List<Producto> listaProd = venta.getListaProductos();
        Map<Long, Double> hm = new HashMap<>();
        Map<Long, Double> hmStockReal = new HashMap<>();
        Producto prodCompleto;
        Double stock = 0.0;
        Double unit = 1.0;
        
        for(Producto p:listaProd)
        {
            Long idProd = p.getIdProducto();
            Double cant;
            if(!hm.containsKey(idProd))
            {
                cant = 0.0;
                prodCompleto = this.getProducto(idProd);
                hmStockReal.put(idProd, prodCompleto.getStock());
            }else
            {
                cant = hm.get(idProd);
            }
            System.out.println("STOCK Real del Producto: " + hmStockReal.get(idProd));
            hm.put(idProd, (cant==0)?unit:cant+unit);
            if(hm.get(idProd) > hmStockReal.get(idProd) || hmStockReal.get(idProd) == 0.0)
            {
                return false;
            }
        }
        
        for(Map.Entry<Long, Double> set:
                hm.entrySet())
        {
            Producto prod = this.getProducto(set.getKey());
            Double newStock = prod.getStock() - set.getValue();
            prod.setStock(newStock);
            this.editProducto(prod);
        }
        
        return true;   
    }

    @Override
    public List<Producto> getBajoStock() {
        
        List<Producto> listaProd = this.getProductos();
        List<Producto> listaStock = new ArrayList<>();
        
        for(Producto p:listaProd)
        {
            if(p.getStock()<5)
            {
                listaStock.add(p);
            }
        }
        
        return listaStock;
    }

    @Override
    public List<Producto> getProductosVenta(Venta venta) {
        
        List<Producto>listaIds = venta.getListaProductos();
        List<Producto>listaProductos = new ArrayList<>();
        
        for(Producto p : listaIds)
        {
            Producto completo = this.getProducto(p.getIdProducto());
            listaProductos.add(completo);
        }
        
        return listaProductos;
    }
    
    
    
}
