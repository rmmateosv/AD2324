package Taller;



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "piezas")
public class Pieza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private int stock;
	@Column(nullable = false)
	private float precio;
	//Indicar qué campo de PiezaReparación contiene la pieza(FK)
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "clave.pieza")
	private List<PiezaReparacion> pr = new ArrayList();
	
	public List<PiezaReparacion> getPr() {
		return pr;
	}
	public void setPr(List<PiezaReparacion> pr) {
		this.pr = pr;
	}
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
