package com.co.tiendaonline.service;

import java.util.List;

import com.co.tiendaonline.entity.Clientes;

public interface IClientesService {

	public List<Clientes> obtenerClientes();
	public Clientes obtenerClienteporId(long id);
	public void agregarCliente(Clientes cliente);
	public void eliminarCliente(long id);
	
}
