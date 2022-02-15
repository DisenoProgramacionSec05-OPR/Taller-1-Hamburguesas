package uniandes.dpoo.taller1.consola;

public class ProductoMenu implements Producto
{
	private String nombre;
	
	private int precioBase;
	
	public ProductoMenu(String pNombre, int pPrecio) 
	{
		nombre=pNombre;
		precioBase=pPrecio;
	}

	@Override
	public int getPrecio() {
		// TODO Auto-generated method stub
		return precioBase;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

	@Override
	public String generarTextoFactura() {
		// TODO Auto-generated method stub
		return nombre+""+precioBase;
	}
}
