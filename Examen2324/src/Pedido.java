import java.text.SimpleDateFormat;
import java.util.Date;

public class Pedido {
	private int codigo;//4
	private Date fecha;//8
	private int empleado, producto,cantidad;//12
	private float precio;//4
	//Total => 28
	public Pedido(int codigo, Date fecha, int empleado, int producto, int cantidad, float precio) {
		
		this.codigo = codigo;
		this.fecha = fecha;
		this.empleado = empleado;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	public Pedido() {
	
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getEmpleado() {
		return empleado;
	}
	public void setEmpleado(int empleado) {
		this.empleado = empleado;
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
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		SimpleDateFormat formato = new  SimpleDateFormat("dd/MM/yyyy");
		return "Pedido [codigo=" + codigo + ", fecha=" + formato.format(fecha) + ", empleado=" + empleado + ", producto=" + producto
				+ ", cantidad=" + cantidad + ", precio=" + precio + ", total=" + precio*cantidad+"]";
	}
	
	
}
