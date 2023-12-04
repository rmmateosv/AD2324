import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	public static Modelo bd = new Modelo();
	public static Scanner t = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (bd.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Selecciona una opción");
				System.out.println("0-Salir");
				System.out.println("1-Crear Ruta");
				System.out.println("2-Añadir Lugar a ruta");
				System.out.println("3-Modificar duración ruta");
				System.out.println("4-Crear Lugar");
				System.out.println("5-Borrar Lugar");
				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {
				case 1:
					crearRuta();
					break;
				case 2:

					break;
				case 3:

					break;
				case 4:

					break;
				}

			} while (opcion != 0);
		} else {
			System.out.println("Error, no hay conexión con la BD");
		}
	}

	private static void crearRuta() {
		SimpleDateFormat formato=new SimpleDateFormat("ddMMyy");
		// TODO Auto-generated method stub
		mostrarParajes();
		System.out.println("Introduce id de paraje");
		Paraje p = bd.obtenerParaje(t.nextInt());t.nextLine();
		if(p!=null) {
			Ruta r = new Ruta();
			r.setParaje(p);
			System.out.println("Color (V-Verde A-Amarilla R-Roja):");
			String color = t.nextLine();
			switch (color.toLowerCase()){
				case "v": r.setColor("verde");break;
				case "a": r.setColor("amarillo");break;
				case "r": r.setColor("rojo");	break;	
			}
			if(r.getColor()==null) {
				System.out.println("Error, color incorrecto");
			}
			else {
				System.out.println("Fecha Ruta:(ddMMyy)");
				try {
					r.setFecha(formato.parse(t.nextLine()));
					System.out.println("Duración en minutos");
					r.setDuracion(t.nextInt());t.nextLine();
					if(bd.crearRuta(r)) {
						System.out.println("Ruta creada con id:"+r.getId());
						mostraRutas();
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					System.out.println("Error, fecha incorrecta");
				}
				
			}
			
		}
		else {
			System.out.println("Error, paraje no existe");
		}

	}

	private static void mostraRutas() {
		// TODO Auto-generated method stub
		ArrayList<Ruta> rutas = bd.obtenerRutas();
		for (Ruta r : rutas) {
			System.out.println(r);
		}
	}

	private static void mostrarParajes() {
		// TODO Auto-generated method stub
		ArrayList<Paraje> parajes = bd.obtenerParajes();
		for (Paraje p : parajes) {
			System.out.println(p);
		}
	}

}
