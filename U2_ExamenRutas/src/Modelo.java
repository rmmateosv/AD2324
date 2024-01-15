import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class Modelo {
	
	private String aws = "servidordam.crjbea6rbpvt.us-east-1.rds.amazonaws.com";
	private String url="jdbc:mysql://"+aws+":3306/rutas";
	private String us="admindam";
	private String ps="admindam";
	private Connection conexion = null;
	
	public Modelo() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion=DriverManager.getConnection(url,us,ps);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

	public ArrayList<Paraje> obtenerParajes() {
		// TODO Auto-generated method stub
		ArrayList<Paraje> resultado = new ArrayList();
		try {
			Statement consulta = conexion.createStatement();
			ResultSet r = consulta.executeQuery("select * from paraje");
			while(r.next()) {
				Paraje p = new Paraje(r.getInt(1), r.getString(2), r.getInt(3));
				resultado.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public Paraje obtenerParaje(int idP) {
		// TODO Auto-generated method stub
		Paraje resultado = null;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from paraje where id = ?");
			consulta.setInt(1, idP);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				resultado = new Paraje(r.getInt(1), r.getString(2), r.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean crearRuta(Ruta r) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta  = conexion.prepareStatement(
					"insert into ruta values (default,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			consulta.setInt(1, r.getParaje().getId());
			consulta.setString(2, r.getColor());
			consulta.setDate(3, new Date(r.getFecha().getTime()));
			consulta.setInt(4, r.getDuracion());
			if(consulta.executeUpdate()==1) {
				resultado=true;
				ResultSet ids = consulta.getGeneratedKeys();
				if(ids.next()) {
					r.setId(ids.getInt(1));
				}				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Ruta> obtenerRutas() {
		// TODO Auto-generated method stub
		ArrayList<Ruta> resultado = new ArrayList();
		try {
			Statement consulta = conexion.createStatement();
			ResultSet r = consulta.executeQuery("select * from ruta r join paraje  p "
					+ "                            on r.paraje=p.id");
			while(r.next()) {
				Ruta ru = new Ruta(r.getInt(1), 
						new Paraje(r.getInt(6),r.getString(7),r.getInt(8)), 
						r.getString(3), 
						r.getDate(4),r.getInt(5));
				resultado.add(ru);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Ruta obtenerRuta(int idR) {
		// TODO Auto-generated method stub
		Ruta resultado = null;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from ruta r join paraje  p"
					+ " on r.paraje=p.id  where r.id = ?");
			consulta.setInt(1, idR);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				resultado = new Ruta(r.getInt(1), 
						new Paraje(r.getInt(6),r.getString(7),r.getInt(8)), 
						r.getString(3), 
						r.getDate(4),r.getInt(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public ArrayList<Lugar> obtenerLugares(Paraje paraje) {
		// TODO Auto-generated method stub
		ArrayList<Lugar> resultado = new ArrayList();
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from lugar l join paraje p on l.paraje = p.id "
					+ "join municipio m on l.municipio = m.id "
					+ "where l.paraje = ?");
			consulta.setInt(1, paraje.getId());
			ResultSet r = consulta.executeQuery();
			while(r.next()) {
				Lugar l = new Lugar(r.getInt(1), r.getString(2), 
						new Paraje(r.getInt(5), r.getString(6), r.getInt(7)), 
						new Municipio(r.getInt(8), r.getString(9), r.getString(10)));
				resultado.add(l);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public Lugar obtenerLugar(int idLugar) {
		// TODO Auto-generated method stub
		Lugar resultado = null;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from lugar l join paraje p on l.paraje = p.id "
					+ "join municipio m on l.municipio = m.id "
					+ "where l.id = ?");
			consulta.setInt(1, idLugar);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				resultado = new Lugar(r.getInt(1), r.getString(2), 
						new Paraje(r.getInt(5), r.getString(6), r.getInt(7)), 
						new Municipio(r.getInt(8), r.getString(9), r.getString(10)));				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearLugaresRuta(Ruta r, ArrayList<Lugar> lugares) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		try {
			//Iniciar transacción
			conexion.setAutoCommit(false);
			for (Lugar l : lugares) {
				//Comprobar si el lugar no está ya en la ruta
				if(!existeLugarEnRuta(r,l)){
					PreparedStatement consulta = conexion.prepareStatement(
							"insert into ruta_lugar values (?,?)");
					consulta.setInt(1, r.getId());
					consulta.setInt(2, l.getId());
					int filas = consulta.executeUpdate();
					if(filas==0) {
						conexion.rollback();
						return false;
					}					
				}
				else {
					System.out.println("Lugar "+l.getId()+"ya se ha añadido, se ignora");
				}
			}
			conexion.commit();
			resultado=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return resultado;
	}

	private boolean existeLugarEnRuta(Ruta r, Lugar l) {
		// TODO Auto-generated method stub
		boolean resultado  = false;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"SELECT * from ruta_lugar where ruta = ? and lugar = ?");
			consulta.setInt(1, r.getId());
			consulta.setInt(2, l.getId());
			ResultSet rs = consulta.executeQuery();
			if(rs.next()) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean modificarRuta(Ruta r, int d) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"update ruta set duracion = ? where id = ?");
			consulta.setInt(1, d);
			consulta.setInt(2, r.getId());
			int filas = consulta.executeUpdate();
			if(filas==1) {
				resultado=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Municipio> obtenerMunicipios() {
		// TODO Auto-generated method stub
		ArrayList<Municipio> resultado = new ArrayList();
		try {
			Statement consulta = conexion.createStatement();
			ResultSet r = consulta.executeQuery("select * from municipio");
			while(r.next()) {
				Municipio m = new Municipio(r.getInt(1),r.getString(2),r.getString(3));
				resultado.add(m);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public Municipio obtenerMunicipio(int idM) {
		// TODO Auto-generated method stub
		Municipio resultado = null;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from municipio where id = ?");
			consulta.setInt(1, idM);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				resultado = new Municipio(r.getInt(1),r.getString(2),r.getString(3));				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public int crearLugar(int p, int m, String nombre) {
		// TODO Auto-generated method stub
		int resultado = 0;
		try {
			CallableStatement consulta = conexion.prepareCall(
					"{? = call crear_lugar(?,?, ?)}");
			consulta.setString(2, nombre);
			consulta.setInt(3, p);
			consulta.setInt(4, m);
			
			consulta.registerOutParameter(1, Types.INTEGER);
			
			consulta.executeUpdate();
			//Recuperar el valor de retorno de la función
			return consulta.getInt(1);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Lugar> obtenerLugares() {
		// TODO Auto-generated method stub
		ArrayList<Lugar> resultado = new ArrayList();
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from lugar l join paraje p on l.paraje = p.id "
					+ "join municipio m on l.municipio = m.id ");
			ResultSet r = consulta.executeQuery();
			while(r.next()) {
				Lugar l = new Lugar(r.getInt(1), r.getString(2), 
						new Paraje(r.getInt(5), r.getString(6), r.getInt(7)), 
						new Municipio(r.getInt(8), r.getString(9), r.getString(10)));
				resultado.add(l);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Ruta> obtenerRutas(Lugar l) {
		// TODO Auto-generated method stub
		ArrayList<Ruta> resultado = new ArrayList();
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from ruta r join paraje  p "
					+ "  on r.paraje=p.id "
					+ "inner join ruta_lugar rl on rl.ruta =r.id "
					+ "where rl.lugar = ?");
			
			consulta.setInt(1, l.getId());
			ResultSet r = consulta.executeQuery();
			while(r.next()) {
				Ruta ru = new Ruta(r.getInt(1), 
						new Paraje(r.getInt(6),r.getString(7),r.getInt(8)), 
						r.getString(3), 
						r.getDate(4),r.getInt(5));
				resultado.add(ru);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean borrarLugar(Lugar l, ArrayList<Ruta> rutas) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		try {
			conexion.setAutoCommit(false);
			PreparedStatement consulta;
			if(!rutas.isEmpty()) {
				consulta = conexion.prepareStatement(
					"delete from ruta_lugar where lugar = ?");
				consulta.setInt(1, l.getId());
				int filas = consulta.executeUpdate();
				if(filas<=0) {
					return false;					
				}
			}
			//borrar el lugar
			consulta = conexion.prepareStatement(
					"delete from lugar where id = ?");
				consulta.setInt(1, l.getId());
				int filas = consulta.executeUpdate();
				if(filas==1) {
					conexion.commit();
					return true;					
				}
				else{
					conexion.rollback();
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return resultado;
	}
	
}
