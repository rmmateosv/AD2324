package ClaseFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Ejercicio1 {
	
	public static Scanner t  = new Scanner(System.in);
	//Ruta de la carpeta
	public static String rutaCarpeta = "rosa";
	
	//Tenemos que declarar un objeto que represente el formato deseado de fecha
	static SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion=0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Info miCarpeta");
			System.out.println("2-Mostrar contenido de miCarpeta");
			System.out.println("3-Crear fichero o carpeta");
			System.out.println("4-Renombrar fichero");
			System.out.println("5-Borrar fichero");
			System.out.println("6-Mostrar contenido de miCarpeta recursivo");
			opcion = t.nextInt();t.nextLine();
			
			switch(opcion) {
				case 1:
					info();
					break;
				case 2:
					mostrarContenido();
					
					break;
				case 3:
					crearObjeto();
					break;
				case 4:
					renombrarFichero();
					break;
				case 5:
					borrarObjeto();
					break;
				case 6:
					mostrarContenido(rutaCarpeta,0);
				
			}
		
		}while(opcion!=0);
	}
	private static void mostrarContenido(String ruta, int numTab) {
		// TODO Auto-generated method stub
		File f = new File(ruta);
		//Obtener el contenido
		File[] contenido = f.listFiles();
		//Recorremos contenido para mostrar
		for(File c:contenido) {	
			//Pintar tabulaciones
			for(int i=0;i<numTab;i++) {
				System.out.print("\t");
			}
			System.out.println(c.getName());
			if(c.isDirectory()) {
				mostrarContenido(c.getAbsolutePath(), numTab+1);
			}
		}
	}
	private static void borrarObjeto() {
		// TODO Auto-generated method stub
		mostrarContenido();
		System.out.println("Introduce el nombre del objeto a borrar");
		String nombre = t.nextLine();
		//Comprobar que existe
		File f = new File(rutaCarpeta+File.separator+nombre);
		if(f.exists()) {
			if(f.delete()) {
				System.out.println("Objeto borrado");
			}
			else {
				//Si la carpeta no está vacía no se borra y se produce un error
				System.out.println("Error al borrar el objeto");
			}
		}
		else {
			System.out.println("Error, no existe el objeto");
		}
		
	}
	private static void renombrarFichero() {
		// TODO Auto-generated method stub
		mostrarContenido();
		System.out.println("Introduce el nombre del fichero a renombrar");
		String nombre = t.nextLine();
		nombre = rutaCarpeta + File.separator+nombre;
		//Comprobar que existe y es fichero
		File f = new File(nombre);
		if(f.exists() && f.isFile()) {
			System.out.println("Introduce el nuevo nombre");
			String nuevoNombre = t.nextLine();
			nuevoNombre = rutaCarpeta + File.separator + nuevoNombre;
			//Comprobar que no existe el fichero con el nuevo nombre
			File f2 = new File(nuevoNombre);
			if(!f2.exists()) {
				//YA se puede renombrar
				if(f.renameTo(f2)) {
					System.out.println("Fichero renombrado");
					mostrarContenido();
				}
				else {
					System.out.println("Error al renombrar el fichero");
				}
			}
			else {
				System.out.println("Error, nuevo fichero ya existe");
			}
		}
		else {
			System.out.println("Error, no existe el fichero");
		}
	}
	private static void crearObjeto() {
		try {
			// TODO Auto-generated method stub
			System.out.println("Introduce el nombre del objeto a crear");
			String nombre = t.nextLine();
			nombre = rutaCarpeta+File.pathSeparator+nombre;
			//Comprobar que no existes antes de seguir
			File f = new File(nombre);
			if(f.exists()) {
				System.out.println("Error, ya existe el objeto");
			}
			else {
				//Pedir Tipo
				System.out.println("Introduce tipo (f-Fichero/*-Carpeta)");
				String tipo = t.nextLine();
				if(tipo.equalsIgnoreCase("f")) {
					if(f.createNewFile()) {
						System.out.println("Fichero creado");
						mostrarContenido();
					}
				}
				else {
					if(f.mkdir()) {
						System.out.println("Carpeta creada");
						mostrarContenido();
					}
				}
			}
		}
		catch (IOException e) {
			// TODO: handle exception
			System.out.println("Error: Se ha producido un error al crear el objeto");
			e.printStackTrace();
		}
	}
	private static void mostrarContenido() {
		// TODO Auto-generated method stub
		//DEclara objeto file a la carpeta
		File f = new File(rutaCarpeta);
		//Obtener el contenido
		File[] contenido = f.listFiles();
		//Recorremos contenido para mostrar
		for(File c:contenido) {
			//Obtener tipo f si es fichero c si es carpeta
			String tipo;
			if(c.isDirectory()) {
				tipo="c";
			}
			else {
				tipo="f";
			}
			//Obtener permisos
			String permisos;
			if(c.canRead()) {
				permisos="r";
			}
			else {
				permisos="-";
			}
			if(c.canWrite()) {
				permisos+="w";
			}
			else {
				permisos+="-";
			}
			if(c.canExecute()) {
				permisos+="x";
			}
			else {
				permisos+="-";
			}
			System.out.println(c.getName()+
					"\t"+c.length()+
					"\t"+tipo+
					"\t"+formatoFecha.format(new Date(f.lastModified()))+
					"\t"+permisos);
		}
		
	}
	private static void info() {
		// TODO Auto-generated method stub
		
		//Declara un objeto File que apunte a nuestra carpeta
		File miCarpeta = new File(rutaCarpeta);
		
		//Mostrar si existe o no
		System.out.println("Existe:"+miCarpeta.exists());
		//Mostrar si es carpeta
		System.out.println("Es Carpeta:"+miCarpeta.isDirectory());
		//Mostrar si es fichero
		System.out.println("Es Fichero:"+miCarpeta.isFile());
		//Mostrar el nombre
		System.out.println("Ruta:"+miCarpeta.getAbsolutePath());
		//Mostrar fecha de modificación
		Date fecha = new Date(miCarpeta.lastModified());
		System.out.println("Fecha Modificación:"+fecha);
		//Mostrar la fecha con formato dd/mm/yyyy hh:mm		
		System.out.println("Fecha Modificación:"+ formatoFecha.format(fecha));
	}

}
