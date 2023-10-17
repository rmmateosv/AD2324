package JAXB;


import java.util.ArrayList;
import java.util.Scanner;





public class Principal {
	public static Scanner t = new Scanner(System.in);

	//Declaramos el acceso a datos para los fichero de objetos
	public static ClaseObject.Modelo adHistorial= new ClaseObject.Modelo();
	public static Modelo adJaxb = new Modelo();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Crear fichero xml");
			System.out.println("2-Mostrar fichero xml");
			System.out.println("3-Modificar nombre alumno en fichero XML");
			System.out.println("4-Borrar historial en fichero XML");
			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
				case 1:
					crearXML();
					break;
				case 2:
					mostrarXML();
					break;
				case 3:
					modificarNombreAlumno();
					break;
				case 4:
					borrarHistorial();
					break;
			}

		} while (opcion != 0);
	}

	private static void borrarHistorial() {
		
	}

	private static void modificarNombreAlumno() {
		
		
	}

	private static void mostrarXML() {
		// TODO Auto-generated method stub
		
	}

	private static void crearXML() {
		
	}


}
