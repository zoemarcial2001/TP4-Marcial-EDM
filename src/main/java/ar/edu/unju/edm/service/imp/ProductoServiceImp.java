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
	
	@Autowired
	Producto unProducto;
	
	//ArrayList<Producto> listaDeProductos = new ArrayList<Producto>();
	private List<Producto> listadoProductos = ListadoProductos.productos;
	
	@Override
	public void guardarProducto(Producto unProducto) {
		// TODO Auto-generated method stub
		
		System.out.println(unProducto.getNombre());
		listadoProductos.add(unProducto);
		
		System.out.println(listadoProductos.size());

		LOGGER.info("METHOD: ingresando a Guardar Producto");
		LOGGER.info("RESULT: guardado " + listadoProductos.get(listadoProductos.size()-1).getNombre());
	}
	
	@Override
	public Producto crearProducto() {
		// TODO Auto-generated method stub
		return unProducto;
	}
	
	@Override
	public List<Producto> obtenerTodosProductos() {
		// TODO Auto-generated method stub
		return listadoProductos;
	}
	
	@Override
	public Producto encontrarUnProducto(int codigo) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listadoProductos.size(); i++){
		    if (listadoProductos.get(i).getCodProducto() == codigo) {
		    	unProducto = listadoProductos.get(i);
		    }
		}
		return unProducto;
	}

	@Override
	public void modificarProducto(Producto productoModificado) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listadoProductos.size(); i++){
		    if (listadoProductos.get(i).getCodProducto() == productoModificado.getCodProducto()) {
		    	listadoProductos.set(i, productoModificado);
		    }
		}	
	}
	@Override
	public void eliminarProducto(int id) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listadoProductos.size(); i++){
		    if (listadoProductos.get(i).getCodProducto() == id) {
		    	listadoProductos.remove(i);
		    }
		}	
	}

}
