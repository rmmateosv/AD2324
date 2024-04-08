package Ventas;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

	public static Scanner tec =  new Scanner(System.in);
	public static Modelo mod = new Modelo();
	
	public static void main(String[] args) {
		
		int opc = -1;
		
		do {
			System.out.println("---------------MENU--------------");
			System.out.println(" 0 - Salir del programa");
			System.out.println("1 - Crear fichero de objetos");
			System.out.println("2 - Crear fichero binario");
			System.out.println("3 - Modificar stock");
			System.out.println("4 - Generar Xml");
			System.out.println("Selepcione una opcion: ");
			opc = tec.nextInt(); tec.nextLine();
			System.out.println("---------------------------------");
			
			switch(opc){
			case 1: ejercico1();
			break;
			case 2: ejercicio2();
			break;
			case 3:;
			break;
			case 4:;
			break;
			}
		}
		while(opc != 0);
	}

	private static void ejercicio2() {
		// TODO Auto-generated method stub
		ArrayList<VentasObj> vObj = mod.obtenerVentasObj();
		for (VentasObj ventasObj : vObj) {
			Producto p = new Producto();
			p.setIdproducto(ventasObj.getProducto());
			System.out.println("Introduce el nombre del producto" +ventasObj.getProducto());
			p.setNombre(tec.nextLine());
			System.out.println("Introduce el stock del producto" +p.getIdproducto());
			p.setStock(tec.nextInt());tec.nextLine();
			if (mod.crearProducto(p)) {
				System.out.println("Producto creado");
			}else {
				System.err.println("Error al crear el producto");
			}
		}
		mostrarStock();
	}

	private static void mostrarStock() {
		// TODO Auto-generated method stub
		ArrayList<Producto> pstock = mod.obtenerProductos();
	}

	private static void ejercico1() {
		
		ArrayList<VentasTxt> listVenTxt = mod.obtenerVentasTxt();
		
		for (VentasTxt vTxt : listVenTxt) {
			
			VentasObj vObj = mod.obtenerVentaObj(vTxt.getProducto());
			
			if (vObj != null) { // Al ser distinto de null ya existe una venta en vObj, por lo que hay que modificarla.
				vObj.setCantidad(vObj.getCantidad() + vTxt.getCantidad());
				vObj.setImporte(vObj.getImporte() + vTxt.getImporte());
				
				if (mod.modificarVenta(vObj)) {
					System.out.println("Venta modificada.");
				} else {
					System.err.println("Erorr al modificar la venta.");
				}
				
			} else {
				vObj = new VentasObj(vTxt.getProducto(), vTxt.getCantidad(), vTxt.getImporte());
				
				if (mod.crearVenta(vObj)) {
					System.out.println("Venta insertada.");
				} else {
					System.err.println("Error al insertar la venta.");
				}
			}
		}
		mostrarventasOBJ();
		
	}

	private static void mostrarventasOBJ() {
		// TODO Auto-generated method stub
		ArrayList<VentasObj> ventas = mod.obtenerVentasObj();
		for (VentasObj v : ventas) {
			System.out.println(v);
		}
	}

	
	
}
