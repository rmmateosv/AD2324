package McBurguer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class Modelo {

	private Connection conexion = null;
	private final String URL = "jdbc:mysql://localhost:3307/mcBurguer";
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

	public boolean comprobarUsuario(int codEmpleado, String ps) {
		boolean resultado = false;

		try {
			CallableStatement cS = conexion.prepareCall("{? = call login(?,?)}");
			cS.registerOutParameter(1, Types.INTEGER);
			cS.setInt(2, codEmpleado);
			cS.setString(3, ps);

			cS.executeUpdate();

			if (cS.getInt(1) == 1) {
				return true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return resultado;
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

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public Empleado obtenerEmpleado(int codEmpleado) {
		Empleado resultado = null;

		try {
			PreparedStatement pS = conexion.prepareStatement("SELECT * FROM empleado WHERE codigo = ?");
			pS.setInt(1, codEmpleado);
			ResultSet datos = pS.executeQuery();

			if (datos.next()) {
				resultado = new Empleado(datos.getInt(1), datos.getString(2), datos.getString(4), datos.getInt(5),
						datos.getInt(6));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return resultado;
	}

	public ArrayList<Producto> obtenerProductos() {
		ArrayList<Producto> resultado = new ArrayList();

		try {
			Statement s = conexion.createStatement();
			ResultSet datos = s.executeQuery("SELECT * FROM Producto");

			while (datos.next()) {
				resultado.add(new Producto(datos.getInt(1), datos.getString(2), datos.getFloat(3)));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return resultado;
	}

	public Producto obtenerProducto(int idProducto) {
		Producto resultado = null;

		try {
			PreparedStatement pS = conexion.prepareStatement("SELECT * FROM Producto WHERE codigo = ?");
			pS.setInt(1, idProducto);
			ResultSet datos = pS.executeQuery();

			if (datos.next()) {
				return new Producto(datos.getInt(1), datos.getString(2), datos.getFloat(3));
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean crearPedido(Pedido p, ArrayList<Detalle> detalle) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			conexion.setAutoCommit(false);
			PreparedStatement pS = conexion.prepareStatement("Insert into Pedido values (default, curdate(), ?, ?)", Statement.RETURN_GENERATED_KEYS);
			pS.setInt(1, p.getCodEmpleado());
			pS.setInt(2, p.getTienda());
			int num = pS.executeUpdate();
			if (num == 1) {
				ResultSet codPedidos = pS.getGeneratedKeys();
				if (codPedidos.next()) {
					p.setCodigo(codPedidos.getInt(1));
					for (Detalle d : detalle) {
						d.setPedido(p.getCodigo());
						Detalle existe = obtenerDetalle(d.getPedido(), d.getProducto());
						if (existe != null) {
							// Update
						} else {
							// Insert
						}
					}
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

	private Detalle obtenerDetalle(int pedido, int producto) {
		// TODO Auto-generated method stub
		Detalle resultado = null;
		try {
			PreparedStatement pS = conexion.prepareStatement("Select * from Detalle where pedido = ? and producto = ?");
			pS.setInt(1, pedido);
			pS.setInt(2, producto);
			ResultSet r = pS.executeQuery();
			if (r.next()) {
				resultado = new Detalle(r.getInt(1), r.getInt(2), r.getInt(3), r.getFloat(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

}
