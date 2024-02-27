import java.util.ArrayList;
import java.util.Arrays;

public class Equipo {
	private String nombre;
	private Estadistica estadistica = new Estadistica();
	private ArrayList<String> jugadores = new ArrayList();
	private int puntos;
	
	
	public Equipo(String nombre, int puntos) {
		this.nombre = nombre;
		this.puntos = puntos;
	}
	
	public Equipo() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Estadistica getEstadistica() {
		return estadistica;
	}
	public void setEstadistica(Estadistica estadistica) {
		this.estadistica = estadistica;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	public ArrayList<String> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<String> jugadores) {
		this.jugadores = jugadores;
	}

	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", estadistica=" + estadistica + ", jugadores=" + jugadores
				+ ", puntos=" + puntos + "]";
	}

	
	
	
	
}
