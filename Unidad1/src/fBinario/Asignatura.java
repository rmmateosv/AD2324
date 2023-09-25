package fBinario;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Asignatura {
	private int id;
	private String nombre;
	private Date fechaRD; //Fecha Real Decreto
	private float creditos;
	private boolean activa;
	
	
	public Asignatura() {
		
	}


	public Asignatura(int id, String nombre, Date fechaRD, float creditos, boolean activa) {
		
		this.id = id;
		this.nombre = nombre;
		this.fechaRD = fechaRD;
		this.creditos = creditos;
		this.activa = activa;
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


	public Date getFechaRD() {
		return fechaRD;
	}


	public void setFechaRD(Date fechaRD) {
		this.fechaRD = fechaRD;
	}


	public float getCreditos() {
		return creditos;
	}


	public void setCreditos(float creditos) {
		this.creditos = creditos;
	}


	public boolean isActiva() {
		return activa;
	}


	public void setActiva(boolean activa) {
		this.activa = activa;
	}


	@Override
	public String toString() {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		return "Asignatura [id=" + id + 
				", nombre=" + nombre + 
				", fechaRD=" + formatoFecha.format(fechaRD) + 
				", creditos=" + creditos
				+ ", activa=" + activa + "]";
	}
	
	
}
