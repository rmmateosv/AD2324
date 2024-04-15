package Ventas;

import java.io.BufferedReader;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Modelo {
	
	final String FBIN = "stock.bin";
	final String FTXT = "ventas.txt";
	final String FOBJ = "ventas.obj";
	final String FTMP = "ventas.tmp";
	private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

	public Modelo() {
	}

	public ArrayList<VentasTxt> obtenerVentasTxt() {

		ArrayList<VentasTxt> resultado = new ArrayList<VentasTxt>();
		BufferedReader lector = null;

		try {
			lector = new BufferedReader(new FileReader(FTXT));

			String linea = "";
			while ((linea = lector.readLine()) != null) {
				String[] campos = linea.split(";");

				VentasTxt v = new VentasTxt(Integer.parseInt(campos[0]), Integer.parseInt(campos[2]),
						formatoFecha.parse(campos[1]), Float.parseFloat(campos[3]));
				resultado.add(v);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.err.println("Error, existe una fecha incorrecta.");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (lector != null) {
				try {
					lector.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return resultado;
	}

	public VentasObj obtenerVentaObj(int producto) {
		VentasObj resultado = null;
		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(new FileInputStream(FOBJ));

			while (true) {
				VentasObj v = (VentasObj) ois.readObject();

				if (v.getProducto() == producto) {
					return v;
				}
			}
		} catch (EOFException e) {
			// No ponemos nada, permite salir del while (true).
		} catch (FileNotFoundException e) {
			System.out.println("El fichero vObj aun no existe.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return resultado;
	}

	public boolean crearVenta(VentasObj vObj) {
		boolean resultado = false;
		ObjectOutputStream oos = null;

		try {
			if (new File(FOBJ).exists()) {
				oos = new miObjectOutputStream(new FileOutputStream(FOBJ, true));
			} else {
				oos = new ObjectOutputStream(new FileOutputStream(FOBJ, true));
			}

			oos.writeObject(vObj);
			resultado = true;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return resultado;
	}

	public boolean modificarVenta(VentasObj vObj) {
		boolean resultado = false;
		ObjectInputStream fOriginal = null;
		ObjectOutputStream fTemporal = null;

		try {
			fOriginal = new ObjectInputStream(new FileInputStream(FOBJ));
			fTemporal = new ObjectOutputStream(new FileOutputStream(FTMP, false));

			while (true) {
				VentasObj v = (VentasObj) fOriginal.readObject();
				if (v.getProducto() == vObj.getProducto()) {
					fTemporal.writeObject(vObj);
				} else {
					fTemporal.writeObject(v);
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fOriginal != null) {
				try {
					fOriginal.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fTemporal != null) {
				try {
					fTemporal.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		File fo = new File(FOBJ);
		File ft = new File(FTMP);
		if (fo.delete()) {
			if (ft.renameTo(fo)) {
				resultado = true;
			} else {
				System.err.println("Error al renombrar");
			}
		} else {
			System.err.println("Error al borrar");
		}

		return resultado;
	}

	public ArrayList<VentasObj> obtenerVentasObj() {
		// TODO Auto-generated method stub
		ArrayList<VentasObj> resultado = new ArrayList<VentasObj>();
		ObjectInputStream fventas = null;
		try {
			fventas = new ObjectInputStream(new FileInputStream(FOBJ));
			while (true) {
				resultado.add((VentasObj) fventas.readObject()); 
				
			}
			
		}catch (EOFException e) {
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
		} finally {
			if (fventas != null) {
				try {
					fventas.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public boolean crearProducto(Producto p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile rd = null;
		try {
			rd = new RandomAccessFile(FBIN, "rw");
			// Si abromos para escritura hay que mover el cursor del fichero al final
			rd.seek(rd.getFilePointer());
			rd.writeInt(p.getIdproducto());
			// Hacemos que el nombre tenga un tamaño fijo
			StringBuffer nombre = new StringBuffer(p.getNombre());
			nombre.setLength(30);
			rd.writeChars(nombre.toString());
			rd.writeInt(p.getStock());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rd != null) {
				try {
					rd.close();
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
		ArrayList<Producto> resultado = null;
		RandomAccessFile productoS = null;
		try {
			productoS = new RandomAccessFile(FBIN, "r");
			while (true) {
				Producto p = new Producto();
				p.setIdproducto(productoS.readInt());
				p.setNombre("");
				
				for(int i =0; i < 30;i++) {
					
					productoS.readChar();
					
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
			if (productoS != null) {
				try {
					productoS.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	

}
