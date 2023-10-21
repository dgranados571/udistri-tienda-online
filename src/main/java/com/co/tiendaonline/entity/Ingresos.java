package com.co.tiendaonline.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ingresos")
public class Ingresos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idIngreso;
	
	@Column(name = "fechaIngreso")
	private String fechaIngreso;
	
	@Column(name = "cantidadIngresada")
	public int cantidadIngresada;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Proveedores_idProveedor")
	private Proveedores proveedor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Productos_idProducto")
	public Productos producto;

	public Ingresos() {
		super();
	}	

}
