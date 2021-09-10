package Modelo;

public class Especialidad {
	private int id;
	private String nombre;
	
	public Especialidad(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	public Especialidad() {
		
		id = -1;
		nombre = "";
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
}
