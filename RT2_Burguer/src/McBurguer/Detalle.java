package McBurguer;

public class Detalle {
	private int pedido;
	private int producto;
	private int cantidad;
	private float precioUnitario;
	
	public Detalle() {
		// TODO Auto-generated constructor stub
	}

	public Detalle(int pedido, int producto, int cantidad, float precioUnitario) {
		this.pedido = pedido;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
	}

	public int getPedido() {
		return pedido;
	}

	public void setPedido(int pedido) {
		this.pedido = pedido;
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

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	@Override
	public String toString() {
		return "Detalle [pedido=" + pedido + ", producto=" + producto + ", cantidad=" + cantidad + ", precioUnitario="
				+ precioUnitario + "]";
	}
	
}
