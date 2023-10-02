package ClaseRAF;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Nota {
	private String dni; //9 caractares = 18B
	private int asig;   // 4B
	private Date fecha; // 8B
	private float nota; // 4B
	private String valoracion; //50 caracteres => 100B
	// TAmaño del registro: 134B
	
	public Nota() {
		
	}
	public Nota(String dni, int asig, Date fecha, float nota, String valoracion) {
		
		this.dni = dni;
		this.asig = asig;
		this.fecha = fecha;
		this.nota = nota;
		this.valoracion = valoracion;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
		return "Nota [dni=" + dni + ", asig=" + asig +
				", fecha=" + formato.format(fecha) 
				+ ", nota=" + nota + ", valoracion="
				+ valoracion + "]";
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getAsig() {
		return asig;
	}
	public void setAsig(int asig) {
		this.asig = asig;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}
	public String getValoracion() {
		return valoracion;
	}
	public void setValoracion(String valoracion) {
		this.valoracion = valoracion;
	}
	
	
	
	
	
	
	
}
