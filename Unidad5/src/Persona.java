
public class Persona {
	private int id;
	private String nombre;
	private Contacto contacto;
	
	public Persona() {
		// TODO Auto-generated constructor stub
	}

	public Persona(int id, String nombre, Contacto contacto) {
		
		this.id = id;
		this.nombre = nombre;
		this.contacto = contacto;
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

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", contacto=" + contacto + "]";
	}
	
}
