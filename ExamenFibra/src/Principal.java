import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

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
				System.out.println("1-Contratar Servicio");
				System.out.println("2-Crear Factura");
				System.out.println("3-Borrar Cliente");
				System.out.println("4-Informe de facturación");
				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {
				case 1:
					contratarServicio();
					break;
				
				}

			} while (opcion != 0);
			bd.cerrar();
			
		} else {
			System.out.println("Error, no hay conexión con la BD");
		}
	}

	private static void contratarServicio() {
		// TODO Auto-generated method stub
		mostrarClientes();
		System.out.println("Id cliente");
		Cliente c = bd.obtenerCliente(t.nextInt()); t.nextLine();
		if(c!=null) {
			ArrayList<Servicio> servicios = bd.obtenerServNoContratados(c);
			for (Servicio s : servicios) {
				System.out.println(s);
			}
			System.out.println("Id Servicio:");
			int idS = t.nextInt(); t.nextLine();
			boolean encontrado = false;
			for (Servicio s : servicios) {
				if(s.getIdS()==idS) {
					encontrado = true;
					//Contratar servicio
					//Comprobar si está en serv contratado pero dado de baja
					bd.obtenerServicioContratado
					
					
				}
			}
			if(!encontrado) {
				System.out.println("Servicio no existe o contratado");
			}
		}
		else {
			System.out.println("Error, cliente no existe");
		}
		
	}

	private static void mostrarClientes() {
		// TODO Auto-generated method stub
		ArrayList<Cliente> clientes = bd.obtenerClientes();
		for (Cliente c : clientes) {
			System.out.println(c);
		}
	}


}
