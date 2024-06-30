/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tpintegrador.bazar.service;

import com.tpintegrador.bazar.model.Cliente;
import java.util.List;

/**
 *
 * @author natal
 */
public interface IClienteService {
    
    public void saveCliente(Cliente cli);
    public List<Cliente> getClientes();
    public Cliente getCliente(Long idCliente);
    public void deleteCliente(Long idCliente);
    public Cliente editCliente(Long idOriginal, Long idNueva, String nombreNuevo, String apellidoNuevo, String dniNUevo);
    public Cliente editCliente(Cliente cli);
}
