package fTexto;

import java.util.Scanner;

public class Principal {
	public static Scanner t  = new Scanner(System.in);
	//Declaramos el objeto modelo que accede a los datos
	public static Modelo ad = new Modelo("alumnos.txt");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion=0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Alta Alumno");
			System.out.println("2-Mostrar Alumnos");
			System.out.println("3-Modificar Alumno");
			System.out.println("4-Borrar Alumno");
			System.out.println("5-Mostrar por dni");
			opcion = t.nextInt();t.nextLine();
			
			switch(opcion) {
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
		
		}while(opcion!=0);
	}
	private static void altaAlumno() {
		// TODO Auto-generated method stub
		//Pedir solamente el dni
		System.out.println("DNI:");
		String dni = t.nextLine();
		//Comprobar si existe el dni en el fichero
		Alumno a = ad.obtenerAlumno(dni);
		
		
	}

}
