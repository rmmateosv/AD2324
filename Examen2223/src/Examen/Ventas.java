package Examen;

public class Ventas {
	private int idProducto,cantidadVedidad;
	private float importeRecaudado;
	public Ventas() {
		
	}
	public Ventas(int idProducto, int cantidadVedidad, float importeRecaudado) {
		this.idProducto = idProducto;
		this.cantidadVedidad = cantidadVedidad;
		this.importeRecaudado = importeRecaudado;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getCantidadVedidad() {
		return cantidadVedidad;
	}
	public void setCantidadVedidad(int cantidadVedidad) {
		this.cantidadVedidad = cantidadVedidad;
	}
	public float getImporteRecaudado() {
		return importeRecaudado;
	}
	public void setImporteRecaudado(float importeRecaudado) {
		this.importeRecaudado = importeRecaudado;
	}
	@Override
	public String toString() {
		return "Ventas [idProducto=" + idProducto + ", cantidadVedidad=" + cantidadVedidad + ", importeRecaudado="
				+ importeRecaudado + "]";
	}
	
	
}
