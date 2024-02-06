import java.util.ArrayList;
import java.util.Date;

public class Partido {
	private String equipoL;
	private String equipoV;
	private Date fecha;
	private ArrayList<Gol> goles= new ArrayList<>();
	private boolean finalizado;
	
	public Partido(String equipoL, String equipoV, Date fecha, ArrayList<Gol> goles) {
		super();
		this.equipoL = equipoL;
		this.equipoV = equipoV;
		this.fecha = fecha;
		this.goles = goles;
	}
	public Partido() {
		super();
	}
	public String getEquipoL() {
		return equipoL;
	}
	public void setEquipoL(String equipoL) {
		this.equipoL = equipoL;
	}
	public String getEquipoV() {
		return equipoV;
	}
	public void setEquipoV(String equipoV) {
		this.equipoV = equipoV;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public ArrayList<Gol> getGoles() {
		return goles;
	}
	public void setGoles(ArrayList<Gol> goles) {
		this.goles = goles;
	}
	
	public boolean isFinalizado() {
		return finalizado;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	@Override
	public String toString() {
		return "Partido [equipoL=" + equipoL + ", equipoV=" + equipoV + ", fecha=" + fecha + ", goles=" + goles
				+ ", finalizado=" + finalizado + "]";
	}
	
	
	
}
