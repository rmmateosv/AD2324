package DOM;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import ClaseObject.Historial;
import ClaseRAF.Nota;
import fBinario.Asignatura;

public class Modelo {
	private String nombreFichero = "historiales.xml";
	private SimpleDateFormat formatoXML = new SimpleDateFormat("yyyy-MM-dd");
	
	
	
	public Modelo() {
		
	}

	public boolean crearHistorial(String nombreIES,ArrayList<Historial> h) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		//Pasos:
		//1-Crear el árbol DOM vacío		
		//2-Añadir todos los nodos	
		//3-Pasar el árbol a fichero
				
		try {
			//Crear árbol vacío
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			//Establecer versión de xml
			doc.setXmlVersion("1.0");

			//Crear elemento raíz
			Element raiz = doc.createElement("centro");
			//Añadir el elemento raiz al árbol
			doc.appendChild(raiz);
			
			//Crear elemento fecha
			Element fecha = doc.createElement("fecha");
			//Añadir elemento fecha al árbol
			raiz.appendChild(fecha);
			//Crear el contenido de fecha
			Text texto = doc.createTextNode(formatoXML.format(new Date()));
			//Añadir el texto de la fecha al elemento fecha
			fecha.appendChild(texto);
			
			//Crear el elemento ies
			Element ies = doc.createElement("ies");
			raiz.appendChild(ies);
			texto = doc.createTextNode(nombreIES);
			ies.appendChild(texto);
			
			//Crear elemento listado
			Element listado = doc.createElement("historiales");
			raiz.appendChild(listado);
			
			//Crear historial, uno por cada uno que hay en historiales.obj
			for(Historial his:h) {
				Element historial = doc.createElement("historial");
				listado.appendChild(historial);
				//Crear atributo con el dni del alumno
				historial.setAttribute("dni", his.getAlumno().getDni());
				//Crear nodo nombre
				Element nombreAl = doc.createElement("nombreAl");
				historial.appendChild(nombreAl);
				texto = doc.createTextNode(his.getAlumno().getNombre());
				nombreAl.appendChild(texto);
				//Crear nodo notas
				Element notas = doc.createElement("notas");
				historial.appendChild(notas);
				for(Object[] n:his.getDatos()) {
					Asignatura as = (Asignatura) n[0];
					Nota nota = (Nota) n[1];
					Element nodoNota = doc.createElement("nota");
					nodoNota.setAttribute("idAsig", Integer.toString(as.getId()));
					nodoNota.setAttribute("nota", Float.toString(nota.getNota()));
					notas.appendChild(nodoNota);
					Element nombreAsig = doc.createElement("nombreAsig");
					nodoNota.appendChild(nombreAsig);
					texto = doc.createTextNode(as.getNombre());
					nombreAsig.appendChild(texto);
					Element fechaEx = doc.createElement("fechaEx");
					nodoNota.appendChild(fechaEx);
					texto = doc.createTextNode(formatoXML.format(nota.getFecha()));
					fechaEx.appendChild(texto);
				}
			}
			
			//Pasar el árbol a fichero
			if(generarFichero(doc)) {
				resultado=true;
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	private boolean generarFichero(Document doc) {
		// TODO Auto-generated method stub
		boolean resultado = false;
				
		try {
			//DEfinir fuente con el árbol
			Source arbol = new DOMSource(doc);
			//Definir destino con fichero xml
			Result ficheroXML = new StreamResult(new File(nombreFichero));
			//Transformar
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.transform(arbol, ficheroXML);
			resultado = true;
			
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;	
	}
}
