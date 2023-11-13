package taller;

import java.sql.Connection;
import java.util.Scanner;

public class Principal {
	
	public static Scanner t  = new Scanner(System.in);
	public static Modelo bd = new Modelo();
	public static Usuario u  =null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(bd.getConexion()!=null) {
			login();
			
			//Cerrar conexión
			bd.cerrar();
		}
		else {
			System.out.println("Error, no hay conexión con la BD");
		}
	}
	
	
	private static void login() {
		// TODO Auto-generated method stub
		System.out.println("Introduce usuario");
		String us = t.nextLine();
		System.out.println("Introduce contraseña");
		String ps=t.nextLine();
		u = bd.obtenerUsuario(us,ps);
		if(u!=null) {
			//Cargar el menú correspondiente
			switch(u.getPerfil()) {
			case "A":
				menuAdministrativo();
				break;
			case "M":
				menuMecanico();
				break;
			case "C":
				menuCliente();
				break;
			}
		}
		else {
			System.out.println("Error, usuario incorrecto");
		}
	}


	private static void menuCliente() {
		// TODO Auto-generated method stub
		int opcion=0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Ver reparaciones");
			System.out.println("2-Cambiar Contraseña");
			
			opcion = t.nextInt();t.nextLine();
			
			switch(opcion) {
				case 1:
					
					break;
			}
		
		}while(opcion!=0);
	}


	private static void menuMecanico() {
		// TODO Auto-generated method stub
		int opcion=0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Añadir Pieza a reparación");
			System.out.println("2-Cambiar Contraseña");
			
			opcion = t.nextInt();t.nextLine();
			
			switch(opcion) {
				case 1:
					
					break;
			}
		
		}while(opcion!=0);
	}


	private static void menuAdministrativo() {
		// TODO Auto-generated method stub
		int opcion=0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Info Base de datos");
			System.out.println("2-Crear Usuario");
			System.out.println("3-Crear Reparación (Si no existe coche, se crea)");
			System.out.println("4-Pagar Reparación");
			System.out.println("5-Cambiar Contraseña");
			
			opcion = t.nextInt();t.nextLine();
			
			switch(opcion) {
				case 1:
					bd.infoBD();
					break;
				case 2:
					crearUsuario();
					break;
				case 5:
					cambiarPS();
					break;
			}
		
		}while(opcion!=0);
	
	}


	private static void cambiarPS() {
		// TODO Auto-generated method stub
		System.out.println("Nueva Contraseña:");
		String ps = t.nextLine();
		if(bd.cambiarPS(u,ps)) {
			System.out.println("Contraseña cambiada");
		}
		else {
			System.out.println("Error al cambiar contraseña");
		}
		
		
	}


	private static void crearUsuario() {
		// TODO Auto-generated method stub
		System.out.println("Introduce el dni");
		String dni = t.nextLine();
		//Comproba que no hay otro us con el mismo dni
		Usuario u = bd.obtenerUsuario(dni);
		if(u==null) {
			u=new Usuario();
			u.setUsuario(dni);
			System.out.println("Perfil(A-Administrativo/M-Mecánico/C-Cliente):");
			String perfil =t.nextLine();
			if(!perfil.equalsIgnoreCase("A") && !perfil.equalsIgnoreCase("M")
				&& !perfil.equalsIgnoreCase("C")){
					System.out.println("Error, perfil incorrecto");
			}
			else {
				u.setPerfil(perfil.toUpperCase());
				if(bd.crearUsuario(u)) {
					System.out.println("Usuario creado con id:"+u.getId());
				}
				else {
					System.out.println("Error al crear el usuario");
				}
			}
			
		}
		else {
			System.out.println("Error, ya existe usuario con ese dni");
		}
	}


	
}
