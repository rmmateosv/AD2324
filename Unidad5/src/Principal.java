import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {
	
	public static Scanner t  = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		int opcion=0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Crear Paciente/Médico");
			System.out.println("2-Modificar Contacto Persona");
			System.out.println("3-Crear consulta");
			System.out.println("4-Modificar Historial Paciente");
			System.out.println("5-Borrar Paciente/Médico");			
			opcion = t.nextInt();t.nextLine();
			switch(opcion) {
				case 1:
					
					break;
			}
		
		}while(opcion!=0);
	}
	
	
	
}
