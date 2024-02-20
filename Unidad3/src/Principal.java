import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {

	public static Scanner t = new Scanner(System.in);
	public static Modelo bd = new Modelo();
	public static SimpleDateFormat formato = new SimpleDateFormat("ddMMyy");
	
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
				System.out.println("32-Borrar Equipo si solamente tiene 1 jugador");
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
				case 3:
					borrarEquipo();
					break;
				case 32:
					borrarEquipo1J();
					break;
				case 4:
					insertarJuador();
					break;
				case 6:
					crearPartido();
					break;
				
				case 7:
					crearGol();
					break;
				
				}

			} while (opcion != 0);
		} else {
			System.out.println("Error de conexión");
		}
	}

	private static void crearGol() {
		// TODO Auto-generated method stub
		mostrarPartidos();
		System.out.println();
	}

	private static void crearPartido() {
		// TODO Auto-generated method stub
		try {
			mostrarEquipos();
			System.out.println("Introduce Equipo Local");
			Equipo local = bd.obtenerEquipo(t.nextLine());
			if(local!=null) {
				mostrarEquipos();
				System.out.println("Introduce Equipo visitante");
				Equipo visi = bd.obtenerEquipo(t.nextLine());
				if(visi!=null && 
						!visi.getNombre().equalsIgnoreCase(local.getNombre())) {
					System.out.println("Fecha Partido(ddmmyy)");
					Date fecha = formato.parse(t.nextLine());
					Partido p = new Partido(local.getNombre(), 
							visi.getNombre(), fecha);
					if(bd.crearPartido(p)) {
						System.out.println("Partido creado");
						mostrarPartidos();
					}
					else {
						System.out.println("Error al crear el partido");
					}
				}
				else {
					System.out.println("Error, equipo no existe o coincide con local");
				}
			}
			else {
				System.out.println("Error, equipo no existe");
			}
		} catch (ParseException e) {
			// TODO: handle exception
			System.out.println("FEcha Errónea");
		}
		
	}

	private static void mostrarPartidos() {
		// TODO Auto-generated method stub
		ArrayList<Partido> partidos = bd.obtenerPartidos();
		for (Partido p : partidos) {
			System.out.println(p);
		}
	}

	private static void insertarJuador() {
		// TODO Auto-generated method stub
		mostrarEquipos();
		Equipo e = bd.obtenerEquipo(t.nextLine());
		if(e!=null) {
			System.out.println("Nombre del jugador");
			String jugador = t.nextLine();
			
			//Null si no existe y nombre equipo actual si existe
			String eActual = bd.existeJugador(jugador); 
			if(eActual==null) {
				if(bd.insertarJugador(e,jugador)) {
					System.out.println("Jugador creado");
				}
				else {
					System.out.println("Error al crear el jugador");
				}
			}
			else {
				System.out.println("Error, jugador existe en equipo "+ eActual);
				//Preguntar si cambiar cuando los equipos no coincidan
				if(!e.getNombre().equalsIgnoreCase(eActual)) {
					System.out.println("Desea cambiar de equipo al jugador?(y-Sí)");
					String respuesta = t.nextLine();
					if(respuesta.equalsIgnoreCase("Y")) {
						if(bd.cambiarEquipo(e,jugador,eActual)) {
							System.out.println("Jugador cambiado de equipo");
						}
					}
				}
			}
		}
		else {
			System.out.println("Error, no existe el equipo");
		}
		
	}

	private static void borrarEquipo1J() {
		// TODO Auto-generated method stub
		mostrarEquipos();
		System.out.println("Nombre del equipo a borrar");
		Equipo e = bd.obtenerEquipo(t.nextLine());
		if(e!=null) {
			if(bd.borrarEquipo1J(e)) {
				System.out.println("Equipo borrado");
			}
			else {
				System.err.println("Error al borrar el equipo");
			}
		}
	}

	private static void borrarEquipo() {
		// TODO Auto-generated method stub
		mostrarEquipos();
		System.out.println("Nombre del equipo a borrar");
		Equipo e = bd.obtenerEquipo(t.nextLine());
		if(e!=null) {
			if(bd.borrarEquipo(e)) {
				System.out.println("Equipo borrado");
			}
			else {
				System.err.println("Error al borrar el equipo");
			}
		}
	}

	private static void mostrarEquipos() {
		// TODO Auto-generated method stub
		System.out.println("Introduce el nombre equipo(1-Todos)");
		String nombre = t.nextLine();
		if(nombre.equals("1")) {
			ArrayList<Equipo> equipos = bd.obtenerEquipos();
			if(equipos.isEmpty())
				{System.out.println("No hay equipos");}
			else {
				for (Equipo e : equipos) {
					System.out.println(e);
				}
			}
		}
		else {
			Equipo e = bd.obtenerEquipo(nombre);
			if(e!=null)
				System.out.println(e);
			else
				System.out.println("Equipo no existe");
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
