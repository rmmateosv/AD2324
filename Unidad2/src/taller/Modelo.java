package taller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
					"insert into reparacion values(default,?,?,?)");
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
}
