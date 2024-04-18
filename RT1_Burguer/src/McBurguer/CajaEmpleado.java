package McBurguer;

import java.io.Serializable;
import java.util.Date;

public class CajaEmpleado implements Serializable{

	private String dni, nombre;
	private Date fecha;
	private String tienda;
	private int numProd;
	private float importe;
	
	public CajaEmpleado() {}
	
	public CajaEmpleado(String dni, String nombre, Date fecha, String tienda, int numProd, float importe) {
		this.dni = dni;
		this.nombre = nombre;
		this.fecha = fecha;
		this.tienda = tienda;
		this.numProd = numProd;
		this.importe = importe;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTienda() {
		return tienda;
	}

	public void setTienda(String tienda) {
		this.tienda = tienda;
	}

	public int getNumProd() {
		return numProd;
	}

	public void setNumProd(int numProd) {
		this.numProd = numProd;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "CajaEmpleado [dni=" + dni + ", nombre=" + nombre + ", fecha=" + fecha + ", tienda=" + tienda
				+ ", numProd=" + numProd + ", importe=" + importe + "]";
	}
	
	
	
}
