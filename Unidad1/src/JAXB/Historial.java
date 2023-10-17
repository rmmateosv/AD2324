package JAXB;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"nombreAL", "notas"})
public class Historial {
	private String dni;
	private String nombreAL;
	private ArrayList<Nota> notas = new ArrayList<>();
	
	public Historial() {
		
	}

	public Historial(String dni, String nombreAL, ArrayList<Nota> notas) {
		
		this.dni = dni;
		this.nombreAL = nombreAL;
		this.notas = notas;
	}
	@XmlAttribute
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	@XmlElement
	public String getNombreAL() {
		return nombreAL;
	}

	public void setNombreAL(String nombreAL) {
		this.nombreAL = nombreAL;
	}
	@XmlElementWrapper(name="notas")
	@XmlElement(name="nota")
	public ArrayList<Nota> getNotas() {
		return notas;
	}

	public void setNotas(ArrayList<Nota> notas) {
		this.notas = notas;
	}
	
	
}
