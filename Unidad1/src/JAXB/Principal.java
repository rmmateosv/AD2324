package JAXB;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import fBinario.Asignatura;







public class Principal {
	public static Scanner t = new Scanner(System.in);
	public static SimpleDateFormat formatoXML = new SimpleDateFormat("yyyy-MM-dd");
	//Declaramos el acceso a datos para los fichero de objetos
	public static ClaseObject.Modelo adHistorial= new ClaseObject.Modelo();
	public static Modelo adJaxb = new Modelo();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Crear fichero xml");
			System.out.println("2-Mostrar fichero xml");
			System.out.println("3-Modificar nombre alumno en fichero XML");
			System.out.println("4-Borrar historial en fichero XML");
			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
				case 1:
					crearXML();
					break;
				case 2:
					mostrarXML();
					break;
				case 3:
					modificarNombreAlumno();
					break;
				case 4:
					borrarHistorial();
					break;
			}

		} while (opcion != 0);
	}

	private static void borrarHistorial() {
		
	}

	private static void modificarNombreAlumno() {
		
		
	}

	private static void mostrarXML() {
		// TODO Auto-generated method stub
		
	}

	private static void crearXML() {
		ArrayList<ClaseObject.Historial> historiales = adHistorial.obtenerHistoriales();
		
		//Crear un objeto de la clase raiz
		Centro c = new Centro();
		c.setFecha(new Date());
		System.out.println("Introduce el nombre del centro");
		c.setIes(t.nextLine());
		
		//Rellenar historiales
		for(ClaseObject.Historial h:historiales) {
			Historial hJAxb = new Historial();
			hJAxb.setDni(h.getAlumno().getDni());
			hJAxb.setNombreAL(h.getAlumno().getNombre());
			//Rellenar notas del alumno
			for(Object[] o:h.getDatos()) {
				Asignatura a = (Asignatura) o[0];
				ClaseRAF.Nota n = (ClaseRAF.Nota) o[1];
				Nota nJB = new Nota(a.getId(), n.getNota(), a.getNombre(), 
						formatoXML.format(n.getFecha()));
				hJAxb.getNotas().add(nJB);
			}
			c.getHistoriales().add(hJAxb);
		}
		if(adJaxb.marshal(c)) {
			System.out.println("Fichero generado");
		}
		else {
			System.out.println("Error al generar XML");
		}
	}


}
