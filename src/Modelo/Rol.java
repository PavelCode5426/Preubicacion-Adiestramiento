package Modelo;

public class Rol {
	private int id;
	private String nombre;
	private String descripcion;
	public Rol(int id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	public Rol() {
		
		id = -1;
		nombre = "";
		descripcion = "";
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
