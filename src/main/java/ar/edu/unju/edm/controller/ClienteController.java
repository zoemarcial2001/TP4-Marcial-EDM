package ar.edu.unju.edm.controller;

import java.time.LocalDate;
import java.time.Period;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.service.IClienteService;
@Controller
public class ClienteController {
	private static final Log LOGGER = LogFactory.getLog(ClienteController.class);
	
	@Autowired
	@Qualifier("unImp")
	IClienteService clienteService;
	
	@GetMapping("/cliente/mostrar")
	public String cargarCliente(Model model) {
		model.addAttribute("unCliente", clienteService.crearCliente());
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return("cliente");
	}
	
	@GetMapping("/cliente/editar/{nroDocumento}")
	public String editarCliente(Model model, @PathVariable(name="nroDocumento") int dni) throws Exception {
		try {
			Cliente clienteEncontrado = clienteService.encontrarUnCliente(dni);
			model.addAttribute("unCliente", clienteEncontrado);	
			model.addAttribute("editMode", "true");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unCliente", clienteService.crearCliente());
			model.addAttribute("editMode", "false");
		}
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return("cliente");
	}

	@PostMapping("/cliente/guardar")
	public String guardarNuevoProducto(@ModelAttribute("unCliente") Cliente nuevoCliente, Model model) {		
		LOGGER.info("METHOD: ingresando el metodo Guardar");
		clienteService.guardarCliente(nuevoCliente);		
		LOGGER.info("Tamaño del Listado: "+ clienteService.obtenerTodosClientes().size());
		//llamo a este método para que puedan ver cómo trabajar con fechas
		trabajarConFechas();
		return "redirect:/cliente/mostrar";
	}

	@PostMapping("/cliente/modificar")
	public String modificarCliente(@ModelAttribute("unCliente") Cliente clienteModificado, Model model) {
		try {
			clienteService.modificarCliente(clienteModificado);
			model.addAttribute("unCliente", new Cliente());				
			model.addAttribute("editMode", "false");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// pasar las excepciones al html
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unCliente", clienteModificado);			
			model.addAttribute("clientes", clienteService.obtenerTodosClientes());
			model.addAttribute("editMode", "true");
		}
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return("cliente");
	}
	
	public void trabajarConFechas() {
		//algunas cosas con fecha;
		//obtengo tres fechas
		LocalDate fecha1 = clienteService.obtenerTodosClientes().get(0).getFechaNacimiento();
		LocalDate fecha2 = LocalDate.now();
		LocalDate fecha3 = LocalDate.of(2020, 3, 25);
		//calculo el período entre dos de ellas
		Period periodo = Period.between(fecha1,fecha2);
		//una vez que tengo el período puedo saber sus cantidades en días meses y años
		int dias = periodo.getDays();		
		System.out.println("dias: "+dias);
		
		
		//revisen la salida en la consola, debería dar la diferencia en días
		//todo esto nos debe hacer revisar la documentación de las clases LocalDate, LocalTime, LocalDateTime y Period
	}
}