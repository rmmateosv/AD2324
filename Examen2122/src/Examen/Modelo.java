package Examen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Modelo {
	
	final String NOMBRE_FT = "empleados.txt";
	public Modelo() {
		
	}

	public ArrayList<Empleado> obtenerEmpleados() {
		// TODO Auto-generated method stub
		ArrayList<Empleado> resultado = new ArrayList();
		BufferedReader f = null;
		try {
			f= new BufferedReader(new FileReader(NOMBRE_FT));
			String linea;
			while((linea=f.readLine())!=null) {
				String[] campos = linea.split(";");
				Empleado e = new Empleado(Integer.parseInt(campos[0]), campos[1], 
						Boolean.parseBoolean(campos[2]));
				resultado.add(e);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("No hy fichero de empleados");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(f!=null) {
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}
}
