package DOM;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import ClaseObject.Historial;

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
		
		//Crear árbol vacío
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			//Establecer versión de xml
			doc.setXmlVersion("1.0");
			//Crear elemento raíz
			Element raiz = doc.createElement("historiales");
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
			Element listado = doc.createElement("listado");
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
;			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
}
