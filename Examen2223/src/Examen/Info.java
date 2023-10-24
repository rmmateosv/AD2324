package Examen;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"id","nombre","stock","vendido","recaudado"})
public class Info {
	private int id;
	private String nombre;
	private int stock, vendido;
	private float recaudado;
	
	
	public Info() {
		
	}


	public Info(int id, String nombre, int stock, int vendido, float recaudado) {
		
		this.id = id;
		this.nombre = nombre;
		this.stock = stock;
		this.vendido = vendido;
		this.recaudado = recaudado;
	}

	@XmlElement
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlElement
	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}

	@XmlElement
	public int getVendido() {
		return vendido;
	}


	public void setVendido(int vendido) {
		this.vendido = vendido;
	}

	@XmlElement
	public float getRecaudado() {
		return recaudado;
	}


	public void setRecaudado(float recaudado) {
		this.recaudado = recaudado;
	}
	
	
	
}
