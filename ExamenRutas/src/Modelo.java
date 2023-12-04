import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
					+ " on r.paraje=p.id  where id = ?");
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
					int filas = 
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			conexion.rollback();
			e.printStackTrace();
		}
		return resultado;
	}
	
}
