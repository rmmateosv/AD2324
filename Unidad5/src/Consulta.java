import java.util.Date;

public class Consulta {
	private int id;
	private Medico medico;
	private Paciente paciente;
	private Date fecha;
	private String diagnostico;
	public Consulta(int id,Medico medico, Paciente paciente, Date fecha, String diagnostico) {
		this.id = id;
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
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Consulta [id = "+id+"medico=" + medico + ", paciente=" + paciente + ", fecha=" + fecha + ", diagnostico="
				+ diagnostico + "]";
	}
	
	

}
