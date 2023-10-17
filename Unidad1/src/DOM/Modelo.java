package DOM;

import java.io.File;
import java.io.IOException;
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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

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

	public void mostrarXML() {
		// TODO Auto-generated method stub
		//Cargar el fichero XML en un árbolDOM
		File f = new File(nombreFichero);
		if(f.exists()) {
			try {
				Document doc = DocumentBuilderFactory.newInstance().
						                             newDocumentBuilder().
						                             parse(f);
				//Obtener nodo raíz
				Element raiz = doc.getDocumentElement();
				pintarNodo(raiz,0);
				
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Aún no se ha creado el fichero");
		}
	}

	private void pintarNodo(Node nodo, int nivel) {
		// TODO Auto-generated method stub
		//Tabular
		for(int i=0;i<nivel;i++) {
			System.out.print("\t");
		}
		System.out.print("<"+nodo.getNodeName());
		//Obtenemos y pintamos los atributos
		NamedNodeMap atrib = nodo.getAttributes();
		for(int i=0; i<atrib.getLength(); i++) {
			Attr a = (Attr) atrib.item(i);
			System.out.print(" "+a.getName()+"=\""+a.getValue()+"\"");
		}
		System.out.println(">");
		//Obtener hijos del nodo
		NodeList hijos = nodo.getChildNodes();
		for(int i=0;i<hijos.getLength();i++) {
			//Si un nodo es de tipo texto, pintamos su contenido
			//Si no, llamamos al método pintarNodo de forma recursiva
			if(hijos.item(i).getNodeType()==Node.TEXT_NODE) {
				//Pintar una tabulación más
				for(int j=0;j<=nivel;j++) {
					System.out.print("\t");
				}
				System.out.println(hijos.item(i).getTextContent());
			}
			else {
				pintarNodo(hijos.item(i), nivel+1);
			}
		}
		for(int i=0;i<nivel;i++) {
			System.out.print("\t");
		}
		System.out.println("</"+nodo.getNodeName()+">");
	}

	public boolean existeAlumno(String dni) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		File f = new File(nombreFichero);
		if(f.exists()) {
			try {
				Document doc = DocumentBuilderFactory.newInstance().
			      newDocumentBuilder().parse(f);
				
				//Obtener todos los nodos historial que es donde está el dni
				//para poder comparar
				NodeList historiales = doc.getElementsByTagName("historial");
				for(int i=0;i<historiales.getLength();i++) {
					Element historial = (Element) historiales.item(i);
					//Obtener atributo dni
					if(historial.getAttribute("dni").equalsIgnoreCase(dni)) {
						return true;
					}
					
				}
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resultado;
	}

	public boolean modificar(String dni, String nuevoNombre) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		File f = new File(nombreFichero);
		if(f.exists()) {
			try {
				Document doc = DocumentBuilderFactory.newInstance().
			      newDocumentBuilder().parse(f);
				
				//Obtener todos los nodos historial que es donde está el dni
				//para poder comparar
				NodeList historiales = doc.getElementsByTagName("historial");
				for(int i=0;i<historiales.getLength();i++) {
					Element historial = (Element) historiales.item(i);
					//Obtener atributo dni
					if(historial.getAttribute("dni").equalsIgnoreCase(dni)) {
						//Modificar el nombre que es un elemento hijo
						//del elemento historial
						//Element nombre = (Element) historial.getChildNodes().item(0);
						Element nombre = (Element) historial.getFirstChild();
						//Obtener el textElement de nombre
						Text textoNombre = (Text) nombre.getFirstChild();
						//Modificamos el texto del nombre
						textoNombre.setNodeValue(nuevoNombre);
						//Volvemos a generar el fichero
						generarFichero(doc);
						return true;
					}
					
				}
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resultado;
	}

	public boolean borrar(String dni) {
		// TODO Auto-generated method stub
boolean resultado = false;
		
		File f = new File(nombreFichero);
		if(f.exists()) {
			try {
				Document doc = DocumentBuilderFactory.newInstance().
			      newDocumentBuilder().parse(f);
				
				//Obtener todos los nodos historial que es donde está el dni
				//para poder comparar
				NodeList historiales = doc.getElementsByTagName("historial");
				for(int i=0;i<historiales.getLength();i++) {
					Element historial = (Element) historiales.item(i);
					//Obtener atributo dni
					if(historial.getAttribute("dni").equalsIgnoreCase(dni)) {
						historial.getParentNode().removeChild(historial);
						//Volvemos a generar el fichero
						generarFichero(doc);
						return true;
					}
					
				}
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resultado;
	}

	
}
