package Examen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Principal {
	public static Scanner t = new Scanner(System.in);
	public static Modelo ad = new Modelo();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-E1");
			System.out.println("2-E2");
			System.out.println("3-E3");
			System.out.println("4-E4");
			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
			case 1:
				ejer1();
				break;
			case 2:
				ejer2();
				break;
			case 3:
				ejer3();
				break;
			case 4:
				ejer4();
				break;
			}

		} while (opcion != 0);
	}

	private static void ejer4() {
		// TODO Auto-generated method stub
		mostrarEmpleados();
		System.out.println("Introduce un id de empleado: ");
		int id = t.nextInt();
		Empleado e = ad.obtenerEmpleado(id);
		if (e != null) {
			ArrayList<Mensaje> mensajes = ad.obtenerMensajes(e);
			Chat c = new Chat();
			c.setIdEmpleado(e.getId());
			c.setNombre(e.getNombre());
			c.setTotal(mensajes.size());
			int contador=1;
			for (Mensaje mensaje : mensajes) {				
				MensajesXML m = new MensajesXML(mensaje.getFecha(), contador, mensaje.getNombre(), 
						mensaje.getTexto(), mensaje.isLeido());
				c.getMensajes().add(m);
				contador++;
			}
			if(ad.marshall(c)) {
				System.out.println("Chat generado");
			}else {
			}
		}else {
			System.out.println("No existe el empleado ");
		}
	}

	private static void ejer3() {
		// TODO Auto-generated method stub
		mostrarEmpleados();
		System.out.println("Introduce un id de empleado: ");
		int id =t.nextInt();
		Empleado e = ad.obtenerEmpleado(id);
		if (e != null) {
			if(ad.borrarMensajes(id)) {
				System.out.println("Todos los mensajes han sido borrados");
				ArrayList<Mensaje> todoMensaje= ad.obtenerTodosMensajes();
				mostrarMensajes(todoMensaje);
			}else {
			}
		}else {
			System.out.println("No existe el empleado ");
		}
	}

	private static void mostrarMensajes(ArrayList<Mensaje> todoMensaje) {
		// TODO Auto-generated method stub
		for (Mensaje mensaje : todoMensaje) {
			System.out.println(mensaje);
		}
	}

	private static void ejer2() {
		// TODO Auto-generated method stub
		mostrarEmpleados();
		System.out.println("Introduce Id");
		Empleado e = ad.obtenerEmpleado(t.nextInt());t.nextLine();
		if(e==null) {
			System.out.println("Error, no existe el empleado");
		}
		else {
			//Marcar mensajes como leidos
			if(ad.marcarLeidos(e)) {
				System.out.println("Mensajes leídos");
			}
			else {
				System.out.println("Error, no se han marcado los mensajes como leídos");
			}
			//Mostrar mensajes del fichero
			mostrarMensajes(e);
		}
	}

	private static void mostrarMensajes(Empleado e) {
		// TODO Auto-generated method stub
		ArrayList<Mensaje> mensajes = ad.obtenerMensajes(e);
		for (Mensaje mensaje : mensajes) {
			System.out.println(mensaje);
		}
	}

	private static void ejer1() {
		// TODO Auto-generated method stub
		mostrarEmpleados();
		System.out.println("Introduce Id");
		Empleado e = ad.obtenerEmpleado(t.nextInt());t.nextLine();
		if(e==null) {
			System.out.println("Error, no existe el empleado");
		}
		else {
			//Comprobar si esta activo
			if(e.isActivo()) {
				
				System.out.println("Introduce texto del mensaje");
				Mensaje m = new Mensaje(new Date(), e.getId(), 
						e.getNombre(), t.nextLine(), false);
				if(ad.crearMensaje(m)) {
					System.out.println("Mensaje creado");
				}
			}
			else {
				System.out.println("Error, el empleado está de baja");
			}
		}
	}

	private static void mostrarEmpleados() {
		// TODO Auto-generated method stub
		ArrayList<Empleado> empleados = ad.obtenerEmpleados();
		for(Empleado e:empleados) {
			System.out.println(e);
		}
		
	}

}
