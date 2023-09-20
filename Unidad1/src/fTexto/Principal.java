package fTexto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Principal {
	public static Scanner t = new Scanner(System.in);

	// Declaramos el objeto modelo que accede a los datos
	public static Modelo ad = new Modelo("alumnos.txt");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Alta Alumno");
			System.out.println("2-Mostrar Alumnos");
			System.out.println("3-Modificar Alumno");
			System.out.println("4-Borrar Alumno");
			System.out.println("5-Mostrar por dni");
			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
			case 1:
				altaAlumno();
				break;
			case 2:

				break;
			case 3:

				break;
			case 4:

				break;
			case 5:

				break;

			}

		} while (opcion != 0);
	}

	private static void altaAlumno() {
		// TODO Auto-generated method stub
		try {
			// Pedir solamente el dni
			System.out.println("DNI:");
			String dni = t.nextLine();
			// Comprobar si existe el dni en el fichero
			Alumno a = ad.obtenerAlumno(dni);
			if (a == null) {
				// Alumno no existes, por lo que se puede crear
				a= new Alumno();
				// Pedir resto de datos
				System.out.println("Nombre:");
				a.setNombre(t.nextLine());
				System.out.println("Fecha Nacimiento (dd/mm/yyyy)");

				a.setFechaN(ad.formatoFecha.parse(t.nextLine()));

			} else {
				System.out.println("Error, el alumno ya existe");
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error, fecha incorrecta");
		}
	}

}
