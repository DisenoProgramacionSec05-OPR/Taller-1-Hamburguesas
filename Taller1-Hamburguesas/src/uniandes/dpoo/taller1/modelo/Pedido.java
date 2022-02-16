package uniandes.dpoo.taller1.consola;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;


public class Pedido
{
	private static int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private LinkedList<Producto> itemsPedido;
	
	public Pedido(String nombreCliente, String direccionCliente)
	{
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		numeroPedidos ++;
		idPedido = numeroPedidos;
		itemsPedido = new LinkedList<Producto>();
	}
	
	
	public int getIdPedido()
	{
		return idPedido;
	}
	
	public String getNombreCliente() 
	{
		return nombreCliente;	
	}
	
	public String getDireccionCliente()
	{
		return direccionCliente;
	}
	
	public LinkedList<Producto> getItemsPedido()
	{
		return itemsPedido;
	}
	
	
	public void agregarProducto(Producto nuevoItem)
	{
		itemsPedido.add(nuevoItem);
	}
	
	
	private int getPrecioTotalPedido()
	{
		int precio = 0;
		Iterator<Producto> iter_items = itemsPedido.iterator();
		
		while (iter_items.hasNext())
		{
			Producto item = iter_items.next();
			precio += item.getPrecio();
		}
		
		return precio;
	}
	
	
	private int getPrecioNetoPedido(int precioT)
	{

		float precioN = precioT/1.19F;
		
		return Math.round(precioN);
	}
	
	
	private int getPrecioIVAPedido(int precioN)
	{

		float IVA = precioN*0.19F;
		
		return Math.round(IVA);
	}
	
	
	private String generarTextoFactura()
	{
		int precioT = getPrecioTotalPedido();
		int pNeto = getPrecioNetoPedido(precioT);
		int pIVA = getPrecioIVAPedido(pNeto);
		
		String factura = "Cliente: " + nombreCliente;
		factura += "Dirección: " + direccionCliente;
		factura += "Número pedido: " + Integer.toString(idPedido);
		factura += "Productos";
		
		Iterator<Producto> iter_items = itemsPedido.iterator();
		
		while (iter_items.hasNext())
		{
			Producto item = iter_items.next();
			String texto = item.generarTextoFactura();
			factura += texto;
		}
		
		factura += "Precio neto: " + Integer.toString(pNeto);
		factura += "IVA: " + Integer.toString(pIVA);
		factura += "Precio total: " + Integer.toString(precioT);	
		
		
		return factura;
	}
	
	
	public void guardarFactura(File archivo)
	{
		String factura = generarTextoFactura();
		FileWriter fw;
		try {
			fw = new FileWriter(archivo);
			BufferedWriter bw = new BufferedWriter(fw);
			 bw.write(factura);
	         bw.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
}
