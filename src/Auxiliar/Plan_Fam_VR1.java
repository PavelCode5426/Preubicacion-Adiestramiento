package Auxiliar;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TreeTableColumn;

public class Plan_Fam_VR1 extends RecursiveTreeObject<Plan_Fam_VR1>
{
    private StringProperty actividad;
    private StringProperty responsable;
    private StringProperty lugar;
    private StringProperty fecha;
    private StringProperty hora;
    private StringProperty desc;

    public Plan_Fam_VR1(String actividad, String responsable, String lugar, String fecha, String hora,String desc) {
        this.actividad = new SimpleStringProperty(actividad);
        this.responsable = new SimpleStringProperty(responsable);
        this.lugar = new SimpleStringProperty(lugar);
        this.fecha = new SimpleStringProperty(fecha);
        this.hora = new SimpleStringProperty(hora);
        this.desc=new SimpleStringProperty(desc);
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

    public String getActividad() {
        return actividad.get();
    }

    public String getResponsable() {
        return responsable.get();
    }

    public String getLugar() {
        return lugar.get();
    }

    public String getFecha() {
        return fecha.get();
    }

    public String getDesc() {
        return desc.get();
    }

    public StringProperty descProperty() {
        return desc;
    }

    public StringProperty lugarProperty() {
        return lugar;
    }
}
