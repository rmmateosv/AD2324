import java.util.Date;

public class ServicioContratado {
	private Cliente cliente;
	private Servicio servicio;
	private Date fechaAlta,fechaBaja;
	public ServicioContratado(Cliente cliente, Servicio servicio, Date fechaAlta, Date fechaBaja) {
		super();
		this.cliente = cliente;
		this.servicio = servicio;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
	}
	public ServicioContratado() {
		super();
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	@Override
	public String toString() {
		return "ServicioContratado [cliente=" + cliente + ", servicio=" + servicio + ", fechaAlta=" + fechaAlta
				+ ", fechaBaja=" + fechaBaja + "]";
	}
	
	
}
