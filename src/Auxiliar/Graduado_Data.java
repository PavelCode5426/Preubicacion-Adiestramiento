package Auxiliar;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Graduado_Data extends RecursiveTreeObject<Graduado_Data>
{
    private int id;
    private StringProperty nombre;
    private StringProperty ci;
    private StringProperty especialidad;
    private StringProperty area;
    private StringProperty anno;

    public int getId() {
        return id;
    }

    public Graduado_Data(String nom, String ci, String esp, String area, String anno, int id) {
        this.nombre = new SimpleStringProperty(nom);
        this.ci = new SimpleStringProperty(ci);
        this.especialidad = new SimpleStringProperty(esp);
        this.area = new SimpleStringProperty(area);
        this.anno = new SimpleStringProperty(anno);
        this.id=id;
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getCi() {
        return ci.get();
    }

    public StringProperty ciProperty() {
        return ci;
    }

    public String getEspecialidad() {
        return especialidad.get();
    }

    public StringProperty especialidadProperty() {
        return especialidad;
    }

    public String getArea() {
        return area.get();
    }

    public StringProperty areaProperty() {
        return area;
    }

    public String getAnno() {
        return anno.get();
    }

    public StringProperty annoProperty() {
        return anno;
    }
}
