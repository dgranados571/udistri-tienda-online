package com.co.tiendaonline.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.co.tiendaonline.dto.FormObject;

@Entity
@Table(name = "productos")
public class Productos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProducto;
	
	@Column(name = "nombreProducto")
	private String nombreProducto;
	
	@Column(name = "descripcionProducto")
	private String descripcionProducto;
	
	@Column(name = "valorUnitarioProducto")
	private String valorUnitarioProducto;
	
	@Column(name = "cantidadDisponibleProducto")
	private String cantidadDisponibleProducto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Categorias_idCategoria")
	private Categorias categoria;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "producto", cascade = CascadeType.ALL)
	private List<Ingresos> ingresos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "producto", cascade = CascadeType.ALL)
	private List<Ventas> ventas;
	 
	public Productos() {
		super();
	}
	
	public Productos(FormObject formObject) {
		this.nombreProducto = formObject.getNombreProducto();
		this.descripcionProducto = formObject.getDescripcionProducto();
		this.valorUnitarioProducto = formObject.getValorUnitarioProducto();
		this.cantidadDisponibleProducto = formObject.getCantidadDisponibleProducto();		
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getValorUnitarioProducto() {
		return valorUnitarioProducto;
	}

	public void setValorUnitarioProducto(String valorUnitarioProducto) {
		this.valorUnitarioProducto = valorUnitarioProducto;
	}

	public String getCantidadDisponibleProducto() {
		return cantidadDisponibleProducto;
	}

	public void setCantidadDisponibleProducto(String cantidadDisponibleProducto) {
		this.cantidadDisponibleProducto = cantidadDisponibleProducto;
	}

	public Categorias getCategoria() {
		return categoria;
	}

	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}

	public List<Ingresos> getIngresos() {
		return ingresos;
	}

	public void setIngresos(List<Ingresos> ingresos) {
		this.ingresos = ingresos;
	}

	public List<Ventas> getVentas() {
		return ventas;
	}

	public void setVentas(List<Ventas> ventas) {
		this.ventas = ventas;
	}
	
	

}
