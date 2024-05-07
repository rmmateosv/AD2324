package HBA;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Capitulo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne()
	@JoinColumn(name = "serie",referencedColumnName = "id")
	private Serie serie;
	@Column(nullable = true)
	private int numero;
	@Column(nullable = true)
	private String titulo;
	@Column(nullable = true)
	private int duracion;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "clave.capitulo")
	private ArrayList<Reproduccion> listaReproducciones = new ArrayList();
	
	public Capitulo() {}

	public Capitulo(int id, Serie serie, int numero, String titulo, int duracion) {
		this.id = id;
		this.serie = serie;
		this.numero = numero;
		this.titulo = titulo;
		this.duracion = duracion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public ArrayList<Reproduccion> getListaReproducciones() {
		return listaReproducciones;
	}

	public void setListaReproducciones(ArrayList<Reproduccion> listaReproducciones) {
		this.listaReproducciones = listaReproducciones;
	}

	@Override
	public String toString() {
		
		return "Capitulo [id=" + id + ", serie=" + serie.getNombre() + ", numero=" + numero + ", titulo=" + titulo 
				+ ", duracion=" + duracion + "]";
	}
	
	
	
}
