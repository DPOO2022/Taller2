package hamburguesas.modelo;
import java.io.File;
import java.util.ArrayList;



public class Restaurante {
	
	// Atributos
	private ArrayList<Pedido> pedidos;
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<IProducto> menuBase;
	private ArrayList<Combo> combos;
	private Pedido pedidoEnCurso;
	
	// Constructor
	public Restaurante() {
		// TODO
	}
	
	// Métodos
	public void iniciarPedido(String nombreCliente, String direccionCliente) {
		
	}
	
	public void cerrarYGuardarPedido() {
		
	}
	
	public Pedido getPedidoEnCurso() {
		return this.pedidoEnCurso;
	}
	
	public ArrayList<IProducto> getMenuBase(){
		return this.menuBase;
	}
	
	public ArrayList<Ingrediente> getIngredientes(){
		return this.ingredientes;
	}
	
	public void cargarInformacionRestaurante(File archivoInredientes, File archivoMenu, File archivoCombos) {
		
	}
	
	private void cargarIngredientes(File archivoIngredientes) {
		
	}
	
	private void cargarMenu(File archivoMenu) {
		
	}
	
	private void cargarCombos(File archivoCombos){}

}
