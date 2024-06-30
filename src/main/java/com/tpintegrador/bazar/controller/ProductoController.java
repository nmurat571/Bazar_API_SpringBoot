/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tpintegrador.bazar.controller;

import com.tpintegrador.bazar.model.Producto;
import com.tpintegrador.bazar.service.IProductoService;
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
public class ProductoController {
    
    @Autowired
    IProductoService productoServ;
    
    @PostMapping("/producto/crear")
    public String saveProducto(@RequestBody Producto prod)
    {
        productoServ.saveProducto(prod);
        return "Producto agregado correctamente";
    }
    
    @GetMapping("/producto/listar")
    public List<Producto> getProductos()
    {
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos = productoServ.getProductos();
        return listaProductos;
    }
    
    @GetMapping("/producto/traer")
    public Producto getProducto(@RequestParam Long prodId)
    {
        Producto prod = productoServ.getProducto(prodId);
        return prod;
    }
    
    @DeleteMapping("/producto/borrar")
    public String deleteProducto(@RequestParam Long idProd)
    {
        productoServ.deleteProducto(idProd);
        return "Producto borrado";
    }
    
    @PutMapping("/producto/editar/{idOriginal}")
    public Producto editProducto(@PathVariable Long idOriginal,
                                 @RequestParam(required = false, name = "idProducto")Long idNueva,
                                 @RequestParam(required = false, name = "nombre")String nombreNuevo,
                                 @RequestParam(required = false, name = "marca")String marcaNueva,
                                 @RequestParam(required = false, name = "costo")Double costoNuevo,
                                 @RequestParam(required = false, name = "stock")Double stockNuevo)
    {
        Producto prod = productoServ.editProducto(idOriginal, idNueva, 
                        nombreNuevo, marcaNueva, costoNuevo, stockNuevo);
        return prod;
    }
    
    @PutMapping("/producto/editar")
    public Producto editProducto(@RequestBody Producto prod)
    {
        Producto editado = productoServ.editProducto(prod);
        return editado;
    }
    
    @GetMapping("/producto/stock")
    public List<Producto> getBajoStock()
    {
        List<Producto> listaStock = new ArrayList<>();
        listaStock = productoServ.getBajoStock();
        return listaStock;
    }
    
    
}
