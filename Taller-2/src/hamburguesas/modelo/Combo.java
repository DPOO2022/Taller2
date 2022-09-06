package hamburguesas.modelo;

import java.util.ArrayList;

public class Combo implements IProducto{
	
	// Atributos
	private double descuento;
	private String nombreCombo;
	private ArrayList<IProducto> itemsCombo;


	//Constructor
	public Combo() {
		itemsCombo = new ArrayList<IProducto>();
	}

	// Métodos
	public void agregarItemACombo(IProducto itemCombo) {
		this.itemsCombo.add(itemCombo);
	}
	
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	
	public void setNombreCombo(String nombre) {
		this.nombreCombo = nombre;
	}
	
	public int getPrecio() {
		int precio=0;
		for (int i=0; i<itemsCombo.size();i++) {
			precio = precio + itemsCombo.get(i).getPrecio();
		}
		int precioTotal= (int) (precio - (precio*(descuento/100)));
		return precioTotal;
	}

	public String getNombre() {
		return this.nombreCombo;
	}
	public double getDescuento() {
		return this.descuento;
	}
	
	public ArrayList<IProducto> getItemsCombo() {
		return itemsCombo;
	}

	public String generarTextoFactura() {
		String producto = this.getNombre();
		String precio = String.valueOf(this.getPrecio());
		String respuesta = producto + " --- " + precio + " -  ";
		return respuesta;
	}

}
