package fTexto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Modelo {
	private String nombreFichero;

	public Modelo(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	public Alumno obtenerAlumno(String dni) {
		// TODO Auto-generated method stub
		Alumno resultado = null;
		
		//Declarar el fichero para leer
		try {
			//Apertura del fichero
			BufferedReader f = new BufferedReader(new FileReader(nombreFichero));
			
			//REcorrido del fichero 
			String linea;
			while((linea=f.readLine())!=null) {
				
			}
			
			//Cierre del fichero
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichero aún no existe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	
}
