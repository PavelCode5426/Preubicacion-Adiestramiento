package Modelo;

import java.sql.Date;

public class Estudiante
{
	private int id;
	private boolean cujaenno;
	private boolean tesisAplicable;
	private Integer procedenciaEstudiante;
	private String temaTesis;
	private String nombreApellidos;
	private Date cumpleannos;
	private int annoIngreso;
	private String nidentidad;
	private Integer especialidad;
	private Integer area;

	public int getId() {
		return id;
	}

	public boolean isCujaenno() {
		return cujaenno;
	}

	public boolean isTesisAplicable() {
		return tesisAplicable;
	}

	public Integer getProcedenciaEstudiante() {
		return procedenciaEstudiante;
	}

	public String getTemaTesis() {
		return temaTesis;
	}

	public String getNombreApellidos() {
		return nombreApellidos;
	}

	public Date getCumpleannos() {
		return cumpleannos;
	}

	public Integer getAnnoIngreso() {
		return annoIngreso;
	}

	public String getNidentidad() {
		return nidentidad;
	}

	public Integer getEspecialidad() {
		return especialidad;
	}

	public Integer getArea() {
		return area;
	}

	public Estudiante()
	{
		id = -1;
		cujaenno = false;
		tesisAplicable = false;
		procedenciaEstudiante = new Integer(-1);
	    temaTesis = "";
		nombreApellidos = "";
		cumpleannos = null;
		annoIngreso = -1;
		nidentidad = "";
		especialidad = -1;
		area = -1;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCujaenno(boolean cujaenno) {
		this.cujaenno = cujaenno;
	}

	public void setTesisAplicable(boolean tesisAplicable) {
		this.tesisAplicable = tesisAplicable;
	}

	public void setProcedenciaEstudiante(Integer procedenciaEstudiante) {
		this.procedenciaEstudiante = procedenciaEstudiante;
	}

	public void setTemaTesis(String temaTesis) {
		this.temaTesis = temaTesis;
	}

	public void setNombreApellidos(String nombreApellidos) {
		this.nombreApellidos = nombreApellidos;
	}

	public void setCumpleannos(Date cumpleannos) {
		this.cumpleannos = cumpleannos;
	}

	public void setAnnoIngreso(int annoIngreso) {
		this.annoIngreso = annoIngreso;
	}

	public void setNidentidad(String nidentidad) {
		this.nidentidad = nidentidad;
	}

	public void setEspecialidad(Integer especialidad) {
		this.especialidad = especialidad;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Estudiante(int id, boolean cujaenno, boolean tesisAplicable, int procedenciaEstudiante, String temaTesis, String nombreApellidos, Date cumpleannos, int annoIngreso, String nidentidad, Integer especialidad, Integer area)
	{
		this.id = id;
		this.cujaenno = cujaenno;
		this.tesisAplicable = tesisAplicable;
		this.procedenciaEstudiante = procedenciaEstudiante;
		this.temaTesis = temaTesis;
		this.nombreApellidos = nombreApellidos;
		this.cumpleannos = cumpleannos;
		this.annoIngreso = annoIngreso;
		this.nidentidad = nidentidad;
		this.especialidad = especialidad;
		this.area = area;
	}
	public void set(Estudiante e)
	{
		this.id = e.getId();
		this.cujaenno = e.isCujaenno();
		this.tesisAplicable = e.isTesisAplicable();
		this.procedenciaEstudiante = e.getProcedenciaEstudiante();
		this.temaTesis = e.getTemaTesis();
		this.nombreApellidos = e.getNombreApellidos();
		this.cumpleannos = e.getCumpleannos();
		this.annoIngreso = e.getAnnoIngreso();
		this.nidentidad = e.getNidentidad();
		this.especialidad = e.getEspecialidad();
	}



}
