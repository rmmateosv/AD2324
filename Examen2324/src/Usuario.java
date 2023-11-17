
public class Usuario {
	private int codEmp;
	private String dni,nombre,codTienda;
	boolean activo;
	
	public Usuario(int codEmp, String dni, String nombre, String codTienda, boolean activo) {
	
		this.codEmp = codEmp;
		this.dni = dni;
		this.nombre = nombre;
		this.codTienda = codTienda;
		this.activo = activo;
	}
	
	public Usuario() {
		
	}
	public int getCodEmp() {
		return codEmp;
	}
	public void setCodEmp(int codEmp) {
		this.codEmp = codEmp;
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
		return "Usuario [codEmp=" + codEmp + ", dni=" + dni + ", nombre=" + nombre + ", codTienda=" + codTienda
				+ ", activo=" + activo + "]";
	}
	
	
	
}
