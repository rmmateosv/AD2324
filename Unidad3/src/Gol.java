
public class Gol {
	private int minuto;
	private String equipo;
	private boolean anulado=false;
	
	
	public Gol(int minuto, String equipo, boolean anulado) {
		super();
		this.minuto = minuto;
		this.equipo = equipo;
		this.anulado = anulado;
	}


	public Gol() {
		super();
	}


	public int getMinuto() {
		return minuto;
	}


	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}


	public String getEquipo() {
		return equipo;
	}


	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}


	public boolean isAnulado() {
		return anulado;
	}


	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}


	@Override
	public String toString() {
		return "Gol [minuto=" + minuto + ", equipo=" + equipo + ", anulado=" + anulado + "]";
	}
	
	
	
}
