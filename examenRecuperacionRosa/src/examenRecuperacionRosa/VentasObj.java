package examenRecuperacionRosa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class VentasObj implements Serializable {
	private String codigo;
	private Date fecha;
	private String dni;
	private int viaje;
	private float precioTotal;
	private ArrayList<Personas> listaPersonas=new ArrayList<Personas>();
	private String codigoVentaAnulada;
	public VentasObj(String codigo, Date fecha, String dni, int viaje, float precioTotal,
			ArrayList<Personas> listaPersonas, String codigoVentaAnulada) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.dni = dni;
		this.viaje = viaje;
		this.precioTotal = precioTotal;
		this.listaPersonas = listaPersonas;
		this.codigoVentaAnulada = codigoVentaAnulada;
	}
	public VentasObj() {
		super();
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getViaje() {
		return viaje;
	}
	public void setViaje(int viaje) {
		this.viaje = viaje;
	}
	public float getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}
	public ArrayList<Personas> getListaPersonas() {
		return listaPersonas;
	}
	public void setListaPersonas(ArrayList<Personas> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}
	public String getCodigoVentaAnulada() {
		return codigoVentaAnulada;
	}
	public void setCodigoVentaAnulada(String codigoVentaAnulada) {
		this.codigoVentaAnulada = codigoVentaAnulada;
	}
	@Override
	public String toString() {
		return "VentasObj [codigo=" + codigo + ", fecha=" + fecha + ", dni=" + dni + ", viaje=" + viaje
				+ ", precioTotal=" + precioTotal + ", listaPersonas=" + listaPersonas + ", codigoVentaAnulada="
				+ codigoVentaAnulada + "]";
	}

	
	
}
