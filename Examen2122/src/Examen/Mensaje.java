package Examen;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mensaje {
	private Date fecha;
	private int id;
	private String nombre;
	private String texto;
	private boolean leido=false;
	public Mensaje() {
		
	}
	public Mensaje(Date fecha, int id, String nombre, String texto, boolean leido) {
		super();
		this.fecha = fecha;
		this.id = id;
		this.nombre = nombre;
		this.texto = texto;
		this.leido = leido;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
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
