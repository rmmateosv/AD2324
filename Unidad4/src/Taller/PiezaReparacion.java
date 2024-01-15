package Taller;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class PiezaReparacion {
	@EmbeddedId
	private ClavePR clave;
	@Column(nullable = false)
	private int cantidad;
	@Column(nullable = false)
	private float precio;
	public PiezaReparacion(ClavePR clave, int cantidad, float precio) {
		super();
		this.clave = clave;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	public PiezaReparacion() {
		super();
	}
	public ClavePR getClave() {
		return clave;
	}
	public void setClave(ClavePR clave) {
		this.clave = clave;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "PiezaReparacion [clave=" + clave + ", cantidad=" + cantidad + ", precio=" + precio + "]";
	}
	
	
}
