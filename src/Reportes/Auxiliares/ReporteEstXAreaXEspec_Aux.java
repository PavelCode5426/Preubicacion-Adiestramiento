package Reportes.Auxiliares;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ReporteEstXAreaXEspec_Aux extends RecursiveTreeObject<ReporteEstXAreaXEspec_Aux> {

    private StringProperty nombre;
    private StringProperty cantEst;

    public ReporteEstXAreaXEspec_Aux(String nombre, Integer cantEst) {
        this.nombre = new SimpleStringProperty(nombre);
        this.cantEst = new SimpleStringProperty(String.valueOf(cantEst));
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getCantEst() {
        return cantEst.get();
    }

    public StringProperty cantEstProperty() {
        return cantEst;
    }
}
