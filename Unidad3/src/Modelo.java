import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;


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
			//Recuperar solamente el campo nombre
			Bson campos = Projections.fields(Projections.include("nombre"),
					Projections.exclude("_id"));
			
			Document d = col.find(filtro).projection(campos).first();
			if(d!=null) {
				System.out.println(d);
				resultado= d.getString("nombre");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultado;
	}

	public boolean insertarJugador(Equipo eq, String jugador) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("Equipo");
			
			//Filtro
			Bson filtro = Filters.eq("nombre",eq.getNombre());
			//Indicar que modificaciones se hacen
			Bson modif = Updates.addToSet("jugadores", jugador);
			//Modificar
			UpdateResult r = col.updateOne(filtro, modif);
			if(r.getModifiedCount()==1) {
				resultado= true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultado;
	}

	public boolean cambiarEquipo(Equipo eq, String jugador, String eActual) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("Equipo");
			//Quitar el jugador del equipo antiguo
			//Filtro
			Bson filtro = Filters.eq("nombre",eActual);
			//Indicar que modificaciones se hacen
			Bson modif = Updates.pull("jugadores", jugador);
			//Modificar
			UpdateResult r = col.updateOne(filtro, modif);
			if(r.getModifiedCount()==1) {
				//Añadir el jugador al equipo nuevo
				//Filtro
				filtro = Filters.eq("nombre",eq.getNombre());
				//Indicar que modificaciones se hacen
				modif = Updates.addToSet("jugadores", jugador);
				//Modificar
				r = col.updateOne(filtro, modif);
				if(r.getModifiedCount()==1) {
					resultado = true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultado;
	}

	public boolean crearPartido(Partido p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("Partido");
			InsertOneResult r =  col.insertOne(
					new Document().append("codigo",obtenerCodigoP())
					.append("equipoL", p.getEquipoL())
					.append("equipoV", p.getEquipoV())
					.append("fecha", p.getFecha())
					.append("goles", p.getGoles())
					.append("fin", p.isFinalizado())
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

	private int obtenerCodigoP() {
		// TODO Auto-generated method stub
		int resultado = 0;
		
		try {
			MongoCollection<Document> col = bd.getCollection("Partido");
			
			Document r = col.aggregate(Arrays.asList(
					Aggregates.group(null, 
							Accumulators.max("ultimo", "$codigo")))).first();
			if(r!=null) {
				System.out.println(r);
				resultado=r.getInteger("ultimo", 0);
				System.out.println(resultado);
			}
			resultado = resultado+1;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultado;
	}

	public ArrayList<Partido> obtenerPartidos() {
		// TODO Auto-generated method stub
		ArrayList<Partido> resultado = new ArrayList();
		try {
			MongoCollection<Document> col = bd.getCollection("Partido");
			
			MongoCursor<Document> r = col.find().iterator();
			while(r.hasNext()) {
				Document d = r.next();
				Partido p = new Partido(d.getString("equipoL"), 
						d.getString("equipoV"), d.getDate("fecha"));
				p.setCodigo(d.getInteger("codigo", 0));
				p.setFinalizado(d.getBoolean("finalizado", false));
				p.setGoles((ArrayList<Gol>) d.get("goles"));
				resultado.add(p);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Partido obtenerPartido(int codigo) {
		// TODO Auto-generated method stub
		Partido resultado = null;
		try {
			MongoCollection<Document> col = bd.getCollection("Partido");
			
			Bson filtro = Filters.eq("codigo",codigo);
			Document d = col.find(filtro).first();
			
			if(d!=null) {
				resultado = new Partido(d.getString("equipoL"), 
						d.getString("equipoV"), d.getDate("fecha"));
				resultado.setCodigo(d.getInteger("codigo", 0));
				resultado.setFinalizado(d.getBoolean("fin", false));
				ArrayList<Document> goles  = (ArrayList<Document>) d.get("goles");
				for (Document dGol : goles) {
					Gol g = new Gol(dGol.getInteger("minuto", 0),
							dGol.getString("equipo"),
							dGol.getBoolean("anulado", false)
							);
					resultado.getGoles().add(g);
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean crearGol(Partido p, Gol g) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("Partido");
			
			Bson filtro = Filters.eq("codigo",p.getCodigo());
			//Crear el documento gol
			Document gol = new Document().append("minuto", g.getMinuto())
					.append("equipo", g.getEquipo())
					.append("anulado", g.isAnulado());
			Bson modif = Updates.addToSet("goles", gol);
			UpdateResult r = col.updateOne(filtro, modif);
			if(r.getModifiedCount()==1) {
				resultado=true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultado;
	}

	public ArrayList<Gol> obtenerGoles(String nombre) {
		// TODO Auto-generated method stub
		ArrayList<Gol> resultado = new ArrayList();
		try {
			MongoCollection<Document> col = bd.getCollection("Partido");
			
			Bson filtro = Filters.eq("goles.equipo",nombre);
			Bson campos = Projections.fields(
					Projections.excludeId(),
					Projections.include("codigo","goles"));
			/* No podemos hacerlo así porque se devuelven todos
			 * los goles de los partidos en los que al menos
			 * hay un gol del equipo buscado 
			 * MongoCursor<Document> r= col.find(filtro).
				projection(campos).
				sort(Sorts.descending("fecha"))
				.iterator();*/
			MongoCursor<Document> r = col.aggregate(Arrays.asList(
					Aggregates.match(filtro), //filtrar partidos donde haya un gol del equipo buscado
					Aggregates.sort(Sorts.descending("fecha")), //Ordena por fecha desc
					Aggregates.unwind("$goles"), //Desagrupa array goles
					Aggregates.match(filtro), //Vuelve a aplicar el filtro para el
					Aggregates.project(campos)
					
					)).iterator();
			while(r.hasNext()){
				Document gol = (Document) r.next().get("goles");
				//Crear gol
				Gol g = new Gol(gol.getInteger("minuto",0), 
						gol.getString("equipo"), gol.getBoolean("anulado", false));
				resultado.add(g);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean anularGol(Partido p, int minuto) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("Partido");
			//Filtro que selecciona el partido si existe el gol en el array
			//de goles
			Bson filtro = Filters.and(Filters.eq("codigo",p.getCodigo()),
					Filters.eq("goles.minuto",minuto),
					Filters.eq("goles.anulado",false));
			Bson modif = Updates.set("goles.$[golSeleccionado].anulado", true);
			//Creamos un filtro que selecciona el gol a modificar
			UpdateOptions golAModificar = new UpdateOptions().
					arrayFilters(Arrays.asList(
							Filters.eq("golSeleccionado.minuto",minuto)));
			UpdateResult r= col.updateOne(filtro, modif,golAModificar);
			if(r.getModifiedCount()==1) {
				resultado=true;
			}
						
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean borrarGol(Partido p, int minuto) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("Partido");
			//Filtro que selecciona el partido si existe el gol en el array
			//de goles
			Bson filtro = Filters.and(Filters.eq("codigo",p.getCodigo()),
					Filters.eq("goles.minuto",minuto));
			Bson modif = Updates.pull("goles", 
					new Document().append("minuto", minuto));
			
			UpdateResult r= col.updateOne(filtro, modif);
			if(r.getModifiedCount()>=1) {
				resultado=true;
			}
						
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean finalizar(Partido p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("Partido");
			//Filtro que selecciona el partido si existe el gol en el array
			//de goles
			Bson filtro = Filters.eq("codigo",p.getCodigo());
			Bson modif = Updates.set("finalizado", true);
			
			UpdateResult r= col.updateOne(filtro, modif);
			if(r.getModifiedCount()==1) {
				//Obtener goles equipo local
				int golesL=obtenerNumGoles(p.getCodigo(),p.getEquipoL());
				int golesV=obtenerNumGoles(p.getCodigo(),p.getEquipoV());
				
				if(golesL==golesV) {
					modificarEquipo(p.getEquipoL(),'e');
					modificarEquipo(p.getEquipoV(),'e');
				}
				if(golesL>golesV) {
					modificarEquipo(p.getEquipoL(),'g');
					modificarEquipo(p.getEquipoV(),'p');
				}
				else {
					modificarEquipo(p.getEquipoL(),'p');
					modificarEquipo(p.getEquipoV(),'g');
				}
				
				resultado = true;
				
			}
						
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultado;
	}

	private int obtenerNumGoles(int codigoP, String equipo) {
		// TODO Auto-generated method stub
		int resultado = 0;
		try {
			MongoCollection<Document> col = bd.getCollection("Partido");
			//Filtro que selecciona el partido si existe el gol en el array
			//de goles
			Document r = col.aggregate(Arrays.asList(
					Aggregates.match(Filters.eq("codigo",codigoP)),
					Aggregates.unwind("$goles"),
					Aggregates.match(Filters.and(Filters.eq("goles.equipo",equipo),
							Filters.eq("anulado",false))),
					Aggregates.count("goles")					
					)).first();
			if(r!=null) {
				resultado=r.getInteger("goles");
			}
									
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return resultado;
	}

	private boolean modificarEquipo(String equipo, char tipo) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("Equipo");
			Bson filtro= Filters.eq("nombre",equipo);
			Bson modif = null;
			switch (tipo) {
				case 'e': 
					modif=Updates.combine(
							Updates.inc("estadistica.jugados", 1),
							Updates.inc("estadistica.empatados", 1)
							);
					break;
				
				case 'g': 
					modif=Updates.combine(
							Updates.inc("estadistica.jugados", 1),
							Updates.inc("estadistica.ganados", 1)
							);
					break;
				
				case 'p': 
					modif=Updates.combine(
							Updates.inc("estadistica.jugados", 1),
							Updates.inc("estadistica.perdidos", 1)
							);
					break;				
			}
			UpdateResult r = col.updateOne(filtro, modif);
			if(r.getModifiedCount()==1) {
				resultado=true;
			}
			
									
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return resultado;
	}

	public ArrayList<Object[]> obtenerDatosPartidosConDatosEquipos(Partido p) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ArrayList<Object[]> resultado = new ArrayList();
		try {
			MongoCollection<Document> col = bd.getCollection("Partido");
			Document r = col.aggregate(Arrays.asList(
					Aggregates.match(Filters.eq("codigo",p.getCodigo())),
					Aggregates.lookup("Equipo", "equipoL", "nombre","eL"),
					Aggregates.lookup("Equipo", "equipoV", "nombre","eV")
					)).first();
			if(r!=null) {
				System.out.println(r.toJson());
			}
					
									
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return resultado;
	}
	
	
}
