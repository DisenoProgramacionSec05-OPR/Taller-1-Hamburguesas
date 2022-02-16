package uniandes.dpoo.taller1.consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import uniandes.dpoo.taller1.modelo.Pedido;
import uniandes.dpoo.taller1.modelo.Producto;
import uniandes.dpoo.taller1.modelo.Restaurante;

public class Aplicacion
{
	private Restaurante restaurante;
	
	
	public void ejecutarOpcion()
	{
		System.out.println("Bienvenido al restaurante");

		boolean inicio = true;
		cargarInformacion();
		
		while (inicio)
		{
			try
			{
				mostrarOpciones();
				
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opcion"));
				
				if (opcion_seleccionada == 1)
					iniciarPedido();
				else if (opcion_seleccionada == 2)
					mostrarMenu();
				else if (opcion_seleccionada == 3)
					agregarElemento();
				else if (opcion_seleccionada == 4)
					cerrarPedido();
				else if (opcion_seleccionada == 5)
					consultarInformacion();
				else if (opcion_seleccionada == 6)
				{
					System.out.println("Saliendo de la aplicacion ...");
					inicio = false;
				}
				else
				{
					System.out.println("Por favor seleccione una opciÃ³n vÃ¡lida.");
				}
		}
		catch (NumberFormatException e)
		{
			System.out.println("La opciÃ³n seleccionada no es vÃ¡lida.");
		}
			
		}
	}
	
	
	public void mostrarOpciones()
	{
		System.out.println("Opciones de la aplicacion: ");
		System.out.println("1. Iniciar un nuevo pedido");
		System.out.println("2. Mostrar el menu del restaurante");
		System.out.println("3. Agregar un producto al pedido");
		System.out.println("4. Cerrar el pedido y guardar la factura");
		System.out.println("5. Consultar la informacion de un pedido antiguo dado su id");
		System.out.println("6. Salir de la aplicacion");
	}
	
	
	public void cargarInformacion()
	{
		System.out.println("Cargando archivos...");
		restaurante = new Restaurante();
		File ingredientes = new File("./data/ingredientes.txt");
		File menu = new File("./data/menu.txt"); 
		File combos = new File("./data/combos.txt");
		try {
			restaurante.cargarInformacionRestaurante(ingredientes,menu,combos);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}
	
	
	
	public void mostrarMenu()
	{
		System.out.println("MenÃº:");
		ArrayList<Producto> Menus = restaurante.getMenus();
		int numProductos = Menus.size();
		
		for (int i1 = 0; i1<numProductos; i1++)
		{
			Producto elProducto = Menus.get(i1);
			String nombreProducto = elProducto.getNombre();
			System.out.println("P" + i1 + ": " + nombreProducto);
		}
		
		
		System.out.println("Combos: ");
		ArrayList<Producto> combos = restaurante.getCombos();
		int numCombos = combos.size();
		
		for (int i2 = 0; i2<numCombos; i2++)
		{
			Producto elCombo = combos.get(i2);
			String nombreCombo = elCombo.getNombre();
			System.out.println("C" + i2 + ": " + nombreCombo);
		}
		
	}
	
	
		
	public void iniciarPedido()
	{
		String nombreCliente = input("Ingrese el nombre del cliente ");
		String direccionCliente = input("Ingrese la direccion del cliente ");
		restaurante.iniciarPedido(nombreCliente, direccionCliente);
	}
	
	
	
	public void agregarElemento()
	{
		Pedido pedidoA = restaurante.getpedidoB();
		ArrayList<Producto> Menus = restaurante.getMenus();
		ArrayList<Producto> combos = restaurante.getCombos();
		
		String numProducto = input("Ingrese el numero del producto que desea agregar como aparece en el menu");
		String typeIndex = numProducto.substring(0,1);
		Integer lstIndex = Integer.parseInt(numProducto.substring(1));
		
		if (typeIndex.equals("P"))
		{
			Producto elProducto = Menus.get(lstIndex);
			pedidoA.agregarProducto(elProducto);
			
			System.out.println("El producto '" + elProducto.getNombre() + "' fue agregado al pedido");
		}
		
		else if (typeIndex.equals("C"))
		{
			Producto elCombo = combos.get(lstIndex);
			pedidoA.agregarProducto(elCombo);
			
			System.out.println("El combo '" + elCombo.getNombre() + "' fue agregado al pedido");
		}
	}
	
	
	
	public void cerrarPedido()
	{
		Pedido pedidoA = restaurante.getpedidoB();
		File factura = new File("./pedidos/"+ pedidoA.getIdPedido() + ".txt");
		try {
			factura.createNewFile();
		} catch (IOException e) {

			e.printStackTrace();
		}
		pedidoA.guardarFactura(factura);
		restaurante.cerrarYGuardarPedido();
		System.out.println("Archivo creado");
	}
	
	
	
	public void consultarInformacion()
	{
		Scanner leer = new Scanner(System.in);
		System.out.println("Escribe el id del pedido: ");
		int id = leer.nextInt();
		ArrayList<Pedido> pedidos = restaurante.consultarPedidos();
		boolean elementoExiste = false;
		
		Iterator<Pedido> nombreIterator = pedidos.iterator();
		while(nombreIterator.hasNext()){
			Pedido elemento = nombreIterator.next();
			if (id == elemento.getIdPedido()) {
				String items = "";
				
				LinkedList<Producto> listaProductos = elemento.getItemsPedido();
				Iterator<Producto> iter_pedidos = listaProductos.iterator();
				while(iter_pedidos.hasNext())
				{
					Producto unProducto = iter_pedidos.next();
					
					if (listaProductos.indexOf(unProducto)>0)
						items += ", ";
					items += unProducto.getNombre();
				}
				
				System.out.println("ID: " + elemento.getIdPedido());
				System.out.println("Cliente: " + elemento.getNombreCliente());
				System.out.println("Direccion: " + elemento.getDireccionCliente());
				System.out.println("Items Pedido: " + items);
				elementoExiste = true;
				break;
				
			}
		}
		if (elementoExiste == false) {
			System.out.println("No se encontro ningun pedido con el ID");
			}
	}
	
	
	
	public String input(String mensaje)
	{
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
	
	
	
	public static void main(String[] args)
	{
		Aplicacion consola = new Aplicacion();
		consola.ejecutarOpcion();
	}

}
