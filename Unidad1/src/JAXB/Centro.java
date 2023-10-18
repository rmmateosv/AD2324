package JAXB;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"fecha","ies","historiales"})
public class Centro {
	private Date fecha;
	private String ies;
	private ArrayList<Historial> historiales = new ArrayList<>();
	private String alumno="Rosa";
	
	public Centro() {
		
	}
	
	public Centro(String ies, ArrayList<Historial> historiales,Date fecha) {
		
		this.fecha = fecha;
		this.ies = ies;
		this.historiales = historiales;
	}
	@XmlElement
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@XmlElement
	public String getIes() {
		return ies;
	}
	public void setIes(String ies) {
		this.ies = ies;
	}
	@XmlElementWrapper(name="historiales")
	@XmlElement(name="historial")
	public ArrayList<Historial> getHistoriales() {
		return historiales;
	}
	public void setHistoriales(ArrayList<Historial> historiales) {
		this.historiales = historiales;
	}
	@XmlTransient
	public String getAlumno() {
		return alumno;
	}

	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}

	@Override
	public String toString() {
		String resultado = 
		 "Centro [fecha=" + fecha + ", ies=" + ies +"]";
		for(Historial h:historiales) {
			resultado+=h;
		}
		return resultado;
		
	}
	
	
}
