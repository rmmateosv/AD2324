package DOM;


import java.util.ArrayList;
import java.util.Scanner;

import ClaseObject.Historial;



public class Principal {
	public static Scanner t = new Scanner(System.in);

	//Declaramos el acceso a datos para los fichero de objetos
	public static ClaseObject.Modelo adHistorial= new ClaseObject.Modelo();
	public static Modelo adDOM = new Modelo();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Crear fichero xml");
			System.out.println("2-Mostrar fichero xml");
			System.out.println("3-Modificar nombre alumno en fichero XML");
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
			}

		} while (opcion != 0);
	}

	private static void modificarNombreAlumno() {
		// TODO Auto-generated method stub
		mostrarXML();
		System.out.println("Introduce dni:");
		String dni = t.nextLine();
		if(adDOM.existeAlumno(dni)) {
			System.out.println("Nuevo nombre:");
			String nombre = t.nextLine();
			if(adDOM.modificar(dni,nombre)) {
				System.out.println("Alumno modificado");
			}
			else {
				System.out.println("Error al modificar el nombre del alumno");
			}
		}
		
	}

	private static void mostrarXML() {
		// TODO Auto-generated method stub
		adDOM.mostrarXML();
	}

	private static void crearXML() {
		// TODO Auto-generated method stub
		ArrayList<Historial> h = adHistorial.obtenerHistoriales();
		//Crear fichero siempre, aunque no haya historiales
		System.out.println("Introduce nombre del IES:");
		String nombreIES = t.nextLine();
		if(adDOM.crearHistorial(nombreIES,h)) {
			System.out.println("Fichero generado");
		}
		else {
			System.out.println("Error al generar el fichero xml");
		}
	}


}
