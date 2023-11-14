package taller;

public class Pieza {
	private int id;
	private String nombre;
	private int stock;
	private float precio;
	
	public Pieza() {
	
	}
	public Pieza(int id, String nombre, int stock, float precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.stock = stock;
		this.precio = precio;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Pieza [id=" + id + ", nombre=" + nombre + ", stock=" + stock + ", precio=" + precio + "]";
	}
	
	
}
