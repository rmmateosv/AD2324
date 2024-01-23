package Taller;

import java.util.ArrayList;
import java.util.Date;
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
		boolean resultado=false;
		EntityTransaction t = null;
		try {
			t =  conexion.getTransaction();
			t.begin();
			conexion.persist(r);			
			t.commit();
			resultado=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Reparacion> obtenerReparaciones() {
		// TODO Auto-generated method stub
		List<Reparacion> resultado = new ArrayList<Reparacion>();
		try {
			
			Query consulta = conexion.createQuery(
					"from Reparacion");
			resultado =  consulta.getResultList();
						
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public Reparacion obtenerReparacion(int idR) {
		// TODO Auto-generated method stub
		Reparacion resultado = null;
		try {		
			resultado = conexion.find(Reparacion.class,idR);
			//RESULTADO ESTÁ EN ESTADO MANAGED
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean pagarReparacion(Reparacion r) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		EntityTransaction t = null;
		try {
			t =  conexion.getTransaction();
			t.begin();
			//Como r ESTÁ EN ESTADO MANAGED => PERSIST HACE UN UPDATE NO UN INSERT
			//conexion.persist(r);	
			//Calcular el total
			//Mano de obra
			r.setTotal(r.getHoras()*r.getPrecioH());
			//Añado el imprte de cada pieza de la reparación
			for (PiezaReparacion pr : r.getPiezasR()) {
				r.setTotal(r.getTotal()+pr.getCantidad()*pr.getPrecio());
			}			
			r.setFechaPago(new Date());
			t.commit();
			resultado=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	
	public List<Pieza> obtenerPiezas() {
		// TODO Auto-generated method stub
		List<Pieza> resultado = new ArrayList<Pieza>();
		try {
			Query q = conexion.createQuery("from Pieza");
			resultado = q.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public Pieza obtenerPieza(int idPieza) {
		// TODO Auto-generated method stub
		Pieza resultado = null;
		try {
			resultado = conexion.find(Pieza.class, idPieza);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean modificar() {
		// TODO Auto-generated method stub
		boolean resultado=false;
		EntityTransaction t = null;
		try {
			t =  conexion.getTransaction();
			t.begin();			
			t.commit();
			resultado=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Reparacion> obtenerReparaciones(String usuario) {
		// TODO Auto-generated method stub
		List<Reparacion> resultado = new ArrayList<Reparacion>();
		try {
			Query q = conexion.createQuery(
					"from Reparacion where vehiculo.propietario = ?1");
			q.setParameter(1, usuario);
			resultado = q.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Object[]> obtenerVentasMes(int mes) {
		// TODO Auto-generated method stub
		List<Object[]> resultado = new ArrayList<>();
		try {
			Query q = conexion.createQuery(
					"select  clave.pieza.id, clave.pieza.nombre, "
					+ "count(*), avg(cantidad), sum(cantidad*precio) "
					+ "from PiezaReparacion "
					+ "where month(clave.reparacion.fecha)=?1 "
					+ "group by clave.pieza");
			q.setParameter(1, mes);
			resultado = q.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearPieza(Pieza p) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		EntityTransaction t = null;
		try {
			t =  conexion.getTransaction();
			t.begin();		
			conexion.persist(p);
			t.commit();
			resultado=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean modificarPieza(Pieza p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			conexion.getTransaction().begin();
			Query q = conexion.createQuery(
					"update Pieza set stock=?1, precio=?2 "
					+ "where id = ?3");
			q.setParameter(1, p.getStock());
			q.setParameter(2, p.getPrecio());
			q.setParameter(3, p.getId());
			int filas = q.executeUpdate();
			if(filas == 1) {
				resultado=true;
				conexion.getTransaction().commit();
				conexion.clear();
			}
		} catch (Exception e) {
			// TODO: handle exception
			conexion.getTransaction().rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean borrarPR(Pieza p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			EntityTransaction t = conexion.getTransaction();
			t.begin();
			boolean error = false;
			//Si la pieza tiene pr
			if(!p.getPr().isEmpty()) {
				Query q = conexion.createQuery(
						"delete from PiezaReparacion "
						+ "where clave.pieza = ?1");
				q.setParameter(1, p);
				int filas = q.executeUpdate();
				if(filas != p.getPr().size()) {
					t.rollback();
					error=true;
				}
				else {
					conexion.clear();
				}
			}
			if(!error) {
				//Borrar pieza
				//p=obtenerPieza(p.getId());
				conexion.remove(p);
				System.out.println("!!!!" + p);
				t.commit();
				conexion.clear();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			conexion.getTransaction().rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean borrarPR2(Pieza p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			EntityTransaction t = conexion.getTransaction();
			t.begin();
			
			//Si la pieza tiene pr
			if(!p.getPr().isEmpty()) {
				for (int i=0;i<p.getPr().size();i++) {
					//p.getPr().remove(i);
					//p.getPr().get(i).setCantidad(100);
				}
				p.setPr(null);
				t.commit();
			}
	
			//Borrar pieza
			//p=obtenerPieza(p.getId());
			//conexion.remove(p);
			//System.out.println("!!!!" + p);
			//t.commit();
			//conexion.clear();
		
			
		} catch (Exception e) {
			// TODO: handle exception
			conexion.getTransaction().rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	

}
