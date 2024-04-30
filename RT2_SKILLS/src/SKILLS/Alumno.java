package SKILLS;

public class Alumno {

	private int id;
	private String dni, nombre;
	private int puntuacion;
	private boolean finalizado;
	private Modalidades modalidad;
	
	public Alumno() {}

	public Alumno(int id, String dni, String nombre, int puntuacion, boolean finalizado, Modalidades modalidad) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.puntuacion = puntuacion;
		this.finalizado = finalizado;
		this.modalidad = modalidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Modalidades getModalidad() {
		return modalidad;
	}

	public void setModalidad(Modalidades modalidad) {
		this.modalidad = modalidad;
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", puntuacion=" + puntuacion
				+ ", finalizado=" + finalizado + ", modalidad=" + modalidad + "]";
	}

	
}
