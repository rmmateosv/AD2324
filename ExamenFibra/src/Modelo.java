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
	private String url="jdbc:mysql://"+aws+":3306/FibraNaval";
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

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public void cerrar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Cliente> obtenerClientes() {
		// TODO Auto-generated method stub
		ArrayList<Cliente> resultado = new ArrayList();
		try {
			Statement consulta = conexion.createStatement();
			ResultSet r = consulta.executeQuery("select * from cliente");
			while(r.next()) {
				Cliente c = new Cliente(r.getInt(1), 
						r.getString(2), r.getString(3), r.getDate(4), 
						r.getString(5), r.getInt(6));
				resultado.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Cliente obtenerCliente(int idC) {
		// TODO Auto-generated method stub
		Cliente resultado=null;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from cliente where idC = ?");
			consulta.setInt(1, idC);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				resultado = new Cliente(r.getInt(1), 
						r.getString(2), r.getString(3), r.getDate(4), 
						r.getString(5), r.getInt(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public ArrayList<Servicio> obtenerServNoContratados(Cliente c) {
		// TODO Auto-generated method stub
		ArrayList<Servicio> resultado=new ArrayList();
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from servicio "
					+ "where idS not in "
					+ "( select servicio from servicioContratado "
					+ "where cliente = ? and fechaBaja is null);");			
			consulta.setInt(1, c.getIdC());
			ResultSet r = consulta.executeQuery();
			while(r.next()) {
				resultado.add(new Servicio(r.getInt(1), r.getString(2), 
						r.getFloat(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
}
