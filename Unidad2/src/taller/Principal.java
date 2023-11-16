package taller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
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
		System.out.println("Introduce usuariossss:");
		String us = t.nextLine();
		System.out.println("Introduce contraseña:");
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
					insertarPiezaReparacion();
					break;
				case 2:
					cambiarPS();
					break;
			}
		
		}while(opcion!=0);
	}


	private static void insertarPiezaReparacion() {
		// TODO Auto-generated method stub
		mostrarReparaciones();
		System.out.println("Introduce código de reparación");
		Reparacion r = bd.obtenerReparacion(t.nextInt()); t.nextLine();
		if(r!=null) {
			String salir="";
			do {
				mostrarPiezas();
				System.out.println("Introduce código de pieza");
				Pieza p = bd.obtenerPieza(t.nextInt()); t.nextLine();
				if(p!=null) {
					System.out.println("Introduce cantidad");
					int cantidad = t.nextInt(); t.nextLine();
					//Chequear si hay stock
					if(p.getStock()>=cantidad) {
						//Añadir pieza a reparación
						PiezaReparacion pr = bd.obtenerPiezaRep(r,p);
						if(pr==null) {
							//insertar
							if(cantidad >= 0) {
								pr = new PiezaReparacion(r.getId(), p.getId(), 
										cantidad, p.getPrecio());
								if(bd.insertarPiezaReparacion(pr)) {
									System.out.println("Pieza añadida");
								}
								else {
									System.out.println("Error al añadir pieza");
								}
							}
							else {
								System.out.println("La cantidad no puede ser negativa");
							}
						}
						else {
							//modificar
							if(bd.modificarCantidad(pr,cantidad)) {
								System.out.println("Pieza modificada");
							}
							else {
								System.out.println("Error al modificar pieza");
							}
						}
					}
					else {
						System.out.println("Error, stock insuficiente");
					}
				}
				else {
					System.out.println("Pieza no existe");
				}
				System.out.println("Desea introducir otra pieza(0-No-*Si)");
				salir = t.nextLine();
			}while(!salir.equals("0"));
		}
		else {
			System.out.println("Reparación no existe");
		}
	}


	private static void mostrarPiezas() {
		// TODO Auto-generated method stub
		ArrayList<Pieza> piezas = bd.obtenerPiezas();
		for (Pieza p : piezas) {
			System.out.println(p);
		}
	}


	private static void mostrarReparaciones() {
		// TODO Auto-generated method stub
		ArrayList<Reparacion> r = bd.obtenerReparaciones();
		for (Reparacion reparacion : r) {
			System.out.println(r);
		}
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
				case 3:
					crearReparacion();
					break;
				case 5:
					cambiarPS();
					break;
			}
		
		}while(opcion!=0);
	
	}
	private static void crearReparacion() {
		// TODO Auto-generated method stub
		mostrarVehiculos();
		System.out.println("Introduce matrícula:");
		String m = t.nextLine();
		Vehiculo v = bd.obtenerVehiculo(m);
		boolean error = false;
		if(v==null) {
			//Crear el vehículo
			System.out.println("Vehículo no existe-Introduce datos del vehículo");
			v = new Vehiculo();
			v.setMatricula(m);
			System.out.println("Nombre Propietario:");
			v.setPropietario(t.nextLine());
			System.out.println("Teléfono:");
			v.setTelf(t.nextLine());
			if(bd.crearVehiculo(v)) {
				System.out.println("Vehiculo creado");
			}
			else {
				error = true;
				System.out.println("Error al crear el vehículo");
			}			
		}
		
		if(!error) {
			//Crear reparación
			Reparacion r = new Reparacion(0, new Date(), v.getMatricula(), u.getId());
			if(bd.crearReparacion(r)) {
				System.out.println("Reparación creada");
			}
			else {
				System.out.println("Error al crear la reparación");
			}
		}
	}


	private static void mostrarVehiculos() {
		// TODO Auto-generated method stub
		ArrayList<Vehiculo> v = bd.obtenerVehiculos();
		for (Vehiculo vehiculo : v) {
			System.out.println(v);
		}
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
