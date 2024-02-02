import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table
public class Partido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private  Date fecha;
	@Column(nullable = false)
	private int numSets;
	@ManyToOne
	@JoinColumn(name = "ganador", referencedColumnName = "codigo", nullable = true)
	private Jugador ganador;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "claveJP.partido")
	List<Jugador_Partido> jugadores = new ArrayList();

	public Partido(int codigo, Date fecha, int numSets, Jugador ganador, ArrayList<Jugador_Partido> jugadores) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.numSets = numSets;
		this.ganador = ganador;
		this.jugadores = jugadores;
	}

	public Partido() {
		super();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getNumSets() {
		return numSets;
	}

	public void setNumSets(int numSets) {
		this.numSets = numSets;
	}

	public Jugador getGanador() {
		return ganador;
	}

	public void setGanador(Jugador ganador) {
		this.ganador = ganador;
	}

	public List<Jugador_Partido> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador_Partido> jugadores) {
		this.jugadores = jugadores;
	}

	@Override
	public String toString() {
		String r= "Partido [codigo=" + codigo + ", fecha=" + fecha + ", numSets=" + 
	              numSets + ", "
				+ "ganador=" + ((ganador==null)?"Pendiente ":ganador.getNombre());
		r+=" jugadores = [ ";
		for (Jugador_Partido jp : jugadores) {
			r+=jp.getClaveJP().getJugador().getNombre()+"/";
		}
		r+=" ]";
		return r;
	}
	
	
}
