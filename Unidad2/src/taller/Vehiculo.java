package taller;

public class Vehiculo {
	private String matricula, propietario, telf;

	public Vehiculo() {
		
	}

	public Vehiculo(String matricula, String propietario, String telf) {
		
		this.matricula = matricula;
		this.propietario = propietario;
		this.telf = telf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public String getTelf() {
		return telf;
	}

	public void setTelf(String telf) {
		this.telf = telf;
	}

	@Override
	public String toString() {
		return "Vehiculo [matricula=" + matricula + ", propietario=" + propietario + ", telf=" + telf + "]";
	}
	
	
}
