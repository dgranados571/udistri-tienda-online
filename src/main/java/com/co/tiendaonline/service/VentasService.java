package com.co.tiendaonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.co.tiendaonline.entity.Ventas;
import com.co.tiendaonline.repo.IVentasRepo;

@Service
public class VentasService implements IVentasService {
	
	@Autowired
	IVentasRepo iVentasRepo; 

	@Override
	public List<Ventas> obtenerVentas() {
		return iVentasRepo.findAll();
	}

	@Override
	public Ventas obtenerVentasporId(long id) {
		return iVentasRepo.getById(id);
	}

	@Override
	public void agregarVenta(Ventas venta) {
		iVentasRepo.save(venta);
	}

	@Override
	public void eliminarVenta(long id) {
		iVentasRepo.deleteById(id);
	}

}
