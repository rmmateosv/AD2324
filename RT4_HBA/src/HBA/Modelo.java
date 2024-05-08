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

	public void cerrar() {
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

	public List<Serie> obtenerSeries() {
		List<Serie> resultado = new ArrayList();

		try {
			Query consulta = conexion.createQuery("FROM Serie");
			resultado = consulta.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	public Serie obtenerSerie(int codigo) {
		Serie resultado = null;

		try {
			resultado = conexion.find(Serie.class, codigo);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	public Capitulo obtenerCapitulo(int codCapitulo) {
		Capitulo resultado = null;

		try {
			resultado = conexion.find(Capitulo.class, codCapitulo);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean crearReproduccion(Reproduccion r) {
		boolean resultado = false;

		try {
			conexion.getTransaction().begin();
			conexion.persist(r);
			conexion.getTransaction().commit();
			conexion.clear();
			resultado = true;
		} catch (Exception e) {
			conexion.getTransaction().rollback();
			e.printStackTrace();
		}

		return resultado;
	}
}
