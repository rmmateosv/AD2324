package examenRecuperacionRosa;

import java.io.Serializable;

public class Personas implements Serializable{
	private String dni;
	private String nombre;
	private int edad;
	
	public Personas() {
		
	}

	@Override
	public String toString() {
		return "Personas [dni=" + dni + ", nombre=" + nombre + ", edad=" + edad + "]";
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

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
}

