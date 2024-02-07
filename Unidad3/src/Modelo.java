import java.util.ArrayList;


import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
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
			
			Document estadistica = new Document()
					.append("jugados",eq.getEstadistica().getJugados())
					.append("ganados", eq.getEstadistica().getGanados())
					.append("perdidos",eq.getEstadistica().getPerdidos())
					.append("empatados",eq.getEstadistica().getEmpatados()) ;
			
			InsertOneResult r = col.insertOne(
					new Document().append("nombre", eq.getNombre())
					.append("estadistica", estadistica)
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

	public ArrayList<Equipo> obtenerEquipos() {
		// TODO Auto-generated method stub
		ArrayList<Equipo> resultado = new ArrayList();
		try {
			MongoCollection<Document> eq =bd.getCollection("Equipo");
			
			MongoCursor<Document> datos = eq.find().iterator();
			while(datos.hasNext()) {
				Document d = datos.next();
				System.out.println(d);
				Equipo e = new Equipo();
				e.setNombre(d.getString("nombre"));
				e.setPuntos(d.getInteger("puntos", 0));
				Estadistica estadistica = new Estadistica();
				//Recuperar Documento estadística
				Document es = (Document)d.get("estadistica");
				estadistica.setJugados(es.getInteger("jugados",0));
				estadistica.setGanados(es.getInteger("ganados",0));
				estadistica.setPerdidos(es.getInteger("perdidos",0));
				estadistica.setEmpatados(es.getInteger("empatados",0));
				e.setEstadistica(estadistica);
				
				//Recuperar Array
				e.setJugadores((ArrayList<String>)d.get("jugadores"));
				resultado.add(e);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	
}
