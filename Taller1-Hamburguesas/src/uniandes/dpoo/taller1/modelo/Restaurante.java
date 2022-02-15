package uniandes.dpoo.taller1.consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import uniandes.dpoo.taller1.modelo.Pedido;
public class Restaurante 
{
	public Restaurante()
	{
		
	}
	
	public void IniciarPedido(String nombreCliente,String direccionCliente)
	{
	    Pedido pedidoEnCurso = new Pedido(nombreCliente,direccionCliente);  
	}
	
	public void cerrarYGuardarPedido(Pedido pedidoEnCurso)
	{
		pedidoEnCurso = new ArrayList<Pedido>(this.IniciarPedido(null, null));
	}
	
	public Pedido getPedidoEnCurso(Pedido pedidoEnCurso)
	{
		return pedidoEnCurso;
	}

	
	public ArrayList<Producto> getMenuBase(Map menu) 
	{
		ArrayList menuBase = new ArrayList(); 
		for (String clave: menu.keySet()) 
		 {
		    int valor = menu.get(clave);
		    ProductoMenu producto = new ProductoMenu(clave,valor);
		    menuBase.add(producto);
		  }
	}
	
	public ArrayList<Ingrediente> getIngredientes()
	{
		
	}
	
	public void cargarInformacionRestaurante()
	{
	File archivoIngredientes = new File("/Taller1-Hamburguesas/__MACOSX/data/combos.txt"+"/Taller1-Hamburguesas/__MACOSX/data/ingredientes.txt"+"/Taller1-Hamburguesas/__MACOSX/data/menu.txt");
	
	BufferedReader menu = new BufferedReader(new FileReader(archivoIngredientes));

	  String strng;
	  while ((strng = menu.readLine()) != null)
	    System.out.println(strng);	  	  
	}
	
	
	private void cargarMenu()	
	{ 
		Map <String,Integer> menu = new HashMap< String,Integer>();		
		BufferedReader br = new BufferedReader(new FileReader("menu.txt"));
		String linea = br.readLine(); 
		linea = br.readLine();
		while (linea != null) 
		{
			// Separar los valores que estaban en una linea
			String[] partes = linea.split(";");
			String nombreProducto = partes[0];
			Integer precioBase = Integer.parseInt(partes[1]);
		    
			if ( menu.get(nombreProducto)== null )
		    	{
				 menu.put(nombreProducto,precioBase);
		    	}
					
		 linea = br.readLine(); // Leer la siguiente linea
	    }
		
		br.close();    
	 }
	
	
	
}


	



