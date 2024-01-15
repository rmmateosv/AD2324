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
import javax.xml.bind.Unmarshaller;

public class Modelo {
	
	private final String FT="empleados.txt";
	private final String FB="pedidos.bin";
	private final String FXML="productos.xml";
	
	public Modelo() {
		// TODO Auto-generated constructor stub
	}
	public Usuario obtenerUsuario(int codigo, String ps) {
		// TODO Auto-generated method stub
		Usuario resultado=null;
		
		BufferedReader f = null;
		try {
			f=new BufferedReader(new FileReader(FT));
			String linea;
			while((linea=f.readLine())!=null){
				String[] campos = linea.split(";");
				if(campos[0].equalsIgnoreCase(Integer.toString(codigo)) && 
						campos[1].equalsIgnoreCase(ps)) {
					resultado = new Usuario(codigo, 
							campos[1], campos[2], campos[3], 
							Boolean.parseBoolean(campos[4]));
					return resultado;
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
	public int obtenerCodigo() {
		// TODO Auto-generated method stub
		int resultado = 0;
		RandomAccessFile f = null;
		try {
			f=new RandomAccessFile(FB, "r");
			f.seek(f.getFilePointer()+f.length()-28);
			return f.readInt()+1;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay pedidos");
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
	public ArrayList<Producto> obtenerProductos() {
		// TODO Auto-generated method stub
		ArrayList<Producto> resultado = new ArrayList();
		//HAcemos el unmarshal
		Burger b;
		b=unmarsahal();
		if(b!=null) {
			resultado = b.getListaProductos();
		}
		return resultado;
	}
	private Burger unmarsahal() {
		// TODO Auto-generated method stub
		Burger resultado = null;
		try {
			Unmarshaller um = JAXBContext.newInstance(Burger.class).createUnmarshaller();
			resultado = (Burger) um.unmarshal(new File(FXML));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public Producto obtenerProducto(int id) {
		// TODO Auto-generated method stub
		Producto resultado = null;
		//HAcemos el unmarshal
		Burger b;
		b=unmarsahal();
		if(b!=null) {
			for (Producto p : b.getListaProductos()) {
				if(p.getId()==id) {
					return p;
				}
			}
		}
		return resultado;
	}
	public boolean registrarPedido(Pedido p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile f = null;
		try {
			f = new RandomAccessFile(FB, "rw");
			f.seek(f.length());
			f.writeInt(p.getCodigo());
			f.writeLong(p.getFecha().getTime());
			f.writeInt(p.getEmpleado());
			f.writeInt(p.getProducto());
			f.writeInt(p.getCantidad());
			f.writeFloat(p.getPrecio());
			resultado =true;
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
	public ArrayList<Pedido> obtenerPedido(int codigo) {
		// TODO Auto-generated method stub
		ArrayList<Pedido> resultado = new ArrayList();
		RandomAccessFile f = null;
		try {
			f=new RandomAccessFile(FB, "r");
			while(true) {
				int codigoF = f.readInt();
				if(codigoF==codigo) {
					Pedido p = new Pedido(codigoF, new Date(f.readLong()), 
							f.readInt(), f.readInt(), f.readInt(), f.readFloat());
					resultado.add(p);
				}
				else{
					f.seek(f.getFilePointer()+24);
				}
			}
		} 
		catch ( EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay pedidos");
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
	public ArrayList<Pedido> obtenerPedidos() {
		// TODO Auto-generated method stub
		ArrayList<Pedido> resultado = new ArrayList();
		RandomAccessFile f = null;
		try {
			f=new RandomAccessFile(FB, "r");
			while(true) {				
				Pedido p = new Pedido(f.readInt(), new Date(f.readLong()), 
						f.readInt(), f.readInt(), f.readInt(), f.readFloat());
				resultado.add(p);				
			}
		} 
		catch ( EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay pedidos");
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
	public Pedido obtenerPedido(int p, int pr, int codEmp) {
		// TODO Auto-generated method stub
		Pedido resultado = null;
		RandomAccessFile f = null;
		try {
			f=new RandomAccessFile(FB, "r");
			while(true) {				
				int pF = f.readInt();
				if(pF==p) {
					f.seek(f.getFilePointer()+8);
					if(f.readInt()==codEmp) {
						if(f.readInt()==pr) {
							//Encontrado
							f.seek(f.getFilePointer()-20);
							resultado = new Pedido(f.readInt(), new Date(f.readLong()), 
									f.readInt(), f.readInt(), f.readInt(), f.readFloat());
							return resultado;
						}
						else {
							f.seek(f.getFilePointer()+8);
						}
					}
					else {
						f.seek(f.getFilePointer()+12);
					}
				}
				else {
					f.seek(f.getFilePointer()+24);
				}
			}
		} 
		catch ( EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay pedidos");
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
	public boolean modificarPedido(Pedido pe) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile f = null;
		try {
			f=new RandomAccessFile(FB, "rw");
			while(true) {				
				int pF = f.readInt();
				if(pF==pe.getCodigo()) {
					f.seek(f.getFilePointer()+8);
					if(f.readInt()==pe.getEmpleado()) {
						if(f.readInt()==pe.getProducto()) {
							//Encontrado
							f.writeInt(pe.getCantidad());
							return true;
						}
						else {
							f.seek(f.getFilePointer()+8);
						}
					}
					else {
						f.seek(f.getFilePointer()+12);
					}
				}
				else {
					f.seek(f.getFilePointer()+24);
				}
			}
		} 
		catch ( EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay pedidos");
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
