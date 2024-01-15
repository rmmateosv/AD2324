package Examen;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Modelo {
	
	final String NOMBRE_FT = "empleados.txt";
	final String NOMBRE_FB = "mensajes.bin";
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

	public boolean crearMensaje(Mensaje m) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile f = null;
		try {
			f= new RandomAccessFile(NOMBRE_FB,"rw");
			//POSICIONAR EL APUNTADOR FICHERO AL FINAL
			f.seek(f.getFilePointer()+f.length());
			//Escribimos
			f.writeLong(m.getFecha().getTime());
			f.writeInt(m.getId());
			StringBuffer texto = new StringBuffer(m.getNombre());
			texto.setLength(100);
			f.writeChars(texto.toString());
			texto = new StringBuffer(m.getTexto());
			texto.setLength(200);
			f.writeChars(texto.toString());
			f.writeBoolean(m.isLeido());
			
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

	public Empleado obtenerEmpleado(int idEmp) {
		// TODO Auto-generated method stub
		Empleado resultado = null;
		BufferedReader f = null;
		try {
			f= new BufferedReader(new FileReader(NOMBRE_FT));
			String linea;
			while((linea=f.readLine())!=null) {
				String[] campos = linea.split(";");
				if(Integer.parseInt(campos[0])==idEmp) {
					resultado = new Empleado(Integer.parseInt(campos[0]), campos[1], 
							Boolean.parseBoolean(campos[2]));
					return resultado;
				}
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

	public boolean marcarLeidos(Empleado emp) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		RandomAccessFile f =null;
		try {
			f= new RandomAccessFile(NOMBRE_FB,"rw");
			while(true) {
				//Leer el id del empleado ¡¡!SALTAR FECHA!
				f.seek(f.getFilePointer()+8);
				int idLeido = f.readInt();
				if(idLeido==emp.getId()) {
					//Leer si está leído
					f.seek(f.getFilePointer()+600);
					boolean leido = f.readBoolean();
					if(!leido) {
						//Escribir true en leido
						f.seek(f.getFilePointer()-1);
						f.writeBoolean(true);
						resultado=true;
					}
				}
				else {
					f.seek(f.getFilePointer()+601);
				}
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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

	public ArrayList<Mensaje> obtenerMensajes(Empleado emp) {
		// TODO Auto-generated method stub
		ArrayList<Mensaje> resultado=new ArrayList();
		RandomAccessFile f =null;
		try {
			f= new RandomAccessFile(NOMBRE_FB,"r");
			while(true) {
				//Leer el id del empleado ¡¡!SALTAR FECHA!
				f.seek(f.getFilePointer()+8);
				int idLeido = f.readInt();
				if(idLeido==emp.getId()) {
					//Nos ponemos al principio del registro
					f.seek(f.getFilePointer()-12);
					Mensaje m = new Mensaje();
					m.setFecha(new Date(f.readLong()));
					m.setId(f.readInt());
					m.setNombre("");
					for (int i = 0; i < 100; i++) {
						m.setNombre(m.getNombre() + f.readChar());
					}					
					m.setNombre(m.getNombre().trim());
					m.setTexto("");
					for (int i = 0; i < 200; i++) {
						m.setTexto(m.getTexto() + f.readChar());
					}
					m.setTexto(m.getTexto().trim());
					m.setLeido(f.readBoolean());
					resultado.add(m);
				}
				else {
					f.seek(f.getFilePointer()+601);
				}
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	
	public boolean borrarMensajes(int id) {
		boolean resultado = false;
		RandomAccessFile r = null;
		RandomAccessFile rTemp = null;

		try {
			r = new RandomAccessFile("mensajes.bin", "r");
			rTemp = new RandomAccessFile("mensajesTemp.bin", "rw");
			while (true) {				
				long fecha = r.readLong();
				int idLEido= r.readInt();
				if (idLEido != id) {
					rTemp.writeLong(fecha);
					rTemp.writeInt(idLEido);
					byte[] resto = new byte[601];
					r.read(resto);
					rTemp.write(resto);					
				} else {
					r.skipBytes(601);
				}
			}
		} catch (EOFException E) {

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no existe");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (r != null) {
					r.close();
				}
				if (rTemp != null) {
					rTemp.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		File original = new File("mensajes.bin");
		File remake = new File("mensajesTemp.bin");
		if (original.exists()) {
			if (original.delete()) {
				if (remake.renameTo(original)) {
					resultado = true;
				} else {
					System.out.println("Error al renombrar");
				}
			} else {
				System.out.println("Error,no se ha borrado el fichero");
			}
		} else {
			System.out.println("No existe");
		}
		return resultado;
	}

	public ArrayList<Mensaje> obtenerTodosMensajes() {
		// TODO Auto-generated method stub
		ArrayList<Mensaje> listMen = new ArrayList<Mensaje>();
		RandomAccessFile r = null;
		try {
			r = new RandomAccessFile("mensajes.bin", "r");
			Mensaje m = new Mensaje();

			while (true) {
				m.setFecha(new Date(r.readLong()));
				m.setId(r.readInt());
				m.setNombre("");
				for (int i = 0; i < 100; i++) {
					m.setNombre(m.getNombre() + r.readChar());
				}
				String letra = m.getNombre().trim();
				m.setNombre(letra);
				m.setTexto("");
				for (int i = 0; i < 200; i++) {
					m.setTexto(m.getTexto() + r.readChar());
				}
				m.setTexto(m.getTexto().trim());
				m.setLeido(r.readBoolean());
				listMen.add(m);
			}

		} catch (EOFException E) {

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no existe");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (r != null) {
					r.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listMen;
	}

	public boolean marshall(Chat c) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			Marshaller m = JAXBContext.newInstance(Chat.class).createMarshaller();
			m.marshal(c, new File(c.getIdEmpleado()+".xml"));
			resultado = true;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

}
