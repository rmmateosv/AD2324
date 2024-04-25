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

			System.out.println("Ventas del empleado Nº "+e.getCodEmpleado()+"-"+e.getNombre());
			
			ArrayList<Object[]> informe = modelo.ObtenerInforme(e.getCodEmpleado()); 
			
			float total=0f;
			
			for (Object[] o : informe) {
			
				total += (Float)o[2];
				
				System.out.println("Producto: "+ o[0]+ "\t Cantidad: "+ o[1]+"\t Importe: "+o[2]);
				
			}
			
		} else {
			System.err.println("No has iniciado sesión.");
		}

	}

	private static void ejercicio3() {
		if (e != null) {

			ArrayList<Pedido> pedidos = modelo.ObtenerPedidos(e.getCodEmpleado());
			
			for (Pedido pe : pedidos) {
			
				System.out.println(pe);
				
			}
			
			System.out.println("Introduce un codigo de pedido");
			
			int codigo = tec.nextInt();tec.nextLine();
			
			Pedido p = modelo.ObtenerPedido(codigo);
			
			if(p != null && p.getCodEmpleado() == e.getCodEmpleado()) {
				
				if(modelo.BorrarPedido(codigo)) {
					
					System.out.println("Pedido "+p.getCodigo()+" borrado");
					
					 pedidos = modelo.ObtenerPedidos(e.getCodEmpleado());
					
					for (Pedido pe : pedidos) {
					
						System.out.println(pe);
						
					}
					
				}else {
					
					System.out.println("Se ha producido un error al borrar el pedido");
					
				}
				
			}else {
				
				System.out.println("Pedido no existe");
				
			}
			
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
					Detalle d = new Detalle();
					d.setProducto(prod.getCodigo());
					d.setPrecioUnitario(prod.getPrecio());
					System.out.println("Cuanta cantidad quieres del producto?");
					d.setCantidad(tec.nextInt());tec.nextLine();
					detalle.add(d);
				} else {
					System.err.println("Error, el producto no exite.");
				}
				
				System.out.println("Quieres meter más productos (0 - No | 1 - Si):");
				opcion = tec.nextInt();
				tec.nextLine();
				
			} while (opcion != 0);
			
			if (modelo.crearPedido(p, detalle)) {
				System.out.println("Pedido creado " +p.getCodigo());
				
				ArrayList<Pedido> pedidos = modelo.ObtenerPedidos(p.getCodEmpleado());
				
				for (Pedido pe : pedidos) {
				
					System.out.println(pe);
					
				}
				
			} else {
				System.err.println("Error, no se ha podido crear el pedido");
			}
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
