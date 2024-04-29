package SKILLS;

public class Modalidades {

	private int id;
	private String modalidad;
	
	public Modalidades() {}

	public Modalidades(int id, String modalidad) {
		this.id = id;
		this.modalidad = modalidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	@Override
	public String toString() {
		return "Modalidades [id=" + id + ", modalidad=" + modalidad + "]";
	}
	
	
}
