package Examen;

import java.io.Serializable;

public class Ventas implements Serializable{
	private int idProducto,cantidadVendidad;
	private float importeRecaudado;
	public Ventas() {
		
	}
	public Ventas(int idProducto, int cantidadVedidad, float importeRecaudado) {
		this.idProducto = idProducto;
		this.cantidadVendidad = cantidadVedidad;
		this.importeRecaudado = importeRecaudado;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getCantidadVendidad() {
		return cantidadVendidad;
	}
	public void setCantidadVendidad(int cantidadVedidad) {
		this.cantidadVendidad = cantidadVedidad;
	}
	public float getImporteRecaudado() {
		return importeRecaudado;
	}
	public void setImporteRecaudado(float importeRecaudado) {
		this.importeRecaudado = importeRecaudado;
	}
	@Override
	public String toString() {
		return "Ventas [idProducto=" + idProducto + ", cantidadVedidad=" + cantidadVendidad + ", importeRecaudado="
				+ importeRecaudado + "]";
	}
	
	
}
