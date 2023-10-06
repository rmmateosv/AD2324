package ClaseRAF;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import fBinario.Asignatura;
import fTexto.Alumno;

public class Principal {
	public static Scanner t = new Scanner(System.in);

	// Declaramos el objeto modelo que accede a los datos
	public static Modelo adNotas = new Modelo("notas.bin");

	// Declaramos un acceso a datos de la clase fichero binario - Asignaturas
	public static fBinario.Modelo adAsig = new fBinario.Modelo("asignaturas.bin");

	// DEclaramos un acceso a datos de la clase fichero texto - Alumnos
	public static fTexto.Modelo adAlumnos = new fTexto.Modelo("alumnos.txt");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Alta Nota");
			System.out.println("2-Mostrar Notas");
			System.out.println("3-Modificar Nota");
			System.out.println("4-Mostrar notas de un alumno");
			System.out.println("5-Borrar nota");
			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
			case 1:
				altaNota();
				break;
			case 2:
				mostrarNotas();
				break;
			case 3:
				modificarNota();
				break;
			case 4:
				mostrarNotasAlumno();
				break;
			case 5:
				borrarNota();
				break;
			}

		} while (opcion != 0);
	}
	
	private static void borrarNota() {
		// TODO Auto-generated method stub
		mostrarNotas();
		System.out.println("Introduce el id de la nota a modificar");
		Nota n = adNotas.obtenerNota(t.nextInt());t.nextLine();
		if(n!=null) {
			if(adNotas.borrarNota(n)) {
				System.out.println("Nota modificada");
			}
			else {
				System.out.println("Error al modificar la nota");
			}
		}
		else {
			System.out.println("Error, la nota no existe");
		}
	}

	public static void mostrarNotasAlumno() {
		// Mostrar alumnos
		ArrayList<Alumno> alumnos = adAlumnos.obtenerAlumnos();
		for (Alumno a : alumnos) {
			System.out.println(a);
		}
		System.out.println("Introduce el dni de un alumno");
		ArrayList<Nota> alumno = adNotas.obtenerNotasAlumno(t.nextLine());
		if(!alumno.isEmpty()) {
			for(Nota n:alumno) {
				System.out.println(n);
			}
					
		}else {
			
			System.out.println("Alumno no encontrado");
		}
	
		
	}

	private static void modificarNota() {
		// TODO Auto-generated method stub
		mostrarNotas();
		System.out.println("Introduce el id de la nota a modificar");
		Nota n = adNotas.obtenerNota(t.nextInt());t.nextLine();
		if(n!=null) {
			System.out.println("Introduce nueva nota");
			n.setNota(t.nextFloat());t.nextLine();
			if(adNotas.modificarNota(n)) {
				System.out.println("Nota modificada");
			}
			else {
				System.out.println("Error al modificar la nota");
			}
		}
		else {
			System.out.println("Error, la nota no existe");
		}
	}

	private static void mostrarNotas() {
		// TODO Auto-generated method stub
		ArrayList<Nota> notas = adNotas.obtenerNotas();
		for(Nota n:notas) {
			System.out.println(n);
		}
	}	

	private static void altaNota() {
		// TODO Auto-generated method stub
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Nota n = new Nota();
			// Obtener ID (AUNQUE NO ES LA MEJOR OPCIÓN, VAMOS A USAR EL AUTONUMÉRICO DE LOS
			// BINARIOS)
			n.setId(adAsig.obtenerId());
			// Mostrar alumnos
			ArrayList<Alumno> alumnos = adAlumnos.obtenerAlumnos();
			for (Alumno a : alumnos) {
				System.out.println(a);
			}
			System.out.println("Introduce DNI:");
			Alumno al = adAlumnos.obtenerAlumno(t.nextLine());
			if (al != null) {
				// Mostrar y comprobar asignaturas
				ArrayList<Asignatura> asigs = adAsig.obtenerAsignaturas();
				for (Asignatura as : asigs) {
					System.out.println(as);
				}
				System.out.println("Introduce id asignatura:");
				Asignatura as = adAsig.obtenerAsignatura(t.nextInt());
				t.nextLine();
				if (as != null) {
					// Rellenar nota
					n.setDni(al.getDni());
					n.setAsig(as.getId());
					// Fecha
					System.out.println("Introduce fecha examen (dd/mm/yyyy)");
					n.setFecha(formatoFecha.parse(t.nextLine()));
					System.out.println("Introduce la nota");
					n.setNota(t.nextFloat());t.nextLine();
					System.out.println("Valoración");
					n.setValoracion(t.nextLine());
					//Guardar en el fichero RandomAccessFile
					if(adNotas.crearNota(n)) {
						System.out.println("Nota creada");
					}
					else {
						System.out.println("Error: No se ha creado la nota");
					}

				} else {
					System.out.println("Error, la asignatura no existe");
				}
			} else {
				System.out.println("Error, el alumno no existe");
			}
		} catch (ParseException e) {
			// TODO: handle exception
			System.out.println("Error: Fecha incorrecta");
		}

	}

}
