package clases;

public class Detalle {

	private String producto;
	private int cantidad;
	private double precioUnidad;

	public Detalle() {
		// TODO Auto-generated constructor stub
	}

	public Detalle(String producto, int cantidad, double precioUnidad) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioUnidad = precioUnidad;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	@Override
	public String toString() {
		return "Detalle [producto=" + producto + ", cantidad=" + cantidad + ", precioUnidad=" + precioUnidad + "]";
	}

}
