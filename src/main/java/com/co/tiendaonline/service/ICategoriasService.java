package com.co.tiendaonline.service;

import java.util.List;

import com.co.tiendaonline.entity.Categorias;

public interface ICategoriasService {
	
	public List<Categorias> obtenerCategorias();
	public Categorias obtenerCategoriaporId(long id);
	public void agregarCategoria(Categorias categoria);
	public void eliminarCategoria(long id);
	
}
