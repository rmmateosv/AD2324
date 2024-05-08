package HBA;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Principal {

	public static Scanner t = new Scanner(System.in);
	public static Modelo bd = new Modelo();
	public static Usuario us = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (bd.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Selecciona una opción");
				System.out.println("0 - Salir");
				System.out.println("1 - Seleccionar usuario");
				System.out.println("2 – Crear reproducción  ");
				System.out.println("3 - Crear capítulo ");
				System.out.println("4 - Eliminar capítulo ");
				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {
				case 1:
					ejercicio1();
					break;
				case 2:
					ejercicio2();
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

	private static void ejercicio2() {
		if (us != null) {
			mostrarSeries();
			
			System.out.println("Introduce el código de la serie a reproducir: ");
			int codigo = t.nextInt();
			t.nextLine();
			
			Serie s = bd.obtenerSerie(codigo);
			
			if (s != null) {
				for (Capitulo c : s.getListaCapitulos()) {
					System.out.println(c);
				}
				System.out.println("Introduce el id del capítulo: ");
				Capitulo c = bd.obtenerCapitulo(t.nextInt());
				t.nextLine();
				
				if (c != null && c.getSerie() == s) {
					Reproduccion r = new Reproduccion(new ClaveReproduccion(us, c), new Date(), 0);
					if (bd.crearReproduccion(r)) {
						System.out.println("Reproducción creada correctamente.");
					} else {
						System.err.println("Error, no se ha podido crear la reproducción.");
					}
				} else {
					System.err.println("Error, el capitulo no existe o no es de la seríe.");
				}
				
			} else {
				System.err.println("Error, no existe ningúna serie con el código introducido.");
			}
			
		} else {
			System.err.println("Error, debes logearte...");
		}
		
	}

	private static void mostrarSeries() {
		List<Serie> listaSeries = bd.obtenerSeries();
		
		for (Serie serie : listaSeries) {
			System.out.println(serie);
		}
		
	}

	private static void ejercicio1() {
		// TODO Auto-generated method stub
		mostrarUsuarios();
		System.out.println("Introduce el nick: ");
		us = bd.obtenerUsuario(t.nextLine());
		if (us == null) {
			System.err.println("ERROR usuario no encontrado");
		} else {
			System.out.println("Usuario logeado");
		}
	}

	private static void mostrarUsuarios() {
		// TODO Auto-generated method stub
		List<Usuario> usuarios = bd.obtenerUsuarios();
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}

}
