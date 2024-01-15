
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	public Paciente obtenerPaciente(int numero, boolean nss) {
		// TODO Auto-generated method stub
		Paciente resultado = null;
		try {
			PreparedStatement consulta = null;
			if(nss) {
				consulta = conexion.prepareStatement(
						"select id, nombre, "
						+ "(datos).telefono, (datos).email, nss, historia "
						+ " from paciente where nss = ?");
			}
			else {
				consulta = conexion.prepareStatement(
						"select id, nombre, "
						+ "(datos).telefono, (datos).email, nss, historia "
						+ " from paciente where id = ?");
			}
			
			consulta.setInt(1, numero);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				//REcuperar un array de un campo de la bd
				//y convertirlo a un ArrayList
				Array historia = r.getArray(6);
				ArrayList<String[]> lista = new ArrayList();
				if(historia!=null) {
					String[][] h = (String[][] ) historia.getArray();					
					for(int i=0;i<h.length;i++) {
						lista.add(h[i]);
					}
				}
				resultado=new Paciente(r.getInt(1), r.getString(2), 
						new Contacto(r.getString(3),r.getString(4)), 
						r.getInt(5), lista);
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

	public Medico obtenerMedico(int numero, boolean colegiado) {
		//Si el parámetro colegiado es true, número es un nº de colegiado
		//Si no, número es el id
		
		// TODO Auto-generated method stub
		Medico resultado = null;
		try {
			PreparedStatement consulta = null;
			if(colegiado) {
				consulta = conexion.prepareStatement(
					"select id, nombre, "
					+ "(datos).telefono, (datos).email, colegiado, especialidad "
					+ " from medico where colegiado = ?");
			}
			else {
				consulta = conexion.prepareStatement(
						"select id, nombre, "
						+ "(datos).telefono, (datos).email, colegiado, especialidad "
						+ " from medico where id = ?");
			}
			consulta.setInt(1, numero);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {			
				resultado=new Medico(r.getInt(1), r.getString(2), 
						new Contacto(r.getString(3),r.getString(4)), 
						r.getInt(5), r.getString(6));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return resultado;
	}

	public ArrayList<Persona> obtenerPersonas() {
		// TODO Auto-generated method stub
		ArrayList<Persona> resultado =new ArrayList();
		PreparedStatement consulta;
		try {
			consulta = conexion.prepareStatement(
					"select id, nombre, "
					+ "(datos).telefono, (datos).email "
					+ " from persona");
			ResultSet r = consulta.executeQuery();
			while(r.next()) {			
				resultado.add(new Persona(r.getInt(1), r.getString(2), 
						new Contacto(r.getString(3),r.getString(4))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public Persona obtenerPersona(int id) {
		// TODO Auto-generated method stub
		Persona resultado =null;
		
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select id, nombre, "
					+ "(datos).telefono, (datos).email "
					+ " from persona where id=?");
			consulta.setInt(1, id);
			ResultSet r = consulta.executeQuery();
			while(r.next()) {			
				resultado = new Persona(r.getInt(1), r.getString(2), 
						new Contacto(r.getString(3),r.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean modificarContacto(Persona p) {
		// TODO Auto-generated method stub
		boolean resultado =false;
		
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"update persona set datos=(?,?) "
					+ "where id = ?");
			consulta.setString(1, p.getContacto().getTelefono());
			consulta.setString(2, p.getContacto().getEmail());
			consulta.setInt(3, p.getId());
			int r = consulta.executeUpdate();
			if(r==1) {
				resultado=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Medico> obtenerMedicos() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ArrayList<Medico> resultado = new ArrayList();
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select id, nombre, "
					+ "(datos).telefono, (datos).email, colegiado, especialidad "
					+ " from medico");
			ResultSet r = consulta.executeQuery();
			while(r.next()) {			
				resultado.add(new Medico(r.getInt(1), r.getString(2), 
						new Contacto(r.getString(3),r.getString(4)), 
						r.getInt(5),r.getString(6)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return resultado;
	}

	public ArrayList<Paciente> obtenerPacientes() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ArrayList<Paciente> resultado = new ArrayList();
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select id, nombre, "
					+ "(datos).telefono, (datos).email, nss, historia "
					+ " from paciente");
			ResultSet r = consulta.executeQuery();
			while(r.next()) {
				//REcuperar un array de un campo de la bd
				//y convertirlo a un ArrayList
				Array historia = r.getArray(6);
				ArrayList<String[]> lista = new ArrayList();
				if(historia!=null) {
					String[][] h = (String[][] ) historia.getArray();					
					for(int i=0;i<h.length;i++) {
						lista.add(h[i]);
					}
				}
				resultado.add(new Paciente(r.getInt(1), r.getString(2), 
						new Contacto(r.getString(3),r.getString(4)), 
						r.getInt(5), lista));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearConsulta(Consulta c) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"insert into consulta values (default, ?,?,?,null)",Statement.RETURN_GENERATED_KEYS);
			consulta.setInt(1, c.getPaciente().getNss());
			consulta.setInt(2, c.getMedico().getColegiado());
			consulta.setDate(3, new Date(c.getFecha().getTime()));
			int r = consulta.executeUpdate();
			if(r==1) {
				ResultSet ids = consulta.getGeneratedKeys();
				if(ids.next()) {
					c.setId(ids.getInt(1));
				}
				resultado = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Consulta> obtenerConsultas() {
		// TODO Auto-generated method stub
		ArrayList<Consulta> resultado = new ArrayList();
		try {
			//CAMBIAR ESTE SELECT POR UN JOIN DE 3 TABLAS
			//INEFICIENTE PORQUE EJECUTAMOS 3 CONSULTAS EN VEZ DE 1 CON LOS JOIN
			PreparedStatement consulta = conexion.prepareStatement(
					"select * "
					+ " from consulta ");
			ResultSet r = consulta.executeQuery();
			while(r.next()) {
				//CAMBIAR - CON JOIN SOBRA ESTAS DOS LÍNEAS
				Paciente p = obtenerPaciente(r.getInt(2),true);
				Medico m = obtenerMedico(r.getInt(3),true);
				//-----
				resultado.add(new Consulta(r.getInt(1), 
						m, 
						p, 
						r.getDate(4), 
						r.getString(5)));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public Consulta obtenerConsulta(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Consulta resultado = null;
		try {
			//CAMBIAR ESTE SELECT POR UN JOIN DE 3 TABLAS
			//INEFICIENTE PORQUE EJECUTAMOS 3 CONSULTAS EN VEZ DE 1 CON LOS JOIN
			PreparedStatement consulta = conexion.prepareStatement(
					"select * "
					+ " from consulta where  id = ?");
			consulta.setInt(1, id);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				//CAMBIAR - CON JOIN SOBRA ESTAS DOS LÍNEAS
				Paciente p = obtenerPaciente(r.getInt(2),true);
				Medico m = obtenerMedico(r.getInt(3),true);
				//-----
				resultado = new Consulta(r.getInt(1), 
						m, 
						p, 
						r.getDate(4), 
						r.getString(5));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean registrarDiagnostico(Consulta c) {
		// TODO Auto-generated method stub
		boolean resultado = false;		
		try {
			conexion.setAutoCommit(false);
			PreparedStatement consulta = conexion.prepareStatement(
					"update consulta set diagnostico = ? where id = ?");
			consulta.setString(1, c.getDiagnostico());
			consulta.setInt(2, c.getId());
			int r = consulta.executeUpdate();
			if(r==1) {
				//Actualizar el array del paciente
				//Si el paciente no tiene historial, acutalizamos el campo
				//historia con un nuevo array de un elemento
				if(c.getPaciente().getHistoria().isEmpty()) {
					consulta = conexion.prepareStatement(
							"update paciente set historia = array[array[?,?]] where id = ?");
					consulta.setString(1, new java.util.Date().toString());
					consulta.setString(2, c.getDiagnostico());
					consulta.setInt(3, c.getPaciente().getId());
					r = consulta.executeUpdate();
					if(r==1) {
						conexion.commit();
					}
					else {
						conexion.rollback();
					}
				}
				else {
					//Si el paciente tiene historial, acutalizamos el campo
					//historia concatenando al array un nuevo elemento
					consulta = conexion.prepareStatement(
							"update paciente set historia = "
							+ "array_cat(historia,array[?,?]::text[][]) where id = ?");
					consulta.setString(1, new java.util.Date().toString());
					consulta.setString(2, c.getDiagnostico());
					consulta.setInt(3, c.getPaciente().getId());
					r = consulta.executeUpdate();
					if(r==1) {
						conexion.commit();
					}
					else {
						conexion.rollback();
					}
				}
				
				
			}
			else {
				conexion.rollback();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean borrarPersona(Persona p, Medico m, Paciente pa) {
		// TODO Auto-generated method stub
		boolean resultado = false;

			try {
				conexion.setAutoCommit(false);
				//Borrar consultas
				PreparedStatement consulta = null;
				if(m!=null) {
					consulta = conexion.prepareStatement(
							"delete from consulta where "
							+ "medico = ?");
					consulta.setInt(1, m.getColegiado());
				}
				else {
					consulta = conexion.prepareStatement(
							"delete from consulta where "
							+ "paciente = ?");
					consulta.setInt(1, pa.getNss());
				}
				consulta.executeUpdate();
											
				//Al haber herencia, se borra de la tabla padre e hija
				consulta = conexion.prepareStatement(
						"delete from persona where id =?");
				consulta.setInt(1, p.getId());
				int r = consulta.executeUpdate();
				if(r==1) {
					conexion.commit();
					resultado=true;
				}
			} catch (SQLException e) {
				try {
					conexion.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return resultado;
	}

	
}
