import java.io.Serializable;

public class Contacto implements Serializable{
	private String telefono;
	private String email;
	
	public Contacto() {
		
	}

	public Contacto(String telefono, String email) {
		
		this.telefono = telefono;
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contacto [telefono=" + telefono + ", email=" + email + "]";
	}
	
	
}
