
public class Servicio {
	private int idS;
	private String nombreS;
	private float precio;
	public int getIdS() {
		return idS;
	}
	public void setIdS(int idS) {
		this.idS = idS;
	}
	public String getNombreS() {
		return nombreS;
	}
	public void setNombreS(String nombreS) {
		this.nombreS = nombreS;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public Servicio() {
		super();
	}
	public Servicio(int idS, String nombreS, float precio) {
		super();
		this.idS = idS;
		this.nombreS = nombreS;
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Servicio [idS=" + idS + ", nombreS=" + nombreS + ", precio=" + precio + "]";
	}
	
}
