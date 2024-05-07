package HBA;

import java.util.ArrayList;

public class Usuario {

	private String nick, email;
	private ArrayList<Reproduccion> listaReproducciones = new ArrayList();
	
	public Usuario() {}

	public Usuario(String nick, String email) {
		this.nick = nick;
		this.email = email;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Reproduccion> getListaReproducciones() {
		return listaReproducciones;
	}

	public void setListaReproducciones(ArrayList<Reproduccion> listaReproducciones) {
		this.listaReproducciones = listaReproducciones;
	}

	@Override
	public String toString() {
		return "Usuario [nick=" + nick + ", email=" + email + "]";
	}
	
	
	
}
