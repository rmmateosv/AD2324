package clases;

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
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

public class Modelo {

	MongoClient conexion = null;
	MongoDatabase bd = null;

	public Modelo() {
		try {
			conexion = MongoClients.create("mongodb://localhost");
			bd = conexion.getDatabase("bricolaje");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MongoClient getConexion() {
		return conexion;
	}

	public void setConexion(MongoClient conexion) {
		this.conexion = conexion;
	}

	public Producto obtenerProducto(String codigo) {
		// TODO Auto-generated method stub
		Producto resultado = null;
		try {
			// Nos conectamos a la colección Producto
			MongoCollection<Document> col = bd.getCollection("productos");
			Bson filtro = Filters.eq("codigo", codigo);
			Document d = col.find(filtro).first();
			if (d != null) {
				resultado = new Producto(d.getString("codigo"), d.getString("nombre"), d.getDouble("precio"),
						d.getInteger("stock"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean crearProducto(Producto p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("productos");
			InsertOneResult r = col.insertOne(new Document().append("codigo", p.getCodigo())
					.append("nombre", p.getNombre()).append("stock", p.getStock()).append("precio", p.getPrecio()));
			if (r.getInsertedId() != null) {
				resultado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	public ArrayList<Producto> obtenerProductos() {
		// TODO Auto-generated method stub
		ArrayList<Producto> resultado = new ArrayList<>();
		try {
			MongoCollection<Document> col = bd.getCollection("productos");
			MongoCursor<Document> r = col.find().iterator();
			while (r.hasNext()) {
				Document d = r.next();
				resultado.add(new Producto(d.getString("codigo"), d.getString("nombre"), d.getDouble("precio"),
						d.getInteger("stock")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public int obtenerNumeroFactura() {
		// TODO Auto-generated method stub
		int resultado = 1;
		try {
			MongoCollection<Document> col = bd.getCollection("facturas");
			Document d = col.aggregate(Arrays.asList(Aggregates.group(null, Accumulators.max("n", "$numero")))).first();
			if (d != null) {
				resultado = d.getInteger("n", 1) + 1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearFactura(Factura f) {
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("facturas");
			ArrayList<Document> datos = new ArrayList();
			for (Detalle detalle : f.getListaDetalles()) {
				Document d = new Document().append("producto", detalle.getProducto())
						.append("cantidad", detalle.getCantidad()).append("PrecioUnidad", detalle.getPrecioUnidad());
				datos.add(d);
			}
			InsertOneResult r = col.insertOne(new Document().append("numero", f.getNumero())
					.append("fecha", f.getFecha()).append("cliente", f.getCliente())
					.append("facturaAnulacion", f.getFacturaAnulacion()).append("detalle", datos));
			if (r.getInsertedId() != null) {
				resultado = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean actualizarStock(Detalle i) {
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("productos");
			Bson filtro = Filters.eq("codigo", i.getProducto());
			Bson modif = Updates.inc("stock", i.getCantidad() * -1);
			UpdateResult rs = col.updateOne(filtro, modif);
			if (rs.getModifiedCount() == 1) {
				resultado = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
		
	}

}
