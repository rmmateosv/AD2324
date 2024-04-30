package SKILLS;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
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
					+ "on al.modalidad = mo.id " + "where dni = ?");
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				resultado = new Alumno(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(5), rs.getBoolean(6),
						new Modalidades(rs.getInt(4), rs.getString(8)));
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

			while (datos.next()) {
				resultado.add(new Modalidades(datos.getInt(1), datos.getString(2)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean crearModalidad(Modalidades modalidad) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			CallableStatement CB = conexion.prepareCall("{? = call crearModalidad(?)}");
			CB.registerOutParameter(1, Types.INTEGER);
			CB.setString(2, modalidad.getModalidad());
			CB.executeUpdate();
			modalidad.setId(CB.getInt(1));
			resultado = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public Modalidades obtenerModalidad(int opc) {
		// TODO Auto-generated method stub
		Modalidades resultado = null;
		try {
			PreparedStatement ps = conexion.prepareStatement("Select * from modalidad where id = ?");
			ps.setInt(1, opc);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resultado = new Modalidades(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean crearAlumno(Alumno alumno) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement ps = conexion.prepareStatement("Insert into alumno values(default, ?, ?, ?, 0, false)");
			ps.setString(1, alumno.getDni());
			ps.setString(2, alumno.getNombre());
			ps.setInt(3, alumno.getModalidad().getId());
			int numfilas = ps.executeUpdate();
			if (numfilas == 1) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearPrueba(Prueba prueba) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement ps = conexion.prepareStatement("Insert into prueba values(default, ?, ? ,? ,?)");
			ps.setInt(1, prueba.getModalidad());
			ps.setDate(2, new Date(prueba.getFecha().getTime()));
			ps.setString(3, prueba.getDescripcion());
			ps.setInt(4, prueba.getPuntuacion());
			int numfilas = ps.executeUpdate();
			if (numfilas == 1) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Prueba> obtenerPruebas(int id) {
		// TODO Auto-generated method stub
		ArrayList<Prueba> resultado = new ArrayList();
		try {
			PreparedStatement ps = conexion.prepareStatement("Select * from prueba where modalidad = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultado.add(new Prueba(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getInt(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

}
