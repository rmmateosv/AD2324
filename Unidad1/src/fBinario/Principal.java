package fBinario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	public static Scanner t = new Scanner(System.in);

	// Declaramos el objeto modelo que accede a los datos
	public static Modelo ad = new Modelo("asignaturas.bin");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Alta Asignatura");
			System.out.println("2-Mostrar Asignaturas");
			System.out.println("3-Baja Asignatura");
			System.out.println("4-Borrar Asignatura");
			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
			case 1:
				altaAsignatura();
				break;
			case 2:
				mostrarAsignaturas();
				break;
			case 3:
				bajaAsignatura();
				break;
			case 4:
				borrarAsignatura();
				break;
			
			}

		} while (opcion != 0);
	}
	
	private static void borrarAsignatura() {
		System.out.print("Introduce el id de la asignatura que quieres dar de baja:");
		Asignatura a = ad.obtenerAsignatura(t.nextInt());t.nextLine();
		if(a!=null) {
			if(ad.borrarAsignatura(a)) {
				System.out.println("Asignatura borrada");
			}
			else {
				System.out.println("Error al borrar la asignatura");
			}
		}
		else {
			System.out.println("Error, asignatura no existe");
		}
	}
	
	
	private static void bajaAsignatura() {
		System.out.print("Introduce el id de la asignatura que quieres dar de baja:");
		Asignatura a = ad.obtenerAsignatura(t.nextInt());t.nextLine();
		if(a!=null) {
			if(ad.darBajaAsignatura(a)) {
				System.out.println("Asignatura modificada");
			}
			else {
				System.out.println("Error al modificar la asignatura");
			}
		}
		else {
			System.out.println("Error, asignatura no existe");
		}
		
	
		
	}
	private static void mostrarAsignaturas() {
		// TODO Auto-generated method stub
		ArrayList<Asignatura> asigs = ad.obtenerAsignaturas();
		for(Asignatura as:asigs) {
			System.out.println(as);
		}
	}

	private static void altaAsignatura() {
		// TODO Auto-generated method stub
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			Asignatura as = new Asignatura();
			System.out.println("Nombre:");
			as.setNombre(t.nextLine());
			System.out.println("fechaRD (dd/mm/yyyy):");		
			as.setFechaRD(formatoFecha.parse(t.nextLine()));
			System.out.println("Créditos:");
			as.setCreditos(t.nextFloat()); t.nextLine();
			as.setActiva(true);
			//Obtener el id
			as.setId(ad.obtenerId());
			
			//Crear la asignaturas en fichero binario
			if(ad.crearAsig(as)) {
				System.out.println("Asignatura creada: "+as);
			}
			else {
				System.out.println("Error, no se ha creado la asignatura");
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error, formato de fecha incorrecto");
		}
		
		
	}
	
}
