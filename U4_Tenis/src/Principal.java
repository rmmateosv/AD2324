import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	public static Scanner t = new Scanner(System.in);
	public static Modelo bd = new Modelo();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (bd.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Selecciona una opción");
				System.out.println("0-Salir");
				System.out.println("1-Crear Partido");
				System.out.println("2-Mostrar datos partido");
				System.out.println("3-Registrar datos partido");
				System.out.println("4-Borrar partido");
				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {
				case 1:
					crearPartido();
					break;
				case 2:
					mostrarPartido();
					break;
				case 3:
					registrarResutlados();
					break;
				case 4:
					borrarPArtido();
					break;
				}

			} while (opcion != 0);
			bd.cerrar();
		} else {
			System.out.println("Error no hay conexión");
		}
	}

	private static void borrarPArtido() {
		// TODO Auto-generated method stub
		mostrarPartidos();
		System.out.println("Código Partido");
		Partido p = bd.obtenerPartido(t.nextInt());t.nextLine();
		if(p!=null) {
			if(bd.borrarPartido(p)) {
				System.out.println("Partido borrado");
			}
			else {
				System.out.println("Error, no se ha borrado el partido");
			}
		}
		else {
			System.out.println("Error, partido no existe");
		}
		
	}

	private static void registrarResutlados() {
		// TODO Auto-generated method stub
		mostrarPartidos();
		System.out.println("Código Partido");
		Partido p = bd.obtenerPartido(t.nextInt());t.nextLine();
		if(p!=null) {
			for(Jugador_Partido jp:p.getJugadores()) {
				jp.setResultado("");
				//Introducir el resultado del jugador en cada set
				for (int i = 1; i <= p.getNumSets(); i++) {
					System.out.println("Puntos de "+jp.getClaveJP().getJugador().getNombre() +
							" en el set "+i+":");
					jp.setResultado(jp.getResultado()+"Set "+i+":"+t.nextInt()+" ");
					t.nextLine();
				}
				//Introducir si el judador ha ganado el partido
				if(p.getGanador()==null) {
					System.out.println("¿Ganador "+jp.getClaveJP().getJugador().getNombre() +
							"?(v/f)");
					String ganador = t.nextLine();
					if(ganador.equalsIgnoreCase("V")) {
						p.setGanador(jp.getClaveJP().getJugador());
					}
				}
			}
			if(p.getGanador()==null) {
				System.out.println("Error, no has seleccionado ganador");
			}
			else {
				if(bd.guardarDatos()) {
					System.out.println(p);
				}
				else {
					System.out.println("Error al guardar el partido");
				}
			}
		}
		else {
			System.out.println("Error, partido no existe");
		}
	}

	private static void mostrarPartido() {
		// TODO Auto-generated method stub
		mostrarPartidos();
		System.out.println("Código Partido");
		Partido p = bd.obtenerPartido(t.nextInt());t.nextLine();
		if(p!=null) {
			System.out.println("Partido:"+p.getCodigo()+
					"\tFecha:"+p.getFecha()+
					"\tNumSet:"+p.getNumSets()+
					"\tGanador:"+(p.getGanador()==null?"Pendiente ":p.getGanador().getNombre()));
			for (Jugador_Partido jp : p.getJugadores()) {
				System.out.println("Nombre:"+jp.getClaveJP().getJugador().getNombre()+
						"\tResultado:"+jp.getResultado());
			}
		}
	}

	private static void mostrarPartidos() {
		// TODO Auto-generated method stub
		ArrayList<Partido> partidos = bd.obtenerPartidos();
		for (Partido p : partidos) {
			System.out.println(p);
		}
	}

	private static void crearPartido() {
		// TODO Auto-generated method stub
		SimpleDateFormat formato = new SimpleDateFormat("ddMMyy");
		try {
			mostrarJugadores();
			System.out.println("Código Jugador 1:");
			Jugador j1 = bd.obtenerJugador(t.nextInt());
			t.nextLine();
			if (j1 != null) {
				mostrarJugadores();
				System.out.println("Código Jugador 2:");
				Jugador j2 = bd.obtenerJugador(t.nextInt());
				t.nextLine();
				if (j2 != null && j1 != j2) {
					// Crear partido
					Partido p = new Partido();
					System.out.println("Fecha Partido (ddmmyy):");
					p.setFecha(formato.parse(t.nextLine()));
					System.out.println("Número de set (3 o 5)");
					p.setNumSets(t.nextInt());
					if (p.getNumSets() == 3 || p.getNumSets() == 5) {
						// Crear jugador_partido en partido
						p.getJugadores().add(new Jugador_Partido(new Clave(p, j1), null));
						p.getJugadores().add(new Jugador_Partido(new Clave(p, j2), null));
						if (bd.crearPartido(p)) {
							System.out.println("Partido Creado");
						} else {
							System.out.println("Error, no se ha creado el partido");
						}
					} else {
						System.out.println("Error, número de sets debe ser 3 o 5");
					}
				} else {
					System.out.println("Error, jugador no existe o coincide con j1");
				}
			} else {
				System.out.println("Error, jugador no existe");
			}
		} catch (ParseException e) {
			// TODO: handle exception
			System.out.println("Fecha incorrecta");
		}

	}

	private static void mostrarJugadores() {
		// TODO Auto-generated method stub
		ArrayList<Jugador> j = bd.obtenerJugadores();
		for (Jugador jugador : j) {
			System.out.println(jugador);
		}
	}

}
