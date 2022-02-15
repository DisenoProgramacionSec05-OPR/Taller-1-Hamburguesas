package uniandes.dpoo.taller1.consola;

import java.io.File;
import java.util.Iterator;
import java.util.ArrayList;

public class Pedido
{
	private static int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido;
	
	public Pedido(String nombreCliente, String direccionCliente)
	{
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		numeroPedidos ++;
		idPedido = numeroPedidos;
		itemsPedido = new ArrayList<Producto>();
	}
	
	
	public int getIdPedido()
	{
		return idPedido;
	}
	
	
	public void agregarProducto(Producto nuevoItem)
	{
		itemsPedido.add(nuevoItem);
	}
	
	
	private int getPrecioNetoPedido()
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
	
	
	private int getPrecioIVAPedido(int precioneto)
	{

		float IVA = precioneto*0.19F;
		
		return IVA;
	}
	
	
	private int getPrecioTotalPedido(int precioneto, int precioIVA)
	{

		return precioneto + precioIVA;
	}
	
	
	private String TextoFactura()
	{	
		int precioNeto = getPrecioNetoPedido();
		int precioIVA = getPrecioIVAPedido(precioNeto);
		int precio_total = getPrecioTotalPedido(precioNeto, precioIVA);
		
		String Factura ="Cliente: " + nombreCliente;
		Factura += "Direccion: " + direccionCliente;
		Factura += "Número del Pedido: " + Integer.toString(idPedido);
		Factura += "Pedido";
		
		Iterator<Producto> iter_items = itemsPedido.iterator();
		
		while (iter_items.hasNext())
		{
			Producto item = iter_items.next();
			String linea = item.generarTextoFactura();
		}
		
		Factura += "Neto: " + Integer.toString(precioNeto);
		Factura += "IVA: " + Integer.toString(precioiIVA);
		Factura += "Total: " + Integer.toString(precio_total);	
		
		
		return factura;
	}
	
	
	public void guardarFactura(File archivo)
	{
		String factura = generarTextoFactura();
	}
	
}
