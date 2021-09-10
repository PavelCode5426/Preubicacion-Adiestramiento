package Modelo;

public class Area {
	private Integer id;
	private String nombre;
	
	public Area(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	public Area() {
		
		id = -1;
		nombre = "";
	}

	public Integer getId() {
		return id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
}
