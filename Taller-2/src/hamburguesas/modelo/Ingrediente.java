package hamburguesas.modelo;

public class Ingrediente {
	
	// Atributos
	private String nombre="";
	private int costoAdicional=0;

	
	// Constructor
	public Ingrediente(String nombre, int costoAdicional) {
		this.nombre=nombre;
		this.costoAdicional=costoAdicional;
	}


	public String getNombre() {
		return nombre;
	}

	public int getCostoAdicional() {
		return costoAdicional;
	}
}
