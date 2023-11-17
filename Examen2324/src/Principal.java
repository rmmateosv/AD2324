

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {
	
	public static Scanner t  = new Scanner(System.in);
	public static Modelo ad = new Modelo();
	public static Usuario us=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion=0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-E1");
			System.out.println("2-E2");
			System.out.println("3-E3");
			System.out.println("4-E4");
			
			opcion = t.nextInt();t.nextLine();
			
			switch(opcion) {
				case 1:
					login();
					break;
				case 2:
					crearPedido();
					break;
				case 3:
					modificarPedido();
					break;
				
			}
		
		}while(opcion!=0);
	}

	private static void modificarPedido() {
		// TODO Auto-generated method stub
		mostrarPedidos();
		System.out.println("Introduce pedido a modifica");
		int p = t.nextInt();t.nextLine();
		System.out.println("Introduce producto");
		int pr = t.nextInt();t.nextLine();
		Pedido pe = ad.obtenerPedido(p,pr,us.getCodEmp());
		if(pe!=null) {
			System.out.println("Nueva cantidad:");
			pe.setCantidad(t.nextInt());t.nextLine();
			if(ad.modificarPedido(pe)) {
				System.out.println("Pedido modificado");
			}
			else {
				System.out.println("Error, no se ha modificado");
			}
		}
		else {
			System.out.println("Error, no existe");
		}
	}

	private static void mostrarPedidos() {
		// TODO Auto-generated method stub
		ArrayList<Pedido> p = ad.obtenerPedidos();
		for (Pedido pedido : p) {
			System.out.println(pedido);
			
		}}

	private static void crearPedido() {
		// TODO Auto-generated method stub
		
		if(us!=null) {
			int codigo = ad.obtenerCodigo();
			Date fecha = new Date();
			String mas;
			do {
				Pedido p = new Pedido();
				p.setCodigo(codigo);
				p.setFecha(fecha);
				p.setEmpleado(us.getCodEmp());
				mostrarProductos();
				System.out.println("Introduce el código de producto");
				Producto pr = ad.obtenerProducto(t.nextInt());t.nextLine();
				if(pr!=null) {
					p.setProducto(pr.getId());
					p.setPrecio(pr.getPrecio());
					System.out.println("Cantidad");
					p.setCantidad(t.nextInt());t.nextLine();
					if(p.getCantidad()>0) {
						
						if(ad.registrarPedido(p)) {
							System.out.println("Pedido creado");
						}
						else {
							System.out.println("Error al crear el pedido");
						}
					}
					else {
						System.out.println("Cantidad debe ser > 0");
					}
				}
				else {
					System.out.println("No existe producto");
				}
				System.out.println("Más productos (0-No/*-Si)");
				mas = t.nextLine();
			}while(!mas.equals("0"));
			ArrayList<Pedido> pedido = ad.obtenerPedido(codigo);
			for (Pedido pe : pedido) {
				System.out.println(pe);
			}
		}
		else {
			System.out.println("No hay usuario logueado");
		}
	}

	private static void mostrarProductos() {
		// TODO Auto-generated method stub
		ArrayList<Producto> productos = ad.obtenerProductos();
		for (Producto producto : productos) {
			System.out.println(producto);
		}
	}

	private static void login() {
		// TODO Auto-generated method stub
		System.out.println("Código Empleado");
		int codigo = t.nextInt();t.nextLine();
		System.out.println("Contraseña");
		String ps = t.nextLine();
		us = ad.obtenerUsuario(codigo,ps);
		if(us!=null && us.isActivo()) {
			System.out.println("Usuario logueado");
		}
		else {
			System.out.println("Usuario incorrecto o no está activo");
			us=null;
		}
	}
	
}
