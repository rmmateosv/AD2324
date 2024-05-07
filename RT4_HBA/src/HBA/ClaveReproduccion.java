package HBA;

public class ClaveReproduccion {

	private String usuario;
	private int capitulo;
	
	public ClaveReproduccion() {}

	public ClaveReproduccion(String usuario, int capitulo) {
		this.usuario = usuario;
		this.capitulo = capitulo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(int capitulo) {
		this.capitulo = capitulo;
	}

	@Override
	public String toString() {
		return "ClaveReproduccion [usuario=" + usuario + ", capitulo=" + capitulo + "]";
	}
	
	
}
