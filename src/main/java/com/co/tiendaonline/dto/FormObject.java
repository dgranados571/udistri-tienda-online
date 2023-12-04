package com.co.tiendaonline.dto;

import com.co.tiendaonline.EnumConstantes;
import com.co.tiendaonline.entity.Categorias;
import com.co.tiendaonline.entity.Clientes;
import com.co.tiendaonline.entity.Productos;
import com.co.tiendaonline.entity.Ventas;

public class FormObject {
	
	private long idFormObject;
	
	private String nombreCategoria;
	private String descripcionCategoria;
	
	private String nombreProducto;
	private String descripcionProducto;
	private String valorUnitarioProducto;
	private String cantidadDisponibleProducto;
	private long idCategoria;
	
	private String nombreCliente;
	private String direccionCliente;
	private String telefonoCliente;
	private String emailCliente;
	
	private String fechaVenta;
	private int cantidadVendida;
	private long idCliente;
	private long idProducto;
	
	public FormObject() {
		super();
	}
	
	public FormObject(Ventas venta) {
		super();
		this.idFormObject = venta.getIdVenta();
		this.fechaVenta = venta.getFechaVenta();
		this.cantidadVendida = venta.getCantidadVendida();
		this.idCliente = venta.getCliente().getIdCliente();
		this.idProducto = venta.getProducto().getIdProducto();
	}
	
	public FormObject(Clientes cliente) {
		super();
		this.idFormObject = cliente.getIdCliente();
		this.nombreCliente = cliente.getNombreCliente();
		this.direccionCliente = cliente.getDireccionCliente();
		this.telefonoCliente = cliente.getTelefonoCliente();
		this.emailCliente = cliente.getEmailCliente();
	}

	public FormObject(Productos producto) {
		super();
		this.idFormObject = producto.getIdProducto();
		this.nombreProducto = producto.getNombreProducto();
		this.descripcionProducto = producto.getDescripcionProducto();
		this.valorUnitarioProducto = producto.getValorUnitarioProducto();
		this.cantidadDisponibleProducto = producto.getCantidadDisponibleProducto();
		this.idCategoria = producto.getCategoria().getIdCategoria();
	}
	
	public FormObject(Categorias categoria) {
		super();
		this.idFormObject = categoria.getIdCategoria();
		this.nombreCategoria = categoria.getNombreCategoria();
		this.descripcionCategoria = categoria.getDescripcionCategoria();
	}
	
	
	public FormObject(ListaVistas listaVista, String menuVista) {
		super();
		this.idFormObject = Long.parseLong(listaVista.getColumn1());
		switch (menuVista) {
		case EnumConstantes.CATEGORIAS:
			this.nombreCategoria = listaVista.getColumn2();
			this.descripcionCategoria = listaVista.getColumn3();	
			break;
		case EnumConstantes.PRODUCTOS:
			this.nombreProducto = listaVista.getColumn2();
			this.descripcionProducto = listaVista.getColumn3();
			this.valorUnitarioProducto = listaVista.getColumn4();
			this.idCategoria = Long.parseLong(listaVista.getColumn5());
			break;
		case EnumConstantes.CLIENTES:
			this.nombreCliente = listaVista.getColumn2();
			this.direccionCliente = listaVista.getColumn3();
			this.telefonoCliente = listaVista.getColumn4();
			this.emailCliente = listaVista.getColumn5();
			break;
		case EnumConstantes.VENTAS:
			this.fechaVenta = listaVista.getColumn2();
			this.idCliente = Long.parseLong(listaVista.getColumn3());
			this.idProducto = Long.parseLong(listaVista.getColumn4());
			this.cantidadVendida = Integer.parseInt(listaVista.getColumn5());
			break;
		default:
			break;
		}
	}

	public long getIdFormObject() {
		return idFormObject;
	}

	public void setIdFormObject(long idFormObject) {
		this.idFormObject = idFormObject;
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

	public long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public String getDescripcionCategoria() {
		return descripcionCategoria;
	}

	public void setDescripcionCategoria(String descripcionCategoria) {
		this.descripcionCategoria = descripcionCategoria;
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

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	
	
	
}
