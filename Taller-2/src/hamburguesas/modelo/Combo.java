package hamburguesas.modelo;

import java.util.ArrayList;

public class Combo implements IProducto{
	
	// Atributos
	private double descuento;
	private String nombreCombo;
	private ArrayList<IProducto> itemsCombo;

	//Constructor
	public Combo() {
		// TODO Auto-generated constructor stub
	}

	// Métodos
	public void agregarItemACombo(IProducto itemCombo) {
		
	}
	
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
