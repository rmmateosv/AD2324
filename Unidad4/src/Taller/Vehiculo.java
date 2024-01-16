package Taller;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="vehiculos")
public class Vehiculo {
	@Id
	private String matricula;
	@Column(nullable = false)
	private String propietario;
	@Column(nullable = false)
	private String telf;
	//Indicar qué campo de reparación contiene el vehículo
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "vehiculo")
	List<Reparacion> reparaciones = new ArrayList();
	

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

	public List<Reparacion> getReparaciones() {
		return reparaciones;
	}

	public void setReparaciones(List<Reparacion> reparaciones) {
		this.reparaciones = reparaciones;
	}
	
	
}
