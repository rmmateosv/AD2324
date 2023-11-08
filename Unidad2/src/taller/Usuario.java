package taller;

public class Usuario {
	private int id;
	private String usuario;
	private String perfil;
	
	public Usuario() {
		
	}
	
	public Usuario(int id, String usuario, String perfil) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.perfil = perfil;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", perfil=" + perfil + "]";
	}
	
	
}
