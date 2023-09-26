package fBinario;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Modelo {
	private String nombreFichero; // Fichero binario de asignaturas

	// Fichero de accceso aleatorio para obtener el id de nueva asig
	private String nombreFichConfig = ".config";

	public Modelo(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	public int obtenerId() {
		// TODO Auto-generated method stub
		// Si el fichero .config existe debe devolver el nº
		// que hay en el fichero más 1 y modificar el nº del
		// fichero con el nuevo valor.

		// Si no existe, devuelve 1 y el fichero se crea con
		// el nº 1.
		int resultado = 1;

		// DEclarar el fichero de acceso aleatorio
		RandomAccessFile fA = null;

		try {
			// Abrir el fichero RandomAccessFile para leer y escribir (rw)
			fA = new RandomAccessFile(nombreFichConfig, "rw");
			
			// Chequear si existe .config
			File f = new File(nombreFichConfig);
			if (f.exists()) {
				// Recorrer el fichero y leer el nº
				while (true) {
					// Leer el nº
					resultado = fA.readInt() + 1;
					// Colocamos el apuntador del fichero al principio para sobreescribir el nº
					// Despalazamos hacia atrás(restamos) 4 Bytes
					fA.seek(fA.getFilePointer() - 4);
				}
			}
			// Escribimos el nuevo nº
			fA.writeInt(resultado);
			
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			if (fA != null) {
				try {
					fA.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return resultado;
	}

}
