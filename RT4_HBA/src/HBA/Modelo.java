package HBA;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class Modelo {
	private EntityManager conexion = null;

	
	public Modelo() {
		try {
			conexion = Persistence.createEntityManagerFactory("Series").createEntityManager();
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
	
	public void cerrar() 
	{
		if (conexion != null) {
			try {
				conexion.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
	}


	public List<Usuario> obtenerUsuarios() {
		// TODO Auto-generated method stub
		List<Usuario> resultado = new ArrayList();
		try {
			Query c = conexion.createQuery("FROM Usuario");
			resultado = c.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}


	public Usuario obtenerUsuario(String nick) {
		Usuario resultado = null;
		try {
			return conexion.find(Usuario.class, nick);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
}

