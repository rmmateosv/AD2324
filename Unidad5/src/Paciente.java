import java.util.ArrayList;

public class Paciente extends Persona{
	private int nss;
	ArrayList<String[]> historia = new ArrayList();
	
	public Paciente() {
		// TODO Auto-generated constructor stub
	}

	public Paciente(int id, String nombre, Contacto contacto,int nss, ArrayList<String[]> historia) {
		super(id, nombre, contacto);
		this.nss = nss;
		this.historia = historia;
	}

	public int getNss() {
		return nss;
	}

	public void setNss(int nss) {
		this.nss = nss;
	}

	public ArrayList<String[]> getHistoria() {
		return historia;
	}

	public void setHistoria(ArrayList<String[]> historia) {
		this.historia = historia;
	}

	@Override
	public String toString() {
		return "Paciente ["+ super.toString() +"nss=" + nss + ", historia=" + historia + "]";
	}
	

}
