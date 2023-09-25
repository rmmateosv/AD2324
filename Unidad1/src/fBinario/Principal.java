package fBinario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
			
			}

		} while (opcion != 0);
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
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
