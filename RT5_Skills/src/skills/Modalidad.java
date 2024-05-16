package skills;

public class Modalidad {
	private int id;
	private String modalidad;

	public Modalidad() {
		
	}

	public Modalidad(int id, String modalidad) {
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
		return "Modalidad [id=" + id + ", modalidad=" + modalidad + "]";
	}
}
