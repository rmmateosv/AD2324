package taller;

import java.sql.Connection;
import java.util.Scanner;

public class Principal {
	
	public static Scanner t  = new Scanner(System.in);
	public static Modelo bd = new Modelo();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(bd.getConexion()!=null) {
			
			
			
			//Cerrar conexión
			bd.cerrar();
		}
		else {
			System.out.println("Error, no hay conexión con la BD");
		}
	}
	
	
	public static void menu(){
		int opcion=0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Info miCarpeta");
			
			opcion = t.nextInt();t.nextLine();
			
			switch(opcion) {
				case 1:
					
					break;
			}
		
		}while(opcion!=0);
	}
}
