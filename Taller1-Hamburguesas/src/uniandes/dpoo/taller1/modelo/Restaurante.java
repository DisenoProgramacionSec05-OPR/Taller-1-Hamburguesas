package uniandes.dpoo.taller1.consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Restaurante 
{
	public Restaurante()
	{
		
	}
	
	public void IniciarPedido()
	{
		String nombreCliente = input("Digitar nombre del cliente");
		String dirreccionCliente = input("Digitar dirrecion de Cliente");		
	}
	
	public void cerrarYGuardarPedido
	{
		pedido = new ArrayList<Pedido>(this.IniciarPedido(null, null));
	}
	
	public Pedido getPedidoEnCurso
	{
		
	}
	
	public ArrayList<Producto> getMenuBase 
	{
		
	}
	
	public ArrayList<Ingrediente> getIngredientes
	{
		
	}
	
	public void cargarInformacion()
	{
	File archivoIngredientes = new File("/Taller1-Hamburguesas/__MACOSX/data/combos.txt"+"/Taller1-Hamburguesas/__MACOSX/data/ingredientes.txt"+"/Taller1-Hamburguesas/__MACOSX/data/menu.txt");
	
	BufferedReader menu = new BufferedReader(new FileReader(archivoIngredientes));

	  String strng;
	  while ((strng = menu.readLine()) != null)
	    System.out.println(strng);	  	  
	}
}
	



