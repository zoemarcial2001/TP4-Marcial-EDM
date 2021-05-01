package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.IProductoService;

@Controller
public class ProductoController {
	
	@Autowired
	IProductoService iProductoService;
	
	@GetMapping("/producto/mostrar")
	public String cargarProducto(Model model) {
		model.addAttribute("unProducto", iProductoService.obtenerProductoNuevo());
		model.addAttribute("productos", iProductoService.obtenerTodosProductos());
		return("producto");
	}

	@PostMapping("/producto/guardar")
	public String guardarNuevoProducto(@ModelAttribute("unProducto") Producto nuevoProducto, Model model) {
		iProductoService.guardarProducto(nuevoProducto);
		//mostrar el listado de producto luego de la carga de un producto
		System.out.println(iProductoService.obtenerTodosProductos().get(0).getMarca());
		model.addAttribute("productos", iProductoService.obtenerTodosProductos());
		return "redirect:/producto/mostrar";
	}

}
