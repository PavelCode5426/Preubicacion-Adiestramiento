package Modelo;

import java.sql.Date;

public class Tarea {
	private int id;
	private String nombre;
	private int etapa;
	private boolean cumplimiento;
	private Date fechaCumplimiento;
	private String observacion;
	private boolean otra;
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public boolean isOtra() {
		return otra;
	}

	public void setOtra(boolean otra) {
		this.otra = otra;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEtapa(int etapa) {
		this.etapa = etapa;
	}

	public void setCumplimiento(boolean cumplimiento) {
		this.cumplimiento = cumplimiento;
	}

	public void setFechaCumplimiento(Date fechaCumplimiento) {
		this.fechaCumplimiento = fechaCumplimiento;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getObservacion() {
		return observacion;
	}

	public int getEtapa() {
		return etapa;
	}

	public boolean isCumplimiento() {
		return cumplimiento;
	}

	public Date getFechaCumplimiento() {
		return fechaCumplimiento;
	}

	public Tarea(int id, String nombre, int etapa, boolean cumplimiento, Date fechaCumplimiento,String observacion,boolean otra) {
		this.id = id;
		this.nombre = nombre;
		this.etapa = etapa;
		this.cumplimiento = cumplimiento;
		this.fechaCumplimiento = fechaCumplimiento;
		if (observacion==null)
		this.observacion="Sin Observaciones";
		else
			this.observacion=observacion;
		this.otra=otra;
	}

	public Tarea() {
		
		id = -1;
		nombre = "";
		etapa = 0;
		cumplimiento = false;
		fechaCumplimiento = null;
		otra=false;
	}


}
