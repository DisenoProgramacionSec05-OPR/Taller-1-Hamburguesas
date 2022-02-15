package uniandes.dpoo.taller1.consola;

public class Aplicacion 
{
	public void ejecutarAplicacion()
	{
		System.out.println("Estadísticas sobre los Juegos Olímpicos\n");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					cargarMenu();
				else if (opcion_seleccionada == 2)
					SeleccionarMenu();
				else if (opcion_seleccionada == 3)
					CargarCombo();
				else if (opcion_seleccionada == 4)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}
	public void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Cargar Menu");
		System.out.println("2. Ir a escoger");
		System.out.println("3.CargarCombos");
		System.out.println("4. Salir de la aplicación\n");
	}

		
			
	
			
	
	public static void main(String[] args) 
	{
		Aplicacion consola = new Aplicacion();
		consola.ejecutarAplicacion();

	}

}
