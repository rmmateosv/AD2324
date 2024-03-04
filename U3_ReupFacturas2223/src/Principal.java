import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {

	public static Scanner t = new Scanner(System.in);
	public static Modelo bd = new Modelo();
	public static SimpleDateFormat formato = new SimpleDateFormat("ddMMyy");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (bd.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Selecciona una opción");
				System.out.println("0-Salir");
				System.out.println("1-Crear Producto");		
				System.out.println("2-Crear Factura");	
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearProducto();
					break;
				case 2:
					crearFactura();
					break;
				
				}

			} while (opcion != 0);
		} else {
			System.out.println("Error de conexión");
		}
	}

	private static void crearFactura() {
		// TODO Auto-generated method stub
		Factura f = new Factura();
		f.setNumero(bd.ultimoCodigoF()+1);
		f.setFecha(new Date());
		System.out.println("Dni Cliente");
		f.setCliente(t.nextLine());
		String mas = "0";
		do {
			mostrarProductos();
			Producto p = bd.obtenerProducto(t.nextLine());
			if(p!=null) {
				Detalle d =new Detalle();
				d.setProducto(p.getCodigo());				
				System.out.println("Cantidad");
				d.setCantidad(t.nextInt());t.nextLine();
				if(d.getCantidad()>p.getStock()) {
					System.out.println("Error, no hay stock");
				}
				else {
					d.setPrecioUdad(p.getPrecio());
					f.getDetalle().add(d);
				}
			}
			else {
				System.out.println("Error, no existe producto");
			}
			System.out.println("Mas productos (1-Sí/*-No");
			mas=t.nextLine();
		}while(mas.equalsIgnoreCase("1"));
		if(!f.getDetalle().isEmpty()) {
			if(bd.crearFactura(f)) {
				if(bd.actualizarStock(f.getDetalle())) {
					System.out.println("Factura creada y stock actualizado");
				}
				else {
					System.out.println("Error al actualizar el stock");
				}
			}
			else {
				System.out.println("Error al crear la factura");
			}
		}
	}

	private static void mostrarProductos() {
		// TODO Auto-generated method stub
		ArrayList<Producto> productos = bd.obtenerProductos();
		for (Producto p : productos) {
			System.out.println(p);
		}
	}

	private static void crearProducto() {
		// TODO Auto-generated method stub
		System.out.println("Código");
		String codigo = t.nextLine();
		Producto p = bd.obtenerProducto(codigo);
		if(p==null) {
			p= new Producto();
			p.setCodigo(codigo);
			System.out.println("Nombre");
			p.setNombre(t.nextLine());
			System.out.println("Precio");
			p.setPrecio(t.nextFloat());t.nextLine();
			System.out.println("Stock");
			p.setStock(t.nextInt());t.nextLine();
			if(bd.crearProducto(p)) {
				System.out.println("Producto Creado");
			}
		}	
		else {
			System.out.println("Producto ya existe");
		}
	}

	
}
