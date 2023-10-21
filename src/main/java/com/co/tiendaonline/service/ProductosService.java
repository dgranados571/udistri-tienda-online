package com.co.tiendaonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.co.tiendaonline.entity.Productos;
import com.co.tiendaonline.repo.IProductosRepo;

@Service
public class ProductosService implements IProductosService {
	
	@Autowired
	IProductosRepo iProductosRepo;

	@Override
	public List<Productos> obtenerProductos() {
		return iProductosRepo.findAll();
	}

	@Override
	public void agregarProducto(Productos producto) {
		iProductosRepo.save(producto);
	}

	@Override
	public Productos obtenerProductosporId(long id) {
		return iProductosRepo.getById(id);
	}

	@Override
	public void eliminarProducto(long id) {
		iProductosRepo.deleteById(id);
	}

}
