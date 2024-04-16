package McBurguer;

public class Empleado {

	private int codEmpleado;
	private String dni;
	private String nombre;
	private String codTienda;
	private boolean activo;

	public Empleado() {
		// TODO Auto-generated constructor stub
	}

	public Empleado(int codEmpleado, String dni, String nombre, String codTienda, boolean activo) {
		this.codEmpleado = codEmpleado;
		this.dni = dni;
		this.nombre = nombre;
		this.codTienda = codTienda;
		this.activo = activo;
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

	public String getCodTienda() {
		return codTienda;
	}

	public void setCodTienda(String codTienda) {
		this.codTienda = codTienda;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Empleado [codEmpleado=" + codEmpleado + ", dni=" + dni + ", nombre=" + nombre + ", codTienda="
				+ codTienda + ", activo=" + activo + "]";
	}

}
