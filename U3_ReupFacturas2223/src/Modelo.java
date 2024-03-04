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
import com.mongodb.client.model.Field;
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
			bd = conexion.getDatabase("facturas");
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

	public Producto obtenerProducto(String codigo) {
		// TODO Auto-generated method stub
		Producto resultado = null;
		try {
			MongoCollection<Document> col = bd.getCollection("productos");
			Document r = col.find(Filters.eq("codigo",codigo)).first();
			if(r!=null) {
				resultado = new Producto();
				resultado.setCodigo(r.getString("codigo"));
				resultado.setNombre(r.getString("nombre"));
				resultado.setPrecio(r.getDouble("precio"));
				resultado.setStock(r.getInteger("stock", 0));				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearProducto(Producto p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("productos");
			InsertOneResult r = col.insertOne(new Document().append("codigo",p.getCodigo())
					.append("nombre", p.getNombre())
					.append("precio",p.getPrecio())
					.append("stock", p.getStock()));
			if(r.getInsertedId()!=null) {
				resultado=true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public int ultimoCodigoF() {
		// TODO Auto-generated method stub
		int resultado = 0;
		try {
			MongoCollection<Document> col = bd.getCollection("facturas");
			
			Document r = col.aggregate(Arrays.asList(
					Aggregates.group(null, Accumulators.max("numero", "$numero")))).first();
			if(r!=null) {
				resultado = r.getInteger("numero", 0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Producto> obtenerProductos() {
		// TODO Auto-generated method stub
		ArrayList<Producto> resultado = new ArrayList();
		try {
			MongoCollection<Document> col = bd.getCollection("productos");
			
			MongoCursor<Document> r = col.find().iterator();
			while(r.hasNext()) {
				Document d = r.next();
				Producto p = new Producto();
				p.setCodigo(d.getString("codigo"));
				p.setNombre(d.getString("nombre"));
				p.setPrecio(d.getDouble("precio"));
				p.setStock(d.getInteger("stock", 0));	
				resultado.add(p);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearFactura(Factura f) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("facturas");
			
			ArrayList<Document> detalle = new ArrayList();
			for (Detalle d : f.getDetalle()) {
				Document doc = new Document().append("producto", d.getProducto())
						.append("cantidad", d.getCantidad())
						.append("precioUnidad",d.getPrecioUdad());
				detalle.add(doc);
			}
			
			InsertOneResult r = col.insertOne(new Document().append("numero",f.getNumero())
					.append("fecha", f.getFecha())
					.append("cliente",f.getCliente())
					.append("detalle", detalle)
					.append("facturaAnulacion", 0));
			if(r.getInsertedId()!=null) {
				resultado=true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean actualizarStock(ArrayList<Detalle> detalle) {
		// TODO Auto-generated method stub
		boolean resultado = true;
		try {
			MongoCollection<Document> col = bd.getCollection("productos");
			
			
			for (Detalle d : detalle) {
				Bson filtro = Filters.eq("codigo",d.getProducto());
				Bson modif = Updates.inc("stock", d.getCantidad()*-1);
				
				UpdateResult r = col.updateOne(filtro, modif);
				if(r.getModifiedCount()!=1) {
					resultado = false;
				}
			}			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

		
}
