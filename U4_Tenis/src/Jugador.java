



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
@Table
public class Jugador {
	
	@Id
	@Column(name = "codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String nombre;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "claveJP.jugador")
	//Qué atributo de Jugador_Partido, tiene el Jugador
	private List<Jugador_Partido> jugados = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ganador")
	//Qué atributo de Partido, tiene el jugador
	private List<Partido> ganados = new ArrayList<>();
	
	public Jugador(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	public Jugador() {
		super();
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
	public List<Jugador_Partido> getJugados() {
		return jugados;
	}
	public void setJugados(List<Jugador_Partido> jugados) {
		this.jugados = jugados;
	}
	public List<Partido> getGanados() {
		return ganados;
	}
	public void setGanados(List<Partido> ganados) {
		this.ganados = ganados;
	}
	
	@Override
	public String toString() {
		return "Jugador [id=" + id + ", nombre=" + nombre + ", "
				+ "jugados=" + jugados + ", ganados=" + ganados + "]";
	}

}
