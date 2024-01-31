import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Principal {
	public static Scanner t = new Scanner(System.in);
	public static Modelo bd = new Modelo();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (bd.getConexion()!=null) {
			int opcion = 0;
			do {
				System.out.println("Selecciona una opción");
				System.out.println("0-Salir");
				System.out.println("1-Crear Partido");
				System.out.println("2-Mostrar datos partido");
				System.out.println("2-Registrar datos partido");
				System.out.println("2-Borrar partido");
				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {
				case 1:
					crearPartido();
					break;
				case 2:

					break;
				case 3:

					break;
				case 4:

					break;
				}

			} while (opcion != 0);
			bd.cerrar();
		} else {
			System.out.println("Error no hay conexión");
		}
	}

	private static void crearPartido() {
		// TODO Auto-generated method stub
		SimpleDateFormat formato = new SimpleDateFormat("ddMMyy");
		mostrarJugadores();
		System.out.println("Código Jugador 1:");
		Jugador j1 = bd.obtenerJugador(t.nextInt());t.nextLine();
		if(j1!=null) {
			mostrarjugadores()
			System.out.println("Código Jugador 2:");
			Jugador j2 = bd.obtenerJugador(t.nextInt());t.nextLine();
			if(j2!=null && j1!=j2) {
				//Crear partido
				Partido p =new Partido();
				System.out.println("Fecha Partido (ddmmyy):");
				p.setFecha(formato.parse(t.nextLine()));
				System.out.println("Número de set (3 o 5)");
				p.setNumSets(t.nextInt());
				if(p.getNumSets()==3 || p.getNumSets()==5) {
				//Crear jugador_partido en partido
					p.getJugadores().add(new Jugador_Partido(new Clave(p, j1), null));
					p.getJugadores().add(new Jugador_Partido(new Clave(p, j2), null));
					if(crearPartido(p)) {
						System.out.println("Partido Creado");
					}
					else {
						System.out.println("Error, no se ha creado el partido");
					}
				}
				else {
					System.out.println("Error, número de sets debe ser 3 o 5");
				}
			}
			else {
				System.out.println("Error, jugador no existe o coincide con j1");
			}
		}
		else {
			System.out.println("Error, jugador no existe");
		}
	}

}
