package Modelo;

public class RolPermiso {
	private int id;
	private int permiso;
	private int rol;
	public RolPermiso(int id, int permiso, int r) {
		super();
		this.id = id;
		this.permiso = permiso;
		rol=r;
	}

	public int getId() {
		return id;
	}

	public int getPermiso() {
		return permiso;
	}

	public int getRol() {
		return rol;
	}

	public RolPermiso() {
		
		id = -1;
		permiso = -1;
		rol=-1;
	}


}
