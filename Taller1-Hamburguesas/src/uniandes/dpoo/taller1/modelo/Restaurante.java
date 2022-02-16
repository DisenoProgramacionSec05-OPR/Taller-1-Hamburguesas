package uniandes.dpoo.taller1.consola;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



public class Restaurante
{
	private ArrayList<Pedido> pedidos;
	private int pedidoB; 
	private HashMap<String, ProductoMenu> Menus;
	private HashMap<String, Ingrediente> ingredientes;
	private HashMap<String, Combo> combos;
	
	public Restaurante()
	{
		pedidos = new ArrayList<Pedido>();
		Menus = new HashMap<String, ProductoMenu>();
		ingredientes = new HashMap<String, Ingrediente>();
		combos = new HashMap<String, Combo>();	
	}
	
	
	public void iniciarPedido(String nombreCliente, String direccionCliente)
	{
		boolean pedidoP = !haypedidoB();
		
		if (pedidoP)
		{
			pedidoB = 1;
			Pedido elPedido = new Pedido(nombreCliente, direccionCliente);
			pedidos.add(elPedido);	
		}
	}
	
	
	public ArrayList<Pedido> consultarPedidos()
	{
		return pedidos;
	}
	
	
	public void cerrarYGuardarPedido()
	{
		pedidoB = 0;
	}
	
	
	public Pedido getpedidoB()
	{
		return pedidos.get(pedidos.size() - 1);
		
	}
	
	
	public ArrayList<Producto> getMenus()
	{
		ArrayList<Producto> newList = new ArrayList<>(Menus.values());
		return newList;
	}
	
	
	public ArrayList<Producto> getCombos()
	{
		ArrayList<Producto> newList = new ArrayList<>(combos.values());
		return newList;
	}
	
	
	public ArrayList<Ingrediente> getIngredientes()
	{
		ArrayList<Ingrediente> newList = new ArrayList<>(ingredientes.values());
		return newList;
	}
	
	
	public void cargarInformacionRestaurante(File archivoIngredientes,
											 File archivoMenu,
											 File archivoCombos) throws FileNotFoundException
	{
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
	}
	
	
	private void cargarIngredientes(File archivoIngredientes) throws FileNotFoundException
	{
		Scanner Reader = new Scanner(archivoIngredientes);
		while (Reader.hasNextLine()) {
			String Line = Reader.nextLine();
			String[] partes = Line.split(";");
			String nombreIngrediente = partes[0];
			int costoAdicional = Integer.parseInt(partes[1]);
			
			Ingrediente ingr = new Ingrediente(nombreIngrediente, costoAdicional);
			ingredientes.put(nombreIngrediente, ingr);
		}
		Reader.close();
	}
	
	
	private void cargarMenu(File archivoMenu) throws FileNotFoundException
	{
		Scanner Reader = new Scanner(archivoMenu);
		while (Reader.hasNextLine()) {
			String Line = Reader.nextLine();
			String[] partes = Line.split(";");
			String nombreProducto = partes[0];
			int precio = Integer.parseInt(partes[1]);
			
			ProductoMenu Producto = new ProductoMenu(nombreProducto, precio);
			Menus.put(nombreProducto, Producto);
		}
		Reader.close();
	}
	
	
	private void cargarCombos(File archivoCombos) throws FileNotFoundException
	{
		Scanner Reader = new Scanner(archivoCombos);
		while (Reader.hasNextLine()) {
			String Line = Reader.nextLine();
			String[] partes = Line.split(";");
			String nombreCombo = partes[0];
			double descuento = Double.parseDouble(partes[1].replace("%", ""));
			descuento = descuento/100;

			Combo combo = new Combo(nombreCombo, descuento);
			
			for(int i=2; i<partes.length ; i++) {
				String nombreProducto = partes[i];
				ProductoMenu nProducto = Menus.get(nombreProducto);
				combo.agregarItemACombo(nProducto);
			}
			combos.put(nombreCombo, combo);
		}
		Reader.close();
	}
	
	
	public boolean haypedidoB()
	{
		boolean a = false;
		
		if (pedidoB==1)
			a = true;

		return a;
	}
}
