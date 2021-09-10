package Auxiliar;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Area_JefeA_CantG extends RecursiveTreeObject<Area_JefeA_CantG>
{
    public StringProperty areaProperty() {
        return area;
    }

    public StringProperty jefeProperty() {
        return jefe;
    }

    public StringProperty cantGProperty() {
        return cantG;
    }

    private int idJefe;
    private StringProperty area;
    private StringProperty jefe;

    public int getIdJefe() {
        return idJefe;
    }

    private StringProperty cantG;

    public Area_JefeA_CantG(String area,String jefe,int cantG,int idJefe)
    {
        this.area=new SimpleStringProperty(area);
        this.jefe=new SimpleStringProperty(jefe);
        this.cantG=new SimpleStringProperty(new Integer(cantG).toString());
        this.idJefe=idJefe;
    }
}
