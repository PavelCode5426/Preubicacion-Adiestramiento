package Auxiliar;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.sun.org.apache.regexp.internal.recompile;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Plan_Familiarizacion extends RecursiveTreeObject<Plan_Familiarizacion>
{
    private StringProperty actividad;
    private StringProperty responsable;
    private StringProperty lugar;
    private StringProperty fecha;
    private StringProperty hora;
    private int idPl;

    public int getIdPl() {
        return idPl;
    }

    public Plan_Familiarizacion(String actividad, String responsable, String lugar, String fecha, String hora, int id) {
        this.actividad = new SimpleStringProperty(actividad);
        this.responsable = new SimpleStringProperty(responsable);
        this.lugar = new SimpleStringProperty(lugar);
        this.fecha = new SimpleStringProperty(fecha);
        this.hora = new SimpleStringProperty(hora);
        idPl=id;
    }

    public String getActividad() {
        return actividad.get();
    }

    public StringProperty actividadProperty() {
        return actividad;
    }
    public StringProperty responsableProperty() {
        return responsable;
    }
    public StringProperty fechaProperty() {
        return fecha;
    }
    public String getHora() {
        return hora.get();
    }
    public StringProperty horaProperty() {
        return hora;
    }
    public StringProperty lugarProperty() {
        return lugar;
    }
}
