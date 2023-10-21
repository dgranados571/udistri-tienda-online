package com.co.tiendaonline.service;

import java.util.List;
import com.co.tiendaonline.entity.Ventas;

public interface IVentasService {

	public List<Ventas> obtenerVentas();
	public Ventas obtenerVentasporId(long id);
	public void agregarVenta(Ventas venta);
	public void eliminarVenta(long id);
	
}
