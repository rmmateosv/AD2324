import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {

	public static Scanner t = new Scanner(System.in);
	public static Modelo bd = new Modelo();

	public static void main(String[] args) {
		if (bd.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Selecciona una opción");
				System.out.println("0-Salir");
				System.out.println("1-Crear Paciente/Médico");
				System.out.println("2-Modificar Contacto Persona");
				System.out.println("3-Crear consulta");
				System.out.println("4-Atender Consulta(Modificar Historial)");
				System.out.println("5-Borrar Paciente/Médico");
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearPaciente();
					break;
				}

			} while (opcion != 0);
		} else {
			System.out.println("Error de conexión");
		}
	}

	private static void crearPaciente() {
		// TODO Auto-generated method stub
		System.out.println();
	}

}
