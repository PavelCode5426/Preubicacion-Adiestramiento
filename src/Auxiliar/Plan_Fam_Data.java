package Auxiliar;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Plan_Fam_Data extends RecursiveTreeObject<Plan_Fam_Data>
{
    private StringProperty nombre;
    private StringProperty cantgrad;
    private StringProperty estado;
    private int idPla;

    public int getIdPla() {
        return idPla;
    }

    public Plan_Fam_Data(String nombre, String cant, String estado, int id)
    {
        this.nombre=new SimpleStringProperty(nombre);
        cantgrad=new SimpleStringProperty(cant);
        this.estado=new SimpleStringProperty(estado);
        idPla=id;
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty cantgradProperty() {
        return cantgrad;
    }

    public StringProperty estadoProperty() {
        return estado;
    }
}
