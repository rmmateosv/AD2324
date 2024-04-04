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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Modelo {

	final String FTXT = "ventas.txt";
	final String FOBJ = "ventas.obj";
	final String FTMP = "ventas.tmp";
	private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	
	public Modelo() {}

	public ArrayList<VentasTxt> obtenerVentasTxt() {
		
		ArrayList<VentasTxt> resultado= new ArrayList<VentasTxt>();
		BufferedReader lector = null;
		
		try {
			lector = new BufferedReader(new FileReader(FTXT));
			
			String linea="";
			while ((linea = lector.readLine())!= null) {
				String[] campos =  linea.split(";");
				
				VentasTxt v = new VentasTxt(Integer.parseInt(campos[0]), Integer.parseInt(campos[2]), formatoFecha.parse(campos[1]), Float.parseFloat(campos[3]));
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
		}
		finally{
			if(lector != null) {
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
		
		return resultado;
	}
	
}
