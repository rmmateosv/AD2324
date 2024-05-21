package skills;

import java.util.ArrayList;

public class Alumno {
	private int id;
	private String nombre;
	private String dni;
	private Modalidad modalidad;
	private int puntuacion;
	private boolean finalizado;
	private ArrayList<String[]> correccion = new ArrayList<String[]>();

	public Alumno() {

	}

	public Alumno(int id, String nombre, String dni, Modalidad modalidad, int puntuacion, boolean finalizado,
			ArrayList<String[]> correccion) {
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.modalidad = modalidad;
		this.puntuacion = puntuacion;
		this.finalizado = finalizado;
		this.correccion = correccion;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public ArrayList<String[]> getCorreccion() {
		return correccion;
	}

	public void setCorreccion(ArrayList<String[]> correccion) {
		this.correccion = correccion;
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", dni=" + dni + ", puntuacion=" + puntuacion
				+ ", finalizado=" + finalizado + "]";
	}
	
	public void mostrarCorreciones() 
	{
		for (String[] datos : correccion) {
			System.out.println("Id de prueba: " +datos[0]+ "\t nota: " +datos[1]+ "\t descripción: " +datos[2]);
		}
	}
}
