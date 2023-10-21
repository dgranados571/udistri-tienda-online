package com.co.tiendaonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.co.tiendaonline.entity.Proveedores;
import com.co.tiendaonline.repo.IProveedoresRepo;

@Service
public class ProveedoresService implements IProveedoresService {
	
	@Autowired
	IProveedoresRepo iProveedoresRepo;

	@Override
	public List<Proveedores> obtenerProveedores() {
		return iProveedoresRepo.findAll();
	}

}
