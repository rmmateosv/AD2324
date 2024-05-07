package HBA;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Reproducciones")
public class Reproduccion {
	@EmbeddedId
	private ClaveReproduccion clave;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Column(nullable = true)
	private int minutoPausa;
	
	public Reproduccion() {}

	public Reproduccion(ClaveReproduccion clave, Date fecha, int minutoPausa) {
		this.clave = clave;
		this.fecha = fecha;
		this.minutoPausa = minutoPausa;
	}

	public ClaveReproduccion getClave() {
		return clave;
	}

	public void setClave(ClaveReproduccion clave) {
		this.clave = clave;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getMinutoPausa() {
		return minutoPausa;
	}

	public void setMinutoPausa(int minutoPausa) {
		this.minutoPausa = minutoPausa;
	}

	@Override
	public String toString() {
		return "Reproduccion [clave=" + clave + ", fecha=" + fecha + ", minutoPausa=" + minutoPausa + "]";
	}
	
	
	
}
