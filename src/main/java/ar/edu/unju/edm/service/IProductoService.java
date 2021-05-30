package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;


@Service
public interface IProductoService {
	//no contiene codigo solo dice qué hace con Producto

	public void guardarProducto(Producto unProducto);
	public Producto crearProducto();
	public List<Producto> obtenerTodosProductos();
	public Producto encontrarUnProducto(int codProducto) throws Exception;
	public void modificarProducto(Producto productoModificado) throws Exception;
	public void eliminarProducto(int id) throws Exception;
	

}