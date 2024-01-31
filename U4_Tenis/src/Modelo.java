import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Modelo {
	private EntityManager conexion = null;

	public Modelo() {
		try {
			conexion=Persistence.createEntityManagerFactory("Tenis").createEntityManager();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public EntityManager getConexion() {
		return conexion;
	}


	public void setConexion(EntityManager conexion) {
		this.conexion = conexion;
	}


	public void cerrar() {
		try {
			conexion.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}	
