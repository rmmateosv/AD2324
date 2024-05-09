package Biblioteca;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table
public class Socio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	@Column (name = "fechaSancion",nullable = true)
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Column(nullable = false, unique = true)
	private String nif;
	@Column(nullable = false)
	private String nombre;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "id.socio")
	private List<Prestamo> listaPrestamos = new  ArrayList<Prestamo>();
	
	public Socio() {}

	public Socio(int id, Date fecha, String nif, String nombre) {
		this.id = id;
		this.fecha = fecha;
		this.nif = nif;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Prestamo> getListaPrestamos() {
		return listaPrestamos;
	}

	public void setListaPrestamos(List<Prestamo> listaPrestamos) {
		this.listaPrestamos = listaPrestamos;
	}

	@Override
	public String toString() {
		return "Socio [id=" + id + ", fecha=" + fecha + ", nif=" + nif + ", nombre=" + nombre + "]";
	}
	
	
	
}
