
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;

public class Modelo {
	private final String url = "jdbc:postgresql://localhost:5432/hospital";
	private final String us = "postgres";
	private final String ps = "postgres";

	private Connection conexion = null;

	public Modelo() {
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection(url, us, ps);
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
			PreparedStatement consulta = conexion
					.prepareStatement("insert into paciente values(default,?,(?,?),?,null)");
			consulta.setString(1, pa.getNombre());
			consulta.setString(2, pa.getContacto().getTelefono());
			consulta.setString(3, pa.getContacto().getEmail());
			consulta.setInt(4, pa.getNss());
			int filas = consulta.executeUpdate();
			if (filas == 1) {
				resultado = true;
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
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select id, nombre, "
					+ "(datos).telefono, (datos).email, historia "
					+ " from paciente where nss = ?");
			consulta.setInt(1, nss);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				//REcuperar un array de un campo de la bd
				//y convertirlo a un ArrayList
				Array historia = r.getArray(5);
				String[][] h = (String[][] ) historia.getArray();
				ArrayList<String[]> lista = new ArrayList();
				for(int i=0;i<h.length;i++) {
					lista.add(h[i]);
				}
				
				resultado=new Paciente(r.getInt(1), r.getString(2), 
						new Contacto(r.getString(3),r.getString(4)), 
						nss, lista);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearMedico(Medico m) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta = conexion.prepareStatement("insert into medico values(default,?,(?,?),?,?)");
			consulta.setString(1, m.getNombre());
			consulta.setString(2, m.getContacto().getTelefono());
			consulta.setString(3, m.getContacto().getEmail());
			// consulta.setObject(2, m.getContacto(),Types.OTHER);
			consulta.setInt(4, m.getColegiado());
			consulta.setString(5, m.getEspecialidad());
			int filas = consulta.executeUpdate();
			if (filas == 1) {
				resultado = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public Medico obtenerMedico(int nc) {
		// TODO Auto-generated method stub
		Medico resultado = null;
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return resultado;
	}
}
