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
					
					break;
				case 4:
					
					break;
			}

		} while (opcion != 0);
	}
	private static void ejer2() {
		// TODO Auto-generated method stub
		ArrayList<Ventas> ventas = ad.obtenerVentas();
		for(Ventas v:ventas) {
			Stock s = new Stock();
			s.setId(v.getIdProducto());
			System.out.println("Introduce nombre para producto "+v.getIdProducto());
			s.setNombre(t.nextLine());
			System.out.println("Introduce stock para producto "+v.getIdProducto());
			s.setStock(t.nextInt());t.nextLine();
			if(ad.crearStock(s)) {
				System.out.println("Stock creado:"+s);
			}
			else {
				System.out.println("Error al crear stock:"+s);
			}
		}
		ArrayList<Stock> ventas = ad.obtenerVentas();
	}
	private static void ejer1() {
		// TODO Auto-generated method stub
		//Obtener ventas de fichero de texto
		ArrayList<Ventas> ventasTXT = ad.obtenerVentasTXT();
		//Procesar cada venta
		for(Ventas v: ventasTXT) {			
			Ventas vOBJ = ad.obtenerVentaOBJ(v.getIdProducto());
			if(vOBJ==null) {
				//Si el id de producto no está en ventas obj => crear venta obj
				if(ad.crearVentaObj(v)) {
					System.out.println("Venta creada "+ v);
				}
				else {
					System.out.println("Error al crea la venta "+ v);
				}
			}
			else {
				//Si no => modficar venta
				//Actualizar el cantidad y el importe
				vOBJ.setCantidadVendidad(vOBJ.getCantidadVendidad()+v.getCantidadVendidad());
				vOBJ.setImporteRecaudado(vOBJ.getImporteRecaudado()+v.getImporteRecaudado());
				if(ad.modificarVenta(vOBJ)) {
					System.out.println("Venta modificada "+ v);
				}
				else {
					System.out.println("Error al modficar la venta "+ v);
				}
			}
			
		}
		
		//Mostrar Ventas
		ArrayList<Ventas> ventas = ad.obtenerVentas();
		for(Ventas v:ventas) {
			System.out.println(v);
		}
	}

	

}
