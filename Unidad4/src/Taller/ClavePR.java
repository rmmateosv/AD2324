package Taller;

import java.io.Serializable;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ClavePR implements Serializable{
	@ManyToOne
	//CAmpo de la tabla reparacion que tiene la PK
	@JoinColumn(name="reparacion",referencedColumnName = "id")
	private Reparacion reparacion;
	@ManyToOne
	//CAmpo de la tabla piezas que tiene la PK
	@JoinColumn(name="pieza",referencedColumnName = "id")
	private Pieza pieza;
	
	public ClavePR(Reparacion reparacion, Pieza pieza) {
		super();
		this.reparacion = reparacion;
		this.pieza = pieza;
	}

	public ClavePR() {
		super();
	}

	public Reparacion getReparacion() {
		return reparacion;
	}

	public void setReparacion(Reparacion reparacion) {
		this.reparacion = reparacion;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	@Override
	public String toString() {
		return "ClavePR [reparacion=" + reparacion + ", pieza=" + pieza + "]";
	}
	
	
}
