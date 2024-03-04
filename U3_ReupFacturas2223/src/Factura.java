import java.util.ArrayList;
import java.util.Date;

public class Factura {
	private int numero;
	private Date fecha;
	private String cliente;
	private ArrayList<Detalle> detalle=new ArrayList<>();
	private int facturaAnulacion=0;
	
	public Factura(int numero, Date fecha, String cliente, ArrayList<Detalle> detalle) {

		this.numero = numero;
		this.fecha = fecha;
		this.cliente = cliente;
		this.detalle = detalle;
	}
	public Factura() {

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
	public ArrayList<Detalle> getDetalle() {
		return detalle;
	}
	public void setDetalle(ArrayList<Detalle> detalle) {
		this.detalle = detalle;
	}
	
	public int getFacturaAnulacion() {
		return facturaAnulacion;
	}
	public void setFacturaAnulacion(int facturaAnulacion) {
		this.facturaAnulacion = facturaAnulacion;
	}
	@Override
	public String toString() {
		return "Factura [numero=" + numero + ", fecha=" + fecha + ", cliente=" + cliente + ", detalle=" + detalle
				+ ", facturaAnulacion=" + facturaAnulacion + "]";
	}
	
	
	
}	
