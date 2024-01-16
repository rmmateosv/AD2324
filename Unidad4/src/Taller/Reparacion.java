package Taller;

import java.text.SimpleDateFormat;
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
@Table()
public class Reparacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@ManyToOne
	//Indicar el campo de Vehículo que es la PK
	@JoinColumn(name="vehiculo", referencedColumnName = "matricula")
	private Vehiculo vehiculo;
	@ManyToOne
	//Indicar el campo de Usuario que es la PK
	@JoinColumn(name="usuario", referencedColumnName = "id")
	private Usuario usuario;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date fechaPago;
	@Column(nullable = false)
	private float total;
	@Column(nullable = false)
	private float horas;
	@Column(nullable = false)
	private float precioH;
	@Column(nullable = false)
	private float borrar;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "clave.reparacion")
	List<PiezaReparacion> piezasR = new ArrayList<PiezaReparacion>();
	
	public Reparacion() {
		
	}

	
	public Reparacion(int id, Date fecha, Vehiculo vehiculo, Usuario usuario, Date fechaPago, float total, float horas,
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


	public Reparacion(int id, Date fecha, Vehiculo vehiculo, Usuario usuario) {
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

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
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


	public List<PiezaReparacion> getPiezasR() {
		return piezasR;
	}


	public void setPiezasR(List<PiezaReparacion> piezasR) {
		this.piezasR = piezasR;
	}

	
	
}
