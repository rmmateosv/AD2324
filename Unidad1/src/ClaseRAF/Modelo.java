package ClaseRAF;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

public class Modelo {
	private String nombreFichero;

	public Modelo(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	public boolean crearNota(Nota n) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		RandomAccessFile f = null;
		
		try {
			f=new RandomAccessFile(nombreFichero, "rw");
			//EL PUENTERO DEL FICHERO ESTÁ AL PRINCIPIO
			//¡¡HAY QUE MOVERLO AL FINAL PARA QUE NO SE SOBREESCRIBA LO QUE HAY!!
			f.seek(f.length());
			//Escribir la nota
			f.writeInt(n.getId());
			//HAcer que el DNI sea de 9 caracteres exactos
			StringBuffer texto = new StringBuffer(n.getDni());
			texto.setLength(9);
			f.writeChars(texto.toString());
			f.writeInt(n.getAsig());
			f.writeLong(n.getFecha().getTime());
			f.writeFloat(n.getNota());
			texto = new StringBuffer(n.getValoracion());
			texto.setLength(50);
			f.writeChars(texto.toString());
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

	public ArrayList<Nota> obtenerNotas() {
		// TODO Auto-generated method stub
		ArrayList<Nota> resultado = new ArrayList<Nota>();
		
		RandomAccessFile f = null;
		
		try {
			f=new RandomAccessFile(nombreFichero, "r");	
			while(true) {
				Nota n = new Nota();
				n.setId(f.readInt());
				
				//HAcer que el DNI sea de 9 caracteres exactos
				n.setDni("");
				for(int i=0;i<9;i++) {
					n.setDni(n.getDni()+f.readChar());
				}
				//Limpiar espacios si hay
				n.setDni(n.getDni().trim());
				
				n.setAsig(f.readInt());
				n.setFecha(new Date(f.readLong()));
				n.setNota(f.readFloat());
								
				n.setValoracion("");
				for(int i=0;i<50;i++) {
					n.setValoracion(n.getValoracion()+f.readChar());
				}
				//Limpiar espacios si hay
				n.setValoracion(n.getValoracion().trim());
				
				resultado.add(n);
			}
			
			
		} 
		catch ( EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichero aún no existe");;
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
