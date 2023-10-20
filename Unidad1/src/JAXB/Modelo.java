package JAXB;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Modelo {
	private String nombreFichero="historialesJAXB.xml";

	public Modelo() {
		
	}

	public boolean marshal(Centro c) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			Marshaller m = JAXBContext.newInstance(Centro.class).createMarshaller();
			m.marshal(c, new File(nombreFichero));
			resultado=true;
			//Mostrar en consola
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(c, System.out);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public Centro unmarshal() {
		// TODO Auto-generated method stub
		Centro resultado = null;
		try {
			Unmarshaller um = JAXBContext.newInstance(Centro.class).createUnmarshaller();
			resultado=(Centro) um.unmarshal(new File(nombreFichero));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	
}
