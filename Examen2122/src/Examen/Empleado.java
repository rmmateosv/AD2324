package Examen;

public class Empleado {
	private int id;
	private String nombre;
	private boolean activo;
	
	public Empleado(int id, String nombre, boolean activo) {
	
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
	}
	public Empleado() {
		
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
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", activo=" + activo + "]";
	}
	
	
}
