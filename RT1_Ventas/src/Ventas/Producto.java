package Ventas;

public class Producto {
	private int idproducto, stock;
	private String nombre;
	
	
	public Producto() {
	}


	public Producto(int idproducto, int stock, String nombre) {
		this.idproducto = idproducto;
		this.stock = stock;
		this.nombre = nombre;
	}



	public int getIdproducto() {
		return idproducto;
	}



	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}



	public int getStock() {
		return stock;
	}



	public void setStock(int stock) {
		this.stock = stock;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	@Override
	public String toString() {
		return "Producto [idproducto=" + idproducto + ", stock=" + stock + ", nombre=" + nombre + "]";
	}
	
	
}
