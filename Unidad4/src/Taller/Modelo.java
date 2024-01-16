package Taller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Modelo {

	private EntityManager conexion = null;

	public Modelo() {
		try {
			// Conectar con la BD
			conexion=Persistence.createEntityManagerFactory("Taller")
					.createEntityManager();
			
			
		} catch (Exception e) {
			// TODO: handle exception
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
			if (conexion != null) {
				conexion.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
