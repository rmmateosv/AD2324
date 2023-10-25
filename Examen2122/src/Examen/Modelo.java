package Examen;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

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
				f.skipBytes(8);
				int idLeido = f.readInt();
				if(idLeido==emp.getId()) {
					//Leer si está leído
					f.skipBytes(600);
					boolean leido = f.readBoolean();
					if(!leido) {
						//Escribir true en leido
						f.skipBytes(-1);
						f.writeBoolean(true);
					}
				}
				else {
					f.skipBytes(601);
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
				f.skipBytes(8);
				int idLeido = f.readInt();
				if(idLeido==emp.getId()) {
					//Nos ponemos al principio del registro
					f.skipBytes(-12);
					Mensaje m
				}
				else {
					f.skipBytes(601);
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
}
