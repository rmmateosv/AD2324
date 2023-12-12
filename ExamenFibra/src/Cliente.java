import java.util.Date;

public class Cliente {
	private int idC;
	private String dni ;    
	private String nombreC ;
	private Date fechaNac;
	private String direccion;
	private int cp ;
	
	public Cliente(int idC, String dni, String nombreC, Date fechaNac, String direccion, int cp) {
	
		this.idC = idC;
		this.dni = dni;
		this.nombreC = nombreC;
		this.fechaNac = fechaNac;
		this.direccion = direccion;
		this.cp = cp;
	}
	
	public Cliente() {

	}

	public int getIdC() {
		return idC;
	}
	public void setIdC(int idC) {
		this.idC = idC;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombreC() {
		return nombreC;
	}
	public void setNombreC(String nombreC) {
		this.nombreC = nombreC;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getCp() {
		return cp;
	}
	public void setCp(int cp) {
		this.cp = cp;
	}
	@Override
	public String toString() {
		return "Cliente [idC=" + idC + ", dni=" + dni + ", nombreC=" + nombreC + ", fechaNac=" + fechaNac
				+ ", direccion=" + direccion + ", cp=" + cp + "]";
	}
	
	
}	
