package SKILLS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Modelo {

	private Connection conexion = null;
	private final String URL = "jdbc:mysql://localhost:3307/skills";
	private final String US = "root";
	private final String PS = "root";
	
	public Modelo() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(URL, US, PS);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

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
		if (conexion != null) {
			try {
				conexion.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public Alumno obtenerAlumno(String dni) {
		
		Alumno resultado = null;
		
		try {
			PreparedStatement ps = conexion.prepareStatement("select * from alumno as al inner join modalidad as mo "
					+ "on al.modalidad = mo.id "
					+ "where dni = ?");
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				resultado = new Alumno(rs.getInt(1), dni, rs.getInt(4), rs.getBoolean(5),new Modalidades(rs.getInt(3),rs.getString(7)));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public ArrayList<Modalidades> obtenerModalidades() {
		
		ArrayList<Modalidades> resultado = new ArrayList();
		
		try {
			Statement st = conexion.createStatement();
			ResultSet datos = st.executeQuery("select * from modalidad");
			
			while(datos.next()){
				resultado.add(new Modalidades(datos.getInt(1), datos.getString(2)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}
	
	
	
}
