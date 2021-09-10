package Reportes.Auxiliares;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ReporteTesisAplicable_Aux  extends RecursiveTreeObject<ReporteTesisAplicable_Aux> {

    public ReporteTesisAplicable_Aux(String nombre, String carnet, String temaTesis, String lugarProc) {
        this.nombre = new SimpleStringProperty(nombre);
        this.carnet = new SimpleStringProperty(carnet);
        this.temaTesis = new SimpleStringProperty(temaTesis);
        this.lugarProc = new SimpleStringProperty(lugarProc);

    }

    private StringProperty nombre;
    private StringProperty carnet;
    private StringProperty temaTesis;
    private StringProperty lugarProc;
    private StringProperty especialidad;


    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty carnetProperty() {
        return carnet;
    }

    public StringProperty temaTesisProperty() {
        return temaTesis;
    }

    public StringProperty lugarProcProperty() {
        return lugarProc;
    }

    public StringProperty especialidadProperty() {
        return especialidad;
    }


    public ReporteTesisAplicable_Aux(String nombre, String carnet, String temaTesis, String lugarProc, String especialidad) {
        this.nombre = new SimpleStringProperty(nombre);
        this.carnet = new SimpleStringProperty(carnet);
        this.temaTesis = new SimpleStringProperty(temaTesis);
        this.lugarProc = new SimpleStringProperty(lugarProc);
        this.especialidad = new SimpleStringProperty(especialidad);
    }




    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(StringProperty nombre) {
        this.nombre = nombre;
    }

    public String getCarnet() {
        return carnet.get();
    }

    public void setCarnet(StringProperty carnet) {
        this.carnet = carnet;
    }

    public String getTemaTesis() {
        return temaTesis.get();
    }

    public void setTemaTesis(StringProperty temaTesis) {
        this.temaTesis = temaTesis;
    }

    public String getLugarProc() {
        return lugarProc.get();
    }

    public void setLugarProc(StringProperty lugarProc) {
        this.lugarProc = lugarProc;
    }

    public String getEspecialidad() {
        return especialidad.get();
    }

    public void setEspecialidad(StringProperty especialidad) {
        this.especialidad = especialidad;
    }


}