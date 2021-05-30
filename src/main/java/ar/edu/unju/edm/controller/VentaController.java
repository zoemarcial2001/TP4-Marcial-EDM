package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.model.Venta;
import ar.edu.unju.edm.service.IClienteService;
import ar.edu.unju.edm.service.IProductoService;
import ar.edu.unju.edm.service.IVentaService;

@Controller
public class VentaController {

	@Autowired
	@Qualifier("unaImp")
	IProductoService productoService;
	
	@Qualifier("impmysql")
	IClienteService clienteService;
	
	@Autowired
	Producto productoSeleccionado;
	
	@Autowired
	IVentaService iVentaService;
	
	@GetMapping("/producto/ventas")
	public String cargarVentas(Model model) {
		model.addAttribute("unProducto", productoService.crearProducto());
		model.addAttribute("productos", productoService.obtenerTodosProductos());
		return("ventas");
	}
	
	@GetMapping("/producto/vender/{codigo}")	
	public String realizarVenta(Model model, @PathVariable(name="codigo") Integer codigo) throws Exception {
		Venta venta = new Venta();	
		
		try {	
			productoSeleccionado = productoService.encontrarUnProducto(codigo);
			venta = iVentaService.crearVenta();		
			venta.setProducto(productoSeleccionado);
			model.addAttribute("venta",venta);
			model.addAttribute("clientes",clienteService.obtenerTodosClientes());
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());		
		}		
		return "venta-modal";
	}
	
	@PostMapping("/producto/vender")
	public String guardarNuevoProducto(@ModelAttribute("venta") Venta unaVenta, Model model) {
		iVentaService.guardarVenta(unaVenta);
		
		return("redirect:/producto/ventas");
	}
}
