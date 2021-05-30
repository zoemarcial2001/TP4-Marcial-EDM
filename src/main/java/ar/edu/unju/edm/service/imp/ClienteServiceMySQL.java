package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.repository.IClienteDAO;
import ar.edu.unju.edm.service.IClienteService;

@Service
@Qualifier("impmysql")
public class ClienteServiceMySQL implements IClienteService{

	@Autowired
	Cliente unCliente;
	
	@Autowired
	IClienteDAO clienteDAO;

	@Override
	public void guardarCliente(Cliente unCliente) {
		// TODO Auto-generated method stub
		String pw = unCliente.getPassword();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		unCliente.setPassword(bCryptPasswordEncoder.encode(pw));
		clienteDAO.save(unCliente);
	}

	@Override
	public Cliente crearCliente() {
		// TODO Auto-generated method stub
		return unCliente;
	}

	@Override
	public List<Cliente> obtenerTodosClientes() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDAO.findAll();
	}

	@Override
	public Cliente encontrarUnCliente(int dni) throws Exception{
		// TODO Auto-generated method stub
		return clienteDAO.findByNroDocumento(dni).orElseThrow(()->new Exception("el cliente no fue encontrado"));
	}

	@Override
	public void modificarCliente(Cliente clienteModificado) throws Exception{
		// TODO Auto-generated method stub
		Cliente clienteAModificar = clienteDAO.findByNroDocumento(clienteModificado.getNroDocumento()).orElseThrow(()->new Exception("El Cliente no fue encontrado"));
		cambiarCliente(clienteModificado, clienteAModificar);
		clienteDAO.save(clienteAModificar);
	}
	
	private void cambiarCliente(Cliente desde, Cliente hacia) {
		hacia.setApellido(desde.getApellido());
		hacia.setNombre(desde.getNombre());
		hacia.setFechaNacimiento(desde.getFechaNacimiento());
		hacia.setCodigoAreaTelefono(desde.getCodigoAreaTelefono());
		hacia.setNumTelefono(desde.getNumTelefono());
		hacia.setEmail(desde.getEmail());
	}

	@Override
	public void eliminarCliente(int dni) throws Exception {
		// TODO Auto-generated method stub
		Cliente clienteEliminar = clienteDAO.findByNroDocumento(dni).orElseThrow(()->new Exception("el cliente no fue encontrado"));
		clienteDAO.delete(clienteEliminar);
	}
}
