package hamburguesas.modelo;

public class ProductoMenu implements IProducto{
	
	// Atributos
	private String nombre;
	private int precioBase;

	// Constructor
	public ProductoMenu(String nombre, int precioBase) {
		this.nombre=nombre;
		this.precioBase=precioBase;
	}

	// Métodos
	public String getNombre() {
		return this.nombre;
	}
	
	public String generarTextoFactura() {
		return this.nombre +" --- "+ this.precioBase;
	}

	@Override
	public int getPrecio() {
		return this.precioBase;
	}

}
