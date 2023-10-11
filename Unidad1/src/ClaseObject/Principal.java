package ClaseObject;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import ClaseRAF.Nota;
import fBinario.Asignatura;
import fTexto.Alumno;

public class Principal {
	public static Scanner t = new Scanner(System.in);

	// Declaramos el objeto modelo que accede a los datos de notas
	public static ClaseRAF.Modelo adNotas = new ClaseRAF.Modelo("notas.bin");

	// Declaramos un acceso a datos de la clase fichero binario - Asignaturas
	public static fBinario.Modelo adAsig = new fBinario.Modelo("asignaturas.bin");

	// DEclaramos un acceso a datos de la clase fichero texto - Alumnos
	public static fTexto.Modelo adAlumnos = new fTexto.Modelo("alumnos.txt");
	
	//Declaramos el acceso a datos para los fichero de objetos
	public static Modelo adHistorial= new Modelo();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Crear Historial");
			System.out.println("2-Mostrar Historiales");
			System.out.println("3-Modificar Nota en el historial");
			System.out.println("4-Mostrar historial de un alumno");
			System.out.println("5-Borrar historial");
			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
			case 1:
				crearHistorial();
				break;
			case 2:
				mostrarHistorial();
				break;
			case 3:
				modificarNota();
				break;
			case 4:
				
				break;
			case 5:
				borrarHistorial();
				break;
			}

		} while (opcion != 0);
	}

private static void borrarHistorial() {
		
		System.out.println("--------------------------------------------------");
		ArrayList<Alumno> alumnos = adAlumnos.obtenerAlumnos();
		for(Alumno a:alumnos) {
			System.out.println(a);
		}
		System.out.println("--------------------------------------------------");
		System.out.println("Introduce el DNI del alumno para borrar su historial:");
		
		// Busqueda de los datos del alumnos
		Alumno alumno = adAlumnos.obtenerAlumno(t.nextLine());		
		if(alumno !=null) {
			Historial delHist = adHistorial.obtenerHistorial(alumno);
			if(delHist!=null) {
				if(!adHistorial.triturarHistorial(delHist)) {
					System.out.println("Error, al borrar el editorial");
				}
				else {
					System.out.println("Historial borrado");
				}
			}
			else {
				System.out.println("Error, no existe el historial");
			}
		}
		else {
			System.out.println("Error, el alumno no existe");
		}
		
		
	}
	private static void modificarNota() {
		// TODO Auto-generated method stub
		ArrayList<Alumno> alumnos = adAlumnos.obtenerAlumnos();
		for(Alumno a:alumnos) {
			System.out.println(a);
		}
		System.out.println("Introduce dni:");
		Alumno a = adAlumnos.obtenerAlumno(t.nextLine());
		if(a!=null) {
			Historial h = adHistorial.obtenerHistorial(a);
			if(h!=null) {
				//Mostrar solamente las notas
				for(Object[] o:h.getDatos()) {
					System.out.println(o[1]);
				}
				System.out.println("Introduce el id de la nota a modificar");
				Nota n = adHistorial.obtenerNota(a,t.nextInt()); t.nextLine();
				if(n!=null) {
					System.out.println("Introduce nueva nota:");
					n.setNota(t.nextFloat()); t.nextLine();
					if(adHistorial.modificarNota(a,n)) {
						System.out.println("Nota modificada");
					}
					else {
						System.out.println("Error al modificar la nota");
					}
				}
				else {
					System.out.println("Error, no existe la nota");
				}
				
			}
			else {
				System.out.println("Error, no existe el historial");
			}
		}
		else {
			System.out.println("Error, no existe el alumno");
		}
	}

	private static void mostrarHistorial() {
		// TODO Auto-generated method stub
		ArrayList<Historial> historiales = adHistorial.obtenerHistoriales();
		for(Historial h:historiales) {
			System.out.println(h);
		}
	}

	private static void crearHistorial() {
		// TODO Auto-generated method stub
		ArrayList<Alumno> alumnos = adAlumnos.obtenerAlumnos();
		for(Alumno a:alumnos) {
			System.out.println(a);
		}
		System.out.println("Introduce dni del alumno");
		Alumno a = adAlumnos.obtenerAlumno(t.nextLine());
		if(a!=null) {
			Historial h = adHistorial.obtenerHistorial(a);
			if(h==null) {
				h=new Historial();
				h.setAlumno(a);
				//Obtener notas y asignaturas
				ArrayList<Nota> notas = adNotas.obtenerNotasAlumno(a.getDni());
				for(Nota n:notas) {
					Object[] o = new Object[2];
					//Rellenamos nota
					o[1]=n;
					//REllenamos asignatura
					o[0]=adAsig.obtenerAsignatura(n.getAsig());
					//Añadir al historial
					h.getDatos().add(o);
				}
				if(adHistorial.crearHistorial(h)) {
					System.out.println("Historial creado");
				}
				else {
					System.out.println("Error al crerar el historial");
				}
			}
			else {
				System.out.println("Error, ya se ha creado el historial para el alumno");
			}
		}
		else {
			System.out.println("No existe el alumno");
		}
	}
	
	

}
