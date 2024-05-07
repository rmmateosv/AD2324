package HBA;

import java.util.Date;

public class Reproduccion {

	private ClaveReproduccion clave;
	private Date fecha;
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
