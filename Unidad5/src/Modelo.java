import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Modelo {
	private final String url="jdbc:postgresql://localhost:5432/hospital";
	private final String us="postgres";
	private final String ps="postgres";
	
	private Connection conexion=null;

	public Modelo() {
		try {
			Class.forName("org.postgresql.Driver");
			conexion=DriverManager.getConnection(url,us,ps);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
	
	
}
