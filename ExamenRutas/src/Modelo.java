import java.sql.Connection;
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
			consulta.setInt(1, r.getParaje());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
}
