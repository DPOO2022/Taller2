package hamburguesas.modelo;

import java.util.ArrayList;

public class ProductoAjustado implements IProducto {

	// Atributos
	private ArrayList<Ingrediente> agregados;
	private ArrayList<Ingrediente> eliminados;
	private ProductoMenu base;
	
	// Constructor
	public ProductoAjustado(ProductoMenu base) {
		// TODO Auto-generated constructor stub
	}

	// Métodos
	public int getPrecio() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	public String generarTextoFactura() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
