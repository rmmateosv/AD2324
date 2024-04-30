package SKILLS;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

	public static Scanner tec = new Scanner(System.in);
	public static Modelo modelo = new Modelo();
	
	public static void main(String[] args) {
		
		int opc = -1;

		if (modelo.getConexion() != null) {
			do {
				System.out.println("---------------MENU--------------");
				System.out.println(" 0 - Salir del programa.");
				System.out.println("1 - Alta alumno/modalidad.");
				System.out.println("2 - Crear prueba.");
				System.out.println("3 - Registrar corrección.");
				System.out.println("4 - Mostrar ganadores.");
				System.out.println("Seleccione una opción: ");
				opc = tec.nextInt();
				tec.nextLine();
				System.out.println("---------------------------------");

				switch (opc) {
				case 1:
					ejercicio1();
					break;
				case 2:
					//ejercicio2();
					break;
				case 3:
					//ejercicio3();
					;
					break;
				case 4:
					//ejercicio4();
					break;
				}
			} while (opc != 0);
			modelo.cerrar();
		}

	}

	private static void ejercicio1() {
		
		System.out.println("Introduce el dni del alumno: ");
		String dni = tec.nextLine();
		
		Alumno alumno = modelo.obtenerAlumno(dni);
		if(alumno != null) {
			System.out.println("ALUMNO YA EXISTENTE en la modalidad " +alumno.getModalidad().getModalidad());
		}
		else {
			mostrarModalidades();
			System.out.println("Introduce el id de la modalidad (0 para crear una nueva): ");
			int opc = tec.nextInt(); tec.nextLine();
			if(opc == 0) {
				// Crear modalidad
				
			}
			else {
				//comprobar que existe la modalidad
			}
			// Crear alumno
			
		}
		
	}

	private static void mostrarModalidades() {
		ArrayList<Modalidades> listaMoladidades = modelo.obtenerModalidades();
		
		for (Modalidades modalidades : listaMoladidades) {
			System.out.println(modalidades);
		}
		
	}

}
