package Modelo;

public class EstudiantePlanFamiliarizacion {
	private int id;
	private int estudiante;
	private int plan;
	private Integer nota;

	public void setId(int id) {
		this.id = id;
	}

	public void setEstudiante(int estudiante) {
		this.estudiante = estudiante;
	}

	public void setPlan(int plan) {
		this.plan = plan;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public EstudiantePlanFamiliarizacion(int id, int estudiante, int plan, int nota) {
		this.id = id;
		this.estudiante = estudiante;
		this.plan = plan;
		if (nota==0)
			this.nota=null;
		else
			this.nota=nota;
	}

	public Integer getNota() {
		return nota;
	}

	public EstudiantePlanFamiliarizacion() {
		
		id = -1;
	    estudiante=-1;
		plan = -1;
		nota=null;
	}

	public int getId() {
		return id;
	}

	public int getEstudiante() {
		return estudiante;
	}

	public int getPlan() {
		return plan;
	}
}
