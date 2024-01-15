import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {

	public static Scanner t = new Scanner(System.in);
	public static Modelo bd = new Modelo();
	public static SimpleDateFormat formato = new SimpleDateFormat("ddMMyyHHmm");

	public static void main(String[] args) {
		if (bd.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Selecciona una opción");
				System.out.println("0-Salir");
				System.out.println("1-E1-Alta Producto");
				System.out.println("2-Crear Precio");
				System.out.println("3-Crear Venta");
				System.out.println("4-Estadística Venta)");
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearProducto();
					break;
				case 2:
					crearPrecio();
					break;
				case 3:
					crearVenta();
					break;
				case 4:
					estadisticaVentas();
					break;
				}

			} while (opcion != 0);
		} else {
			System.out.println("Error de conexión");
		}
	}

	private static void estadisticaVentas() {
		// TODO Auto-generated method stub
		ArrayList<Object[]> datos = bd.obtenerEstadistica();
		for (Object[] o : datos) {
			System.out.println("Producto:"+o[0]+
					"\tUnidades Vendidas:"+o[1]+
					"\tImporte Recaudado:"+o[2]);
		}
	}

	private static void crearVenta() {
		// TODO Auto-generated method stub
		mostrarProductos();
		Producto p = bd.obtenerProducto(t.nextInt());
		t.nextLine();
		if (p != null) {
			if (p.getPrecios().isEmpty()) {
				System.out.println("Error, el producto no tiene precio");
			} else {
				System.out.println("Introduce cantidad");
				Venta v = new Venta(0, null, p, t.nextInt(), p.getPrecios().get(p.getPrecios().size() - 1));
				t.nextLine();
				if (bd.crearVenta(v)) {
					System.out.println("Venta creada");
					mostrarVentas();
				} else {
					System.out.println("Error al crear la venta");
				}
			}
		} else {
			System.out.println("Error, no existe producto");
		}
	}

	private static void mostrarVentas() {
		// TODO Auto-generated method stub
		ArrayList<Venta> ventas = bd.obtenerVentas();
		for (Venta v : ventas) {
			System.out.println(v);
		}
	}

	private static void crearPrecio() {
		// TODO Auto-generated method stub
		mostrarProductos();
		System.out.println("Introduce código de producto");
		Producto p = bd.obtenerProducto(t.nextInt());
		t.nextLine();
		if (p != null) {
			System.out.println("introduce nuevo precio");
			int nuevo = t.nextInt();
			t.nextLine();
			if (bd.crearPrecio(p, nuevo)) {
				mostrarProductos();
			} else {
				System.out.println("Error al crear el precio");
			}
		} else {
			System.out.println("Error, no existe producto");
		}
	}

	private static void crearProducto() {
		// TODO Auto-generated method stub
		Producto p = new Producto();
		System.out.println("Nombre:");
		p.setNombre(t.nextLine());
		p.setDatosNutricion(new Info());
		System.out.println("KiloCalorías:");
		p.getDatosNutricion().setKcal(t.nextInt());
		t.nextLine();
		System.out.println("Grasas:");
		p.getDatosNutricion().setGrasas(t.nextInt());
		t.nextLine();
		System.out.println("Hidratos:");
		p.getDatosNutricion().setHidratos(t.nextInt());
		t.nextLine();
		if (bd.crearProducto(p)) {
			mostrarProductos();
		} else {
			System.out.println("Erorr, al crear el producto");
		}

	}

	private static void mostrarProductos() {
		// TODO Auto-generated method stub
		ArrayList<Producto> productos = bd.obtenerProductos();
		for (Producto p : productos) {
			System.out.println(p);
		}
	}

}
