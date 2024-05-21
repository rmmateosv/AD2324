package skills;

import java.util.Date;

public class Prueba {
	private int id;
	private int modalidad;
	private Date fecha;
	private Infoprueba info;
	private int puntuacion;

	public Prueba() {
		
	}

	public Prueba(int id, int modalidad, Date fecha, Infoprueba info, int puntuacion) {
		this.id = id;
		this.modalidad = modalidad;
		this.fecha = fecha;
		this.info = info;
		this.puntuacion = puntuacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getModalidad() {
		return modalidad;
	}

	public void setModalidad(int modalidad) {
		this.modalidad = modalidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Infoprueba getInfo() {
		return info;
	}

	public void setInfo(Infoprueba info) {
		this.info = info;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	@Override
	public String toString() {
		return "Prueba [id=" + id + ", modalidad=" + modalidad + ", fecha=" + fecha + ", info=" + info + ", puntuacion="
				+ puntuacion + "]";
	}

}
