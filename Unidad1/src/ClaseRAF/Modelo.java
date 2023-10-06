package ClaseRAF;

import java.io.EOFException;
import java.io.File;
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
		boolean resultado = false;
		RandomAccessFile f = null;

		try {
			f = new RandomAccessFile(nombreFichero, "rw");
			// EL PUENTERO DEL FICHERO ESTÁ AL PRINCIPIO
			// ¡¡HAY QUE MOVERLO AL FINAL PARA QUE NO SE SOBREESCRIBA LO QUE HAY!!
			f.seek(f.length());
			// Escribir la nota
			f.writeInt(n.getId());
			// HAcer que el DNI sea de 9 caracteres exactos
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
		} finally {
			if (f != null) {
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
			f = new RandomAccessFile(nombreFichero, "r");
			while (true) {
				Nota n = new Nota();
				n.setId(f.readInt());

				// HAcer que el DNI sea de 9 caracteres exactos
				n.setDni("");
				for (int i = 0; i < 9; i++) {
					n.setDni(n.getDni() + f.readChar());
				}
				// Limpiar espacios si hay
				n.setDni(n.getDni().trim());

				n.setAsig(f.readInt());
				n.setFecha(new Date(f.readLong()));
				n.setNota(f.readFloat());

				n.setValoracion("");
				for (int i = 0; i < 50; i++) {
					n.setValoracion(n.getValoracion() + f.readChar());
				}
				// Limpiar espacios si hay
				n.setValoracion(n.getValoracion().trim());

				resultado.add(n);
			}

		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichero aún no existe");
			;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
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

	public Nota obtenerNota(int idBuscado) {
		// TODO Auto-generated method stub
		Nota resultado = null;
		RandomAccessFile f = null;
		try {
			f = new RandomAccessFile(nombreFichero, "r");
			while (true) {
				// Leer el id
				int idLeido = f.readInt();
				if (idLeido == idBuscado) {
					// Encontrado
					resultado = new Nota();
					resultado.setId(idLeido);
					String dni = "";
					for (int i = 0; i < 9; i++) {
						dni += f.readChar();
					}
					// trim:quita espacios en blanco
					resultado.setDni(dni.trim());
					resultado.setAsig(f.readInt());
					resultado.setFecha(new Date(f.readLong()));
					resultado.setNota(f.readFloat());
					String val = "";
					for (int i = 0; i < 50; i++) {
						val += f.readChar();
					}
					// trim:quita espacios en blanco
					resultado.setValoracion(val.trim());
					return resultado;

				} else {
					// Continuar al siguiente
					// Desplazar desde la posición actual 134 B
					// 18 dni+4 asig+8 fecha+4 nota+100 valoracion=134
					f.seek(f.getFilePointer() + 134);
				}
			}
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
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

	public boolean modificarNota(Nota n) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile f = null;
		try {
			f = new RandomAccessFile(nombreFichero, "rw");
			while (true) {
				// Leer el id
				int idLeido = f.readInt();
				if (idLeido == n.getId()) {
					// Encontrado
					// Saltamos para colocar el apuntador
					// justo delante del campo nota
					// Saltamos 18 dni+ 4 asi+ 8 fecha=30B
					f.seek(f.getFilePointer() + 30);
					// Escribir nota
					f.writeFloat(n.getNota());
					return true;

				} else {
					// Continuar al siguiente
					// Desplazar desde la posición actual 134 B
					// 18 dni+4 asig+8 fecha+4 nota+100 valoracion=134
					f.seek(f.getFilePointer() + 134);
				}
			}
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
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

	public ArrayList<Nota> obtenerNotasAlumno(String dniBuscado) {
		// TODO Auto-generated method stub

		ArrayList<Nota> resultado = new ArrayList<Nota>();

		RandomAccessFile f = null;

		try {
			f = new RandomAccessFile(nombreFichero, "r");

			while (true) {
				String dni = "";
				//Colocar puntero para leer dni
				f.seek(f.getFilePointer() + 4);
				for (int i = 0; i < 9; i++) {
					dni += f.readChar();
				}
				dni = dni.trim();

				if (dniBuscado.equalsIgnoreCase(dni)) {
					Nota n = new Nota();
					//Desplazo hacia atrás 18 dni + 4 id
					f.seek(f.getFilePointer() - 22);
					n.setId(f.readInt());
					n.setDni(dni);
					//Saltar dni
					f.seek(f.getFilePointer() + 18);
					n.setAsig(f.readInt());
					n.setFecha(new Date(f.readLong()));
					n.setNota(f.readFloat());
					String val = "";
					for (int i = 0; i < 50; i++) {
						val += f.readChar();
					}
					// trim:quita espacios en blanco
					n.setValoracion(val.trim());

					resultado.add(n);

				} else {
					// Continuar al siguiente
					// Desplazar desde la posición actual 116 B
					// +4 asig+8 fecha+4 nota+100 valoracion=116

					f.seek(f.getFilePointer() + 116);
				}

			}

		} catch (EOFException e) {
			// TODO: handle exception
		}

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
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

	public boolean borrarNota(Nota n) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile fO=null, fTmp=null;
		try {
			fO=new RandomAccessFile(nombreFichero, "r");
			fTmp=new RandomAccessFile("notas.tmp", "rw");
			while(true) {
				int idLeido = fO.readInt();
				if(idLeido==n.getId()) {
					// Desplazarme hasta la siguiente nota
					fO.seek(fO.getFilePointer()+134);
				}
				else {
					//Escribir en el tmp el id que ya se ha leído y el resto de bytes
					fTmp.writeInt(idLeido);
					byte[] resto = new byte[134];
					fO.read(resto);
					fTmp.write(resto);
					
				}
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
		}
		finally {
			try {
				if (fO != null) {
					fO.close();
				} 
				if (fTmp != null) {
					fTmp.close();
				}
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		File fOri = new File(nombreFichero);
		if(fOri.delete()) {
			File fT = new File("notas.tmp");
			if(fT.renameTo(fOri)) {
				resultado=true;
			}
			else {
				System.out.println("error al renombrar fO");
			}
		}
		else {
			System.out.println("error al borrar fO");
		}
		return resultado;
	}

}
