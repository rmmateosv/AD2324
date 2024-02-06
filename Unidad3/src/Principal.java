import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Principal {

	public static Scanner t = new Scanner(System.in);
	public static Modelo bd = new Modelo();
	public static SimpleDateFormat formato = new SimpleDateFormat("ddMMyyHHmm");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (bd.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Selecciona una opción");
				System.out.println("0-Salir");
				System.out.println("1-Crear Equipo");
				System.out.println("2-Mostrar Equipo/s");
				System.out.println("3-Borrar Equipo");
				System.out.println("4-Añadir Jugador a Equipo");
				System.out.println("5-Borrar Jugador de Equipo");
				System.out.println("6-Crear Partido");
				System.out.println("7-Añadir gol a partido");
				System.out.println("8-Finalizar partido");
				
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					
					break;
				
				}

			} while (opcion != 0);
		} else {
			System.out.println("Error de conexión");
		}
	}

}
