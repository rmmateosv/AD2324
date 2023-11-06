package taller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Modelo {
	private Connection conexion;
	
	final String URL="jdbc:mysql://127.0.0.1:3307/tallerDAM";
	final String USUARIO="root";
	final String PS="root";

	public Modelo() {		
		try {
			//Cargar la clase con el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion= DriverManager.getConnection(URL,USUARIO,PS);
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
		if(conexion!=null) {
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
