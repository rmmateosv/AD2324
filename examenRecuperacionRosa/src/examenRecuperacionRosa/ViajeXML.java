package examenRecuperacionRosa;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"noches","precio","plazas"})
public class ViajeXML {
		private String titulo;
		private String destino;
		private int noches;
		private float precio;
		private int plazas;
		
		@XmlAttribute
		public String getTitulo() {
			return titulo;
		}
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		@XmlAttribute
		public String getDestino() {
			return destino;
		}
		public void setDestino(String destino) {
			this.destino = destino;
		}
		@XmlElement
		public int getNoches() {
			return noches;
		}
		public void setNoches(int noches) {
			this.noches = noches;
		}
		@XmlElement
		public float getPrecio() {
			return precio;
		}
		public void setPrecio(float precio) {
			this.precio = precio;
		}
		@XmlElement
		public int getPlazas() {
			return plazas;
		}
		public void setPlazas(int plazas) {
			this.plazas = plazas;
		}
		
		public ViajeXML(String titulo, String destino, int noches, int precio, int plazas) {
			super();
			this.titulo = titulo;
			this.destino = destino;
			this.noches = noches;
			this.precio = precio;
			this.plazas = plazas;
		}
		@Override
		public String toString() {
			return "viaje [titulo=" + titulo + ", destino=" + destino + ", noches=" + noches + ", precio=" + precio
					+ ", plazas=" + plazas + "]";
		}
		public ViajeXML() {
			super();
		}
		
		
}
