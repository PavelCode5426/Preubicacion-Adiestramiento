package Modelo;

import java.sql.Date;

public class Etapa {
	private int id;
	private int plan;
	private int nombre;
	private Date fechaInicio;
	private Date fechaFinal;
	private String objetivo;
	private Integer nota;
	public Etapa() {
		
		id =-1;
		plan = -1;
		nombre = -1;
		fechaInicio = null;
		fechaFinal = null;
		objetivo = "";
		nota = -1;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPlan(int plan) {
		this.plan = plan;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public int getId() {
		return id;
	}

	public int getPlan() {
		return plan;
	}

	public int getNombre() {
		return nombre;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public String getObjetivo() {
		return objetivo;
	}



	public Etapa(int id, int plan, int nombre, Date fechaInicio, Date fechaFinal, String objetivo, Integer nota) {
		this.id = id;
		this.plan = plan;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.objetivo = objetivo;
		this.nota = nota;
	}
}
