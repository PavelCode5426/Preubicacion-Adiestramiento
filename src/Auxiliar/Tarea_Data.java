package Auxiliar;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tarea_Data  extends RecursiveTreeObject<Tarea_Data>
{
    private StringProperty nombre;
    private StringProperty fechaCump;
    private StringProperty observ;
    private StringProperty estado;
    private int idTarea;

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getFechaCump() {
        return fechaCump.get();
    }

    public StringProperty fechaCumpProperty() {
        return fechaCump;
    }

    public void setFechaCump(String fechaCump) {
        this.fechaCump.set(fechaCump);
    }

    public String getObserv() {
        return observ.get();
    }

    public StringProperty observProperty() {
        return observ;
    }

    public void setObserv(String observ) {
        this.observ.set(observ);
    }

    public String getEstado() {
        return estado.get();
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    public int getIdTarea() {
        return idTarea;
    }

    public Tarea_Data(String nombre, String fechaCump, String observ, String estado, int id) {
        this.nombre = new SimpleStringProperty(nombre);
        this.fechaCump = new SimpleStringProperty(fechaCump);
        this.observ = new SimpleStringProperty(observ);
        this.estado = new SimpleStringProperty(estado);
        idTarea=id;
    }
}
