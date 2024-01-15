package Examen;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"idEmpleado","nombre","mensajes","total"})
public class Chat {
	private int idEmpleado;
	private String nombre;
	private ArrayList<MensajesXML> mensajes=new ArrayList<>();
	private int total;
	
	public Chat() {
		super();
	}

	public Chat(int idEmpleado, String nombre, ArrayList<MensajesXML> mensajes, int total) {
		
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.mensajes = mensajes;
		this.total = total;
	}
	@XmlElement
	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	@XmlElement
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElementWrapper(name="mensajes")
	@XmlElement(name="mensaje")
	public ArrayList<MensajesXML> getMensajes() {
		return mensajes;
	}

	public void setMensajes(ArrayList<MensajesXML> mensajes) {
		this.mensajes = mensajes;
	}
	@XmlElement
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
