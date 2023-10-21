package com.co.tiendaonline.service;

import java.util.List;
import com.co.tiendaonline.entity.Productos;

public interface IProductosService {
	
	public List<Productos> obtenerProductos();
	public Productos obtenerProductosporId(long id);
	public void agregarProducto(Productos producto);
	public void eliminarProducto(long id);

}
