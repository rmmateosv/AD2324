import java.util.Date;

public class Consulta {
	private Medico medico;
	private Paciente paciente;
	private Date fecha;
	private String diagnostico;
	public Consulta(Medico medico, Paciente paciente, Date fecha, String diagnostico) {
	
		this.medico = medico;
		this.paciente = paciente;
		this.fecha = fecha;
		this.diagnostico = diagnostico;
	}
	public Consulta() {
		
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	@Override
	public String toString() {
		return "Consulta [medico=" + medico + ", paciente=" + paciente + ", fecha=" + fecha + ", diagnostico="
				+ diagnostico + "]";
	}
	
	

}
