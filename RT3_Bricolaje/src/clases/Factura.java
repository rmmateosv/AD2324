package clases;

import java.util.ArrayList;
import java.util.Date;

public class Factura {

	private int numero;
	private Date fecha;
	private String cliente;
	private ArrayList<Detalle> listaDetalles = new ArrayList<Detalle>();
	private int facturaAnulacion;

	public Factura() {
		// TODO Auto-generated constructor stub
	}

	public Factura(int numero, Date fecha, String cliente, ArrayList<Detalle> listaDetalles, int facturaAnulacion) {
		this.numero = numero;
		this.fecha = fecha;
		this.cliente = cliente;
		this.listaDetalles = listaDetalles;
		this.facturaAnulacion = facturaAnulacion;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Detalle> getListaDetalles() {
		return listaDetalles;
	}

	public void setListaDetalles(ArrayList<Detalle> listaDetalles) {
		this.listaDetalles = listaDetalles;
	}

	public int getFacturaAnulacion() {
		return facturaAnulacion;
	}

	public void setFacturaAnulacion(int facturaAnulacion) {
		this.facturaAnulacion = facturaAnulacion;
	}

	@Override
	public String toString() {
		String r = "** FACTURA **\n Factura [numero=" + numero + ", fecha=" + fecha + ", cliente=" + cliente + ", facturaAnulacion=" + facturaAnulacion + "]";
		for (Detalle d : listaDetalles) {
			r+="\n"+d;
		}
		r+= "\n*************";
		return r;
	}

}
