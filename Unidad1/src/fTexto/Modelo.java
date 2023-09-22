package fTexto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Modelo {
	private String nombreFichero;
	public SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

	public Modelo(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	public Alumno obtenerAlumno(String dniBuscado) {
		// TODO Auto-generated method stub
		Alumno resultado = null;
		
		//Declarar el fichero para leer
		BufferedReader f = null;
		try {
			//Apertura del fichero
			f = new BufferedReader(new FileReader(nombreFichero));
			
			//REcorrido del fichero 
			String linea;
			while((linea=f.readLine())!=null) {
				//Dividir la línea por ; y guardar en array de String
				String[] campos = linea.split(";");
				//Comprabamos si el dni de línea coincide con el dni buscado
				if(dniBuscado.equalsIgnoreCase(campos[0])) {
					//Alumno encontrado
					resultado = new Alumno(campos[0],campos[1], 
							formatoFecha.parse(campos[2]), Integer.parseInt(campos[3]), 
							Float.parseFloat(campos[4]), Boolean.parseBoolean(campos[5]));
					
					//Acabar el método devolviendo el alumno
					return resultado;
				}
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichero aún no existe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Se ha encontrado una fecha incorrecta en el fichero");
		}
		finally {
			try {
				f.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resultado;
	}

	
	
}
