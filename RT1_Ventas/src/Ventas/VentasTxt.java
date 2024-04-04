package Ventas;

import java.util.Date;

public class VentasTxt {

	private int producto, cantidad;
	private Date fecha;
	private float importe;
	
	public VentasTxt() {}
	
	public VentasTxt(int producto, int cantidad, Date fecha, float importe) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.fecha = fecha;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "ventasTxt [producto=" + producto + ", cantidad=" + cantidad + ", fecha=" + fecha + ", importe="
				+ importe + "]";
	}
	
	
	
}
