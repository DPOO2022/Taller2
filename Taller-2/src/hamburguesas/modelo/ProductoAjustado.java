package hamburguesas.modelo;

import java.util.ArrayList;

public class ProductoAjustado implements IProducto {

	// Atributos
	private ArrayList<Ingrediente> agregados;
	private ArrayList<Ingrediente> eliminados;
	private ProductoMenu base;
	
	// Constructor
	public ProductoAjustado(ProductoMenu producto) {
		this.base = producto;
		this.agregados = new ArrayList<Ingrediente>();
		this.eliminados = new ArrayList<Ingrediente>();
	}


	// Métodos
	public void agregarIngrediente(Ingrediente ingrediente) {
		this.agregados.add(ingrediente);
		
	}
	public void eliminarIngrediente(Ingrediente ingrediente) {
		this.eliminados.add(ingrediente);
	}
	public int getPrecio() {
		// TODO Auto-generated method stub
		int precioTotal= base.getPrecio();
		for (int i=0; i<agregados.size();i++) {
			precioTotal+=agregados.get(i).getCostoAdicional();
		}
		return precioTotal;
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return this.base.getNombre();
	}

	public String generarTextoFactura() {
		String producto = this.getNombre();
		String precio = String.valueOf(this.getPrecio());
		String respuesta = producto + " --- " + precio;
		return respuesta;
	}
	
}
