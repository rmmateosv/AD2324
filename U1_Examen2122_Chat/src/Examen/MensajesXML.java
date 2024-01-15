package Examen;

import java.beans.Transient;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"fecha","texto"})
public class MensajesXML {
	private Date fecha; //8
	private int id;//4
	private String nombre;//200
	private String texto;//400
	private boolean leido=false;//1
	//Tamaño: 613
	public MensajesXML() {
		
	}
	public MensajesXML(Date fecha, int id, String nombre, String texto, boolean leido) {
		super();
		this.fecha = fecha;
		this.id = id;
		this.nombre = nombre;
		this.texto = texto;
		this.leido = leido;
	}
	@XmlElement
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@XmlAttribute
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	@XmlTransient
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElement
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	@XmlAttribute
	public boolean isLeido() {
		return leido;
	}
	public void setLeido(boolean leido) {
		this.leido = leido;
	}
	@Override
	public String toString() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		return "Mensaje [fecha=" + formato.format(fecha) + 
				", id=" + id + ", nombre=" + nombre + ", texto=" + texto + ", leido=" + leido
				+ "]";
	}
	
}
