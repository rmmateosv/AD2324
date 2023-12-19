
public class Medico extends Persona{
	private int colegiado;
	private String especialidad;
	
	public Medico() {
		// TODO Auto-generated constructor stub
	}

	public Medico(int id, String nombre, Contacto contacto,int colegiado, String especialidad) {
		super(id, nombre, contacto);
		this.colegiado = colegiado;
		this.especialidad = especialidad;
	}

	public int getColegiado() {
		return colegiado;
	}

	public void setColegiado(int colegiado) {
		this.colegiado = colegiado;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public String toString() {
		return "Medico ["+ super.toString() +"colegiado=" + colegiado + ", especialidad=" + especialidad + "]";
	}
	

}
