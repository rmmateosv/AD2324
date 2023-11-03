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
			switch (accion) {
			case "crear":
				crearAlumno(request, response);
				break;
			default:
				// Redireccionar a la página alumnos.jsp
				request.getRequestDispatcher("alumnos.jsp").forward(request, response);
			}
		} 
		else {
			// Redireccionar a la página alumnos.jsp
			request.getRequestDispatcher("alumnos.jsp").forward(request, response);
		}
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
				}
				else {
					System.out.println("Error al crear el alumno");
				}
			} else {
				// Error
				System.out.println("Error, ya existe alumno");
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
