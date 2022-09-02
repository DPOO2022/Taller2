package hamburguesas.consola;

import hamburguesas.modelo.Combo;
import hamburguesas.modelo.Ingrediente;
import hamburguesas.modelo.ProductoMenu;
import hamburguesas.modelo.Restaurante;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Aplicacion {
	// Atributos
	
	private Restaurante restaurante;
	
	// Constructor
	public Aplicacion(){
		restaurante = new Restaurante();
	}

	//Métodos
	public void ejecutarAplicacion() throws FileNotFoundException, IOException{
		restaurante.cargarInformacionRestaurante("src\\data\\ingredientes.txt", "src\\data\\menu.txt", "src\\data\\combos.txt");
		boolean ejecutar = true;
		while (ejecutar) {
			mostrarOpciones();
			int opcion = Integer.parseInt(input("Seleccione una opción"));
			ejecutarOpcion(opcion);
			
		}
	}
	
	public void mostrarOpciones() {
		// TODO
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Mostrar Menú");
		System.out.println("2. Iniciar Nuevo Pedido");
		System.out.println("3. Agregar Elemento al Pedido");
		System.out.println("4. Cerrar Un Pedido y Guardar Factura");
		System.out.println("5. Consultar Infromación Pedido\n");
		
	}
	
	public void ejecutarOpcion(int opcion){
		if (opcion == 1) {
			System.out.println("\nMenu:");
			
			ProductoMenu producto;
			Combo combo;
			Ingrediente ingrediente;
			double precioCombo;
			for(int i = 0; i < restaurante.getMenuBase().size(); i++) {
				producto = restaurante.getMenuBase().get(i);
				
				System.out.println(Integer.toString(i+1) + ". Nombre: " + producto.getNombre() + ", Precio: " + Integer.toString(producto.getPrecio()));
			}
			
			System.out.println("\nCombos:");
			
			for(int i = 0; i < restaurante.getCombos().size(); i++) {
				combo = restaurante.getCombos().get(i);
				System.out.println(Integer.toString(i+1) + ". Nombre: " + combo.getNombre());
				System.out.println("	Incluye: ");
				precioCombo = 0;
				
				for (int i1 = 0; i1 < combo.getItemsCombo().size(); i1++) {
					producto = combo.getItemsCombo().get(i1);
					System.out.println("		" + producto.getNombre());
					precioCombo += producto.getPrecio();
				}
				
				System.out.println("	Precio: " + Double.toString(precioCombo) + ", Precio Descuento: " + Double.toString(precioCombo*(1-(combo.getDescuento()/100)))+"\n");
			}
			
			System.out.println("\nIngredientes Adicionales:");
			
			for(int i = 0; i < restaurante.getIngredientes().size(); i++) {
				ingrediente = restaurante.getIngredientes().get(i);
				System.out.println(Integer.toString(i+1)+". Nombre: " + ingrediente.getNombre() + ", Precio: " + Integer.toString(ingrediente.getCostoAdicional()));
			}
		}
		
		if(opcion == 2) {
			String nombreCliente = input("Ingrese su nombre");
			String direccion = input("Ingrese su dirección");
			restaurante.iniciarPedido(nombreCliente, direccion);
		}
		
        if(opcion == 2 || opcion == 3) {
			if (restaurante.getPedidoEnCurso() != null) {
				
			}
			else {
				System.out.println("Antes de agregar productos debe iniciar el pedido (opción 2)");
				return;
			}
		}
        
        if(opcion == 4) {
			
		}
        
        if(opcion == 5) {
			
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Aplicacion consola = new Aplicacion();
		consola.ejecutarAplicacion();
	}
	
	private String input(String mensaje) {
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
}
