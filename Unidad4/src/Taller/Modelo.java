package Taller;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
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

	public Usuario obtenerUsuario(String dni) {
		// TODO Auto-generated method stub
		Usuario resultado=null;
		try {
			Query consulta = conexion.createQuery(
					"from Usuario "
					+ "where usuario = ?1");
			consulta.setParameter(1, dni);
			
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

	public boolean crearUsuario(Usuario u) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		EntityTransaction t = null;
		try {
			t =  conexion.getTransaction();
			t.begin();
			conexion.persist(u);
			t.commit();
			resultado=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Vehiculo> obtenerVehiculos() {
		// TODO Auto-generated method stub
		List<Vehiculo> resultado=null;
		try {
			Query consulta = conexion.createQuery(
					"from Vehiculo");
			resultado = consulta.getResultList();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public Vehiculo obtenerVehiculo(String m) {
		// TODO Auto-generated method stub
		Vehiculo resultado=null;
		try {
			
			resultado = conexion.find(Vehiculo.class,m);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearVehiculo(Vehiculo v) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		boolean resultado=false;
		EntityTransaction t = null;
		try {
			t =  conexion.getTransaction();
			t.begin();
			conexion.persist(v);
			v.setPropietario("P_"+v.getPropietario());
			t.commit();
			resultado=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearReparacion(Reparacion r) {
		// TODO Auto-generated method stub
		return false;
	}

}
