package McBurguer;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Modelo {
	
	private final String FTXT = "empleados.txt";
	private final String FBIN = "pedidos.bin";
	private final String FXML = "productos.xml";

	public Empleado obtenerEmpleado(int codEmp, String ps) {
		
		Empleado resultado = null;
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(FTXT));
			String linea;
			while((linea = br.readLine())!=null) {
				String[] datos = linea.split(";");
				if(Integer.parseInt(datos[0]) == codEmp) {
					if(datos[1].equalsIgnoreCase(ps) && Boolean.parseBoolean(datos[4])) {
						resultado = new Empleado(codEmp, ps, datos[2], datos[3], Boolean.parseBoolean(datos[4]));
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return resultado;
	}

	public int obtenerCodPedido() {
		// TODO Auto-generated method stub
		int resultado = 1;
		
		RandomAccessFile raf = null;
		
		try {
			raf = new RandomAccessFile(FBIN, "r");
			raf.seek(raf.length() - 28);
			resultado = raf.readInt() + 1;
		}catch(EOFException eof) {
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(raf != null) {
				try {
					raf.close();
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
		ArrayList<Producto> resultado = new ArrayList<>();
		
		try {
			Unmarshaller um = JAXBContext.newInstance(McBurgerXML.class).createUnmarshaller();
			McBurgerXML productos = (McBurgerXML) um.unmarshal(new File(FXML));
			resultado = productos.getListaProductos();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

}
