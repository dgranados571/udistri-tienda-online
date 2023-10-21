package com.co.tiendaonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.co.tiendaonline.entity.Categorias;
import com.co.tiendaonline.repo.ICategoriasRepo;


@Service
public class CategoriasService implements ICategoriasService { 
	
	@Autowired
	ICategoriasRepo iCategoriasRepo;
	
	@Override
	public List<Categorias> obtenerCategorias() {
		return iCategoriasRepo.findAll();
	}

	@Override
	public void agregarCategoria(Categorias categoria) {
		iCategoriasRepo.save(categoria);
	}

	@Override
	public Categorias obtenerCategoriaporId(long id) {
		return iCategoriasRepo.getById(id);
	}

	@Override
	public void eliminarCategoria(long id) {
		iCategoriasRepo.deleteById(id);
	}

}
