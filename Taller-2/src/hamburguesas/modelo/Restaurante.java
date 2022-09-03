package hamburguesas.modelo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;

public class Restaurante {
	
	// Atributos
	private ArrayList<Pedido> pedidos;
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<ProductoMenu> menuBase;
	private ArrayList<Combo> combos;
	private Pedido pedidoEnCurso;
	
	// Constructores
	public Restaurante() {
		pedidos = new ArrayList<Pedido>();
		combos = new ArrayList<Combo>();
		menuBase = new ArrayList<ProductoMenu>();
		ingredientes = new ArrayList<Ingrediente>();
	}
	
	public void iniciarPedido(int idPedido, String nombreCliente, String direccionCliente) {
		pedidoEnCurso = new Pedido(idPedido, nombreCliente, direccionCliente);
	}
	
	
	// Métodos
	
	public void cerrarYGuardarPedido() {
		
		for(Pedido p: pedidos) {
			if(p == this.pedidoEnCurso) {
				String idPedido = String.valueOf(p.getIdPedido());
				System.out.println("Un pedido igual había sido ordenado por: " + idPedido);
			}
		}
		pedidos.add(this.pedidoEnCurso);
		pedidoEnCurso = null;
	}
	
	
	public Pedido getPedidoEnCurso() {
		return this.pedidoEnCurso;
	}
	
	public ArrayList<ProductoMenu> getMenuBase(){
		return this.menuBase;
	}
	
	public ArrayList<Combo> getCombos(){
		return this.combos;
	}
	
	public ArrayList<Ingrediente> getIngredientes(){
		return this.ingredientes;
	}
	
	public void cargarInformacionRestaurante(String archivoIngredientes, String archivoMenu, String archivoCombos) throws FileNotFoundException, IOException {
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
		
	}
	
	private void cargarIngredientes(String archivoIngredientes) throws FileNotFoundException, IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes));
		
		String linea = br.readLine();
		
		while (linea != null){
			String[] partes = linea.split(";");
			Ingrediente ingrediente = new Ingrediente(partes[0], Integer.parseInt(partes[1]));
			this.ingredientes.add(ingrediente);
			linea = br.readLine();
		}
	
		br.close();
	}
	
	private void cargarMenu(String archivoMenu) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoMenu));
		
		String linea = br.readLine();
		
		while (linea != null){
			
			String[] partes = linea.split(";");
			ProductoMenu item = new ProductoMenu(partes[0], Integer.parseInt(partes[1]));
			this.menuBase.add(item);
			linea = br.readLine();
		}
		
		br.close();
	}
	
	private void cargarCombos(String archivoCombos) throws FileNotFoundException, IOException{
		BufferedReader br = new BufferedReader(new FileReader(archivoCombos));
		
		String linea = br.readLine();
	
		while (linea != null){
			Combo combo = new Combo();
			String[] partes = linea.split(";");
			double descuento = Double.parseDouble((partes[1].split("%"))[0]);
			combo.setDescuento(descuento);
			combo.setNombreCombo(partes[0]);
			
			for (int i = 2; i < partes.length; i++) {
				ProductoMenu itemCombo = buscarItemMenuBase(partes[i]);
				
				if (itemCombo != null) {
					combo.agregarItemACombo(itemCombo);
				}
			}
			this.combos.add(combo);
			
			linea = br.readLine();
		}
		
		br.close();
	}
	
	private ProductoMenu buscarItemMenuBase(String nombreItem) {
		for(int i=0; i < this.menuBase.size(); i++){
			ProductoMenu item = this.menuBase.get(i);
			if (item.getNombre().equals(nombreItem)){
				return item;
			}
		}
		
		return null;
		
	}

}
