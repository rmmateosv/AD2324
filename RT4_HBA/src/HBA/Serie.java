package HBA;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Serie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nombre",nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String genero;
	@Column(nullable = true)
	private int edad;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "serie")
	private ArrayList<Capitulo> listaCapitulos = new ArrayList();
	
	public Serie() {}

	public Serie(int id, String nombre, String genero, int edad) {
		this.id = id;
		this.nombre = nombre;
		this.genero = genero;
		this.edad = edad;
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public ArrayList<Capitulo> getListaCapitulos() {
		return listaCapitulos;
	}

	public void setListaCapitulos(ArrayList<Capitulo> listaCapitulos) {
		this.listaCapitulos = listaCapitulos;
	}

	@Override
	public String toString() {
		String resultado = "Serie [id=" + id + ", nombre=" + nombre + ", genero=" + genero + ", edad=" + edad + "]";
		for (Capitulo capitulo : listaCapitulos) {
			resultado += "\n"+capitulo;
		}
		return resultado;
	}
	
	
	
}
