package McBurguer;

import java.util.Date;

public class Pedido {

	private int codigo; //4B
	private Date fecha; //8B
	private int codEmp; //4B
	private int codProd; //4B
	private int cantidad; //4B
	private float precio; //4B
	// 28B

	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public Pedido(int codigo, Date fecha, int codEmp, int codProd, int cantidad, float precio) {
		this.codigo = codigo;
		this.fecha = fecha;
		this.codEmp = codEmp;
		this.codProd = codProd;
		this.cantidad = cantidad;
		this.precio = precio;
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

	public int getCodEmp() {
		return codEmp;
	}

	public void setCodEmp(int codEmp) {
		this.codEmp = codEmp;
	}

	public int getCodProd() {
		return codProd;
	}

	public void setCodProd(int codProd) {
		this.codProd = codProd;
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
		return "Pedido [codigo=" + codigo + ", fecha=" + fecha + ", codEmp=" + codEmp + ", codProd=" + codProd
				+ ", cantidad=" + cantidad + ", precio=" + precio + "]";
	}

}
