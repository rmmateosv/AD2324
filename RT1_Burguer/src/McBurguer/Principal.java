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
				ejercicio3();
				;
				break;
			case 4:
				ejercicio4();
				break;
			}
		} while (opc != 0);

	}

	private static void ejercicio4() {
		
		if(e != null) {
			CajaEmpleado c = modelo.obtenerCajaEmpleado(e.getDni());
			if(c != null) {
				System.out.println("ERROR - Caja ya cerrada.");
			}
			else {
				c = new CajaEmpleado();
				c.setDni(e.getDni());
				c.setNombre(e.getNombre());
				c.setTienda(e.getCodTienda());
				c.setFecha(new Date());
				
				ArrayList<Pedido> listaPedidos = modelo.obtenerPedidosEmpleado(e.getCodEmpleado());
				for (Pedido pedido : listaPedidos) {
					c.setNumProd(c.getNumProd()+pedido.getCantidad());
					c.setImporte(c.getImporte()+(pedido.getPrecio()*pedido.getCantidad()));
				}
				if(modelo.CerrarCaja(c)){
					System.out.println("Caja cerrada con exito.");
					
				}
				else {System.out.println("!ERROR - Al cerrar caja...!");}
				
			}
		}
		else {System.out.println("!No hay empleado logueado..!");}
		ArrayList<CajaEmpleado> listaCaja =  modelo.obtenerCaja();
		for (CajaEmpleado cajaEmpleado : listaCaja) {
			System.out.println(cajaEmpleado);
		}
	}

	private static void ejercicio3() {

		if (e != null) {

			ArrayList<Pedido> listaPedidos = modelo.obtenerPedidosEmpleado(e.getCodEmpleado());

			for (Pedido pedido : listaPedidos) {
				System.out.println(pedido.toString());
			}
			System.out.println("Selecciona el pedido que quiere modificar: ");
			int codPed = tec.nextInt();
			tec.nextLine();
			System.out.println("Introduce el codigo del producto que quieres modificar: ");
			int codPro = tec.nextInt();
			tec.nextLine();

			ArrayList<Pedido> pedido = modelo.obtenerPedido(codPed);

			for (Pedido p : pedido) {
				if (p.getCodProd() == codPro && e.getCodEmpleado() == p.getCodEmp()) {
					System.out.println("Introduce la nueva cantidad: ");
					p.setCantidad(tec.nextInt());
					tec.nextLine();
					if (modelo.modificarCantidadPedido(p)) {
						System.out.println("Pedido modificado correctamente.");
						ArrayList<Pedido> pedidoActual = modelo.obtenerPedido(p.getCodigo());
						for (Pedido pa : pedidoActual) {
							System.out.println(pa);
						}
					} else {
						System.out.println("ERROR- Modificar Cantidad del pedido.");
					}

				}
			}

		} else {
			System.out.println("Error. No hay empleado logueado.");
		}

	}

	private static void ejercicio2() {
		// TODO Auto-generated method stub
		if (e != null) {
			Pedido p = new Pedido();
			p.setCodigo(modelo.obtenerCodPedido());
			p.setFecha(new Date());
			p.setCodEmp(e.getCodEmpleado());
			int opcion = 0;
			do {
				mostrarProductos();
				System.out.println("Introduce código del producto: ");
				Producto prod = modelo.obtenerProducto(tec.nextInt());
				tec.nextLine();
				if (prod != null) {
					System.out.println("Introduce cantidad");
					p.setCantidad(tec.nextInt());
					tec.nextLine();
					p.setCodProd(prod.getId());
					p.setPrecio(prod.getPrecio());
					if (modelo.registrarPedido(p)) {
						System.out.println("Producto añadido al pedido.");
					} else {
						System.out.println("Error. No se ha añadido el producto al pedido.");
					}
				} else {
					System.out.println("Error. El producto no existe.");
				}

				System.out.println("¿Más productos? - 0-No / 1-Sí");
				opcion = tec.nextInt();
				tec.nextLine();
			} while (opcion != 0);

			ArrayList<Pedido> pedidoActual = modelo.obtenerPedido(p.getCodigo());
			for (Pedido pa : pedidoActual) {
				System.out.println(pa);
			}

		} else {
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
		int codEmp = tec.nextInt();
		tec.nextLine();
		System.out.println("Introduzca la contraseña");
		String ps = tec.nextLine();
		e = modelo.obtenerEmpleado(codEmp, ps);
		if (e == null) {
			System.out.println("Error, el usuario no existe.");
		} else {
			System.out.println("Empleado logueado exitosamente.");
		}
	}

}
