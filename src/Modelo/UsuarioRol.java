package Modelo;

import java.sql.Date;

public class UsuarioRol {
	private int id;
	private int usuario;
	private int role;
	private Date fechaAsignacion;
	
	public UsuarioRol(int id, int usuario, int r, Date fechaAsignacion) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.role = r;
		this.fechaAsignacion = fechaAsignacion;
	}

	public int getId() {
		return id;
	}

	public int getUsuario() {
		return usuario;
	}

	public int getRole() {
		return role;
	}

	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	public UsuarioRol() {
		
		id = -1;
		usuario = -1;
		role = -1;
		fechaAsignacion = null;
	}


}
