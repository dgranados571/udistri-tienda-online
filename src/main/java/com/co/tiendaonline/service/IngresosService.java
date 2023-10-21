package com.co.tiendaonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.co.tiendaonline.entity.Ingresos;
import com.co.tiendaonline.repo.IIngresosRepo;

@Service
public class IngresosService implements IIngresosService {
	
	@Autowired
	IIngresosRepo iIngresosRepo; 

	@Override
	public List<Ingresos> obtenerIngresos() {
		return iIngresosRepo.findAll();
	}

}
