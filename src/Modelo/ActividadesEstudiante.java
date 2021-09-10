package Modelo;

public class ActividadesEstudiante {
	private int id;
	private int estudiante;
	private int actividad;
	private Boolean asistencia;


	public ActividadesEstudiante() {

		id = -1;
		estudiante = -1;
		actividad = -1;
		asistencia = false;
	}

	public ActividadesEstudiante(int id, int estudiante, int actividad,int asistencia) {
		this.id = id;
		this.estudiante = estudiante;
		this.actividad = actividad;
		this.asistencia=true;
		if (asistencia==-1)
			this.asistencia=null;
		else if (asistencia==1)
			this.asistencia = false;
	}

	public int getId() {
		return id;
	}

	public int getEstudiante() {
		return estudiante;
	}

	public int getActividad() {
		return actividad;
	}


	public Boolean isAsistencia() {
		return asistencia;
	}


}
