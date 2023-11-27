package examenRecuperacionRosa;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"mayorista","mes","listaViajes","cargado"})
public class Agencia {
	 private String mayorista;
	 private int mes;
	 private ArrayList<ViajeXML> listaViajes = new ArrayList<ViajeXML>();
	 private int cargado;
	 @XmlElement
	public String getMayorista() {
		return mayorista;
	}
	public void setMayorista(String mayorista) {
		this.mayorista = mayorista;
	}
	@XmlElement
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	@XmlElementWrapper (name = "listaViajes")
@XmlElement(name = "viaje")
	public ArrayList<ViajeXML> getListaViajes() {
		return listaViajes;
	}
	public void setListaViajes(ArrayList<ViajeXML> listaViajes) {
		this.listaViajes = listaViajes;
	}
	
	@XmlElement
	public int getCargado() {
		return cargado;
	}
	public void setCargado(int cargado) {
		this.cargado = cargado;
	}
	@Override
	public String toString() {
		return "agencia [mayorista=" + mayorista + ", mes=" + mes + ", listaViajes=" + listaViajes + ", cargado="
				+ cargado + "]";
	}
	
	 
	 
}
