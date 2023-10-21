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

import com.co.tiendaonline.dto.FormObject;

@Entity
@Table(name = "ventas")
public class Ventas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idVenta;
	
	@Column(name = "fechaVenta", length = 24)
	private String fechaVenta;
	
	@Column(name = "cantidadVendida")
	private int cantidadVendida;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Clientes_idCliente")
	private Clientes cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Productos_idProducto")
	private Productos producto;

	public Ventas() {
		super();
	}

	public Ventas(FormObject formObject) {
		super();
		this.fechaVenta = formObject.getFechaVenta();
		this.cantidadVendida = formObject.getCantidadVendida();
	}

	public long getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(long idVenta) {
		this.idVenta = idVenta;
	}

	public String getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public int getCantidadVendida() {
		return cantidadVendida;
	}

	public void setCantidadVendida(int cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}
	
}
