package Modelo;

public class Directorio {
	private int id;
	private String carnetIdentidad;
	private String cargo;
	private String nombreApellido;
	private int area;
	private String nusuario;
	private String contrasenna;
	private boolean activo ;

	public Directorio(int id, String carnetIdentidad, String cargo, String nombreApellido, int a, String nusuario, String contrasenna, boolean activo) {
		this.id = id;
		this.carnetIdentidad = carnetIdentidad;
		this.cargo = cargo;
		this.nombreApellido = nombreApellido;
		this.area= a;
		this.nusuario = nusuario;
		this.contrasenna = contrasenna;
		this.activo = activo;
	}

	public void setCarnetIdentidad(String carnetIdentidad) {
		this.carnetIdentidad = carnetIdentidad;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public void setNusuario(String nusuario) {
		this.nusuario = nusuario;
	}

	public void setContrasenna(String contrasenna) {
		this.contrasenna = contrasenna;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getId() {
		return id;
	}

	public String getCarnetIdentidad() {
		return carnetIdentidad;
	}

	public String getCargo() {
		return cargo;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public int getArea() {
		return area;
	}

	public String getNusuario() {
		return nusuario;
	}

	public String getContrasenna() {
		return contrasenna;
	}

	public boolean isActivo() {
		return activo;
	}

	public Directorio() {
		
		id = -1;
		carnetIdentidad = "";
		cargo = "";
		nombreApellido = "";
		nusuario = "";
		area=-1;
		contrasenna = "";
		activo = false;
	}

}
