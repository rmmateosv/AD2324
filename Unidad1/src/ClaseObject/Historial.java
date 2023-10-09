package ClaseObject;

import java.io.Serializable;
import java.util.ArrayList;

import ClaseRAF.Nota;
import fTexto.Alumno;

public class Historial implements Serializable{
	private Alumno alumno;
	ArrayList<Object[]> datos = new ArrayList<Object[]>();
	public Historial() {
		
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public ArrayList<Object[]> getDatos() {
		return datos;
	}
	public void setDatos(ArrayList<Object[]> datos) {
		this.datos = datos;
	}
	@Override
	public String toString() {
		String resultado = "Historial [alumno=" + alumno + "]\n";
		
		for(Object[] obj:datos) {
			//Mostrar datos de asignatura y de la nota
			resultado += "\t"+obj[0]+"\n\t\t[nota="+obj[1]+"]\n";
		}
		return resultado;
	}
	
	
	
}
