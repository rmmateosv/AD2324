import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
				System.out.println("9-Modificar gol a partido");
				System.out.println("10'-Borrar gol a partido");
				System.out.println("11-Finalizar partido");
				
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearEquipo();
					break;
				case 2:
					mostrarEquipos();
					break;
				
				}

			} while (opcion != 0);
		} else {
			System.out.println("Error de conexión");
		}
	}

	private static void mostrarEquipos() {
		// TODO Auto-generated method stub
		System.out.println("Introduce el nombre equipo(1-Todos)");
		String nombre = t.nextLine();
		if(nombre.equals("1")) {
			ArrayList<Equipo> equipos = bd.obtenerEquipos();
			for (Equipo e : equipos) {
				System.out.println(e);
			}
		}
		else {
			Equipo e = bd.obtenerEquipo(nombre);
			System.out.println(e);
		}
		
	}

	private static void crearEquipo() {
		// TODO Auto-generated method stub
		System.out.println("Nombre");
		String nombre = t.nextLine();
		Equipo e= bd.obtenerEquipo(nombre);
		if(e==null) {
			e= new Equipo();
			e.setNombre(nombre);
			int opcion;
			int i=0;
			//Pedir jugadores
			do {
				i++;
				System.out.println("Nombre jugador"+i+":");
				e.getJugadores().add(t.nextLine());				
				System.out.println("Desea añadir otro jugador?(0-No)");
				opcion = t.nextInt();t.nextLine();
			}while(opcion!=0);
			
			if(bd.crearEquipo(e)) {
				System.out.println("Equipo creado");
			}
			else {
				System.out.println("Error al crear el equipo");
			}
		}
		else {
			System.out.println("Error, ya existe el equipo");
		}
	}	

}
