import java.util.Date;

public class Ruta {
	private int id;
	private Paraje paraje;
	private String color;
	private Date fecha;
	private int duracion;
	public Ruta(int id, Paraje paraje, String color, Date fecha, int duracion) {
		
		this.id = id;
		this.paraje = paraje;
		this.color = color;
		this.fecha = fecha;
		this.duracion = duracion;
	}
	public Ruta() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Paraje getParaje() {
		return paraje;
	}
	public void setParaje(Paraje paraje) {
		this.paraje = paraje;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	@Override
	public String toString() {
		return "Ruta [id=" + id + ", paraje=" + paraje.getNombre() + ", color=" + color + ", fecha=" + fecha + ", duracion="
				+ duracion + "]";
	}
	
	
}
