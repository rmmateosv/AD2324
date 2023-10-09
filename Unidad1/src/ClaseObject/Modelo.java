package ClaseObject;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import fTexto.Alumno;

public class Modelo {
	private String nombreFichero = "historial.obj";

	public Modelo() {
		
	}

	

	public Historial obtenerHistorial(Alumno a) {
		// TODO Auto-generated method stub
		Historial resultado=null;
		ObjectInputStream f = null;
		try {
			f= new ObjectInputStream(new FileInputStream(nombreFichero));
			while(true) {
				Historial h = (Historial)f.readObject();
				if(h.getAlumno().getDni().equalsIgnoreCase(a.getDni())) {
					return h;
				}
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichero aún no existe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
	
	public boolean crearHistorial(Historial h) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		
		ObjectOutputStream f = null;
		try {
			//Chequear si existe el fichero
			if(new File(nombreFichero).exists()) {
				f= new MiObjectOutputStream(new FileOutputStream(nombreFichero,true));
			}
			else {
				f= new ObjectOutputStream(new FileOutputStream(nombreFichero,true));
			}
			
			f.writeObject(h);
			resultado=true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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



	public ArrayList<Historial> obtenerHistoriales() {
		// TODO Auto-generated method stub
		ArrayList<Historial> resultado = new ArrayList<Historial>();
		
		ObjectInputStream f = null;
		try {
			f= new ObjectInputStream(new FileInputStream(nombreFichero));
			while(true) {
				Historial h = (Historial)f.readObject();
				resultado.add(h);
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichero aun no existe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
