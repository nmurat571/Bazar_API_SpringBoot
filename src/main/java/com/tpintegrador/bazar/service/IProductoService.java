/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tpintegrador.bazar.service;

import com.tpintegrador.bazar.model.Producto;
import com.tpintegrador.bazar.model.Venta;
import java.util.List;

/**
 *
 * @author natal
 */
public interface IProductoService {
    
    public void saveProducto(Producto produ);
    public List<Producto> getProductos();
    public Producto getProducto(Long idProducto);
    public void deleteProducto (Long idProducto);
    public Producto editProducto (Long idOriginal, Long IdNueva,String nombreNuevo,String marcaNueva,
                                 Double costoNuevo,Double stockNuevo);
    public Producto editProducto(Producto producto);
    public boolean checkStockVenta(Venta venta);
    public List<Producto> getBajoStock();
    public List<Producto> getProductosVenta(Venta venta);
}
