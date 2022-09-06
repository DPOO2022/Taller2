package hamburguesas.modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	
	public void guardarFactura() {
		
		String texto = this.generarTectoFactura();
		File factura = new File("src\\data\\factura.txt");
		FileWriter escribir = null;
		try {
			escribir = new FileWriter(factura,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter linea = new PrintWriter(escribir);
		linea.println(texto);
		linea.close();
		try {
			escribir.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		String texto = "";
		for(IProducto p: itemsPedido) {
			texto = texto + p.generarTextoFactura();
		}
		return texto;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public void setItemsPedido(ArrayList<IProducto> itemsPedido) {
		this.itemsPedido = itemsPedido;
	}

	public ArrayList<IProducto> getItemsPedido(){
		return this.itemsPedido;
	}
}
