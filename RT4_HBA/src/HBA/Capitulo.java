package HBA;

import java.util.ArrayList;

public class Capitulo {

	private int id;
	private Serie serie;
	private int numero;
	private String titulo;
	private int duracion;
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
