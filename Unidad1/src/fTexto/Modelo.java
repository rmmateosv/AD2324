package fTexto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Modelo {
	private String nombreFichero;
	public SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

	public Modelo(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	public Alumno obtenerAlumno(String dniBuscado) {
		// TODO Auto-generated method stub
		Alumno resultado = null;

		// Declarar el fichero para leer
		BufferedReader f = null;
		try {
			// Apertura del fichero
			f = new BufferedReader(new FileReader(nombreFichero));

			// REcorrido del fichero
			String linea;
			while ((linea = f.readLine()) != null) {
				// Dividir la línea por ; y guardar en array de String
				String[] campos = linea.split(";");
				// Comprabamos si el dni de línea coincide con el dni buscado
				if (dniBuscado.equalsIgnoreCase(campos[0])) {
					// Alumno encontrado
					resultado = new Alumno(campos[0], campos[1], formatoFecha.parse(campos[2]),
							Integer.parseInt(campos[3]), Float.parseFloat(campos[4]), Boolean.parseBoolean(campos[5]));

					// Acabar el método devolviendo el alumno
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
		} finally {
			try {
				if (f != null) {
					f.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultado;
	}

	public boolean crearAlumno(Alumno a) {
		// TODO Auto-generated method stub
		boolean resultado = false;

		// Declaramos el fichero
		BufferedWriter f = null;

		// Abrir fichero
		try {
			f = new BufferedWriter(new FileWriter(nombreFichero, true));
			// Escribir los datos del alumno con el formato definido en el fichero
			// Ponemos un salto de línea al final de cada alumno
			f.write(a.getDni() + ";" + 
			a.getNombre() + ";" + 
			formatoFecha.format(a.getFechaN()) + ";" + 
			a.getNumExp()+ ";" + 
			a.getEstatura() + ";" + 
			a.isActivo() + "\n");
			resultado = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (f != null) {
					f.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}

	public ArrayList<Alumno> obtenerAlumnos() {
		// TODO Auto-generated method stub
		ArrayList<Alumno> resultado = new ArrayList<Alumno>();
		
		// Declarar el fichero para leer
		BufferedReader f = null;
		try {
			// Apertura del fichero
			f = new BufferedReader(new FileReader(nombreFichero));

			// REcorrido del fichero
			String linea;
			while ((linea = f.readLine()) != null) {
				// Dividir la línea por ; y guardar en array de String
				String[] campos = linea.split(";");
				
				Alumno a = new Alumno(campos[0], campos[1], formatoFecha.parse(campos[2]),
							Integer.parseInt(campos[3]), Float.parseFloat(campos[4]), 
							Boolean.parseBoolean(campos[5]));
				//Añadimos cada alumno al array list resultado
				resultado.add(a);					
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
		} finally {
			try {
				if (f != null) {
					f.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultado;
	}

	public boolean bajaAlumno(Alumno a) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		//Declarar dos ficheros el fichero Original, para leer
		//un fichero tmp para escribir
		BufferedReader fOriginal = null;
		BufferedWriter fTmp = null;
		try {
			//Abrir los dos ficheros
			fOriginal = new BufferedReader(new FileReader(nombreFichero));
			fTmp = new BufferedWriter(new FileWriter("alumnos.tmp",false));
			
			//REcorrer el fOriginal
			String linea;
			while((linea=fOriginal.readLine())!=null) {
				//Dividir la línea en campos
				String[] campos = linea.split(";");
				//Comprobar si hay que modificar el alumno o no
				if(campos[0].equalsIgnoreCase(a.getDni())) {
					//Modficar el campo baja
					a.setActivo(false);
					//Escribir el alumno modificado
					fTmp.write(a.getDni()+";"+
								a.getNombre()+";"+
								formatoFecha.format(a.getFechaN())+";"+
								a.getNumExp()+";"+
								a.getEstatura()+";"+
								a.isActivo()+"\n"
					);					
				}
				else {
					//Escribir el alumno en el fichero temporal
					fTmp.write(linea+"\n");
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(fOriginal!=null) {
				try {
					fOriginal.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fTmp!=null) {
				try {
					fTmp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//Borrar el fichero original y renombrar el temporal
		File fO = new File(nombreFichero);
		File fT = new File("alumnos.tmp");
		if(fO.delete()) {
			//REnombrar
			if(fT.renameTo(fO)) {
				resultado = true;
			}
			else {
				System.out.println("Error, no se ha podido borrar el fT");
			}
		}
		else {
			System.out.println("Error, no se ha podido borrar el fO");
		}
		return resultado;
	}

	public boolean borrarAlumno(Alumno a) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		//Declarar dos ficheros el fichero Original, para leer
		//un fichero tmp para escribir
		BufferedReader fOriginal = null;
		BufferedWriter fTmp = null;
		try {
			//Abrir los dos ficheros
			fOriginal = new BufferedReader(new FileReader(nombreFichero));
			fTmp = new BufferedWriter(new FileWriter("alumnos.tmp",false));
			
			//REcorrer el fOriginal
			String linea;
			while((linea=fOriginal.readLine())!=null) {
				//Dividir la línea en campos
				String[] campos = linea.split(";");
				//Comprobar si hay que modificar el alumno o no
				if(!campos[0].equalsIgnoreCase(a.getDni())) {
					//Escribir el alumno en el fichero temporal
					fTmp.write(linea+"\n");				
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(fOriginal!=null) {
				try {
					fOriginal.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fTmp!=null) {
				try {
					fTmp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//Borrar el fichero original y renombrar el temporal
		File fO = new File(nombreFichero);
		File fT = new File("alumnos.tmp");
		if(fO.delete()) {
			//REnombrar
			if(fT.renameTo(fO)) {
				resultado = true;
			}
			else {
				System.out.println("Error, no se ha podido borrar el fT");
			}
		}
		else {
			System.out.println("Error, no se ha podido borrar el fO");
		}
		return resultado;
	}

}
