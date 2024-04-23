package McBurguer;

public class Empleado {
	private int codEmpleado;
	private String dni;
	private String nombre;
	private int valoracion;
	private int tienda;
	
	public Empleado() {
		
	}

	public Empleado(int codEmpleado, String dni, String nombre, int valoracion, int tienda) {
		this.codEmpleado = codEmpleado;
		this.dni = dni;
		this.nombre = nombre;
		this.valoracion = valoracion;
		this.tienda = tienda;
	}

	public int getCodEmpleado() {
		return codEmpleado;
	}

	public void setCodEmpleado(int codEmpleado) {
		this.codEmpleado = codEmpleado;
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

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public int getTienda() {
		return tienda;
	}

	public void setTienda(int tienda) {
		this.tienda = tienda;
	}

	@Override
	public String toString() {
		return "Empleado [codEmpleado=" + codEmpleado + ", dni=" + dni + ", nombre=" + nombre + ", valoracion="
				+ valoracion + ", tienda=" + tienda + "]";
	}
}
