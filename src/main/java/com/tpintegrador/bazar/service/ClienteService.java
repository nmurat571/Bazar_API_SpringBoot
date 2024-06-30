/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tpintegrador.bazar.service;

import com.tpintegrador.bazar.model.Cliente;
import com.tpintegrador.bazar.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{
    
    @Autowired
    IClienteRepository clienteRepo;

    @Override
    public void saveCliente(Cliente cli) {
        clienteRepo.save(cli);
    }

    @Override
    public List<Cliente> getClientes() {
        
        List<Cliente> listaClientes = clienteRepo.findAll();
        return listaClientes;
        
    }

    @Override
    public Cliente getCliente(Long idCliente) {
        
        Cliente cli = clienteRepo.findById(idCliente).orElse(null);
        return cli;
    }

    @Override
    public void deleteCliente(Long idCliente) {
        clienteRepo.deleteById(idCliente);
    }

    @Override
    public Cliente editCliente(Long idOriginal, Long idNueva, String nombreNuevo, String apellidoNuevo, String dniNUevo) {
        
        Cliente cli = this.getCliente(idOriginal);
        
        cli.setIdCliente(idNueva);
        cli.setNombre(nombreNuevo);
        cli.setApellido(apellidoNuevo);
        cli.setDni(dniNUevo);
        this.saveCliente(cli);
        
        return this.getCliente(idNueva);
    }

    @Override
    public Cliente editCliente(Cliente cli) {
        this.saveCliente(cli);
        return this.getCliente(cli.getIdCliente());
    }
    
}
