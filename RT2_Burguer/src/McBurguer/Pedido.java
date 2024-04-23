package McBurguer;

import java.util.Date;

public class Pedido {
	private int codigo;
	private Date fecha;
	private int codEmpleado;
	private int tienda;
	
	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public Pedido(int codigo, Date fecha, int codEmpleado, int tienda) {
		this.codigo = codigo;
		this.fecha = fecha;
		this.codEmpleado = codEmpleado;
		this.tienda = tienda;
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

	public int getCodEmpleado() {
		return codEmpleado;
	}

	public void setCodEmpleado(int codEmpleado) {
		this.codEmpleado = codEmpleado;
	}

	public int getTienda() {
		return tienda;
	}

	public void setTienda(int tienda) {
		this.tienda = tienda;
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", fecha=" + fecha + ", codEmpleado=" + codEmpleado + ", tienda=" + tienda
				+ "]";
	}
	
	
}
