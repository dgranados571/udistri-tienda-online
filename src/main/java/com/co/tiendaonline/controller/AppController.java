package com.co.tiendaonline.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.co.tiendaonline.EnumConstantes;
import com.co.tiendaonline.dto.FormObject;
import com.co.tiendaonline.dto.ListObject;
import com.co.tiendaonline.dto.ListaVistas;
import com.co.tiendaonline.entity.Categorias;
import com.co.tiendaonline.entity.Clientes;
import com.co.tiendaonline.entity.Productos;
import com.co.tiendaonline.entity.Ventas;
import com.co.tiendaonline.service.ICategoriasService;
import com.co.tiendaonline.service.IClientesService;
import com.co.tiendaonline.service.IProductosService;
import com.co.tiendaonline.service.IVentasService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
public class AppController {

	@Autowired
	ICategoriasService iCategoriasService;

	@Autowired
	IProductosService iProductosService;

	@Autowired
	IClientesService iClientesService;

	@Autowired
	IVentasService iVentasService;
	
	boolean servicePython = false;
	
	Gson gson = new Gson();

	@GetMapping("/web/udistri")
	public String getUdistriControl(HttpServletRequest request, HttpServletResponse reponse, Model model)
			throws JsonProcessingException {

		HttpSession session = request.getSession();
		String menuVista = "index";
		String elementoBotom = EnumConstantes.ELEMENT_BUTTOM_CREATE;

		if (session.getAttribute(EnumConstantes.MENU) != null) {
			menuVista = (String) session.getAttribute(EnumConstantes.MENU);
			session.removeAttribute(EnumConstantes.MENU);
		}

		if (session.getAttribute(EnumConstantes.MESSAGE_INFO) != null) {
			String messageInfo = (String) session.getAttribute(EnumConstantes.MESSAGE_INFO);
			model.addAttribute(EnumConstantes.MESSAGE_INFO, messageInfo);
			session.removeAttribute(EnumConstantes.MESSAGE_INFO);
		}

		if (session.getAttribute(EnumConstantes.MESSAGE_ERROR) != null) {
			String messageError = (String) session.getAttribute(EnumConstantes.MESSAGE_ERROR);
			model.addAttribute(EnumConstantes.MESSAGE_ERROR, messageError);
			session.removeAttribute(EnumConstantes.MESSAGE_ERROR);
		}

		FormObject formObject = new FormObject();
		if (session.getAttribute(EnumConstantes.VISTA_EDICION) != null) {
			Type listType = new TypeToken<ArrayList<ListaVistas>>(){}.getType();
			menuVista = (String) session.getAttribute(EnumConstantes.VISTA_EDICION);
			long idElement = (long) session.getAttribute(EnumConstantes.ELEMENT_EDICION_ID);
			switch (menuVista) {
			case EnumConstantes.CATEGORIAS:
				if(servicePython) {
					String response = sendGET("http://127.0.0.1:5000/bdserver/vistaEdicion/" + EnumConstantes.CATEGORIAS + "/" + idElement);
					try {
						List<ListaVistas> listaVista = gson.fromJson(response, listType);
						formObject = new FormObject(listaVista.get(0), EnumConstantes.CATEGORIAS);
					}catch (Exception e) {
						System.out.println("Error --> " + e);
					}
				}else {
					Categorias entityCategorias = iCategoriasService.obtenerCategoriaporId(idElement);
					formObject = new FormObject(entityCategorias);
				}
				break;
			case EnumConstantes.PRODUCTOS:				
				if(servicePython) {
					String response = sendGET("http://127.0.0.1:5000/bdserver/vistaEdicion/" + EnumConstantes.PRODUCTOS + "/" + idElement);
					try {
						List<ListaVistas> listaVista = gson.fromJson(response, listType);
						formObject = new FormObject(listaVista.get(0), EnumConstantes.PRODUCTOS);
					}catch (Exception e) {
						System.out.println("Error --> " + e);
					}
				}else {
					Productos entityProductos = iProductosService.obtenerProductosporId(idElement);
					formObject = new FormObject(entityProductos);	
				}
				break;
			case EnumConstantes.CLIENTES:
				if(servicePython) {
					String response = sendGET("http://127.0.0.1:5000/bdserver/vistaEdicion/" + EnumConstantes.CLIENTES + "/" + idElement);
					try {
						List<ListaVistas> listaVista = gson.fromJson(response, listType);
						formObject = new FormObject(listaVista.get(0), EnumConstantes.CLIENTES);
					}catch (Exception e) {
						System.out.println("Error --> " + e);
					}
				}else {
					Clientes entityCliente = iClientesService.obtenerClienteporId(idElement);
					formObject = new FormObject(entityCliente);
				}
				break;
			case EnumConstantes.VENTAS:
				if(servicePython) {
					String response = sendGET("http://127.0.0.1:5000/bdserver/vistaEdicion/" + EnumConstantes.VENTAS + "/" + idElement);
					try {
						List<ListaVistas> listaVista = gson.fromJson(response, listType);
						formObject = new FormObject(listaVista.get(0), EnumConstantes.VENTAS);
					}catch (Exception e) {
						System.out.println("Error --> " + e);
					}
				}else {
					Ventas entityVentas = iVentasService.obtenerVentasporId(idElement);
					formObject = new FormObject(entityVentas);
				}
				break;
			default:
				break;
			}
			elementoBotom = EnumConstantes.ELEMENT_BUTTOM_UPDATE;
			session.removeAttribute(EnumConstantes.ELEMENT_EDICION_ID);
			session.removeAttribute(EnumConstantes.VISTA_EDICION);
		}

		model.addAttribute(EnumConstantes.FORM_OBJECT, formObject);
		model.addAttribute(EnumConstantes.ELEMENT_BUTTOM, elementoBotom);

		model.addAttribute(EnumConstantes.OPCIONES_CATEGORIAS, obtieneListaCategorias());
		model.addAttribute(EnumConstantes.OPCIONES_CLIENTES, obtieneListaClientes());
		model.addAttribute(EnumConstantes.OPCIONES_PRODUCTOS, obtieneListaProductos());
		
		List<ListaVistas> listaCategorias = obtieneListaVista(EnumConstantes.CATEGORIAS);
		List<ListaVistas> listaProductos = obtieneListaVista(EnumConstantes.PRODUCTOS);
		List<ListaVistas> listaClientes = obtieneListaVista(EnumConstantes.CLIENTES);
		List<ListaVistas> listaVentas = obtieneListaVista(EnumConstantes.VENTAS);

		model.addAttribute(EnumConstantes.LISTA_VISTA_CATEGORIAS, listaCategorias);
		model.addAttribute(EnumConstantes.LISTA_VISTA_PRODUCTOS, listaProductos);
		model.addAttribute(EnumConstantes.LISTA_VISTA_CLIENTES, listaClientes);
		model.addAttribute(EnumConstantes.LISTA_VISTA_VENTAS, listaVentas);
		
		model.addAttribute(EnumConstantes.TOTAL_CATEGORIAS, listaCategorias.size());
		model.addAttribute(EnumConstantes.TOTAL_PRODUCTOS, listaProductos.size());
		model.addAttribute(EnumConstantes.TOTAL_CLIENTES, listaClientes.size());
		model.addAttribute(EnumConstantes.TOTAL_VENTAS, listaVentas.size());
		
		return menuVista;
	}

	@GetMapping("/web/actionMenu/{menu}")
	public String actualizaUsuarioAction(HttpServletRequest request,
			@PathVariable(value = EnumConstantes.MENU) String menu) {
		HttpSession session = request.getSession();
		session.setAttribute(EnumConstantes.MENU, menu);
		return EnumConstantes.REDIRECT_PAGE_UDISTRI;
	}

	@PostMapping("/web/agregarCategoria")
	public String agregarCategoria(HttpServletRequest request, @ModelAttribute FormObject formObject) {
		HttpSession session = request.getSession();
		if (!formObject.getNombreCategoria().isEmpty() && !formObject.getDescripcionCategoria().isEmpty()) {
			try {
				if (formObject.getIdFormObject() == 0) {
					Categorias categoriaEntity = new Categorias(formObject);
					iCategoriasService.agregarCategoria(categoriaEntity);
					session.setAttribute(EnumConstantes.MESSAGE_INFO, "Categoria agregada con exito");
				} else {
					Categorias categoriaEntity = iCategoriasService.obtenerCategoriaporId(formObject.getIdFormObject());
					categoriaEntity.setNombreCategoria(formObject.getNombreCategoria());
					categoriaEntity.setDescripcionCategoria(formObject.getDescripcionCategoria());
					iCategoriasService.agregarCategoria(categoriaEntity);
					session.setAttribute(EnumConstantes.MESSAGE_INFO, "Categoria actualizada con exito!!!");
				}
			} catch (Exception e) {
				session.setAttribute(EnumConstantes.MESSAGE_ERROR,
						"Error registrando la información, consulte al administrador!!!");
			}
		} else {
			session.setAttribute(EnumConstantes.MESSAGE_ERROR, "Algunos campos son obligatorios");
		}
		session.setAttribute(EnumConstantes.MENU, EnumConstantes.CATEGORIAS);
		return EnumConstantes.REDIRECT_PAGE_UDISTRI;
	}

	@PostMapping("/web/agregarProducto")
	public String agregarProducto(HttpServletRequest request, @ModelAttribute FormObject formObject) {
		HttpSession session = request.getSession();
		if (!formObject.getNombreProducto().isEmpty() && !formObject.getDescripcionProducto().isEmpty()
				&& !formObject.getValorUnitarioProducto().isEmpty() && formObject.getIdCategoria() != 0) {
			try {
				if (formObject.getIdFormObject() == 0) {
					Productos productoEntity = new Productos(formObject);
					productoEntity.setCategoria(iCategoriasService.obtenerCategoriaporId(formObject.getIdCategoria()));
					iProductosService.agregarProducto(productoEntity);
					session.setAttribute(EnumConstantes.MESSAGE_INFO, "Producto agregado con exito");
				} else {
					Productos productoEntity = iProductosService.obtenerProductosporId(formObject.getIdFormObject());
					productoEntity.setNombreProducto(formObject.getNombreProducto());
					productoEntity.setDescripcionProducto(formObject.getDescripcionProducto());
					productoEntity.setValorUnitarioProducto(formObject.getValorUnitarioProducto());
					productoEntity.setCategoria(iCategoriasService.obtenerCategoriaporId(formObject.getIdCategoria()));
					iProductosService.agregarProducto(productoEntity);
					session.setAttribute(EnumConstantes.MESSAGE_INFO, "Producto Actualizado con exito");
				}
			} catch (Exception e) {
				session.setAttribute(EnumConstantes.MESSAGE_ERROR,
						"Error registrando la información, consulte al administrador!!!");
			}
		} else {
			session.setAttribute(EnumConstantes.MESSAGE_ERROR, "Algunos campos son obligatorios");
		}
		session.setAttribute(EnumConstantes.MENU, EnumConstantes.PRODUCTOS);
		return EnumConstantes.REDIRECT_PAGE_UDISTRI;
	}

	@PostMapping("/web/agregarCliente")
	public String agregarCliente(HttpServletRequest request, @ModelAttribute FormObject formObject) {
		HttpSession session = request.getSession();
		if (!formObject.getNombreCliente().isEmpty() && !formObject.getDireccionCliente().isEmpty()
				&& !formObject.getTelefonoCliente().isEmpty() && !formObject.getEmailCliente().isEmpty()) {
			try {
				if (formObject.getIdFormObject() == 0) {
					Clientes clienteEntity = new Clientes(formObject);
					iClientesService.agregarCliente(clienteEntity);
					session.setAttribute(EnumConstantes.MESSAGE_INFO, "Cliente agregado con exito");
				} else {
					Clientes clienteEntity = iClientesService.obtenerClienteporId(formObject.getIdFormObject());
					clienteEntity.setNombreCliente(formObject.getNombreCliente());
					clienteEntity.setDireccionCliente(formObject.getDireccionCliente());
					clienteEntity.setTelefonoCliente(formObject.getTelefonoCliente());
					clienteEntity.setEmailCliente(formObject.getEmailCliente());
					iClientesService.agregarCliente(clienteEntity);
					session.setAttribute(EnumConstantes.MESSAGE_INFO, "Cliente agregado con exito");
				}
			} catch (Exception e) {
				session.setAttribute(EnumConstantes.MESSAGE_ERROR,
						"Error registrando la información, consulte al administrador!!!");
			}
		} else {
			session.setAttribute(EnumConstantes.MESSAGE_ERROR, "Algunos campos son obligatorios");
		}
		session.setAttribute(EnumConstantes.MENU, EnumConstantes.CLIENTES);
		return EnumConstantes.REDIRECT_PAGE_UDISTRI;
	}

	@PostMapping("/web/agregarVenta")
	public String agregarVenta(HttpServletRequest request, @ModelAttribute FormObject formObject) {
		HttpSession session = request.getSession();
		if (!formObject.getFechaVenta().isEmpty() && formObject.getIdCliente() != 0 && formObject.getIdProducto() != 0
				&& formObject.getCantidadVendida() != 0) {
			try {
				if (formObject.getIdFormObject() == 0) {
					Ventas ventaEntity = new Ventas(formObject);
					ventaEntity.setCliente(iClientesService.obtenerClienteporId(formObject.getIdCliente()));
					ventaEntity.setProducto(iProductosService.obtenerProductosporId(formObject.getIdProducto()));
					iVentasService.agregarVenta(ventaEntity);
					session.setAttribute(EnumConstantes.MESSAGE_INFO, "Buena por esa venta!!!");
				} else {
					Ventas ventaEntity = iVentasService.obtenerVentasporId(formObject.getIdFormObject());
					ventaEntity.setFechaVenta(formObject.getFechaVenta());
					ventaEntity.setCantidadVendida(formObject.getCantidadVendida());
					ventaEntity.setCliente(iClientesService.obtenerClienteporId(formObject.getIdCliente()));
					ventaEntity.setProducto(iProductosService.obtenerProductosporId(formObject.getIdProducto()));
					iVentasService.agregarVenta(ventaEntity);
					session.setAttribute(EnumConstantes.MESSAGE_INFO, "Informacion actualizada con Exito!!!");
				}
			} catch (Exception e) {
				session.setAttribute(EnumConstantes.MESSAGE_ERROR,
						"Error registrando la información, consulte al administrador!!!");
			}
		} else {
			session.setAttribute(EnumConstantes.MESSAGE_ERROR, "Algunos campos son obligatorios");
		}
		session.setAttribute(EnumConstantes.MENU, EnumConstantes.VENTAS);
		return EnumConstantes.REDIRECT_PAGE_UDISTRI;
	}

	@GetMapping("/web/actualiza/{vista}/{idElement}")
	public String actualizaAction(HttpServletRequest request, @PathVariable(value = "vista") String vista,
			@PathVariable(value = "idElement") long idElement) {
		HttpSession session = request.getSession();
		session.setAttribute(EnumConstantes.ELEMENT_EDICION_ID, idElement);
		switch (vista) {
		case EnumConstantes.CATEGORIAS:
			session.setAttribute(EnumConstantes.VISTA_EDICION, EnumConstantes.CATEGORIAS);
			break;
		case EnumConstantes.PRODUCTOS:
			session.setAttribute(EnumConstantes.VISTA_EDICION, EnumConstantes.PRODUCTOS);
			break;
		case EnumConstantes.CLIENTES:
			session.setAttribute(EnumConstantes.VISTA_EDICION, EnumConstantes.CLIENTES);
			break;
		case EnumConstantes.VENTAS:
			session.setAttribute(EnumConstantes.VISTA_EDICION, EnumConstantes.VENTAS);
			break;
		default:
			break;
		}
		return EnumConstantes.REDIRECT_PAGE_UDISTRI;
	}

	@GetMapping("/web/elimina/{vista}/{idElement}")
	public String eliminaAction(HttpServletRequest request, @PathVariable(value = "vista") String vista,
			@PathVariable(value = "idElement") long idElement) {
		HttpSession session = request.getSession();
		switch (vista) {
		case EnumConstantes.CATEGORIAS:
			iCategoriasService.eliminarCategoria(idElement);
			session.setAttribute(EnumConstantes.MENU, EnumConstantes.CATEGORIAS);
			session.setAttribute(EnumConstantes.MESSAGE_INFO, "Categoria eliminada con exito !!!");
			break;
		case EnumConstantes.PRODUCTOS:
			iProductosService.eliminarProducto(idElement);
			session.setAttribute(EnumConstantes.MENU, EnumConstantes.PRODUCTOS);
			session.setAttribute(EnumConstantes.MESSAGE_INFO, "Producto eliminado con exito !!!");
			break;
		case EnumConstantes.CLIENTES:
			iClientesService.eliminarCliente(idElement);
			session.setAttribute(EnumConstantes.MENU, EnumConstantes.CLIENTES);
			session.setAttribute(EnumConstantes.MESSAGE_INFO, "Cliente eliminado con exito !!!");
			break;
		case EnumConstantes.VENTAS:
			iVentasService.eliminarVenta(idElement);
			session.setAttribute(EnumConstantes.MENU, EnumConstantes.VENTAS);
			session.setAttribute(EnumConstantes.MESSAGE_INFO, "Venta eliminada con exito !!!");
			break;
		default:
			break;
		}
		return EnumConstantes.REDIRECT_PAGE_UDISTRI;
	}

	public List<ListObject> obtieneListaCategorias() {
		List<ListObject> listOpcionesCategoria = new ArrayList<ListObject>();
		Type listType = new TypeToken<ArrayList<ListaVistas>>(){}.getType();
		if(servicePython) {
			String response = sendGET("http://127.0.0.1:5000/bdserver/obtieneListaVista/" + EnumConstantes.CATEGORIAS);
			try {
				List<ListaVistas> listaVista = gson.fromJson(response, listType);
				for (ListaVistas idElement : listaVista) {
					listOpcionesCategoria.add(new ListObject(Long.parseLong(idElement.getColumn1()), idElement.getColumn2()));
				}
			}catch (Exception e) {
				System.out.println("Error --> " + e);
			}
		}else {
			List<Categorias> categoriasList = iCategoriasService.obtenerCategorias();
			for (Categorias idElement : categoriasList) {
				listOpcionesCategoria.add(new ListObject(idElement.getIdCategoria(), idElement.getNombreCategoria()));
			}	
		}
		return listOpcionesCategoria;
	}

	public List<ListObject> obtieneListaClientes() {
		List<ListObject> listOpcionesCategoria = new ArrayList<ListObject>();
		Type listType = new TypeToken<ArrayList<ListaVistas>>(){}.getType();		
		if(servicePython) {
			String response = sendGET("http://127.0.0.1:5000/bdserver/obtieneListaVista/" + EnumConstantes.CLIENTES);
			try {
				List<ListaVistas> listaVista = gson.fromJson(response, listType);
				for (ListaVistas idElement : listaVista) {
					listOpcionesCategoria.add(new ListObject(Long.parseLong(idElement.getColumn1()), idElement.getColumn2()));
				}
			}catch (Exception e) {
				System.out.println("Error --> " + e);
			}
		}else {
			List<Clientes> clientesList = iClientesService.obtenerClientes();
			for (Clientes idElement : clientesList) {
				listOpcionesCategoria.add(new ListObject(idElement.getIdCliente(), idElement.getNombreCliente()));
			}	
		}
		return listOpcionesCategoria;
	}

	public List<ListObject> obtieneListaProductos() {
		List<ListObject> listOpcionesCategoria = new ArrayList<ListObject>();
		if(servicePython) {
			String response = sendGET("http://127.0.0.1:5000/bdserver/obtieneListaVista/" + EnumConstantes.PRODUCTOS);
			Type listType = new TypeToken<ArrayList<ListaVistas>>(){}.getType();
			try {
				List<ListaVistas> listaVista = gson.fromJson(response, listType);
				for (ListaVistas idElement : listaVista) {
					listOpcionesCategoria.add(new ListObject(Long.parseLong(idElement.getColumn1()), idElement.getColumn2()));
				}
			}catch (Exception e) {
				System.out.println("Error --> " + e);
			}
		}else {
			List<Productos> productosList = iProductosService.obtenerProductos();
			for (Productos idElement : productosList) {
				listOpcionesCategoria.add(new ListObject(idElement.getIdProducto(), idElement.getNombreProducto()));
			}	
		}
		return listOpcionesCategoria;
	}

	public List<ListaVistas> obtieneListaVista(String vista) {
		Type listType = new TypeToken<ArrayList<ListaVistas>>(){}.getType();
		List<ListaVistas> listaVista = new ArrayList<>();
		switch (vista) {
		case EnumConstantes.CATEGORIAS:
			if(servicePython) {
				String response = sendGET("http://127.0.0.1:5000/bdserver/obtieneListaVista/" + EnumConstantes.CATEGORIAS);
				try {
					listaVista = gson.fromJson(response, listType) ;	
				}catch (Exception e) {
					System.out.println("Error --> " + e);
				}
			}else {
				List<Categorias> categorias = iCategoriasService.obtenerCategorias();
				for (Categorias idElement : categorias) {
					ListaVistas elementoFila = new ListaVistas();
					elementoFila.setColumn1(String.valueOf(idElement.getIdCategoria()));
					elementoFila.setColumn2(idElement.getNombreCategoria());
					elementoFila.setColumn3(idElement.getDescripcionCategoria());
					listaVista.add(elementoFila);
				}
			}
			break;
		case EnumConstantes.PRODUCTOS:
			if(servicePython) {
				String response = sendGET("http://127.0.0.1:5000/bdserver/obtieneListaVista/" + EnumConstantes.PRODUCTOS);
				try {
					listaVista = gson.fromJson(response, listType) ;	
				}catch (Exception e) {
					System.out.println("Error --> " + e);
				}
			}else {
				List<Productos> productosList = iProductosService.obtenerProductos();
				for (Productos idElement : productosList) {
					ListaVistas elementoFila = new ListaVistas();
					elementoFila.setColumn1(String.valueOf(idElement.getIdProducto()));
					elementoFila.setColumn2(idElement.getNombreProducto());
					elementoFila.setColumn3(idElement.getDescripcionProducto());
					elementoFila.setColumn4(idElement.getValorUnitarioProducto());
					elementoFila.setColumn5(idElement.getCategoria().getNombreCategoria());
					listaVista.add(elementoFila);
				}	
			}
			break;
		case EnumConstantes.CLIENTES:
			if(servicePython) {
				String response = sendGET("http://127.0.0.1:5000/bdserver/obtieneListaVista/" + EnumConstantes.CLIENTES);
				try {
					listaVista = gson.fromJson(response, listType) ;	
				}catch (Exception e) {
					System.out.println("Error --> " + e);
				} 
			}else {
				List<Clientes> clientesList = iClientesService.obtenerClientes();
				for (Clientes idElement : clientesList) {
					ListaVistas elementoFila = new ListaVistas();
					elementoFila.setColumn1(String.valueOf(idElement.getIdCliente()));
					elementoFila.setColumn2(idElement.getNombreCliente());
					elementoFila.setColumn3(idElement.getDireccionCliente());
					elementoFila.setColumn4(idElement.getTelefonoCliente());
					elementoFila.setColumn5(idElement.getEmailCliente());
					listaVista.add(elementoFila);
				}	
			}
			break;
		case EnumConstantes.VENTAS:
			if(servicePython) {
				String response = sendGET("http://127.0.0.1:5000/bdserver/obtieneListaVista/" + EnumConstantes.VENTAS);
				try {
					listaVista = gson.fromJson(response, listType) ;	
				}catch (Exception e) {
					System.out.println("Error --> " + e);
				} 
			}else {
				List<Ventas> ventasList = iVentasService.obtenerVentas();
				for (Ventas idElement : ventasList) {
					ListaVistas elementoFila = new ListaVistas();
					elementoFila.setColumn1(String.valueOf(idElement.getIdVenta()));
					elementoFila.setColumn2(idElement.getFechaVenta());
					elementoFila.setColumn3(idElement.getCliente().getNombreCliente());
					elementoFila.setColumn4(idElement.getProducto().getNombreProducto());
					elementoFila.setColumn5(String.valueOf(idElement.getCantidadVendida()));
					listaVista.add(elementoFila);
				}	
			}
			break;
		default:
			break;
		}				
		return listaVista;
	}
	
	public String sendGET(String urlStr) {
		String responseObject = "";
		try {
			URL obj = new URL(urlStr);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				responseObject = response.toString();
			} else {
				responseObject = "GET request no logra resolver.";
			}
		} catch (MalformedURLException | ProtocolException e) {
			responseObject = "GET-1 Exepcion request no logra resolver. " + e;
		} catch (IOException e) {
			responseObject = "GET-2 Exepcion request no logra resolver. " + e;
		}
		return responseObject;
	}

}
