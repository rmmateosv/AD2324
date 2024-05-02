package SKILLS;

public class Correccion {
	private int alumno;
	private int prueba;
	private int nota;
	private String comentario;

	public Correccion() {
		// TODO Auto-generated constructor stub
	}

	public Correccion(int alumno, int prueba, int nota, String comentario) {
		this.alumno = alumno;
		this.prueba = prueba;
		this.nota = nota;
		this.comentario = comentario;
	}

	public int getAlumno() {
		return alumno;
	}

	public void setAlumno(int alumno) {
		this.alumno = alumno;
	}

	public int getPrueba() {
		return prueba;
	}

	public void setPrueba(int prueba) {
		this.prueba = prueba;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "Correccion [alumno=" + alumno + ", prueba=" + prueba + ", nota=" + nota + ", comentario=" + comentario
				+ "]";
	}

}
