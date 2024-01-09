import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {

	public static Scanner t = new Scanner(System.in);
	public static Modelo bd = new Modelo();
	public static SimpleDateFormat formato = new SimpleDateFormat("ddMMyyHHmm");

	public static void main(String[] args) {
		if (bd.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Selecciona una opción");
				System.out.println("0-Salir");
				System.out.println("1-Crear Paciente/Médico");
				System.out.println("2-Modificar Contacto Persona");
				System.out.println("3-Crear consulta");
				System.out.println("4-Atender Consulta(Modificar Historial)");
				System.out.println("5-Borrar Paciente/Médico");
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearPersona();
					break;
				case 2:
					modificarContacto();
					break;
				case 3:
					crearConsulta();
					break;
				case 4:
					atenderPaciente();
				}

			} while (opcion != 0);
		} else {
			System.out.println("Error de conexión");
		}
	}

	private static void atenderPaciente() {
		// TODO Auto-generated method stub
		mostrarConsultas();
		System.out.println("Ide consulta:");
		Consulta c = bd.obtenerConsulta(t.nextInt());t.nextLine();
		if(c!=null) {
			System.out.println("Diagnóstico");
			c.setDiagnostico(t.nextLine());
			if(bd.registrarDiagnostico(c)) {
				System.out.println("Historial actualizado");
				Paciente p = bd.obtenerPaciente(c.getPaciente().getNss());
				System.out.println(p);
			}
		}
		else {
			System.out.println("Error, consulta no existe");
		}
		
	}

	private static void mostrarConsultas() {
		// TODO Auto-generated method stub
		ArrayList<Consulta> c = bd.obtenerConsultas();
		for (Consulta co : c) {
			System.out.println(co);
			
		}
	}

	private static void crearConsulta() {
		// TODO Auto-generated method stub
		try {
			mostraPacientes();
			System.out.println("Nss:");
			Paciente p = bd.obtenerPaciente(t.nextInt());t.nextLine();
			if(p!=null) {
				mostrarMedicos();
				System.out.println("Nº Colegiado:");
				Medico m = bd.obtenerMedico(t.nextInt());t.nextLine();
				if(m!=null) {
					Consulta c = new Consulta();
					c.setPaciente(p);
					c.setMedico(m);
					System.out.println("Fecha Consulta (ddMMyyhhmm):");
					c.setFecha(formato.parse(t.nextLine()));
					if(bd.crearConsulta(c)) {
						System.out.println("Consulta cread con id:" + c.getId());
					}
					else {
						System.out.println("Error al crear la consulta");
					}
					
				}
				else {
					System.out.println("Error, médico no existe");
				}
			}
			else {
				System.out.println("Error, paciente no existe");
			}
		} catch (ParseException e) {
			// TODO: handle exception
			System.out.println("Fecha inválida");
		}
		
	}

	private static void mostraPacientes() {
		// TODO Auto-generated method stub
		ArrayList<Paciente> p = bd.obtenerPacientes();
		for (Paciente pa : p) {
			System.out.println(pa);
			
		}
	}

	private static void mostrarMedicos() {
		// TODO Auto-generated method stub
		ArrayList<Medico> m = bd.obtenerMedicos();
		for (Medico medico : m) {
			System.out.println(medico);
			
		}
	}

	private static void modificarContacto() {
		// TODO Auto-generated method stub
		mostrarPersonas();
		System.out.println("Introduce id");
		Persona p = bd.obtenerPersona(t.nextInt()); t.nextLine();
		if(p!=null) {
			System.out.println("Nuevo telefono");
			p.getContacto().setTelefono(t.nextLine());
			System.out.println("Nuevo email");
			p.getContacto().setEmail(t.nextLine());
			if(bd.modificarContacto(p)) {
				System.out.println("Contacto modificado");
			}
			else{
				System.out.println("Error, al modificar el contacto");
			}
			
		}
		else {
			System.out.println("Error, persona no existe");
		}
		
	}

	private static void mostrarPersonas() {
		// TODO Auto-generated method stub
		ArrayList<Persona> p = bd.obtenerPersonas();
		for (Persona persona : p) {
			System.out.println(persona);
		}
	}

	private static void crearPersona() {
		// TODO Auto-generated method stub
		Persona p = new Persona();
		System.out.println("Nombre");
		p.setNombre(t.nextLine());
		System.out.println("Teléfono");
		p.setContacto(new Contacto());
		p.getContacto().setTelefono(t.nextLine());
		System.out.println("Email");
		p.getContacto().setEmail(t.nextLine());
		System.out.println("¿Paciente(p) o Médico(*)?");
		String opcion = t.nextLine();
		if(opcion.equalsIgnoreCase("p")) {			
			System.out.println("NSS");
			int nss = t.nextInt();
			Paciente pa = bd.obtenerPaciente(nss); t.nextLine();
			if(pa==null) {
				pa = new Paciente(0, p.getNombre(), 
						p.getContacto(), nss, new ArrayList() );
				if(bd.crearPaciente(pa)) {
					System.out.println("Paciente creado");
				}
				else {
					System.out.println("Error al crear el paciente");
				}
			}
			else {
				System.out.println("Error, paciente ya existe");
			}
			
		}
		else {
			System.out.println("Nº de colegiado");
			int nc = t.nextInt();
			Medico m = bd.obtenerMedico(nc);t.nextLine();
			if(m==null) {
				System.out.println("Especialidad:");
				m = new Medico(0, p.getNombre(), 
						p.getContacto(), nc, t.nextLine() );
				if(bd.crearMedico(m)) {
					System.out.println("Paciente creado");
				}
				else {
					System.out.println("Error al crear el médico");
				}
			}
			else {
				System.out.println("Error, médico ya existe");
			}
		}
	}

}
