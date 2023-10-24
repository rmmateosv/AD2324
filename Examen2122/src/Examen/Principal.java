package Examen;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {
	public static Scanner t = new Scanner(System.in);
	public static Modelo ad = new Modelo();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-E1");
			System.out.println("2-E2");
			System.out.println("3-E3");
			System.out.println("4-E4");
			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
			case 1:
				ejer1();
				break;
			case 2:
				ejer2();
				break;
			case 3:
				ejer3();
				break;
			
			}

		} while (opcion != 0);
	}

	private static void ejer3() {
		// TODO Auto-generated method stub
		
	}

	private static void ejer2() {
		// TODO Auto-generated method stub
		
	}

	private static void ejer1() {
		// TODO Auto-generated method stub
		mostrarEmpleados();
		System.out.println("Introduce Id");
		Empleado e = ad.obtenerEmpleado(t.nextInt());t.nextLine();
		if(e==null) {
			System.out.println("Error, no existe el empleado");
		}
		else {
			//Comprobar si esta activo
			if(e.isActivo()) {
				
				System.out.println("Introduce texto del mensaje");
				Mensaje m = new Mensaje(new Date(), e.getId(), 
						e.getNombre(), t.nextLine(), false);
			}
			else {
				System.out.println("Error, el empleado está de baja");
			}
		}
	}

	private static void mostrarEmpleados() {
		// TODO Auto-generated method stub
		ArrayList<Empleado> empleados = ad.obtenerEmpleados();
		for(Empleado e:empleados) {
			System.out.println(e);
		}
		
	}

}
