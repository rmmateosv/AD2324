package examenRecuperacionRosa;

public class ViajesBin {
		private int codigo; // 4
		private String mayorista; // 50*2 100B
		private String titulo; // 100*2 200B
		private String destino; //100*2 // 200B
		private int nPlazas; // 4
		private int duracion; // 4
		private float precio; // 4
		private boolean disponible; // 1
		public int getCodigo() {
			return codigo;
		}
		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}
		public String getMayorista() {
			return mayorista;
		}
		public void setMayorista(String mayorista) {
			this.mayorista = mayorista;
		}
		public String getTitulo() {
			return titulo;
		}
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		public String getDestino() {
			return destino;
		}
		public void setDestino(String destino) {
			this.destino = destino;
		}
		public int getnPlazas() {
			return nPlazas;
		}
		public void setnPlazas(int nPlazas) {
			this.nPlazas = nPlazas;
		}
	
		public float getPrecio() {
			return precio;
		}
		public void setPrecio(float precio) {
			this.precio = precio;
		}
		public boolean isDisponible() {
			return disponible;
		}
		public void setDisponible(boolean disponible) {
			this.disponible = disponible;
		}
		
		public ViajesBin() {
			
		}
		public int getDuracion() {
			return duracion;
		}
		public void setDuracion(int duracion) {
			this.duracion = duracion;
		}
		@Override
		public String toString() {
			return "Viajes [codigo=" + codigo + ", mayorista=" + mayorista + ", titulo=" + titulo + ", destino="
					+ destino + ", nPlazas=" + nPlazas + ", duracion=" + duracion + ", precio=" + precio
					+ ", disponible=" + disponible + "]";
		}
		
		
}
