import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Clave {
	@ManyToOne
	//Indicar la clave primaria de partido
	@JoinColumn(name="partido",referencedColumnName = "codigo", nullable = false) 
	private Partido partido;
	@ManyToOne
	//Indicar la clave primaria de partido
	@JoinColumn(name="jugador",referencedColumnName = "codigo", nullable = false)
	private Jugador jugador;
	
	public Clave(Partido partido, Jugador jugador) {
		super();
		this.partido = partido;
		this.jugador = jugador;
	}
	public Clave() {
		super();
	}
	public Partido getPartido() {
		return partido;
	}
	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	@Override
	public String toString() {
		return "Clave [partido=" + partido + ", jugador=" + jugador + "]";
	}
	
}
