package ClaseFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Ejercicio1 {
	
	public static Scanner t  = new Scanner(System.in);
	//Ruta de la carpeta
	public static String rutaCarpeta = "C:\\rosa";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion=0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Info miCarpeta");
			System.out.println("2-Mostrar contenido de miCarpeta");
			System.out.println("3-Crear fichero");
			System.out.println("4-Renombrar fichero");
			System.out.println("5-Borrar fichero");
			opcion = t.nextInt();t.nextLine();
			
			switch(opcion) {
				case 1:
					info();
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
			}
		
		}while(opcion!=0);
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
		//Tenemos que declarar un objeto que represente el formato deseado
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		System.out.println("Fecha Modificación:"+ formatoFecha.format(fecha));
	}

}
