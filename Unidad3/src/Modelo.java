import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

public class Modelo {
	private MongoClient conexion = null;
	private MongoDatabase bd = null;
	
	public Modelo() {
		try {
			conexion= MongoClients.create("mongodb://localhost:27017");
			bd = conexion.getDatabase("liga");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public MongoClient getConexion() {
		return conexion;
	}

	public void setConexion(MongoClient conexion) {
		this.conexion = conexion;
		
	}
	public void cerrar() {
		try {
			conexion.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public Equipo obtenerEquipo(String nextLine) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean crearEquipo(Equipo eq) {
		boolean resultado = false;
		// TODO Auto-generated method stub
		try {
			//Seleccionamos la colección
			MongoCollection<Document> col = bd.getCollection("Equipo");
			
			InsertOneResult r = col.insertOne(
					new Document().append("nombre", eq.getNombre())
					.append("estadistica", eq.getEstadistica())
					.append("jugadores", eq.getJugadores())
					.append("puntos",eq.getPuntos())
					);
			if(r.getInsertedId()!=null) {
				resultado=true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultado;
		
	}
	
}
