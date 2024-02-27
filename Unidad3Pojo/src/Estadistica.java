
public class Estadistica {
	private int jugados;
	private int ganados;
	private int perdidos;
	private int empatados;
	
	public Estadistica(int jugados, int ganados, int perdidos, int empatados) {
		
		this.jugados = jugados;
		this.ganados = ganados;
		this.perdidos = perdidos;
		this.empatados = empatados;
	}

	public Estadistica() {
		
	}

	public int getJugados() {
		return jugados;
	}

	public void setJugados(int jugados) {
		this.jugados = jugados;
	}

	public int getGanados() {
		return ganados;
	}

	public void setGanados(int ganados) {
		this.ganados = ganados;
	}

	public int getPerdidos() {
		return perdidos;
	}

	public void setPerdidos(int perdidos) {
		this.perdidos = perdidos;
	}

	public int getEmpatados() {
		return empatados;
	}

	public void setEmpatados(int empatados) {
		this.empatados = empatados;
	}

	@Override
	public String toString() {
		return "Estadistica [jugados=" + jugados + ", ganados=" + ganados + ", perdidos=" + perdidos + ", empatados="
				+ empatados + "]";
	}
	
	
}
