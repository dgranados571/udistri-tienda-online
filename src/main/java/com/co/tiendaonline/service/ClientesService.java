package com.co.tiendaonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.co.tiendaonline.entity.Clientes;
import com.co.tiendaonline.repo.IClientesRepo;

@Service
public class ClientesService implements IClientesService {
	
	@Autowired
	IClientesRepo iClientesRepo; 

	@Override
	public List<Clientes> obtenerClientes() {
		return iClientesRepo.findAll();
	}

	@Override
	public void agregarCliente(Clientes cliente) {
		iClientesRepo.save(cliente);
	}

	@Override
	public Clientes obtenerClienteporId(long id) {
		return iClientesRepo.getById(id);
	}

	@Override
	public void eliminarCliente(long id) {
		iClientesRepo.deleteById(id);
	}

}
