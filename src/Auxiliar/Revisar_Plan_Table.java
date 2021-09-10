package Auxiliar;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Revisar_Plan_Table extends RecursiveTreeObject<Revisar_Plan_Table>
{
    private StringProperty nombre;
    private StringProperty cant;
    private StringProperty estado;
    private StringProperty anno;
    private int id;

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getCantgrad() {
        return cant.get();
    }

    public StringProperty cantgradProperty() {
        return cant;
    }

    public String getEstado() {
        return estado.get();
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public String getAnno() {
        return anno.get();
    }

    public StringProperty annoProperty() {
        return anno;
    }

    public int getId() {
        return id;
    }

    public Revisar_Plan_Table(String nombre, String cantgrad, String estado, String anno, int id) {
        this.nombre = new SimpleStringProperty(nombre);
        this.cant = new SimpleStringProperty(cantgrad);
        this.estado = new SimpleStringProperty(estado);
        this.anno = new SimpleStringProperty(anno);
        this.id=id;
    }
}
