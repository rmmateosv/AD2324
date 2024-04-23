package McBurguer;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {

	public static Scanner tec = new Scanner(System.in);
	public static Modelo modelo = new Modelo();
	public static Empleado e = null;

	public static void main(String[] args) {

		int opc = -1;

		if (modelo.getConexion() != null) {
			do {
				System.out.println("---------------MENU--------------");
				System.out.println(" 0 - Salir del programa");
				System.out.println("1 - Login");
				System.out.println("2 - Registrar Pedido");
				System.out.println("3 - Borrar Pedido");
				System.out.println("4 - Informe ventas");
				System.out.println("Seleccione una opción: ");
				opc = tec.nextInt();
				tec.nextLine();
				System.out.println("---------------------------------");

				switch (opc) {
				case 1:
					ejercicio1();
					break;
				case 2:
					ejercicio2();
					break;
				case 3:
					ejercicio3();
					;
					break;
				case 4:
					ejercicio4();
					break;
				}
			} while (opc != 0);
			modelo.cerrar();
		}
	}

	private static void ejercicio4() {
		if (e != null) {

		} else {
			System.err.println("No has iniciado sesión.");
		}

	}

	private static void ejercicio3() {
		if (e != null) {

		} else {
			System.err.println("No has iniciado sesión.");
		}

	}

	private static void ejercicio2() {

		if (e != null) {
			Pedido p = new Pedido(0, new Date(), e.getCodEmpleado(), e.getTienda());
			
			ArrayList<Detalle> detalle = new ArrayList();
			
			int opcion = 0;
			
			do {
				
				mostrarProductos();
				
				System.out.println("Introduce un código de producto: ");
				Producto prod = modelo.obtenerProducto(tec.nextInt());
				tec.nextLine();
				
				if (prod != null) {
					
				} else {
					System.err.println("Error, el producto no exite.");
				}
				
				System.out.println("Quieres meter más productos (0 - No | 1 - Si):");
				opcion = tec.nextInt();
				tec.nextLine();
				
			} while (opcion != 0);
			
		} else {
			System.err.println("No has iniciado sesión.");
		}

	}

	private static void mostrarProductos() {
		ArrayList<Producto> productos = modelo.obtenerProductos();
		
		for (Producto producto : productos) {
			System.out.println(producto);
		}
		
	}

	private static void ejercicio1() {
		System.out.println("Introduce tu código de usuario: ");
		int codEmpleado = tec.nextInt();
		tec.nextLine();

		System.out.println("Introduce tu contraseña: ");
		String ps = tec.nextLine();

		if (modelo.comprobarUsuario(codEmpleado, ps)) {
			System.out.println("Empleado logeado correctamente.");
			e = modelo.obtenerEmpleado(codEmpleado);
		} else {
			System.err.println("Error al logearte.");
		}

	}

}
