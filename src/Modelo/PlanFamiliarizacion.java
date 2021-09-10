package Modelo;

public class PlanFamiliarizacion {
	private int id;
	private int anno;
	private int confeccionado;
	private Integer aprobado;
	private boolean revisado;
	private boolean rechazado;
	public PlanFamiliarizacion() {
	
		id = -1;
		anno = 0;
		confeccionado = -1;
		aprobado = null;
		revisado=false;
		rechazado=false;
	}

	public int getId() {
		return id;
	}

	public int getAnno() {
		return anno;
	}

	public int getConfeccionado() {
		return confeccionado;
	}

	public Integer getAprobado() {
		return aprobado;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public void setConfeccionado(int confeccionado) {
		this.confeccionado = confeccionado;
	}

	public void setAprobado(Integer aprobado) {
		this.aprobado = aprobado;
	}

	public void setRevisado(boolean revisado) {
		this.revisado = revisado;
	}

	public boolean isRechazado() {
		return rechazado;
	}

	public void setRechazado(boolean rechazado) {
		this.rechazado = rechazado;
	}

	public boolean isRevisado() {
		return revisado;
	}

	public PlanFamiliarizacion(int id, int anno, int confeccionado, Integer aprobado, boolean revisado, boolean rechazado) {
		this.id = id;
		this.anno = anno;
		this.confeccionado = confeccionado;
		if (aprobado!=null&&aprobado!=0)
		this.aprobado = aprobado;
		else
			this.aprobado=null;
		this.revisado = revisado;
		this.rechazado = rechazado;
	}
}
