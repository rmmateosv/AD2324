package Taller;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

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

	public Usuario obtenerUsuario(String us, String ps) {
		// TODO Auto-generated method stub
		Usuario resultado=null;
		try {
			Query consulta = conexion.createQuery(
					"from Usuario "
					+ "where usuario = ?1 and ps = sha2(?2,512)");
			consulta.setParameter(1, us);
			consulta.setParameter(2, ps);
			
			List<Usuario> r = consulta.getResultList();
			if(r.size()==1) {
				resultado=r.get(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

}
