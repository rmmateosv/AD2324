package SKILLS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
					ejercicio2();
					break;
				case 3:
					// ejercicio3();
					;
					break;
				case 4:
					// ejercicio4();
					break;
				}
			} while (opc != 0);
			modelo.cerrar();
		}

	}

	private static void ejercicio2() {
		// TODO Auto-generated method stub
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMYY");
			mostrarModalidades();
			System.out.println("Introduce un código de modalidad");
			Modalidades modalidad = modelo.obtenerModalidad(tec.nextInt());
			tec.nextLine();
			if (modalidad == null) {
				System.err.println("No se ha encontrado la modalidad");
			} else {

				Prueba prueba = new Prueba();
				prueba.setModalidad(modalidad.getId());
				System.out.println("Introduce la fecha de la Modalidad");
				prueba.setFecha(sdf.parse(tec.nextLine()));
				System.out.println("Introduce la descripción de la prueba");
				prueba.setDescripcion(tec.nextLine());
				System.out.println("Introduce la puntuación de la prueba");
				prueba.setPuntuacion(tec.nextInt());tec.nextLine();
				if (modelo.crearPrueba(prueba)) {
					ArrayList<Prueba> pruebas = modelo.obtenerPruebas(modalidad.getId());
					int total = 0;
					for (Prueba p : pruebas) {
						System.out.println(p);
						total += p.getPuntuacion();
					}
					System.out.println("La puntutación total es: " +total);
				} else {
					System.err.println("No se ha podido crear la prueba");
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void ejercicio1() {

		System.out.println("Introduce el dni del alumno: ");
		String dni = tec.nextLine();

		Alumno alumno = modelo.obtenerAlumno(dni);
		if (alumno != null) {
			System.out.println("ALUMNO YA EXISTENTE en la modalidad " + alumno.getModalidad().getModalidad());
		} else {
			mostrarModalidades();
			System.out.println("Introduce el id de la modalidad (0 para crear una nueva): ");
			int opc = tec.nextInt();
			tec.nextLine();
			Modalidades modalidad = null;
			boolean error = false;
			if (opc == 0) {
				// Crear modalidad
				modalidad = new Modalidades();
				System.out.println("Introduce la descripción de la modalidad");
				modalidad.setModalidad(tec.nextLine());

				if (modelo.crearModalidad(modalidad)) {
					System.out.println("Modalidad " + modalidad.getId() + " Creada con éxito");

				} else {
					System.err.println("Se ha producido un error al crear la modalidad");
					error = true;
				}

			} else {
				// comprobar que existe la modalidad
				modalidad = modelo.obtenerModalidad(opc);
				if (modalidad == null) {
					System.err.println("Modalidad no encontrada");
					error = true;
				}
			}
			if (!error) {
				System.out.println("Introduce el nombre del alumno");
				alumno = new Alumno(0, dni, tec.nextLine(), 0, false, modalidad);
				if (!modelo.crearAlumno(alumno)) {
					System.err.println("El alumno no ha podido ser creado");
				}

			}

		}

	}

	private static void mostrarModalidades() {
		ArrayList<Modalidades> listaMoladidades = modelo.obtenerModalidades();

		for (Modalidades modalidades : listaMoladidades) {
			System.out.println(modalidades);
		}

	}

}
