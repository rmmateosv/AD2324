package skills;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Modelo {
	final String URL = "jdbc:postgresql://localhost:5432/Skills";
	final String USR = "postgres";
	final String PS = "postgres";

	private Connection conexion = null;

	public Modelo() {
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection(URL, USR, PS);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cerrar() {
		try {
			conexion.close();
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

	public int obtenerPuntuacion(int id) {
		// TODO Auto-generated method stub
		int resultado = 0;
		try {
			PreparedStatement ps = conexion.prepareStatement("SELECT SUM (puntuacion) FROM prueba WHERE modalidad = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resultado = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearPrueba(Prueba p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO prueba VALUES(default, ?, ?, (?, ?), ?)");
			ps.setInt(1, p.getModalidad());
			ps.setDate(2, new Date(p.getFecha().getTime()));
			ps.setString(3, p.getInfo().getTitulo());
			ps.setString(4, p.getInfo().getDescripcion());
			ps.setInt(5, p.getPuntuacion());
			int filas = ps.executeUpdate();
			if (filas == 1) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Prueba> obtenerPruebas(Modalidad m) {
		// TODO Auto-generated method stub
		ArrayList<Prueba> resultado = new ArrayList();
		try {
			if (m != null) {
				PreparedStatement ps = conexion.prepareStatement("SELECT id, fecha, (info).titulo, (info).descripcion, puntuacion "
						+ "FROM prueba WHERE modalidad = ?");
				ps.setInt(1, m.getId());
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					resultado.add(new Prueba(rs.getInt(1), m.getId(), rs.getDate(2), new Infoprueba(rs.getString(3), rs.getString(4)), rs.getInt(5)));
				}
			} else {
				Statement s = conexion.createStatement();
				ResultSet rs = s.executeQuery("SELECT id, fecha, (info).titulo, (info).descripcion, puntuacion, modalidad"
						+ "	FROM prueba");
				while (rs.next()) {
					resultado.add(new Prueba(rs.getInt(1), rs.getInt(6),rs.getDate(2), new Infoprueba(rs.getString(3), rs.getString(4)), rs.getInt(5)));
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public ArrayList<Modalidad> obtenerModalidades() {
		// TODO Auto-generated method stub
		ArrayList<Modalidad> resultado = new ArrayList();
		try {
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM modalidad");
			while (rs.next()) {
				resultado.add(new Modalidad(rs.getInt(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Modalidad obtenerModalidad(int id) {
		Modalidad resultado = null;
		try {
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM modalidad WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resultado = new Modalidad(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Prueba obtenerPrueba(int id) {
		// TODO Auto-generated method stub
		Prueba resultado = null;
		try {
			PreparedStatement ps = conexion.prepareStatement("SELECT id, fecha, (info).titulo, (info).descripcion, puntuacion, modalidad"
					+ "	FROM prueba WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resultado = new Prueba(rs.getInt(1), rs.getInt(6),rs.getDate(2), new Infoprueba(rs.getString(3), rs.getString(4)), rs.getInt(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean modificarPrueba(Prueba p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement ps = conexion.prepareStatement("UPDATE prueba SET fecha = ?, puntuacion = ?, info = (?, ?) WHERE id = ?");
			ps.setDate(1, new Date(p.getFecha().getTime()));
			ps.setInt(2, p.getPuntuacion());
			ps.setString(3, p.getInfo().getTitulo());
			ps.setString(4, p.getInfo().getDescripcion());
			ps.setInt(5, p.getId());
			if (ps.executeUpdate() == 1) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
}
