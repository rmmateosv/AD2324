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
	final String URL = "jdbc:postgresql://postgres.cuiqyq8gvjr4.us-east-1.rds.amazonaws.com/skill";
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
				PreparedStatement ps = conexion
						.prepareStatement("SELECT id, fecha, (info).titulo, (info).descripcion, puntuacion "
								+ "FROM prueba WHERE modalidad = ?");
				ps.setInt(1, m.getId());
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					resultado.add(new Prueba(rs.getInt(1), m.getId(), rs.getDate(2),
							new Infoprueba(rs.getString(3), rs.getString(4)), rs.getInt(5)));
				}
			} else {
				Statement s = conexion.createStatement();
				ResultSet rs = s
						.executeQuery("SELECT id, fecha, (info).titulo, (info).descripcion, puntuacion, modalidad"
								+ "	FROM prueba");
				while (rs.next()) {
					resultado.add(new Prueba(rs.getInt(1), rs.getInt(6), rs.getDate(2),
							new Infoprueba(rs.getString(3), rs.getString(4)), rs.getInt(5)));
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
			PreparedStatement ps = conexion
					.prepareStatement("SELECT id, fecha, (info).titulo, (info).descripcion, puntuacion, modalidad"
							+ "	FROM prueba WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resultado = new Prueba(rs.getInt(1), rs.getInt(6), rs.getDate(2),
						new Infoprueba(rs.getString(3), rs.getString(4)), rs.getInt(5));
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
			PreparedStatement ps = conexion
					.prepareStatement("UPDATE prueba SET fecha = ?, puntuacion = ?, info = (?, ?) WHERE id = ?");
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

	public ArrayList<Alumno> obtenerAlumnos() {

		ArrayList<Alumno> resultado = new ArrayList<Alumno>();

		try {
			Statement st = conexion.createStatement();

			ResultSet rs = st.executeQuery("select * from alumno as a inner join modalidad as m on a.modalidad = m.id");

			while (rs.next()) {
				ArrayList<String[]> datosB = new ArrayList<String[]>();

				if (rs.getArray(7) != null) {
					String[][] datos = (String[][]) rs.getArray(7).getArray();

					datosB = new ArrayList<String[]>();
					for (String[] c : datos) {
						datosB.add(c);
					}
				} 

				resultado.add(new Alumno(rs.getInt(1), rs.getString(2), rs.getString(3),
						new Modalidad(rs.getInt(4), rs.getString(9)), rs.getInt(5), rs.getBoolean(6), datosB));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public Alumno obtenerAlumno(int id) {
		Alumno resultado = null;

		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select * from alumno as a inner " + "join modalidad as m on a.modalidad = m.id where a.id = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				ArrayList<String[]> datosB = new ArrayList<String[]>();

				if (rs.getArray(7) != null) {
					String[][] datos = (String[][]) rs.getArray(7).getArray();

					datosB = new ArrayList<String[]>();
					for (String[] c : datos) {
						datosB.add(c);
					}
				} 

				resultado = new Alumno(rs.getInt(1), rs.getString(2), rs.getString(3),
						new Modalidad(rs.getInt(4), rs.getString(9)), rs.getInt(5), rs.getBoolean(6), datosB);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean anadirCorrecion(Alumno al, Prueba p, String comentario, int puntuacion) {

		boolean resultado = false;
		PreparedStatement ps = null;

		try {
			if (al.getCorreccion().size() > 0) {
				ps = conexion.prepareStatement("update alumno set "
						+ "correccion = array_cat(correccion, array[?,?,?]::text[][]) where id = ?");

			} else {
				ps = conexion.prepareStatement("update alumno set correccion = array[array[?,?,?]] where id = ?");

			}
			ps.setString(1, String.valueOf(p.getId()));
			ps.setString(2, String.valueOf(puntuacion));
			ps.setString(3, String.valueOf(comentario));
			ps.setInt(4, al.getId());

			if (ps.executeUpdate() == 1) {
				resultado = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean actualizarPuntuacionAlumno(Alumno al, int puntuacion) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		PreparedStatement ps = null;

		try {
			ps = conexion.prepareStatement("update alumno set puntuacion = puntuacion + ?  where id = ?");
			ps.setInt(1, puntuacion);
			ps.setInt(2, al.getId());

			if (ps.executeUpdate() == 1) {
				resultado = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean finalizarCorrecionAlumno(Alumno al) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		PreparedStatement ps = null;

		try {
			ps = conexion.prepareStatement("update alumno set finalizado = true  where id = ?");
			ps.setInt(1, al.getId());

			if (ps.executeUpdate() == 1) {
				resultado = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public ArrayList<Alumno> obtenerAlumnosModalidad(int modalidad) {
		// TODO Auto-generated method stub
		ArrayList<Alumno> resultado = new ArrayList<Alumno>();

		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select * from alumno as a inner " + "join modalidad as m on a.modalidad = m.id where a.modalidad = ?");
			ps.setInt(1, modalidad);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ArrayList<String[]> datosB = new ArrayList<String[]>();

				if (rs.getArray(7) != null) {
					String[][] datos = (String[][]) rs.getArray(7).getArray();

					datosB = new ArrayList<String[]>();
					for (String[] c : datos) {
						datosB.add(c);
					}
				} 

				resultado.add( new Alumno(rs.getInt(1), rs.getString(2), rs.getString(3),
						new Modalidad(rs.getInt(4), rs.getString(9)), rs.getInt(5), rs.getBoolean(6), datosB));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean borrarPrueba(Prueba p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		PreparedStatement ps = null;

		try {
			ps = conexion.prepareStatement("delete from prueba where id = ?");
			ps.setInt(1, p.getId());

			if (ps.executeUpdate() == 1) {
				resultado = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public int obtenerNotaMax(int id) {
		// TODO Auto-generated method stub
		int resultado=0;
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select max(puntuacion) from alumno where modalidad = ?");
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

	public ArrayList<Alumno> obtenerAlumnosNota(int id, int notaMax) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ArrayList<Alumno> resultado = new ArrayList<Alumno>();

		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select * from alumno as a inner " + "join modalidad as m on a.modalidad = m.id where a.modalidad = ? and puntuacion = ?");
			ps.setInt(1, id);
			ps.setInt(2, notaMax);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ArrayList<String[]> datosB = new ArrayList<String[]>();

				if (rs.getArray(7) != null) {
					String[][] datos = (String[][]) rs.getArray(7).getArray();

					datosB = new ArrayList<String[]>();
					for (String[] c : datos) {
						datosB.add(c);
					}
				} 
				resultado.add( new Alumno(rs.getInt(1), rs.getString(2), rs.getString(3),
						new Modalidad(rs.getInt(4), rs.getString(9)), rs.getInt(5), rs.getBoolean(6), datosB));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
}
