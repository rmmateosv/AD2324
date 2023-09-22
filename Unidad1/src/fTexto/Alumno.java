package fTexto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Alumno {
	private String dni;
	private String nombre;
	private Date fechaN;
	private int numExp;
	private float estatura;
	private boolean activo;
	public Alumno() {
		
	}
	public Alumno(String dni, String nombre, Date fechaN, int numExp, float estatura, boolean activo) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.fechaN = fechaN;
		this.numExp = numExp;
		this.estatura = estatura;
		this.activo = activo;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaN() {
		return fechaN;
	}
	public void setFechaN(Date fechaN) {
		this.fechaN = fechaN;
	}
	public int getNumExp() {
		return numExp;
	}
	public void setNumExp(int numExp) {
		this.numExp = numExp;
	}
	public float getEstatura() {
		return estatura;
	}
	public void setEstatura(float estatura) {
		this.estatura = estatura;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	@Override
	public String toString() {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		return "Alumno [dni=" + dni + ", "
				+ "nombre=" + nombre + ", "
				+ "fechaN=" + formatoFecha.format(fechaN) + ", "
				+ "numExp=" + numExp + ", estatura="
				+ estatura + ", activo=" + activo + "]";
	}
	
	
	
}
