package taller;

public class PiezaReparacion {
	private int reparacion;
	private int pieza;
	private int cantidad;
	private float precio;
	
	public PiezaReparacion() {
		
	}

	public PiezaReparacion(int reparacion, int pieza, int cantidad, float precio) {
		
		this.reparacion = reparacion;
		this.pieza = pieza;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	

	public int getReparacion() {
		return reparacion;
	}

	public void setReparacion(int reparacion) {
		this.reparacion = reparacion;
	}

	public int getPieza() {
		return pieza;
	}

	public void setPieza(int pieza) {
		this.pieza = pieza;
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
		return "PiezaReparacion [reparacion=" + reparacion + ", pieza=" + pieza + ", cantidad=" + cantidad + ", precio="
				+ precio + "]";
	}
	
	
}
