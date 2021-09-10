package Auxiliar;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Plan_Adiestramiento
        extends RecursiveTreeObject<Plan_Adiestramiento>
{
    private StringProperty tarea;
    private StringProperty responsable;
    private StringProperty participante;
    private int idPla;

    public int getIdPla() {
        return idPla;
    }

    public Plan_Adiestramiento(String tarea, String responsable, String participante, int id) {
        this.tarea = new SimpleStringProperty(tarea);
        this.responsable = new SimpleStringProperty(responsable);
        this.participante = new SimpleStringProperty(participante);
        idPla=id;
    }

    public String getTarea() {
        return tarea.get();
    }

    public StringProperty tareaProperty() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea.set(tarea);
    }

    public String getResponsable() {
        return responsable.get();
    }

    public StringProperty responsableProperty() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable.set(responsable);
    }

    public String getParticipante() {
        return participante.get();
    }

    public StringProperty participanteProperty() {
        return participante;
    }

    public void setParticipante(String participante) {
        this.participante.set(participante);
    }

}
