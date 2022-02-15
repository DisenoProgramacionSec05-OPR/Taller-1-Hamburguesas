package uniandes.dpoo.taller1.consola;

import java.util.ArrayList;

public class ProductoAjustado implements Producto
{
	private ProductoMenu base;
	private ArrayList<Ingrediente>agregados;
	private ArrayList<Ingrediente>eliminados;
	
	public ProductoAjustado(ProductoMenu productoMenuBase)
	{
		base=productoMenuBase;
	}

	public int getPrecio() 
	{
		return base.getPrecio();
	}

	public String getNombre() 
	{
		return base.getNombre();
	}

	public String generarTextoFactura() 
	{
		return base.generarTextoFactura();
	}
}
