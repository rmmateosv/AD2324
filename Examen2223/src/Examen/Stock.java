package Examen;

public class Stock {
	private int id; //4 B
	private String nombre; //30c => 60B
	private int stock; // 4B
	//Tamaño del registro = 68B
	
	public Stock() {
		super();
	}
	public Stock(int id, String nombre, int stock) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.stock = stock;
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
	@Override
	public String toString() {
		return "Stock [id=" + id + ", nombre=" + nombre + ", stock=" + stock + "]";
	}
	
	
}
