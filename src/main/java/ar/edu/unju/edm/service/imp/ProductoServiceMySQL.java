package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.repository.IProductoDAO;
import ar.edu.unju.edm.service.IProductoService;

@Service
@Qualifier("unaImp")
public class ProductoServiceMySQL implements IProductoService{

	@Autowired
	Producto unProducto;
	
	@Autowired
	IProductoDAO productoDAO;
	
	@Override
	public void guardarProducto(Producto unProducto) {
		// TODO Auto-generated method stub
		productoDAO.save(unProducto);
	}

	@Override
	public Producto crearProducto() {
		// TODO Auto-generated method stub
		return unProducto;
	}

	@Override
	public List<Producto> obtenerTodosProductos() {
		// TODO Auto-generated method stub
		return (List<Producto>) productoDAO.findAll();
	}

	@Override
	public Producto encontrarUnProducto(int codigo) throws Exception{
		// TODO Auto-generated method stub
		return productoDAO.findById(codigo).orElseThrow(()->new Exception("el producto no existe"));
	}

	@Override
	public void modificarProducto(Producto productoModificado) {
		// TODO Auto-generated method stub
		productoDAO.save(unProducto);
	}

	@Override
	public void eliminarProducto(int id) throws Exception{
		// TODO Auto-generated method stub
		Producto productoEliminar = productoDAO.findById(id).orElseThrow(()->new Exception("el producto no existe"));
		productoDAO.delete(productoEliminar);
	}

}
