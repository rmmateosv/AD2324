<%@page import="Modelo.Alumno"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
//REcuperar alumnos que vienen del controlador
ArrayList<Alumno> alumnos = (ArrayList<Alumno>) request.getAttribute("alumnos");
if(alumnos==null){
	alumnos = new ArrayList<>();
}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Alumnos</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"crossorigin="anonymous"></script>
</head>
<body>
	<header>
		<jsp:include page="menu.jsp"/>		
		<h3 style="text-align: center;">GESTI�N DE ALUMNOS</h3>
	</header>
	
	<section>
		<div class="container mt-3 p-3 w-100 border">
			<form action="ControladorAlumnos" class="form-inline" method="post">
				<div class="row">
					<div class="col">
						<label>DNI</label>
						<input type="text" name="dni" required="required" class="form-control form-control-sm"/>
					</div>
					<div class="col">
						<label>Nombre</label>
						<input type="text" name="nombre" required="required"  class="form-control form-control-sm"/>
					</div>
					<div class="col">
						<label>N�Expediente</label>
						<input type="number" name="exp" required="required"  class="form-control form-control-sm"/>
					</div>
					<div class="col">
						<label>Fecha Nacimiento</label>
						<input type="date" name="fecha" required="required"  class="form-control form-control-sm"/>
					</div>
					<div class="col">
						<label>Estatura</label>
						<input type="number" name="estatura"  required="required"  class="form-control form-control-sm"/>
					</div>
					<div class="col">
						<br/>
						<button type="submit" name="accion" value="crear" 
						class="btn btn-outline-dark">Crear</button>
					</div>
				</div>
			</form>
		</div>
	</section>
	<section>
		<!--  pintar alumnos en una tabla -->
		<table>
			<tr>
				<th>DNI</th>
				<th>Nombre</th>
				<th>Fecha Nacimiento</th>
				<th>N� Expediente</th>
				<th>Estatura</th>
				<th>Activo</th>
			</tr>
			<%
			for(Alumno a : alumnos){
				out.println("<tr>");
				out.println("<td>"+a.getDni()+"</td>");
				out.println("<td>"+a.getNombre()+"</td>");
				out.println("<td>"+a.getFechaN()+"</td>");
				out.println("<td>"+a.getNumExp()+"</td>");
				out.println("<td>"+a.getEstatura()+"</td>");
				out.println("<td>"+a.isActivo()+"</td>");
				out.println("</tr>");
			}
			%>
		</table>
	</section>
	
	<footer>
	</footer>
	
</body>
</html>