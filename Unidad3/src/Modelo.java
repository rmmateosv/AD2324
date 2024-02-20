import java.util.ArrayList;


import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
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

	public Equipo obtenerEquipo(String nombre) {
		// TODO Auto-generated method stub
		Equipo resultado=null;
		//Seleccionamos la colección
		MongoCollection<Document> col = bd.getCollection("Equipo");
		//Filtro para comparar el nombre del equipo
		Bson filtro = Filters.eq("nombre",nombre);
		//Recuperar el primer documento que cumpla con el filtro
		Document d = col.find(filtro).first();
		if(d!=null) {
			//System.out.println(d.toJson());
			//Creamos el resultado
			resultado = new Equipo();
			resultado.setNombre(d.getString("nombre"));
			resultado.setPuntos(d.getInteger("puntos", 0));
			resultado.setJugadores((ArrayList<String>) d.get("jugadores"));
			//Obtener el doc estadística
			Document e = (Document) d.get("estadistica");
			resultado.setEstadistica(new Estadistica(
					e.getInteger("jugados", 0), 
					e.getInteger("ganados", 0), 
					e.getInteger("perdidos", 0), 
					e.getInteger("empatados", 0)));
		}
		return resultado;
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

	public boolean borrarEquipo(Equipo eq) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("Equipo");
			
			//Filtro
			Bson filtro = Filters.eq("nombre",eq.getNombre());
			
			DeleteResult r = col.deleteOne(filtro);
			if(r.getDeletedCount()==1) {
				resultado=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultado;
	}

	public boolean borrarEquipo1J(Equipo eq) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("Equipo");
			
			//Filtro
			Bson filtro = Filters.and(Filters.eq("nombre",eq.getNombre()),
					Filters.size("jugadores", 1));
			
			DeleteResult r = col.deleteOne(filtro);
			if(r.getDeletedCount()==1) {
				resultado=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultado;
	}

	public String existeJugador(String jugador) {
		// TODO Auto-generated method stub
		String resultado = null;
		try {
			MongoCollection<Document> col = bd.getCollection("Equipo");
			
			//Filtro
			Bson filtro = Filters.in("jugadores",jugador);
			
			Document d = col.find(filtro).first();
			if(d!=null) {
				resultado= d.getString("nombre");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultado;
	}
	
}
