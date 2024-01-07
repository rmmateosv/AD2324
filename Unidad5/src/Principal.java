import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {

	public static Scanner t = new Scanner(System.in);
	public static Modelo bd = new Modelo();

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
				}

			} while (opcion != 0);
		} else {
			System.out.println("Error de conexión");
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
			Medico m = bd.obtenerMedico(nc);
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
