package Ventas;

import java.io.Serializable;

public class VentasObj implements Serializable {

	private int producto, cantidad;
	private float importe;
	
	public VentasObj() {}

	public VentasObj(int producto, int cantidad, float importe) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.importe = importe;
	}

	public int getProducto() {
		return producto;
	}

	public void setProducto(int producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "VentasObj [producto=" + producto + ", cantidad=" + cantidad + ", importe=" + importe + "]";
	}
	
	
}
