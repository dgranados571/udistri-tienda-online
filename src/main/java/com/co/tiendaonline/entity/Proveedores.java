package com.co.tiendaonline.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "proveedores")
public class Proveedores {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long idProveedor;
	
	@Column(name = "nombreProveedor")
	public String nombreProveedor;
	
	@Column(name = "direccionProveedor")
	public String direccionProveedor;
	
	@Column(name = "telefonoProveedor")
	public String telefonoProveedor;
	
	@Column(name = "emailProveedor")
	public String emailProveedor;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proveedor", cascade = CascadeType.ALL)
	public List<Ingresos> ingresos;
	
	public Proveedores() {
		super();
	}

}
