package clases;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {

	public static Scanner t = new Scanner(System.in);
	public static Modelo bd = new Modelo();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (bd.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Selecciona una opción");
				System.out.println("0-Salir");
				System.out.println("1-Crear Producto");
				System.out.println("2-Crear Factura");
				System.out.println("3-Anular Factura");
				System.out.println("4-Obtener Informe");
				
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
		} else {
			System.out.println("Error de conexión");
		}
	}

	private static void ejercicio2() {
		// TODO Auto-generated method stub
		Factura f = new Factura();
		f.setNumero(bd.obtenerNumeroFactura());
		f.setFecha(new Date());
		System.out.println("Introduce el DNI del cliente: ");
		f.setCliente(t.nextLine());
		String masProds="0";
		do {
			mostrarProductos();
			System.out.println("Introduce código de producto: ");
			Producto p = bd.obtenerProducto(t.nextLine());
			System.out.println("Introduce cantidad: ");
			int cantidad = t.nextInt();t.nextLine();
			if(p.getStock() >= cantidad) {
				Detalle d = new Detalle(p.getCodigo(), cantidad, p.getPrecio());
				f.getListaDetalles().add(d);
			}else {
				System.err.println("Error, no hay suficiente stock. " + p.getStock() + " unidades disponibles.");
			}
			System.out.println("¿Desea añadir otro producto? (0-No/*-Sí)");
			masProds = t.nextLine();
		}while(!masProds.equals("0"));
		if(f.getListaDetalles().isEmpty()) {
			System.err.println("No se creó la factura, no hay productos.");
		}else {
			if(bd.crearFactura(f)) {
				for (Detalle i : f.getListaDetalles()) {
					bd.actualizarStock(i);
				}
			}else {
				System.err.println("Error al crear la factura.");
			}
		}
	}

	private static void ejercicio1() {
		// TODO Auto-generated method stub
		System.out.println("Introduce código de producto: ");
		String codigo = t.nextLine();
		Producto p = bd.obtenerProducto(codigo);
		if(p == null) {
			p = new Producto();
			p.setCodigo(codigo);
			System.out.println("Introduce nombre: ");
			p.setNombre(t.nextLine());
			System.out.println("Introduce cantidad: ");
			p.setStock(t.nextInt());t.nextLine();
			System.out.println("Introduce precio: ");
			p.setPrecio(t.nextFloat());t.nextLine();
			if(bd.crearProducto(p)) {
				mostrarProductos();
			}else {
				System.err.println("Error al crear el producto.");
			}
		}else {
			System.err.println("Error, el producto indicado existe.");
		}
	}

	private static void mostrarProductos() {
		// TODO Auto-generated method stub
		ArrayList<Producto> listaProductos = bd.obtenerProductos();
		for (Producto p : listaProductos) {
			System.out.println(p);
		}
	}

}
