package Examen;

import java.util.ArrayList;
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
		// Mostrar stocks
		ArrayList<Stock> stocks = ad.obtenerStocks();
		for (Stock p : stocks) {
			System.out.println(p);
		}
		System.out.println("Introduce Id de producto");
		Stock s = ad.obtenerProductoBin(t.nextInt()); t.nextLine();
		if(s!=null)
		{
			Ventas vOBj =  ad.obtenerVentaOBJ(s.getId());
			Info raiz = new Info(s.getId(), s.getNombre(), s.getStock(), 
					   vOBj.getCantidadVendidad(), 
					   vOBj.getImporteRecaudado());
			ad.marshall(raiz);
		}
		else {
			System.out.println("Error, el producto no existe");
		}
	}

	private static void ejer3() {
		// TODO Auto-generated method stub
		// Mostrar stocks
		ArrayList<Stock> stocks = ad.obtenerStocks();

		if (!stocks.isEmpty()) {
			for (Stock p : stocks) {
				System.out.println(p);
			}
			
			System.out.println("Introduce el id de producto a modificar:");
			Stock s = ad.obtenerProductoBin(t.nextInt());
			t.nextLine();
			
			if (s != null) {
				System.out.println("Introduce nuevo stock:");
				s.setStock(t.nextInt());t.nextLine();
				if(ad.modificarStock(s)) {
					System.out.println("Stock modificado");
				}
				else {
					System.out.println("Error al modificar el stock");
				}
			} else {
				System.out.println("");
			}

			// Mostrar stocks
			stocks = ad.obtenerStocks();
			for (Stock p : stocks) {
				System.out.println(p);
			}
		}
		
	}

	private static void ejer2() {
		// TODO Auto-generated method stub
		ArrayList<Ventas> ventas = ad.obtenerVentas();
		for (Ventas v : ventas) {
			Stock s = new Stock();
			s.setId(v.getIdProducto());
			System.out.println("Introduce nombre para producto " + v.getIdProducto());
			s.setNombre(t.nextLine());
			System.out.println("Introduce stock para producto " + v.getIdProducto());
			s.setStock(t.nextInt());
			t.nextLine();
			if (ad.crearStock(s)) {
				System.out.println("Stock creado:" + s);
			} else {
				System.out.println("Error al crear stock:" + s);
			}
		}
		// Mostrar stocks
		ArrayList<Stock> stocks = ad.obtenerStocks();
		for (Stock p : stocks) {
			System.out.println(p);
		}
	}

	private static void ejer1() {
		// TODO Auto-generated method stub
		// Obtener ventas de fichero de texto
		ArrayList<Ventas> ventasTXT = ad.obtenerVentasTXT();
		// Procesar cada venta
		for (Ventas v : ventasTXT) {
			Ventas vOBJ = ad.obtenerVentaOBJ(v.getIdProducto());
			if (vOBJ == null) {
				// Si el id de producto no está en ventas obj => crear venta obj
				if (ad.crearVentaObj(v)) {
					System.out.println("Venta creada " + v);
				} else {
					System.out.println("Error al crea la venta " + v);
				}
			} else {
				// Si no => modficar venta
				// Actualizar el cantidad y el importe
				vOBJ.setCantidadVendidad(vOBJ.getCantidadVendidad() + v.getCantidadVendidad());
				vOBJ.setImporteRecaudado(vOBJ.getImporteRecaudado() + v.getImporteRecaudado());
				if (ad.modificarVenta(vOBJ)) {
					System.out.println("Venta modificada " + v);
				} else {
					System.out.println("Error al modficar la venta " + v);
				}
			}

		}

		// Mostrar Ventas
		ArrayList<Ventas> ventas = ad.obtenerVentas();
		for (Ventas v : ventas) {
			System.out.println(v);
		}
	}

}
