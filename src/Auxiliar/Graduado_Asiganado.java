package Auxiliar;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;

public class Graduado_Asiganado extends RecursiveTreeObject<Graduado_Asiganado>
{
    StringProperty Nombre;
    StringProperty Especialidad;
    StringProperty Procedencia;
    StringProperty annoIngreso;
    private int idest;


    public Graduado_Asiganado(String nombre, String especialidad, String procedencia, int annoIngreso,int id) {
        Nombre = new SimpleStringProperty(nombre);
        Especialidad = new SimpleStringProperty(especialidad);
        Procedencia = new SimpleStringProperty(procedencia);
        this.annoIngreso = new SimpleStringProperty(String.valueOf(annoIngreso));
        idest=id;
    }

    public String getNombre() {
        return Nombre.get();
    }

    public StringProperty nombreProperty() {
        return Nombre;
    }

    public String getEspecialidad() {
        return Especialidad.get();
    }

    public StringProperty especialidadProperty() {
        return Especialidad;
    }

    public String getProcedencia() {
        return Procedencia.get();
    }

    public StringProperty procedenciaProperty() {
        return Procedencia;
    }

    public void setNombre(String nombre) {
        this.Nombre.set(nombre);
    }

    public void setEspecialidad(String especialidad) {
        this.Especialidad.set(especialidad);
    }

    public void setProcedencia(String procedencia) {
        this.Procedencia.set(procedencia);
    }

    public String getAnnoIngreso() {
        return annoIngreso.get();
    }

    public StringProperty annoIngresoProperty() {
        return annoIngreso;
    }

    public void setAnnoIngreso(String annoIngreso) {
        this.annoIngreso.set(annoIngreso);
    }

    public int getIdest() {
        return idest;
    }
}
