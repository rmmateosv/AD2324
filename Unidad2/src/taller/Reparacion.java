package taller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reparacion {
	private int id;
	private Date fecha;
	private String vehiculo;
	private int usuario;
	
	public Reparacion() {
		
	}

	public Reparacion(int id, Date fecha, String vehiculo, int usuario) {
		this.id = id;
		this.fecha = fecha;
		this.vehiculo = vehiculo;
		this.usuario = usuario;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return "Vehiculo [id=" + id + ", fecha=" + formato.format(fecha) + 
				", vehiculo=" + vehiculo + ", usuario=" + usuario + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	
}
