package Modelo;

public class Actividad {
	private int id;
	private String nombre;
	private String descripcion;
	


	public Actividad(int id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
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

	public Actividad() {
	
		id = -1;
		nombre = "";
		descripcion = "";
	}

	public void set(Actividad a)
	{
		id=a.getId();
		nombre=a.getNombre();
		descripcion=a.getDescripcion();
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
