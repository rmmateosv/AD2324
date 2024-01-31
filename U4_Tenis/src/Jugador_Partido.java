import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Jugador_Partido {
	@EmbeddedId
	private Clave claveJP;
	@Column(nullable = true)
	private String resultado;
	public Jugador_Partido(Clave claveJP, String resultado) {
		super();
		this.claveJP = claveJP;
		this.resultado = resultado;
	}
	public Jugador_Partido() {
		super();
	}
	public Clave getClaveJP() {
		return claveJP;
	}
	public void setClaveJP(Clave claveJP) {
		this.claveJP = claveJP;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	@Override
	public String toString() {
		return "Jugador_Partido [claveJP=" + claveJP + ", resultado=" + resultado + "]";
	}
	
	

}
