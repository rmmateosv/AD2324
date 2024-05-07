package HBA;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ClaveReproduccion {
	@ManyToOne()
	@JoinColumn(name ="usuario",referencedColumnName = "nick")
	private Usuario usuario;
	@ManyToOne()
	@JoinColumn(name = "capitulo",referencedColumnName = "id")
	private Capitulo capitulo;
	
	public ClaveReproduccion() {}

	public ClaveReproduccion(Usuario usuario, Capitulo capitulo) {
		this.usuario = usuario;
		this.capitulo = capitulo;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Capitulo getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(Capitulo capitulo) {
		this.capitulo = capitulo;
	}

	@Override
	public String toString() {
		return "ClaveReproduccion [usuario=" + usuario + ", capitulo=" + capitulo + "]";
	}
	
	
}
