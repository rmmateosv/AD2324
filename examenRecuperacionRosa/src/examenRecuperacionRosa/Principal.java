package examenRecuperacionRosa;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;



public class Principal {
	static Scanner sc = new Scanner(System.in);
	static Model m = new Model();
	static SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyyhhmmss");
	public static void main(String[] args) {
		int opcion = 0;
		boolean salir = false;
	
		do {

			System.out.println("AGENCIA MORALA TRAVEL BEST");
			System.out.println("1.Cargar viajes");
			System.out.println("2.Vender viaje");
			System.out.println("4.Salir");
			System.out.println("-->");
			opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {

			case 1:
				
				cargarViajes();
				break;

			case 2:
				venderViaje();
				break;
			case 3:
				anularVenta();
				break;
			case 4:
				salir = true;
				break;

			
			}

		} while (!salir);


	}
	
	private static void anularVenta() {
		// TODO Auto-generated method stub
		mostrarVentas();
		System.out.println("Introduce el código de venta a anular");
		VentasObj v = m.obtenerVenta(sc.nextLine());
		if(v!=null) {
			ViajesBin viaje = m.obtenerViaje(v.getViaje());
			if(viaje.isDisponible()) {
				VentasObj vAnul = new VentasObj("v"+formato.format(new Date()),new Date(),v.getDni(),v.getViaje(),
						v.getPrecioTotal()*-1,v.getListaPersonas(),v.getCodigo());
				if(m.crearViajesObj(vAnul)) {
					if(m.modificarViaje(viaje, v.getListaPersonas().size()*-1)) {
						System.out.println("Venta anulada");
						mostrarVentas();
					}
				}
			}
			else {
				System.out.println("No se puede anular la venta porque el viaje no está disponible");
			}
		}
		else {
			System.out.println("No existe la venta");
		}
	}

	private static void venderViaje() {
		System.out.println("Indica al duración deseada del viaje");
		ArrayList<ViajesBin> viajesDuracion = m.obtenerViajesDuracion(sc.nextInt());sc.nextLine();
		for(ViajesBin x : viajesDuracion) {
			System.out.println(x);
		}
		System.out.println("Introduce el codigo del viaje a vender: ");
		int cod = sc.nextInt(); sc.nextLine();
		System.out.println("Introduce el numero de personas: ");
		int numPersonas = sc.nextInt();sc.nextLine();
		
		ViajesBin v = m.obtenerViajeConChequeo(cod,numPersonas);
		if(v != null) {
			VentasObj venta = new VentasObj();
			venta.setCodigo("v"+formato.format(new Date()));
			venta.setFecha(new Date());
			System.out.println("Introduce el dni de la persona que contrata el viaje:");
			venta.setDni(sc.nextLine());
			venta.setViaje(v.getCodigo());
			venta.setPrecioTotal(numPersonas*v.getPrecio());
			venta.setCodigoVentaAnulada("");
			for(int i=1;i<=numPersonas;i++) {
				System.out.println("Introduce dni persona "+i+": ");
				String dni = sc.nextLine();
				System.out.println("Introduce nombre persona"+i+": ");
				String nombre = sc.nextLine();
				System.out.println("introduce edad persona"+i+":");
				int edad = sc.nextInt();
				sc.nextLine();
				Personas p =  new Personas();
				p.setDni(dni);
				p.setNombre(nombre);
				p.setEdad(edad);
				venta.getListaPersonas().add(p);
			}	
			if(m.crearViajesObj(venta)) {
				if(m.modificarViaje(v,numPersonas)) {
					System.out.println("Venta creada");
					mostrarVentas();
				}
				else {
					System.out.println("Error al crear la venta");
				}
			} 			
		}else {
			System.err.println("El viaje no cumple con las condiciones solicitadas o no existe");
		}
		
		
	}

	private static void mostrarVentas() {
		// TODO Auto-generated method stub
		ArrayList<VentasObj> ventas = m.obtenerVentas();
		for (VentasObj v : ventas) {
			System.out.println(v);		
		}
	}

	private static void cargarViajes() {
		System.out.println("Indica el nombre del fichero");
		String fichero = sc.nextLine();
		String cadena = fichero + ".xml";
		
		Agencia a = m.unMarshall(cadena);
		if(a == null) {
			
			System.out.println("No existe fichero");
			}else {
				if(a.getCargado() == 0) {
					int codViaje = m.obtenerCodigoUltViaje() + 1;
					System.out.println(codViaje);
					for(ViajeXML x: a.getListaViajes()) {
						if(m.crearViaje(x,codViaje,a)) {
							codViaje++;
						}else {
							System.out.println("Error al crear el viaje con cod" + codViaje);
						}
					}
					// Cambiamos a no cargado
				a.setCargado(1);
				m.Marshall(a,cadena);
					// Mostrar viajes
					mostrarViajes();
				}else {
					System.out.println("Atención, estos viajes ya se han añadido");
				}
			}
		
		
	}

	
	
	private static void mostrarViajes() {
		ArrayList<ViajesBin> listaViajes = m.obtenerViajes();
		for(ViajesBin x: listaViajes) {
			System.out.println(x);
		}
		
		
		
	}

	
	

	

}
