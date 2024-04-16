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

		do {
			System.out.println("---------------MENU--------------");
			System.out.println(" 0 - Salir del programa");
			System.out.println("1 - Login");
			System.out.println("2 - Registrar Pedido");
			System.out.println("3 - Modificar Pedido");
			System.out.println("4 - Cerrar Caja");
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

				;
				break;
			case 4:

				break;
			}
		} while (opc != 0);

	}

	private static void ejercicio2() {
		// TODO Auto-generated method stub
		if(e != null) {
			Pedido p = new Pedido();
			p.setCodigo(modelo.obtenerCodPedido());
			p.setFecha(new Date());
			p.setCodEmp(e.getCodEmpleado());
			int opcion = 0;
			do {
				mostrarProductos();
				System.out.println("Introduce código del producto: ");
				Producto prod = modelo.obtenerProducto(tec.nextInt());tec.nextLine();
				if(prod != null) {
					System.out.println("Introduce cantidad");
					p.setCantidad(tec.nextInt());tec.nextLine();
					p.setCodProd(prod.getId());
					p.setPrecio(prod.getPrecio());
					if(modelo.registrarPedido(p)) {
						System.out.println("Producto añadido al pedido.");
					}else {
						System.out.println("Error. No se ha añadido el producto al pedido.");
					}
				}else {
					System.out.println("Error. El producto no existe.");
				}
				
				System.out.println("¿Más productos? - 0-No / 1-Sí");
				opcion = tec.nextInt();tec.nextLine();
			}while(opcion != 0);
			
			ArrayList<Pedido> pedidoActual = modelo.obtenerPedido(p.getCodigo());
			for (Pedido pa : pedidoActual) {
				System.out.println(pa);
			}
			
		}else {
			System.out.println("No hay empleado logueado.");
		}
	}

	private static void mostrarProductos() {
		// TODO Auto-generated method stub
		ArrayList<Producto> productos = modelo.obtenerProductos();
		for (Producto p : productos) {
			System.out.println(p);
		}
	}

	private static void ejercicio1() {
		// TODO Auto-generated method stub
		System.out.println("Introduzca código de empleado: ");
		int codEmp = tec.nextInt(); tec.nextLine();
		System.out.println("Introduzca la contraseña");
		String ps = tec.nextLine();
		e = modelo.obtenerEmpleado(codEmp, ps);
		if(e == null) {
			System.out.println("Error, el usuario no existe.");
		}else {
			System.out.println("Empleado logueado exitosamente.");
		}
	}

}
