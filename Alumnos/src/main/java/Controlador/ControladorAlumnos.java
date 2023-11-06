package Controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Modelo.Alumno;
import Modelo.Modelo;

/**
 * Servlet implementation class ControladorAlumnos
 */
public class ControladorAlumnos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Crear el acceso a datos
	private static Modelo ad = new Modelo("alumnos.txt");
	private SimpleDateFormat fechaHTML = new SimpleDateFormat("yyyy-MM-dd");
	private static String mensaje="";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControladorAlumnos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// Leer el parámetro acción
		String accion = request.getParameter("accion");
		if (accion != null) {
			String[]  eleccion = accion.split("/");
			switch (eleccion [0]) {
			case "crear":
				crearAlumno(request, response);
				break;
			case "baja":
				bajaAlumno(request, response,eleccion[1]);
				break;
			case "borrar":
				borrarAlumno(request, response,eleccion[1]);
				break;
			default:
				cargarVistaAlumno(request, response);
			}
		} 
		else {
			cargarVistaAlumno(request, response);
		}
	}

	private void borrarAlumno(HttpServletRequest request, HttpServletResponse response, String dni) {
		// TODO Auto-generated method stub
		Alumno a = ad.obtenerAlumno(dni);
		if(a!=null) {
			if(ad.borrarAlumno(a)) {
				mensaje="Alumno borrado";
			}
			else {
				mensaje="Error al borrar el alumno";
			}
		}
		else {
			mensaje="Error, el alumno no existe";
		}
		cargarVistaAlumno(request, response);
	}

	private void bajaAlumno(HttpServletRequest request, HttpServletResponse response, String dni) {
		// TODO Auto-generated method stub
		Alumno a = ad.obtenerAlumno(dni);
		if(a!=null) {
			if(ad.bajaAlumno(a)) {
				mensaje="Alumno dado de baja";
			}
			else {
				mensaje="Error al dar de baja el alumno";
			}
		}
		else {
			mensaje="Error, el alumno no existe";
		}
		cargarVistaAlumno(request, response);
	}

	private void crearAlumno(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			// Comprobar si existe, si no existe crear
			Alumno a = ad.obtenerAlumno(request.getParameter("dni"));
			if (a == null) {
				// Crear
				a = new Alumno(request.getParameter("dni"), request.getParameter("nombre"),
						fechaHTML.parse(request.getParameter("fecha")), Integer.parseInt(request.getParameter("exp")),
						Integer.parseInt(request.getParameter("estatura")), true);
				if(ad.crearAlumno(a)) {
					System.out.println("Alumno creado");
					mensaje="Alumno Creado";
				}
				else {
					System.out.println("Error al crear el alumno");
					mensaje="Error al crear el alumno";
				}
			} else {
				// Error
				System.out.println("Error, ya existe alumno");
				mensaje="Alumno ya existe";
			}
		} catch (ParseException e) {
			// TODO: handle exception
			System.out.println("Error, fecha incorrecta");
		}
		cargarVistaAlumno(request,response);
	}

	private void cargarVistaAlumno(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub		
		try {
			//Cargar los alumnos del fichero
			ArrayList<Alumno> alumnos = ad.obtenerAlumnos();
			//REdireccionar a la vista de alumnos pasando los alumnos como parámetros
			request.setAttribute("alumnos", alumnos);			
			//Pasar mensaje a la vista de alumnos
			if(!mensaje.isEmpty()) {
				request.setAttribute("mensaje", mensaje);
			}
			
			
			//Redirigir
			request.getRequestDispatcher("alumnos.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
