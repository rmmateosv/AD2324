package Taller;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique= true)
	private String usuario;
	@Column(nullable = false)
	private String perfil;
	@Column(nullable = false)
	private String ps;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	List<Reparacion> reparaciones = new ArrayList<Reparacion>();
	
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

	public List<Reparacion> getReparaciones() {
		return reparaciones;
	}

	public void setReparaciones(List<Reparacion> reparaciones) {
		this.reparaciones = reparaciones;
	}
	
	
}
