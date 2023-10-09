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

import ClaseRAF.Nota;
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



	public Nota obtenerNota(Alumno a, int idNota) {
		Nota resultado = null;
		// TODO Auto-generated method stub
		ObjectInputStream f = null;
		try {
			f= new ObjectInputStream(new FileInputStream(nombreFichero));
			while(true) {
				Historial h = (Historial)f.readObject();
				//Chequear alumno
				if(a.getDni().equalsIgnoreCase(h.getAlumno().getDni())) {
					//Chequear la nota
					for(Object[] o: h.getDatos()) {
						Nota n = (Nota) o[1];
						if(idNota == n.getId()) {
							return n;
						}
					}
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



	public boolean modificarNota(Alumno a, Nota n) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		ObjectInputStream fO = null;
		ObjectOutputStream fT = null;
		try {
			fO= new ObjectInputStream(new FileInputStream(nombreFichero));
			fT = new ObjectOutputStream(new FileOutputStream("historial.tmp",false));
			while(true) {
				Historial h = (Historial) fO.readObject();
				//Chequear alumno
				if(a.getDni().equalsIgnoreCase(h.getAlumno().getDni())) {
					//Chequear la nota
					for(Object[] o: h.getDatos()) {
						Nota nuevaNota = (Nota) o[1];
						if(n.getId() == nuevaNota.getId()) {
							nuevaNota.setNota(n.getNota());
							
						}
					}
				}
				fT.writeObject(h);				
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(fO!=null) {
				
					fO.close();
				} 
				if(fT!=null) {
					
					fT.close();
				} 
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		File fOR = new File(nombreFichero);
		if(fOR.delete()) {
			File fTmp = new File("historial.tmp");
			if(fTmp.renameTo(fOR)) {
				resultado=true;				
			}
			else {
				System.out.println("Error al renombrar el fichero");
			}
		}
		else {
			System.out.println("Error al borrar el fichero");
		}
		return resultado;
	}
	
}
