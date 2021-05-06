package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.IProductoService;
import ar.edu.unju.edm.util.ListadoProductos;

@Service
public class ProductoServiceImp implements IProductoService{

	private static final Log LOGGER = LogFactory.getLog(ProductoServiceImp.class);
	//como se hace la solucion del problema
	
	@Autowired
	Producto unProducto;
	
	//ArrayList<Producto> listaDeProductos = new ArrayList<Producto>();
	private List<Producto> listaDeProductos = ListadoProductos.productos;
	
	@Override
	public void guardarProducto(Producto unProducto) {
		// TODO Auto-generated method stub
		
		System.out.println(unProducto.getNombre());
		listaDeProductos.add(unProducto);
		
		System.out.println(listaDeProductos.size());

		LOGGER.info("METHOD: ingresando a Guardar Producto");
		LOGGER.info("RESULT: guardado " + listaDeProductos.get(listaDeProductos.size()-1).getNombre());
	}

	@Override
	public void modificarProducto(Producto productoModificado) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listaDeProductos.size(); i++){
		    if (listaDeProductos.get(i).getCodProducto() == productoModificado.getCodProducto()) {
		    	listaDeProductos.set(i, productoModificado);
		    }
		}	
	}
	
	@Override
	public Producto obtenerUnProducto(String nombreProducto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Producto> obtenerTodosProductos() {
		// TODO Auto-generated method stub
		return listaDeProductos;
	}

	
	//public Producto obtenerProductoNuevo() {
		// TODO Auto-generated method stub
		//return unProducto;
	//}

	
	//public Producto obtenerUltimoProducto() {
		// TODO Auto-generated method stub
		//int i = listaDeProductos.size() - 1;
		//return listaDeProductos.get(i);
	//}

	@Override
	public Producto encontrarUnProducto(int codigo) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listaDeProductos.size(); i++){
		    if (listaDeProductos.get(i).getCodProducto() == codigo) {
		    	unProducto = listaDeProductos.get(i);
		    }
		}
		return unProducto;
	}

	@Override
	public Producto obtenerProductoNuevo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarProducto(int id) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listaDeProductos.size(); i++){
		    if (listaDeProductos.get(i).getCodProducto() == id) {
		    	listaDeProductos.remove(i);
		    }
		}	
	}


}
