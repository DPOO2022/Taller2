package hamburguesas.modelo;

import java.util.ArrayList;

public class Combo implements IProducto{
	
	// Atributos
	private double descuento;
	private String nombreCombo;
	private ArrayList<ProductoMenu> itemsCombo;


	//Constructor
	public Combo() {
		itemsCombo = new ArrayList<ProductoMenu>();
	}

	// Métodos
	public void agregarItemACombo(ProductoMenu itemCombo) {
		this.itemsCombo.add(itemCombo);
	}
	
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	
	public void setNombreCombo(String nombre) {
		this.nombreCombo = nombre;
	}
	
	public int getPrecio() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getNombre() {
		return this.nombreCombo;
	}
	public double getDescuento() {
		return this.descuento;
	}
	
	public ArrayList<ProductoMenu> getItemsCombo() {
		return itemsCombo;
	}

	public void setItemsCombo(ArrayList<ProductoMenu> itemsCombo) {
		this.itemsCombo = itemsCombo;
	}

	public String generarTextoFactura() {
		// TODO Auto-generated method stub
		return null;
	}

}
