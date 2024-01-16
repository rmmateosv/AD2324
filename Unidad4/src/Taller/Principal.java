package Taller;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Modelo bd = new Modelo();
		if(bd.getConexion()!=null) {
			System.out.println("todo ok");
		}
		else {
			System.out.println("Error, no hay conexión con la BD");
		}
	}

}
