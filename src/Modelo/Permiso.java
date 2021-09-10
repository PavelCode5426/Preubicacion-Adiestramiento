package Modelo;

public class Permiso {
	private int id;
	private String nombre;
	public Permiso(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Permiso() {
	    id = -1;
		nombre = "";
	}


}
