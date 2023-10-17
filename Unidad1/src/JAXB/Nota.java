package JAXB;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"nombreAsig", "fechaExamen"})
public class Nota {
	private int asignatura;
	private float nota;
	private String nombreAsig;
	private String fechaExamen;
	
	public Nota() {
		
	}
	public Nota(int asignatura, float nota, String nombreAsig, String fechaExamen) {
		
		this.asignatura = asignatura;
		this.nota = nota;
		this.nombreAsig = nombreAsig;
		this.fechaExamen = fechaExamen;
	}
	@XmlAttribute(name="asig")
	public int getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(int asignatura) {
		this.asignatura = asignatura;
	}
	@XmlAttribute
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}
	@XmlElement
	public String getNombreAsig() {
		return nombreAsig;
	}
	public void setNombreAsig(String nombreAsig) {
		this.nombreAsig = nombreAsig;
	}
	@XmlElement(name="fechaEx")
	public String getFechaExamen() {
		return fechaExamen;
	}
	
	public void setFechaExamen(String fechaExamen) {
		this.fechaExamen = fechaExamen;
	}
	
	
	
}
