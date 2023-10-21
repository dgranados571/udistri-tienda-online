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

import com.co.tiendaonline.dto.FormObject;

@Entity
@Table(name = "clientes")
public class Clientes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCliente;
	
	@Column(name = "nombreCliente")
	private String nombreCliente;
	
	@Column(name = "direccionCliente")
	private String direccionCliente;
	
	@Column(name = "telefonoCliente")
	private String telefonoCliente;
	
	@Column(name = "emailCliente")
	private String emailCliente;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Ventas> ventas;

	public Clientes() {
		super();
	}

	public Clientes(FormObject formObject) {
		super();
		this.nombreCliente = formObject.getNombreCliente();
		this.direccionCliente = formObject.getDireccionCliente();
		this.telefonoCliente = formObject.getTelefonoCliente();
		this.emailCliente = formObject.getEmailCliente();
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public List<Ventas> getVentas() {
		return ventas;
	}

	public void setVentas(List<Ventas> ventas) {
		this.ventas = ventas;
	}

}
