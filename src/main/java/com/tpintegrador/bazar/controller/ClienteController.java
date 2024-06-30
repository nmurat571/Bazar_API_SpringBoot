/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tpintegrador.bazar.controller;

import com.tpintegrador.bazar.model.Cliente;
import com.tpintegrador.bazar.service.IClienteService;
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
public class ClienteController {
    
    @Autowired
    IClienteService clienteServ;
    
    @PostMapping("/cliente/crear")
    public String saveCliente(@RequestBody Cliente cli)
    {
        clienteServ.saveCliente(cli);
        return "Cliente agregado correctamente";
    }
    
    @GetMapping("/cliente/listar")
    public List<Cliente> getClientes()
    {
        List<Cliente> listaClientes = clienteServ.getClientes();
        return listaClientes;
    }
    
    @GetMapping("/cliente/traer")
    public Cliente getCliente(@RequestParam Long idCliente)
    {
        Cliente cli = clienteServ.getCliente(idCliente);
        return cli;
    }
    
    @DeleteMapping("/cliente/borrar")
    public String deleteCliente(@RequestParam Long idCliente)
    {
        clienteServ.deleteCliente(idCliente);
        return "Cliente eliminado";
    }
    
    @PutMapping("/cliente/editar/{idOriginal}")
    public Cliente editCliente(@PathVariable Long idOriginal,
                                @RequestParam(required = false, name = "idCliente") Long idNueva,
                                @RequestParam(required = false, name = "nombre") String nombreNuevo,
                                @RequestParam(required = false, name = "apellido") String apellidoNuevo,
                                @RequestParam(required = false, name = "dni") String dniNuevo)
    {
        Cliente cli = clienteServ.editCliente(idOriginal, idNueva, nombreNuevo, apellidoNuevo, dniNuevo);
        return cli;
    }
    
    @PutMapping("/cliente/editar")
    public Cliente editCliente(Cliente cli)
    {
        Cliente editado = clienteServ.editCliente(cli);
        return editado;
    }
}

