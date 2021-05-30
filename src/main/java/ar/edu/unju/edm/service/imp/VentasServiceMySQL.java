package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Venta;
import ar.edu.unju.edm.service.IVentaService;


@Service
@Qualifier("impVentasql")
public class VentasServiceMySQL implements IVentaService{

	@Autowired
	Venta venta;
	
	@Override
	public void guardarVenta(Venta unaVenta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Venta crearVenta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Venta> obtenerTodasVentas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Venta encontrarUnaVenta(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
