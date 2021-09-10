package Auxiliar;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Conformado_Estado_Data extends RecursiveTreeObject<Conformado_Estado_Data>
{
    StringProperty conformado;
    StringProperty aprobado;
    StringProperty estado;
    StringProperty anno;
    int idPlan;

    public Conformado_Estado_Data(String nombre, String especialidad,int idPlan) {
        conformado = new SimpleStringProperty(nombre);
        estado = new SimpleStringProperty(especialidad);
        this.idPlan=idPlan;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public Conformado_Estado_Data(String conformado, String aprobado, String estado, String anno, int getIdPlan) {
        this.conformado = new SimpleStringProperty(conformado);
        this.aprobado = new SimpleStringProperty(aprobado);
        this.estado = new SimpleStringProperty(estado);
        this.anno = new SimpleStringProperty(anno);
        this.idPlan=idPlan;
    }

    public String getAprobado() {
        return aprobado.get();
    }

    public StringProperty aprobadoProperty() {
        return aprobado;
    }

    public String getAnno() {
        return anno.get();
    }

    public StringProperty annoProperty() {
        return anno;
    }

    public StringProperty conformadoProperty() {
        return conformado;
    }
    public StringProperty estadoProperty() {
        return estado;
    }

    public String getConformado() {
        return conformado.get();
    }

    public String getEstado() {
        return estado.get();
    }
}
