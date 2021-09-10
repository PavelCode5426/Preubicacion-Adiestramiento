package Modelo;

import java.sql.Time;
import java.sql.Date;

public class ActividadesPlan {
	private int id;
	private Date fecha;
	private int act;
	private int plan;
	private int responsable;
	private Time hora;
	private int area;
	

	public ActividadesPlan() {
		
		id = -1;
		fecha = null;
		act = -1;
		plan = -1;
		responsable = -1;
		hora = null;
		area = -1;
	}

	public int getId() {
		return id;
	}

	public Date getFecha() {
		return fecha;
	}

	public int getAct() {
		return act;
	}

	public int getPlan() {
		return plan;
	}

	public int getResponsable() {
		return responsable;
	}

	public Time getHora() {
		return hora;
	}

	public int getArea() {
		return area;
	}

	public ActividadesPlan(int id, Date fecha, int act, int plan, int responsable, Time hora, int area) {
		this.id = id;
		this.fecha = fecha;
		this.act = act;
		this.plan = plan;
		this.responsable = responsable;
		this.hora = hora;
		this.area = area;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setAct(int act) {
		this.act = act;
	}

	public void setPlan(int plan) {
		this.plan = plan;
	}

	public void setResponsable(int responsable) {
		this.responsable = responsable;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public void set(ActividadesPlan actividadesPlan)
	{

		fecha=actividadesPlan.getFecha();
		act=actividadesPlan.getAct();
		plan=actividadesPlan.getPlan();
		responsable=actividadesPlan.getResponsable();
		hora=actividadesPlan.getHora();
		area=actividadesPlan.getArea();
	}
}


