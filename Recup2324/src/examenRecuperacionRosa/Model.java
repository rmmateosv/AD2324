package examenRecuperacionRosa;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Model {
	private final String FICHERO_BIN_VIAJES = "viajes.bin";
	private final String FICHERO_VIAJES_OBJ= "ventas.obj";
	
	public Model() {
		
	}
	public boolean crearViaje(ViajeXML x, int codViaje, Agencia a) {
		boolean resultado = false;
		RandomAccessFile rnd = null;
		
		try {
			rnd = new RandomAccessFile(FICHERO_BIN_VIAJES,"rw");
			rnd.seek(rnd.length());
			rnd.writeInt(codViaje);
			StringBuffer sb = new StringBuffer(a.getMayorista());
			sb.setLength(50);
			rnd.writeChars(sb.toString());
			sb = new StringBuffer(x.getTitulo());
			sb.setLength(100);
			rnd.writeChars(sb.toString());
			sb = new StringBuffer(x.getDestino());
			sb.setLength(100);
			rnd.writeChars(sb.toString());
			rnd.writeInt(x.getPlazas());
			rnd.writeInt(x.getNoches() + 1);
			rnd.writeFloat(x.getPrecio() * 1.20f);
			rnd.writeBoolean(true);
			resultado = true;
		} catch (FileNotFoundException e) {
			System.out.println("No existen aún viajes");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if(rnd != null) {
				try {
					rnd.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}
	public ArrayList<ViajesBin> obtenerViajes() {
		ArrayList<ViajesBin> lista = new ArrayList<ViajesBin>();
			RandomAccessFile rnd = null;
		
		try {
			rnd = new RandomAccessFile(FICHERO_BIN_VIAJES,"r");
			while(true) {
				ViajesBin v = new ViajesBin();
				v.setCodigo(rnd.readInt());
				String mayorista = "";
				for(int i = 0; i < 50;i++) {
					mayorista+=rnd.readChar();
				}
				v.setMayorista(mayorista.trim());
				String titulo = "";
				for(int i = 0; i < 100;i++) {
					titulo+=rnd.readChar();
				}
				v.setTitulo(titulo.trim());
				String destino = "";
				for(int i = 0; i < 100;i++) {
					destino+=rnd.readChar();
				}
				v.setDestino(destino.trim());
				v.setnPlazas(rnd.readInt());
				v.setDuracion(rnd.readInt());
				v.setPrecio(rnd.readFloat());
				v.setDisponible(rnd.readBoolean());
				lista.add(v);
			}
		}catch(EOFException e) {
			
		}
		catch (FileNotFoundException e) {
			System.out.println("No existen aún viajes");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if(rnd != null) {
				try {
					rnd.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return lista;
	}
	public int obtenerCodigoUltViaje() {
		int cod = 0;
		RandomAccessFile rnd = null;
		try {
			rnd = new RandomAccessFile(FICHERO_BIN_VIAJES,"r");
			rnd.seek(rnd.length());
			rnd.seek(rnd.getFilePointer() - 517);
			cod = rnd.readInt();
		} catch (FileNotFoundException e) {
			System.out.println("No hay viajes aún");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if(rnd != null) {
				try {
					rnd.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cod;
	}
	
	
	public  Agencia unMarshall(String fichero) {
		 Agencia a = null;
		try {
			JAXBContext contexto = JAXBContext.newInstance(Agencia.class);
			Unmarshaller um = contexto.createUnmarshaller();
			a =(Agencia)um.unmarshal(new File(fichero));

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	public  void  Marshall(Agencia a, String fichero) {
		
		try {
			JAXBContext contexto = JAXBContext.newInstance(Agencia.class);
			Marshaller m = contexto.createMarshaller();
			
	
			m.marshal(a,new File(fichero));

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ArrayList<ViajesBin> obtenerViajesDuracion(int duracion) {
		ArrayList<ViajesBin> lista = new ArrayList<ViajesBin>();
		RandomAccessFile rnd = null;
		try {
			rnd = new RandomAccessFile(FICHERO_BIN_VIAJES,"r");
			while(true) {
				rnd.seek(rnd.getFilePointer() + 508);
				int dur = rnd.readInt();
				if(dur <= duracion) {
					ViajesBin v = new ViajesBin();
					rnd.seek(rnd.getFilePointer() - 512);
					v.setCodigo(rnd.readInt());
					String mayorista = "";
					for(int i = 0; i < 50;i++) {
						mayorista+=rnd.readChar();
					}
					v.setMayorista(mayorista.trim());
					String titulo = "";
					for(int i = 0; i < 100;i++) {
						titulo+=rnd.readChar();
					}
					v.setTitulo(titulo.trim());
					String destino = "";
					for(int i = 0; i < 100;i++) {
						destino+=rnd.readChar();
					}
					v.setDestino(destino.trim());
					v.setnPlazas(rnd.readInt());
					v.setDuracion(rnd.readInt());
					v.setPrecio(rnd.readFloat());
					v.setDisponible(rnd.readBoolean());
					lista.add(v);
				}else {
					rnd.skipBytes(5);
				}
				
			}
			
		}catch(EOFException e) {
			
		}
		catch (FileNotFoundException e) {
			System.out.println("No existen viajes");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if(rnd != null) {
				try {
					rnd.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return lista;
	}
	public ViajesBin obtenerViajeConChequeo(int cod, int numPersonas) {
		ViajesBin v = null;
		RandomAccessFile rnd = null;
		try {
			rnd = new RandomAccessFile(FICHERO_BIN_VIAJES,"r");
			while(true) {
				int codigo = rnd.readInt();
				if(codigo == cod) {
					rnd.seek(rnd.getFilePointer() + 500);
					if(rnd.readInt() >= numPersonas) {
							rnd.seek(rnd.getFilePointer() + 8);
							if(rnd.readBoolean() == true) {
								rnd.seek(rnd.getFilePointer() - 517);
								v = new ViajesBin();
								v.setCodigo(rnd.readInt());
								String mayorista = "";
								for(int i = 0; i < 50;i++) {
									mayorista+=rnd.readChar();
								}
								v.setMayorista(mayorista.trim());
								String titulo = "";
								for(int i = 0; i < 100;i++) {
									titulo+=rnd.readChar();
								}
								v.setTitulo(titulo.trim());
								String destino = "";
								for(int i = 0; i < 100;i++) {
									destino+=rnd.readChar();
								}
								v.setDestino(destino.trim());
								v.setnPlazas(rnd.readInt());
								v.setDuracion(rnd.readInt());
								v.setPrecio(rnd.readFloat());
								v.setDisponible(rnd.readBoolean());
								return v;
								
							}
					}else {
						rnd.skipBytes(9);
					}
				}else {
					rnd.skipBytes(513);
				}
			}
		} catch(EOFException e) {
			
		}
		catch (FileNotFoundException e) {
			System.out.println("No hay viajes aún");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if(rnd != null) {
				try {
					rnd.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return v;
	}
	public boolean crearViajesObj(VentasObj v) {
		boolean resultado = false;
		File f = new File(FICHERO_VIAJES_OBJ);
		ObjectOutputStream fv = null;
		try {
			if(f.exists()) {
				fv = new MyObjectOutputStream(new FileOutputStream(FICHERO_VIAJES_OBJ,true));
				
			}else {
				fv = new  ObjectOutputStream(new FileOutputStream(FICHERO_VIAJES_OBJ,true));
			}
			fv.writeObject(v);
			resultado=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(fv!=null) {
				try {
					fv.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}
	public boolean modificarViaje(ViajesBin v, int numPersonas) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		RandomAccessFile rnd = null;
		try {
			rnd = new RandomAccessFile(FICHERO_BIN_VIAJES,"rw");
			while(true) {
				int codigo = rnd.readInt();
				if(codigo == v.getCodigo()) {
					rnd.seek(rnd.getFilePointer() + 500);
					int plazas = rnd.readInt();
					//Escribir plazas menos las vendidas
					rnd.seek(rnd.getFilePointer() - 4);
					rnd.writeInt(plazas-numPersonas);
					if(plazas-numPersonas==0) {
						//Poner no disponible
						rnd.seek(rnd.getFilePointer() + 8);
						rnd.writeBoolean(false);
					}
					return true;			
				}else {
					rnd.skipBytes(513);
				}
			}
		} catch(EOFException e) {
			
		}
		catch (FileNotFoundException e) {
			System.out.println("No hay viajes aún");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if(rnd != null) {
				try {
					rnd.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}
	public ArrayList<VentasObj> obtenerVentas() {
		// TODO Auto-generated method stub
		ArrayList<VentasObj> resultado = new ArrayList<VentasObj>();
		ObjectInputStream f = null;
		try {
			f=new ObjectInputStream(new FileInputStream(FICHERO_VIAJES_OBJ));
			while(true) {
				resultado.add((VentasObj) f.readObject());
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
			if(f != null) {
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
	public VentasObj obtenerVenta(String codigo) {
		// TODO Auto-generated method stub
		VentasObj resultado = null;
		ObjectInputStream f = null;
		try {
			f=new ObjectInputStream(new FileInputStream(FICHERO_VIAJES_OBJ));
			while(true) {
				VentasObj v = (VentasObj) f.readObject();
				if(v.getCodigo().equals(codigo)) {
					return v;
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(f != null) {
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
	public ViajesBin obtenerViaje(int viaje) {
		// TODO Auto-generated method stub
		ViajesBin resultado = null;
		RandomAccessFile rnd = null;
		try {
			rnd = new RandomAccessFile(FICHERO_BIN_VIAJES,"r");
			while(true) {
				int codigo = rnd.readInt();
				if(codigo == viaje) {
					ViajesBin v = new ViajesBin();
					v.setCodigo(codigo);
					String mayorista = "";
					for(int i = 0; i < 50;i++) {
						mayorista+=rnd.readChar();
					}
					v.setMayorista(mayorista.trim());
					String titulo = "";
					for(int i = 0; i < 100;i++) {
						titulo+=rnd.readChar();
					}
					v.setTitulo(titulo.trim());
					String destino = "";
					for(int i = 0; i < 100;i++) {
						destino+=rnd.readChar();
					}
					v.setDestino(destino.trim());
					v.setnPlazas(rnd.readInt());
					v.setDuracion(rnd.readInt());
					v.setPrecio(rnd.readFloat());
					v.setDisponible(rnd.readBoolean());
					return v;
					
				}else {
					rnd.skipBytes(513);
				}
			}
		} catch(EOFException e) {
			
		}
		catch (FileNotFoundException e) {
			System.out.println("No hay viajes aún");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if(rnd != null) {
				try {
					rnd.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}
	
	
}
