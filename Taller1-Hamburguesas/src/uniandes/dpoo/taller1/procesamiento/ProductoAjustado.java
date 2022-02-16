package uniandes.dpoo.taller1.consola;

import java.util.Iterator;
import java.util.LinkedList;


public class ProductoAjustado implements Producto
{
	private ProductoMenu base;
	private LinkedList<Ingrediente> agregados;
	private LinkedList<Ingrediente> eliminados;
	
	public ProductoAjustado(ProductoMenu base)
	{
		this.base = base;
		agregados = new LinkedList<Ingrediente>();
		eliminados = new LinkedList<Ingrediente>();
	}

	
	public String getNombre()
	{
		String nombre = base.getNombre();
		return nombre;
	}
	
	
	public int getPrecio()
	{
		
		int precio = base.getPrecio();
		Iterator<Ingrediente> iter_ag = agregados.iterator();
		
		while (iter_ag.hasNext())
		{
			Ingrediente i_agregado = iter_ag.next();
			precio += i_agregado.getCostoAdicional();
		}
		
		return precio;
	}
	
	
	public String generarTextoFactura()
	{
		String nombre = getNombre();
		String precio_base = Integer.toString(base.getPrecio());
		String precio_total = Integer.toString(getPrecio());
		String texto = "Producto ajustado";
		texto += "Producto Base: " + nombre + "  " + precio_base;
		
		
		Iterator<Ingrediente> iter_ag = agregados.iterator();
		Iterator<Ingrediente> iter_el = eliminados.iterator();
		
		while (iter_ag.hasNext())
		{
			Ingrediente i_agregado = iter_ag.next();
			String ag_nombre = i_agregado.getNombre();
			String adicional = Integer.toString(i_agregado.getCostoAdicional());
			texto += "Extra de " + ag_nombre + " " + adicional;
		}
		
		while (iter_el.hasNext())
		{
			Ingrediente i_eliminado = iter_el.next();
			String el_nombre = i_eliminado.getNombre();
			texto += "Sin " + el_nombre;
		}
		
		texto += "Total " + nombre + " " + precio_total;
		
		return texto;
	}
}
