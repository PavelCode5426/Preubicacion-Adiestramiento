package Modelo;

public class PlanAdiestramiento {
	private int id;
	private Integer aprobado;
	private int annoInicio;
	private int tutor;
	private int estudiante;
	private boolean revisado;


	public PlanAdiestramiento() {
		
		id = -1;
		aprobado = -1;
	    annoInicio = -1;
		tutor = -1;
		estudiante=-1;
		revisado=false;
	}

	public PlanAdiestramiento(int id, Integer aprobado, int annoInicio, int tutor, Integer estudiante, boolean revisado) {
		this.id = id;
		this.aprobado = aprobado;
		this.annoInicio = annoInicio;
		this.tutor = tutor;
		this.estudiante = estudiante;
		this.revisado = revisado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getAprobado() {
		return aprobado;
	}

	public void setAprobado(Integer aprobado) {
		this.aprobado = aprobado;
	}

	public int getAnnoInicio() {
		return annoInicio;
	}

	public void setAnnoInicio(int annoInicio) {
		this.annoInicio = annoInicio;
	}

	public int getTutor() {
		return tutor;
	}

	public void setTutor(int tutor) {
		this.tutor = tutor;
	}

	public int getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(int estudiante) {
		this.estudiante = estudiante;
	}

	public boolean isRevisado() {
		return revisado;
	}



	public void setRevisado(boolean revisado) {
		this.revisado = revisado;
	}
}
