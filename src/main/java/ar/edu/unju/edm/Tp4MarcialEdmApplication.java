package ar.edu.unju.edm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.service.IClienteService;

@SpringBootApplication
public class Tp4MarcialEdmApplication implements CommandLineRunner {

	@Autowired
	@Qualifier("impmysql")
	IClienteService clienteService;
	
	@Autowired
	Cliente cliente;
	
	public static void main(String[] args) {
		SpringApplication.run(Tp4MarcialEdmApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	//inicializo un cliente para pruebas
		//cliente.setNroDocumento(17907623);
			//cliente.setNombre("Zoe");
			//cliente.setApellido("Marcial");
     		//cliente.setTipoDocumento("DNI");
		
			//cliente.setEmail("cor@gmail.com");
			//cliente.setPassword("1234");	
			
			//cliente.setFechaNacimiento(LocalDate.now());
			//cliente.setFechaUltimaCompra(LocalDate.now());
			//cliente.setCodigoAreaTelefono(388);
			//cliente.setNumTelefono(4436620);
			
			//clienteService.guardarCliente(cliente);	
	}

}
