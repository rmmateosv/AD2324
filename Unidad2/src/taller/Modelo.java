package taller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;





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

	public void infoBD() {
		// TODO Auto-generated method stub
		
		try {
			//Obtener metadatos del servidor
			DatabaseMetaData md =conexion.getMetaData();
			System.out.println("--- METADATOS SERVIDOR ---");
			System.out.println("Tipo de SGBD:" + md.getDatabaseProductName());
			System.out.println("Versión de SGBD:" + md.getDatabaseProductVersion());
			System.out.println("URL BD:"+md.getURL());
			System.out.println("Usuario:"+md.getUserName());
			System.out.println("--- TABLAS ----------- ---");
			ResultSet tablas = md.getTables("tallerdam", null, null, null);
			while(tablas.next()) {
				System.out.println("\tCátalogo:"+tablas.getString(1)+
						"\tEsquema:"+tablas.getString(2)+
						"\tTabla:"+ tablas.getString(3) + 
						"\tTipo:"+tablas.getString(4));
			}
			System.out.println("--- ------------------ ---");
			System.out.println("--- METADATOS tabla Usuarios ---");
			Statement st = conexion.createStatement();
			ResultSet r = st.executeQuery("select * from usuarios");
			ResultSetMetaData mdC = r.getMetaData();
			System.out.println("Nº de columnas:"+ mdC.getColumnCount());
			for(int i=1;i<=mdC.getColumnCount();i++) {
				System.out.println("Columna:"+ mdC.getColumnName(i)+
						"\t"+mdC.getColumnTypeName(i)+ 
						"\t"+mdC.getPrecision(i) + 
						"\tNulos:"+mdC.isNullable(i)+
						"\tAuotIncrement:"+mdC.isAutoIncrement(i));
			}
			
			
			
			//Obtener metados de una consulta
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public Usuario obtenerUsuario(String us, String ps) {
		// TODO Auto-generated method stub
		Usuario resultado = null;
		//Crear consulta para comprobar usuario y ps
		try {
			//Declarar la consulta
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from usuarios where "
					+ "usuario = ? and ps = sha2(?,512)");
			//REllenar parámetros
			consulta.setString(1, us);
			consulta.setString(2, ps);
			//Ejecutamos
			ResultSet r =consulta.executeQuery();
			//Comprobar si el cursor tiene datos
			if(r.next()) {
				//Usuario enontrado
				//resultado = new Usuario(r.getInt("id"), r.getString("nombre"), r.getString("perfil"));
				resultado = new Usuario(r.getInt(1), r.getString(2), r.getString(4));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Usuario obtenerUsuario(String dni) {
		// TODO Auto-generated method stub
		Usuario resultado = null;
		
		try {
			PreparedStatement st = conexion.prepareStatement(
					"select * from usuarios where usuario = ?");
			st.setString(1, dni);
			ResultSet r = st.executeQuery();
			if(r.next()) {
				resultado = new Usuario(r.getInt(1), 
						r.getString(2), r.getString(4));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearUsuario(Usuario u) {
		boolean resultado = false;
		// TODO Auto-generated method stub
		try {
			//Consulta que no devuelve autoincrementable geneardo
			/*PreparedStatement ps = conexion.prepareStatement(
					"insert into usuarios(usuario,ps,perfil) "
					+ "values(?,sha2(?,512),?)");*/
			
			//Consulta que devuelve el autoincrementable geneardo
			PreparedStatement ps = conexion.prepareStatement(
					"insert into usuarios(usuario,ps,perfil) "
					+ "values(?,sha2(?,512),?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, u.getUsuario());
			ps.setString(2, u.getUsuario());
			ps.setString(3, u.getPerfil());
			int filas = ps.executeUpdate();
			if(filas==1) {
				resultado =true;
				//REcuperar el id del usuario creado
				ResultSet r = ps.getGeneratedKeys();
				if(r.next()) {
					u.setId(r.getInt(1));
				}
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean cambiarPS(Usuario u, String ps2) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"update usuarios set ps = sha2(?,512) where id = ?");
			ps.setString(1, ps2);
			ps.setInt(2, u.getId());			
			int filas = ps.executeUpdate();
			if(filas==1) {
				resultado =true;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Vehiculo> obtenerVehiculos() {
		// TODO Auto-generated method stub
		ArrayList<Vehiculo> resultado = new ArrayList<Vehiculo>();
		Statement st;
		try {
			st = conexion.createStatement();
			ResultSet datos = st.executeQuery("select * from vehiculos");
			while(datos.next()) {
				Vehiculo v = new Vehiculo(datos.getString(1),
						datos.getString(2),datos.getString(3));
				resultado.add(v);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public Vehiculo obtenerVehiculo(String m) {
		// TODO Auto-generated method stub
		Vehiculo resultado = null;
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select * from vehiculos where matricula = ?");
			ps.setString(1, m);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				resultado=new Vehiculo(m, rs.getString(2), rs.getString(3));
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearVehiculo(Vehiculo v) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"insert into vehiculos values(?,?,?)");
			ps.setString(1, v.getMatricula());
			ps.setString(2, v.getPropietario());
			ps.setString(3, v.getTelf());
			int filas = ps.executeUpdate();
			if(filas==1) {
				resultado =true;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearReparacion(Reparacion r) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"insert into reparacion values(default,?,?,?,null,0,0,0)");
			ps.setDate(1, new Date(r.getFecha().getTime()));
			ps.setString(2, r.getVehiculo());
			ps.setInt(3, r.getUsuario());
			int filas = ps.executeUpdate();
			if(filas==1) {
				resultado =true;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Reparacion> obtenerReparaciones() {
		// TODO Auto-generated method stub
		ArrayList<Reparacion> resultado = new ArrayList<Reparacion>();
		Statement st;
		try {
			st = conexion.createStatement();
			ResultSet datos = st.executeQuery("select * from reparacion");
			while(datos.next()) {
				Reparacion r = new Reparacion(datos.getInt(1), 
						datos.getDate(2), datos.getString(3), datos.getInt(4),
						datos.getDate(5),datos.getFloat(6),
						datos.getFloat(7),datos.getFloat(8));
				resultado.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public Reparacion obtenerReparacion(int codigo) {
		// TODO Auto-generated method stub
		Reparacion resultado = null;
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select * from reparacion where id = ?");
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				resultado=new Reparacion(rs.getInt(1), 
						rs.getDate(2), rs.getString(3), rs.getInt(4));
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Pieza> obtenerPiezas() {
		// TODO Auto-generated method stub
		ArrayList<Pieza> resultado = new ArrayList<Pieza>();
		Statement st;
		try {
			st = conexion.createStatement();
			ResultSet datos = st.executeQuery("select * from piezas");
			while(datos.next()) {
				Pieza p = new Pieza(datos.getInt(1), datos.getString(2), 
						datos.getInt(3), datos.getFloat(4));
				resultado.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public Pieza obtenerPieza(int idP) {
		// TODO Auto-generated method stub
		Pieza resultado = null;
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select * from piezas where id = ?");
			ps.setInt(1, idP);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				resultado=new Pieza(rs.getInt(1), rs.getString(2), 
						rs.getInt(3), rs.getFloat(4));;
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public PiezaReparacion obtenerPiezaRep(Reparacion r, Pieza p) {
		// TODO Auto-generated method stub
		PiezaReparacion resultado = null;
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select * from piezareparacion where reparacion = ? and "
					+ "pieza = ?");
			ps.setInt(1, r.getId());
			ps.setInt(2, p.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				resultado=new PiezaReparacion(rs.getInt(1), rs.getInt(2), 
						rs.getInt(3), rs.getFloat(4));
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean insertarPiezaReparacion(PiezaReparacion pr) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			//Insert en piezareapacion
			//Update en pieza para actualizar el stock
			//=> DEBEMOS INICIAR UNA TRANSACCIÓN Y ASEGURAR QUE 
			//O SE HACEN LAS DOS OPERACIONES O NO SE HACE NINGUNA
			//Iniciar transacción => EQUIVALE A START_TRANSACTION
			conexion.setAutoCommit(false);
			PreparedStatement ps = conexion.prepareStatement(
					"insert into piezareparacion values (?,?,?,?)");
			ps.setInt(1, pr.getReparacion());
			ps.setInt(2, pr.getPieza());
			ps.setInt(3, pr.getCantidad());
			ps.setFloat(4, pr.getPrecio());
			int filas = ps.executeUpdate();
			if(filas==1) {
				ps = conexion.prepareStatement("update piezas set "
						+ "stock = stock - ? "
						+ "where id = ?");
				ps.setInt(1, pr.getCantidad());
				ps.setInt(2, pr.getPieza());
				filas = ps.executeUpdate();
				if(filas==1) {
					//Todo ha ido bien
					resultado=true;
					conexion.commit();
				}
				else {
					//Algo ha fallado
					conexion.rollback();
				}
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

	public boolean modificarCantidad(PiezaReparacion pr, int cantidad) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			//Insert en piezareapacion
			//Update en pieza para actualizar el stock
			//=> DEBEMOS INICIAR UNA TRANSACCIÓN Y ASEGURAR QUE 
			//O SE HACEN LAS DOS OPERACIONES O NO SE HACE NINGUNA
			//Iniciar transacción => EQUIVALE A START_TRANSACTION
			conexion.setAutoCommit(false);
			PreparedStatement ps = conexion.prepareStatement(
					"update piezareparacion set cantidad = cantidad + ? "
					+ "where reparacion = ? and pieza = ?");
			ps.setInt(1, cantidad);
			ps.setInt(2, pr.getReparacion());
			ps.setInt(3, pr.getPieza());
			int filas = ps.executeUpdate();
			if(filas==1) {
				ps = conexion.prepareStatement("update piezas set "
						+ "stock = stock - ? "
						+ "where id = ?");
				ps.setInt(1, cantidad);
				ps.setInt(2, pr.getPieza());
				filas = ps.executeUpdate();
				if(filas==1) {
					//Todo ha ido bien
					resultado=true;
					conexion.commit();
				}
				else {
					//Algo ha fallado
					conexion.rollback();
				}
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

	public boolean pagarReparacion(Reparacion r, float horas, float precio) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			
			CallableStatement consulta = conexion.prepareCall(
					"{? = call pagarReparacion(?,?,?)}");
			consulta.setInt(2, r.getId());
			consulta.setFloat(3, horas);
			consulta.setFloat(4, precio);
			//REgistrar el tipo del parámetro de salida
			consulta.registerOutParameter(1, Types.FLOAT);
			
			//Ejecutamos la consulta
			int ok = consulta.executeUpdate();
			System.out.println("Salida executeUpdate de función:"+ok);
			
			//Recuperar el valor devuelto por la función
			r.setTotal(consulta.getFloat(1));
			resultado = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
}
