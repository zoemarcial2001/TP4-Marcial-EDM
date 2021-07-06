package ar.edu.unju.edm.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.IProductoService;

@Controller
public class ProductoController {
	
	@Autowired
	@Qualifier("unaImp")
	IProductoService productoService;
	
	
	@GetMapping("/producto/mostrar")
	public String cargarProducto(Model model) {
		model.addAttribute("unProducto", productoService.crearProducto());
		model.addAttribute("productos", productoService.obtenerTodosProductos());
		return("producto");
	}
	
	@PostMapping(value="/producto/guardar", consumes = "multipart/form-data")
	public String guardarNuevoProducto(@RequestParam("file") MultipartFile file, @ModelAttribute("unProducto") Producto nuevoProducto, Model model) throws IOException{
		byte[] content = file.getBytes();
		String base64 = Base64.getEncoder().encodeToString(content);
		nuevoProducto.setImagen(base64);
		productoService.guardarProducto(nuevoProducto);
		//mostrar el listado de producto luego de la carga de un producto
		System.out.println(productoService.obtenerTodosProductos().get(0).getMarca());
		model.addAttribute("productos", productoService.obtenerTodosProductos());
		return "redirect:/producto/mostrar";
	}

	@GetMapping("/producto/editar/{codProducto}")
	public String editarProducto(Model model, @PathVariable(name="codProducto") int codigo) throws Exception {
		try {
			Producto productoEncontrado = productoService.encontrarUnProducto(codigo);
			model.addAttribute("unProducto", productoEncontrado);	
			model.addAttribute("editMode", "true");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unProducto", productoService.crearProducto());
			model.addAttribute("editMode", "false");
		}
		model.addAttribute("productos", productoService.obtenerTodosProductos());
		return("producto");
	}

	@PostMapping("/producto/modificar")
	public String modificarProducto(@ModelAttribute("unProducto") Producto productoModificado, Model model) {
		try {
			productoService.modificarProducto(productoModificado);
			model.addAttribute("unProducto", new Producto());				
			model.addAttribute("editMode", "false");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// pasar las excepciones al html
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unProducto", productoModificado);			
			model.addAttribute("productos", productoService.obtenerTodosProductos());
			model.addAttribute("editMode", "true");
		}
		model.addAttribute("productos", productoService.obtenerTodosProductos());
		return("producto");
	}
	
	@GetMapping("/producto/eliminarProducto/{id}")
	public String eliminarProducto(Model model, @PathVariable(name="id") int id) {		
		try {			
			productoService.eliminarProducto(id);			
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/producto/mostrar";
	}
}
