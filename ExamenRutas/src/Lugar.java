
public class Lugar {
	private int id;
	private String nombre;
	private Paraje paraje;
	private Municipio municipio;
	public Lugar(int id, String nombre, Paraje paraje, Municipio municipio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.paraje = paraje;
		this.municipio = municipio;
	}
	public Lugar() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Paraje getParaje() {
		return paraje;
	}
	public void setParaje(Paraje paraje) {
		this.paraje = paraje;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	@Override
	public String toString() {
		return "Lugar [id=" + id + ", nombre=" + nombre + ", paraje=" + paraje.getNombre() + ", municipio=" + municipio.getNombre() + "]";
	}
	
	
	
}
