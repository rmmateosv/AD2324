import java.util.ArrayList;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

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


	public ArrayList<Jugador> obtenerJugadores() {
		// TODO Auto-generated method stub
		ArrayList<Jugador> resultado = new ArrayList<Jugador>();
		try {
			Query consulta = conexion.createQuery("from Jugador");
			resultado = (ArrayList<Jugador>) consulta.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}


	public Jugador obtenerJugador(int idJ) {
		// TODO Auto-generated method stub
		Jugador resultado = null;
		try {
			resultado=conexion.find(Jugador.class, idJ);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}


	public boolean crearPartido(Partido p) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		try {
			conexion.getTransaction().begin();
			conexion.persist(p);
			conexion.getTransaction().commit();
			conexion.clear();
			resultado = true;			
		} catch (Exception e) {
			// TODO: handle exception
			conexion.getTransaction().rollback();
			e.printStackTrace();
		}
		return resultado;
	}


	public ArrayList<Partido> obtenerPartidos() {
		// TODO Auto-generated method stub
		ArrayList<Partido> resultado = new ArrayList<Partido>();
		try {
			Query consulta = conexion.createQuery("from Partido");
			resultado = (ArrayList<Partido>) consulta.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}


	public Partido obtenerPartido(int idP) {
		// TODO Auto-generated method stub
		Partido resultado = null;
		try {
			resultado = conexion.find(Partido.class, idP);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}


	public boolean guardarDatos() {
		// TODO Auto-generated method stub
		boolean resultado=false;
		try {
			conexion.getTransaction().begin();
			conexion.getTransaction().commit();
			conexion.clear();
			resultado = true;			
		} catch (Exception e) {
			// TODO: handle exception
			conexion.getTransaction().rollback();
			e.printStackTrace();
		}
		return resultado;
	}


	public boolean borrarPartido(Partido p) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		try {
			conexion.getTransaction().begin();
			Query consulta;
			if(p.getJugadores().size()>0) {
				//Borar JP
				consulta=conexion.createQuery("delete from Jugador_Partido where "
						+ "claveJP.partido = ?1");
				consulta.setParameter(1, p);
				int filas =consulta.executeUpdate();
				if(filas!=p.getJugadores().size()) {
					conexion.getTransaction().rollback();
					return false;
				}
			}
			consulta=conexion.createQuery("delete from Partido where "
					+ "codigo = ?1");
			consulta.setParameter(1, p.getCodigo());
			int filas =consulta.executeUpdate();
			if(filas==1) {
				conexion.getTransaction().commit();
				conexion.clear();
				resultado = true;
			}						
		} catch (Exception e) {
			// TODO: handle exception
			conexion.getTransaction().rollback();
			e.printStackTrace();
		}
		return resultado;
	}


	
}	
