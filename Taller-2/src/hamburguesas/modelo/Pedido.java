package hamburguesas.modelo;

import java.io.File;
import java.util.ArrayList;

public class Pedido {
	
	// Atributos
	private int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<IProducto> itemsPedido;

	// Constructor
	public Pedido(int idPedido, String nombreCliente, String direccionCliente) {
		this.idPedido = idPedido;
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.itemsPedido = new ArrayList<IProducto>();
	}
	
	// Métodos
	public int getIdPedido() {
		return this.idPedido;
	}
	
	public void agregarProducto(IProducto nuevoItem) {
		this.itemsPedido.add(nuevoItem);
	}
	
	public void eliminarProducto(int pos) {
		this.itemsPedido.remove(pos);
	}
	
	public void guardarFactura(File archivo) {
		
	}
	
	public ArrayList<IProducto> getItemsPedido(){
		return this.itemsPedido;
	}
	
	private int getPrecioNetoPedido() {
		return 0;
	}
	
	private int getPrecioTotalPedido() {
		return 0;
	}

	private int getPrecioIvaPedido() {
		return 0;
	}

	private String generarTectoFactura() {
		return "";
	}
}
