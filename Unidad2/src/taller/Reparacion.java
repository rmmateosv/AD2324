package taller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reparacion {
	private int id;
	private Date fecha;
	private String vehiculo;
	private int usuario;
	private Date fechaPago;
	private float total;
	private float horas;
	private float precioH;
	
	public Reparacion() {
		
	}

	
	public Reparacion(int id, Date fecha, String vehiculo, int usuario, Date fechaPago, float total, float horas,
			float precioH) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.vehiculo = vehiculo;
		this.usuario = usuario;
		this.fechaPago = fechaPago;
		this.total = total;
		this.horas = horas;
		this.precioH = precioH;
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
		return "Reparacion [id=" + id + ", fecha=" + formato.format(fecha) + ", vehiculo=" + vehiculo + ", usuario=" + usuario
				+ ", fechaPago=" + (fechaPago==null?"No Pagado":formato.format(fecha)) +
				", total=" + total + ", horas=" + horas + ", precioH=" + precioH + "]";
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


	public Date getFechaPago() {
		return fechaPago;
	}


	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}


	public float getTotal() {
		return total;
	}


	public void setTotal(float total) {
		this.total = total;
	}


	public float getHoras() {
		return horas;
	}


	public void setHoras(float horas) {
		this.horas = horas;
	}


	public float getPrecioH() {
		return precioH;
	}


	public void setPrecioH(float precioH) {
		this.precioH = precioH;
	}

	
	
}
