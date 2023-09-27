package fBinario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

public class Modelo {
	private String nombreFichero; // Fichero binario de asignaturas

	// Fichero de accceso aleatorio para obtener el id de nueva asig
	private String nombreFichConfig = ".Miconfig";

	public Modelo(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	public int obtenerId() {
		// TODO Auto-generated method stub
		// Si el fichero .config existe debe devolver el nº
		// que hay en el fichero más 1 y modificar el nº del
		// fichero con el nuevo valor.

		// Si no existe, devuelve 1 y el fichero se crea con
		// el nº 1.
		int resultado = 1;

		// DEclarar el fichero de acceso aleatorio
		RandomAccessFile fA = null;

		try {
			// Chequear si existe .config
			File f = new File(nombreFichConfig);
			boolean existe = f.exists();
			// Abrir el fichero RandomAccessFile para leer y escribir (rw)
			fA = new RandomAccessFile(nombreFichConfig, "rw");
			if (existe) {
				// Recorrer el fichero y leer el nº
				while (true) {
					// Leer el nº
					resultado = fA.readInt() + 1;
					// Colocamos el apuntador del fichero al principio para sobreescribir el nº
					// Despalazamos hacia atrás(restamos) 4 Bytes
					fA.seek(fA.getFilePointer() - 4);
					// Escribimos el nuevo nº
					fA.writeInt(resultado);
				}
			}
			else {
				// Escribimos el nuevo nº
				fA.writeInt(resultado);
			}
			
			
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			if (fA != null) {
				try {
					fA.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return resultado;
	}

	public boolean crearAsig(Asignatura as) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		
		//Declarar fichero 
		DataOutputStream f = null;
		
		
		try {
			//abrir fichero
			f = new DataOutputStream(new FileOutputStream(nombreFichero,true));
			//Escribir asignatura
			//ID (int)
			f.writeInt(as.getId());
			//Nombre
			f.writeChars(as.getNombre()+"\n");
			//Fecha (long)
			f.writeLong(as.getFechaRD().getTime());
			f.writeFloat(as.getCreditos());
			f.writeBoolean(as.isActiva());
			resultado = true;
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

	public ArrayList<Asignatura> obtenerAsignaturas() {
		// TODO Auto-generated method stub
		ArrayList<Asignatura> resultado = new ArrayList<Asignatura>();
		
		DataInputStream f = null;
		
		try {
			f=new DataInputStream(new FileInputStream(nombreFichero));
			while(true) {
				Asignatura as = new Asignatura();
				as.setId(f.readInt());
				//Nombre
				char letra;
				//Inicializamos el nombre a vacío para
				//poder concatenar
				as.setNombre("");
				while((letra=f.readChar())!='\n') {
					as.setNombre(as.getNombre()+letra);
				}
				//Convertir fecha long en fecha Date
				as.setFechaRD(new Date(f.readLong()));
				as.setCreditos(f.readFloat());
				as.setActiva(f.readBoolean());
				
				resultado.add(as);
				
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Aún no hay asignaturas");
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
