package Taller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;

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
		System.out.println("Introduce usuario:");
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
					verReparaciones();
					break;
			}
		
		}while(opcion!=0);
	}


	private static void verReparaciones() {
		// TODO Auto-generated method stub
		List<Reparacion> r = bd.obtenerReparaciones(u.getUsuario());
		for (Reparacion re : r) {
			System.out.println(re);
		}
		System.out.println("Introduce código de reparación:");
		int idR = t.nextInt(); t.nextLine();
		for (Reparacion reparacion : r) {
			if(reparacion.getId()==idR && reparacion.getFechaPago()!=null) {
				mostrarTicket(reparacion);
				break;
			}
		}
	
	}


	private static void mostrarTicket(Reparacion r) {
		// TODO Auto-generated method stub
		System.out.println("Nombre del cliente:"+
		r.getVehiculo().getPropietario());
		System.out.println("MAtrícula Vehículo"+
				r.getVehiculo().getMatricula());
		System.out.println("Fecha Reparación:"+
				r.getFecha());
		StringBuilder texto = new StringBuilder("Concepto");
		texto.setLength(50);
		System.out.println(texto+"\t\tCantidad\t\tPreciU\t\tTotal");
		//Detalle del ticket
		texto = new StringBuilder("Mano de obra");
		texto.setLength(50);
		System.out.println(texto+
				"\t\t"+ r.getHoras()+
				"\t\t"+r.getPrecioH() +
				"\t\t"+(r.getHoras()*r.getPrecioH())
				);
		for (PiezaReparacion pr: r.getPiezasR()) {
			texto = new StringBuilder(pr.getClave().getPieza().getNombre());
			texto.setLength(50);
			System.out.println(texto.toString()+
					"\t\t"+ pr.getCantidad()+
					"\t\t"+ pr.getPrecio()+
					"\t\t"+((pr.getCantidad() * pr.getPrecio()))
					);
		}
		System.out.println("Total factura:"+r.getTotal());
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
		if(r!=null && r.getFechaPago()==null) {
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
						//Buscar en el arraylist  pr de reparación
						//y si la pieza existe, se añade cantidad y
						// si no se añade un pr al arraylist
						boolean existe = false;
						for (PiezaReparacion pr : r.getPiezasR()) {
							if(pr.getClave().getPieza()==p) {
								existe=true;
								pr.setCantidad(pr.getCantidad()+cantidad);
								pr.getClave().getPieza().setStock(
										pr.getClave().getPieza().getStock()-cantidad
								);
								break;
							}
						}
						if(!existe) {
							//Añadir pr al arraylist de pr en reparacion
							r.getPiezasR().add(
									new PiezaReparacion(new ClavePR(r, p), cantidad, p.getPrecio()));
						}
						if(bd.modificar()) {
							System.out.println("Reparación modificada");
							mostrarReparaciones();
						}
						else {
							System.out.println("Error al modificar la reparación");
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
			System.out.println("Reparación no existe o está pagada");
		}
	}


	private static void mostrarPiezas() {
		// TODO Auto-generated method stub
		List<Pieza> piezas = bd.obtenerPiezas();
		for (Pieza p : piezas) {
			System.out.println(p);
		}
	}


	private static void mostrarReparaciones() {
		// TODO Auto-generated method stub
		List<Reparacion> r = bd.obtenerReparaciones();
		for (Reparacion reparacion : r) {
			System.out.println(reparacion);
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
			System.out.println("5-Imprimir Ticket");
			System.out.println("6-Mostrar Ventas del mes por pieza");
			System.out.println("7-Cambiar Contraseña");
			
			opcion = t.nextInt();t.nextLine();
			
			switch(opcion) {
				case 1:
					System.out.println("No implementado");
					break;
				case 2:
					crearUsuario();
					break;
				case 3:
					crearReparacion();
					break;
				case 4:
					pagarReparacion();
					break;
				case 5:
					imprimirTicket();
					break;
				case 6:
					mostrarVentasMes();
					break;
				case 7:
					cambiarPS();
					break;
			}
		
		}while(opcion!=0);
	
	}
	private static void mostrarVentasMes() {
		// TODO Auto-generated method stub
		System.out.println("Mes");
		ArrayList<Object[]> datos = bd.obtenerVentasMes(t.nextInt());t.nextLine();
		float totalMes=0;
		for (Object[] o : datos) {
			System.out.println("Código:"+o[0]+ //Código de pieza
					"\tNombre:"+o[1]+ //Nombre de pieza
					"\tNº de Reparaciones:"+o[4]+
					"\tPrecio Medio de Venta:"+o[5]+
					"\tCantidad:"+o[2]+
					"\tTotalPieza:"+o[3]);
			totalMes+=(int)o[2]*(float)o[3];
		}
		System.out.println("Total Vendido:"+totalMes);
	}


	private static void imprimirTicket() {
		// TODO Auto-generated method stub
		mostrarReparaciones();
		System.out.println("Introduce reparación a imprimir");
		Reparacion r = bd.obtenerReparacion(t.nextInt());t.nextLine();
		if(r!=null && r.getFechaPago()!=null) {
			mostrarTicket(r);
			
		}
		else {
			System.out.println("Reparación no existe o no está pagada");
		}
	}


	private static void pagarReparacion() {
		// TODO Auto-generated method stub
		mostrarReparaciones();
		System.out.println("Introduce reparación a pagar");
		
		Reparacion r = bd.obtenerReparacion(t.nextInt());t.nextLine();
		if(r!=null && r.getFechaPago()==null) {
			//Recuperamos r de la bd => r ESTÁ EN ESTADO MANAGED
			System.out.println("Horas invertidas");
			r.setHoras(t.nextFloat());t.nextLine();
			System.out.println("Precio Hora");
			r.setPrecioH(t.nextFloat());t.nextLine();			
			if(bd.pagarReparacion(r)) {
				System.out.println("Reparación pagada por "+r.getTotal()+" euros");
				mostrarReparaciones();
			}
		}
		else {
			System.out.println("Reparación no existe o ya está pagada");
		}
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
			System.out.println("DNI Propietario:");
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
			Reparacion r = new Reparacion(0, new Date(), v, u);
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
		ArrayList<Vehiculo> v = (ArrayList<Vehiculo>) bd.obtenerVehiculos();
		for (Vehiculo vehiculo : v) {
			System.out.println(vehiculo);
		}
	}


	private static void cambiarPS() {
		// TODO Auto-generated method stub
		System.out.println("Nueva Contraseña:");
		String ps = t.nextLine();
		u.setPs(DigestUtils.sha512Hex(ps));
		if(bd.modificar()) {
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
				u.setPs(DigestUtils.sha512Hex(u.getUsuario()));
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
