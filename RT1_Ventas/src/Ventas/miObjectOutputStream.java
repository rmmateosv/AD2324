package Ventas;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class miObjectOutputStream extends ObjectOutputStream {

	protected miObjectOutputStream() throws IOException, SecurityException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public miObjectOutputStream(OutputStream out) throws IOException {
		super(out);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void writeStreamHeader() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
