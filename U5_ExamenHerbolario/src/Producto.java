import java.util.ArrayList;

public class Producto {
	private int codigo;
	private String nombre;
	private Info datosNutricion;
	ArrayList<Integer> precios;
	public Producto(int codigo, String nombre, Info datosNutricion, ArrayList<Integer> precios) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.datosNutricion = datosNutricion;
		this.precios = precios;
	}
	public Producto() {
		super();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Info getDatosNutricion() {
		return datosNutricion;
	}
	public void setDatosNutricion(Info datosNutricion) {
		this.datosNutricion = datosNutricion;
	}
	public ArrayList<Integer> getPrecios() {
		return precios;
	}
	public void setPrecios(ArrayList<Integer> precios) {
		this.precios = precios;
	}
	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", datosNutricion=" + datosNutricion + ", precios="
				+ precios + "]";
	}
	
	
}
