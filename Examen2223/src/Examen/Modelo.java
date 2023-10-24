package Examen;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Modelo {
	final String NOMBRE_FT = "ventas.txt";
	final String NOMBRE_FO = "ventas.obj";
	final String NOMBRE_FB = "stock.bin";

	public ArrayList<Ventas> obtenerVentasTXT() {
		// TODO Auto-generated method stub
		ArrayList<Ventas> resultado = new ArrayList();

		BufferedReader f = null;
		try {
			f = new BufferedReader(new FileReader(NOMBRE_FT));
			String linea;
			while ((linea = f.readLine()) != null) {
				String[] campos = linea.split(";");
				Ventas v = new Ventas(Integer.parseInt(campos[0]), Integer.parseInt(campos[2]),
						Float.parseFloat(campos[3]));
				resultado.add(v);
			}
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

	public Ventas obtenerVentaOBJ(int idProducto) {
		// TODO Auto-generated method stub
		Ventas resultado = null;
		ObjectInputStream f = null;
		try {
			f = new ObjectInputStream(new FileInputStream(NOMBRE_FO));
			while (true) {
				Ventas v = (Ventas) f.readObject();
				if (v.getIdProducto() == idProducto) {
					return v;
				}
			}
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Aún no hay ventas obj");
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

	public boolean crearVentaObj(Ventas v) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		File f = new File(NOMBRE_FO);
		ObjectOutputStream fichero = null;
		try {
			if (f.exists()) {
				fichero = new MiObjectOutputStream(new FileOutputStream(NOMBRE_FO, true));
			} else {
				fichero = new ObjectOutputStream(new FileOutputStream(NOMBRE_FO));
			}
			fichero.writeObject(v);
			resultado = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fichero != null) {
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return resultado;
	}

	public boolean modificarVenta(Ventas vOBJ) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		ObjectInputStream fO = null;
		ObjectOutputStream fT = null;

		try {
			fO = new ObjectInputStream(new FileInputStream(NOMBRE_FO));
			fT = new ObjectOutputStream(new FileOutputStream("ventas.tmp", false));

			while (true) {
				Ventas v = (Ventas) fO.readObject();
				if (v.getIdProducto() == vOBJ.getIdProducto()) {
					v = vOBJ;
				}
				fT.writeObject(v);
			}
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (fO != null) {
					fO.close();
				}
				if (fT != null) {
					fT.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		File fOr = new File(NOMBRE_FO);
		File fTm = new File("ventas.tmp");
		if(fOr.delete()) {
			if(fTm.renameTo(fOr)) {
				resultado = true;
			}
			else {
				System.out.println("Error al renombrar fichero tmp");
			}
		}
		else {
			System.out.println("Error al borrar fichero original");
		}
		return resultado;
	}

	public ArrayList<Ventas> obtenerVentas() {
		// TODO Auto-generated method stub
		ArrayList<Ventas> resultado = new ArrayList();
		ObjectInputStream f = null;
		try {
			f = new ObjectInputStream(new FileInputStream(NOMBRE_FO));
			while (true) {
				resultado.add((Ventas) f.readObject());				
			}
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Aún no hay ventas obj");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearStock(Stock s) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		DataOutputStream f = null;
		try {
			f= new DataOutputStream(new FileOutputStream(NOMBRE_FB,true));
			f.writeInt(s.getId());
			StringBuffer texto = new StringBuffer(s.getNombre());
			texto.setLength(30);
			f.writeChars(texto.toString());
			f.writeInt(s.getStock());
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

	public ArrayList<Stock> obtenerStocks() {
		// TODO Auto-generated method stub
		ArrayList<Stock> resultado = new ArrayList();
		DataInputStream f=null;
		try {
			f = new DataInputStream(new FileInputStream(NOMBRE_FB));
			while(true) {
				Stock s = new Stock();
				s.setId(f.readInt());
				String t = "";
				for(int i=0;i<30;i++) {
					t+=f.readChar();
				}
				s.setNombre(t.trim());
				s.setStock(f.readInt());
				resultado.add(s);
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Aún no hay productos");
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

	public Stock obtenerProductoBin(int id) {
		// TODO Auto-generated method stub
		Stock resultado = null;
		RandomAccessFile f=null;
		try {
			f = new RandomAccessFile(NOMBRE_FB,"r");
			while(true) {
				Stock s = new Stock();
				s.setId(f.readInt());
				if(s.getId()==id) {
					String t = "";
					for(int i=0;i<30;i++) {
						t+=f.readChar();
					}
					s.setNombre(t.trim());
					s.setStock(f.readInt());
					return s;				
				}else {
					f.skipBytes(64);
					//f.seek(f.getFilePointer()+64);
				}
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Aún no hay productos");
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

	public boolean modificarStock(Stock s) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile f=null;
		try {
			f = new RandomAccessFile(NOMBRE_FB,"rw");
			while(true) {				
				int id=f.readInt();
				if(s.getId()==id) {
					//Saltamos la stock
					f.skipBytes(60);
					f.writeInt(s.getStock());
					return true;
				}else {
					f.skipBytes(64);
					//f.seek(f.getFilePointer()+64);
				}
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Aún no hay productos");
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

	public void marshall(Info raiz) {
		// TODO Auto-generated method stub
		try {
			Marshaller m = JAXBContext.newInstance(Info.class).createMarshaller();
			m.marshal(raiz, new File(raiz.getId()+".xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
