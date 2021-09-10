package Modelo;

public class Notificacion {
	private int id;
	private String mensaje;
	private int subscriptor;

	public Notificacion(int id, String mensaje, int subscriptor) {
		super();
		this.id = id;
		this.mensaje = mensaje;
		this.subscriptor = subscriptor;
	}

	public int getId() {
		return id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public int getSubscriptor() {
		return subscriptor;
	}

	public Notificacion() {
		
	    id = -1;
		mensaje = "";
		subscriptor = -1;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setSubscriptor(int subscriptor) {
		this.subscriptor = subscriptor;
	}
}
