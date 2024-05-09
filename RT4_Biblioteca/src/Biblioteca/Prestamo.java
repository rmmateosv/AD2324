package Biblioteca;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table
public class Prestamo {
	@EmbeddedId
	private ClavePrestamo id;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaDevolPrevista;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date fechaDevolReal;
	
	public Prestamo() {}

	public Prestamo(ClavePrestamo id, Date fechaDevolPrevista, Date fechaDevolReal) {
		this.id = id;
		this.fechaDevolPrevista = fechaDevolPrevista;
		this.fechaDevolReal = fechaDevolReal;
	}

	public ClavePrestamo getId() {
		return id;
	}

	public void setId(ClavePrestamo id) {
		this.id = id;
	}

	public Date getFechaDevolPrevista() {
		return fechaDevolPrevista;
	}

	public void setFechaDevolPrevista(Date fechaDevolPrevista) {
		this.fechaDevolPrevista = fechaDevolPrevista;
	}

	public Date getFechaDevolReal() {
		return fechaDevolReal;
	}

	public void setFechaDevolReal(Date fechaDevolReal) {
		this.fechaDevolReal = fechaDevolReal;
	}

	@Override
	public String toString() {
		return "Prestamo [id=" + id + ", fechaDevolPrevista=" + fechaDevolPrevista + ", fechaDevolReal="
				+ fechaDevolReal + "]";
	}
	
	
}
