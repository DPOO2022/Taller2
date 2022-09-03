package hamburguesas.consola;

import hamburguesas.modelo.Combo;
import hamburguesas.modelo.IProducto;
import hamburguesas.modelo.Ingrediente;
import hamburguesas.modelo.ProductoAjustado;
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
		System.out.println("4. Ver Items Pedido En Curso");
		System.out.println("5. Modificar Item Pedido En Curso");
		System.out.println("6. Eliminar Item Pedido En Curso");
		System.out.println("7. Cerrar Un Pedido y Guardar Factura");
		System.out.println("8. Consultar Infromación Pedido\n");
		
	}
	
	public void ejecutarOpcion(int opcion){
		if (opcion == 1) {
			mostrarMenu();
		}
		
		if(opcion == 2) {
			int error = iniciarPedido();
			if (error==1) {
				return;
			}
		}
		
        if(opcion == 2 || opcion == 3) {
			agregarProducto();
		}
        
        if(opcion == 4) {
			printItemsPedidoEnCurso();
		}
        
        if(opcion == 5) {
			modificarPedido();
		}
        
        if(opcion == 6) {
			eliminarProducto();
		}
        
        if(opcion == 7) {
        	restaurante.cerrarYGuardarPedido();
		}
        
        if(opcion == 8) {
			
		}
	}
	
	private void modificarPedido() {
		
		
	}
	
	private void printItemsPedidoEnCurso() {
		if (restaurante.getPedidoEnCurso()!=null) {
			if (restaurante.getPedidoEnCurso().getItemsPedido().get(0) != null) {
				System.out.println("\nActualmente su pedido cuenta con los siguientes items:\n");
				String nombre;
				for(int i = 0; i<restaurante.getPedidoEnCurso().getItemsPedido().size(); i++) {
					nombre = restaurante.getPedidoEnCurso().getItemsPedido().get(i).getNombre();
					System.out.println(Integer.toString(i+1) + ". '"+nombre+"'");
				}
			}
			else {
				System.out.println("\nSu Pedido aun no cuenta con ningún producto, para agregar seleccione la opcion 3 en el menú principal.");
			}
		}
		else {
			System.out.println("\nNo hay ningún pedido en curso, para iniciar un pedido seleccione la opción 2 en el menú principal");
		}
	}
	
	private void agregarProducto(){
		if (restaurante.getPedidoEnCurso() != null) {
			boolean continuar = true;
			while (continuar){
				System.out.println("\nAgregar Producto");
				mostrarMenu();
				
				int numProducto = Integer.parseInt(input("\nSeleccione el número del producto/combo que desea agregar"));
				
				ejecutarAgregarProducto(numProducto);
				
				int numContinuar = Integer.parseInt(input("\nPara agregar otro producto digite '1', para volver al menú principal digite '0'"));
				
				if (numContinuar == 0) {
					continuar = false;
				}
			}
		}
		else {
			System.out.println("\nAntes de agregar productos debe iniciar el pedido (opción 2)");
			return;
		}
	}
	private void eliminarProducto() {
		printItemsPedidoEnCurso();
		String[] itemsEliminar = input("\nSeleccione el número de todos los items que desea eliminar del pedido. \nSi no quiere eliminar ningún producto digite '0'").trim().split(",");
		if (Integer.parseInt(itemsEliminar[0])!= 0) {
			ejecutarEliminarProducto(itemsEliminar);
		}
		
	}
	private void ejecutarEliminarProducto(String[] itemsEliminar) {
		int pos;
		for (int i = 0; i < itemsEliminar.length;i++) {
			pos = Integer.parseInt(itemsEliminar[i]);
			restaurante.getPedidoEnCurso().eliminarProducto(pos-1);
		}
		
	}
	private void ejecutarAgregarProducto(int numProducto){
		if (numProducto > 0 && numProducto<=22) {
			ProductoMenu productoBase = restaurante.getMenuBase().get(numProducto-1);
			
			int numModificar = Integer.parseInt(input("\nPara agregar o eliminar ingredientes del producto digite '1'. \nDe lo contrario digite '0'"));
			
			if (numModificar == 1) {
				ProductoAjustado producto;
				producto = ejecutarModificarProducto(productoBase);
				restaurante.getPedidoEnCurso().agregarProducto(producto);
				System.out.println("\nEl producto '"+ producto.getNombre()+"' se ha agregado.");
			}
			else {
				restaurante.getPedidoEnCurso().agregarProducto(productoBase);
				System.out.println("\nEl producto '"+ productoBase.getNombre()+"' se ha agregado.");
			}
			
		}
		else if (numProducto > 22 && numProducto<=26) {
			Combo combo = restaurante.getCombos().get(numProducto-23);
			
			restaurante.getPedidoEnCurso().agregarProducto(combo);
			
			System.out.println("\nEl producto '"+ combo.getNombre()+"' se ha agregado.");
		}
		else {
			System.out.println("\nEl número del producto no es valido, seleccione otra opción");
			return;
		}
		
		
	}
	
	private ProductoAjustado ejecutarModificarProducto(ProductoMenu producto) {
		ProductoAjustado productoMod = new ProductoAjustado(producto);
		boolean continuar = true;
		int numContinuar;
		
		while (continuar) {
			mostrarIngredientes();
		
			String[] productosAgregar = input("\nSeleccione el número de todos los ingredientes que quiere agregar, separados por comas (','). \nSi no quiere aregar ningún producto digite '0'").trim().split(",");
			String[] productosQuitar = input("\nSeleccione el número de todos los ingredientes que quiere quitar, separados por comas (','). \nSi no quiere agregar ningún producto digite '0'").trim().split(",");
			if (Integer.parseInt(productosAgregar[0])!= 0) {
				ejecutarAgregarIngredientes(productosAgregar, productoMod);
			}
			
			if (Integer.parseInt(productosQuitar[0])!= 0) {
				ejecutarEliminarIngredientes(productosQuitar, productoMod);
			}
			
			numContinuar = Integer.parseInt(input("\nSi desea hacer otra modificación digite '1', de lo contrario digite '0'"));
			
			if (numContinuar == 0) {
				continuar = false;
			}
		}
		
		return productoMod;
		
	}
	
	private void ejecutarAgregarIngredientes(String[] productosAgregar, ProductoAjustado productoMod) {
		int pos;
		Ingrediente ingrediente;
		for(int i = 0; i < productosAgregar.length; i++){
			
			pos = Integer.parseInt(productosAgregar[i])-1;
			
			ingrediente = restaurante.getIngredientes().get(pos);
			
			productoMod.agregarIngrediente(ingrediente);
			
		}
	}
	
	private void ejecutarEliminarIngredientes(String[] productosQuitar, ProductoAjustado productoMod) {
		int pos;
		Ingrediente ingrediente;
		for(int i = 0; i < productosQuitar.length; i++){
			
			pos = Integer.parseInt(productosQuitar[i])-1;
			
			ingrediente = restaurante.getIngredientes().get(pos);
			
			productoMod.eliminarIngrediente(ingrediente);
			
		}
	}
	
	private int iniciarPedido(){
		if (restaurante.getPedidoEnCurso() == null) {
			int idPedido = Integer.valueOf(input("Ingrese su id"));
			String nombreCliente = input("Ingrese su nombre");
			String direccion = input("Ingrese su dirección");
			restaurante.iniciarPedido(idPedido, nombreCliente, direccion);
			return 0;
		}
		else {
			System.out.println("\nYa hay un pedido en curso, cierre el pedido antes de crear uno nuevo.");
			return 1;
		}
	}
	
	private void mostrarIngredientes() {
		
		  System.out.println("\nIngredientes Adicionales:");
		  Ingrediente ingrediente;
		  for(int i = 0; i < restaurante.getIngredientes().size(); i++) { 
			  ingrediente = restaurante.getIngredientes().get(i);
			  System.out.println(Integer.toString(i+1)+". Nombre: " + ingrediente.getNombre() + ", Precio: " + Integer.toString(ingrediente.getCostoAdicional())); 
		  }
		 
	}
	
	private void mostrarMenu() {
		System.out.println("\nMenu:");
		
		IProducto producto;
		Combo combo;
		int cont = 1;
		double precioCombo;
		for(int i = 0; i < restaurante.getMenuBase().size(); i++) {
			producto = restaurante.getMenuBase().get(i);
			
			System.out.println(Integer.toString(cont) + ". Nombre: " + producto.getNombre() + ", Precio: " + Integer.toString(producto.getPrecio()));
			cont += 1;
		}
		
		System.out.println("\nCombos:");
		
		for(int i = 0; i < restaurante.getCombos().size(); i++) {
			combo = restaurante.getCombos().get(i);
			System.out.println(Integer.toString(cont) + ". Nombre: " + combo.getNombre());
			System.out.println("	Incluye: ");
			precioCombo = 0;
			
			for (int i1 = 0; i1 < combo.getItemsCombo().size(); i1++) {
				producto = combo.getItemsCombo().get(i1);
				System.out.println("		" + producto.getNombre());
				precioCombo += producto.getPrecio();
			}
			
			System.out.println("	Precio: " + Double.toString(precioCombo) + ", Precio Descuento: " + Double.toString(precioCombo*(1-(combo.getDescuento()/100)))+"\n");
			cont += 1;
		}
		
		/*
		 * System.out.println("\nIngredientes Adicionales:");
		 * 
		 * for(int i = 0; i < restaurante.getIngredientes().size(); i++) { ingrediente =
		 * restaurante.getIngredientes().get(i);
		 * System.out.println(Integer.toString(i+1)+". Nombre: " +
		 * ingrediente.getNombre() + ", Precio: " +
		 * Integer.toString(ingrediente.getCostoAdicional())); }
		 */
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
