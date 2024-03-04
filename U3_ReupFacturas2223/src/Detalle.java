
public class Detalle {
	private String producto;
	private int cantidad;
	private double precioUdad;
	public Detalle() {

	}
	public Detalle(String producto, int cantidad, double precioUdad) {

		this.producto = producto;
		this.cantidad = cantidad;
		this.precioUdad = precioUdad;
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
	public double getPrecioUdad() {
		return precioUdad;
	}
	public void setPrecioUdad(double precioUdad) {
		this.precioUdad = precioUdad;
	}
	@Override
	public String toString() {
		return "Detalle [producto=" + producto + ", cantidad=" + cantidad + ", precioUdad=" + precioUdad + "]";
	}
	
	
}
