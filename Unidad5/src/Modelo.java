import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

	public boolean crearPaciente(Paciente pa) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"insert into paciente values(default,?,(?,?),?,null)");
			consulta.setString(1, pa.getNombre());
			consulta.setString(2, pa.getContacto().getTelefono());
			consulta.setString(3, pa.getContacto().getEmail());
			consulta.setInt(4, pa.getNss());
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

	public Paciente obtenerPaciente(int nss) {
		// TODO Auto-generated method stub
		Paciente resultado = null;
		PreparedStatement consulta = conexion.prepareStatement(ps);
		return resultado;
	}
	
	
}
