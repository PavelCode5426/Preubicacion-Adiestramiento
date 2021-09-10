package Reportes.Auxiliares;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reporte_AsistenciaActiv_X_Area_Aux extends RecursiveTreeObject<Reporte_AsistenciaActiv_X_Area_Aux> {

    private StringProperty nombreAct;
      private StringProperty fecha;
    private StringProperty asistencia;
    private StringProperty hora;



    public Reporte_AsistenciaActiv_X_Area_Aux(String nombreAct, String fecha, String asistencia, String hora) {
        this.nombreAct = new SimpleStringProperty(nombreAct);
          this.fecha =new SimpleStringProperty( fecha);
        this.asistencia = new SimpleStringProperty(asistencia);
        this.hora = new SimpleStringProperty(hora);
    }


    public String getNombreAct() {
        return nombreAct.get();
    }

    public StringProperty nombreActProperty() {
        return nombreAct;
    }

    public void setNombreAct(String nombreAct) {
        this.nombreAct.set(nombreAct);
    }


    public String getFecha() {
        return fecha.get();
    }

    public StringProperty fechaProperty() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public String getAsistencia() {
        return asistencia.get();
    }

    public StringProperty asistenciaProperty() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia.set(asistencia);
    }

    public String getHora() {
        return hora.get();
    }

    public StringProperty horaProperty() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora.set(hora);
    }


}
