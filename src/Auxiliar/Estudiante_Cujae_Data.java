package Auxiliar;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;

public class Estudiante_Cujae_Data extends RecursiveTreeObject<Estudiante_Cujae_Data>
{
    private int idEst;
    private SimpleObjectProperty<JFXCheckBox> isAplicable;
    private SimpleStringProperty nombre;
    private SimpleStringProperty carnet;
    private SimpleStringProperty especialidad;


    public Estudiante_Cujae_Data(int idEst, boolean Aplicable, String nombre, String carnet, String especialidad) {
        this.nombre=new SimpleStringProperty(nombre);
        this.carnet=new SimpleStringProperty(carnet);
        this.especialidad=new SimpleStringProperty(especialidad);

        final JFXCheckBox checkBox=new JFXCheckBox();
        checkBox.setCheckedColor(Color.DARKGREEN);
        checkBox.setSelected(Aplicable);
        checkBox.selectedProperty().addListener(observable -> {checkBox.setSelected(Aplicable);});
        isAplicable=new SimpleObjectProperty<>(checkBox);
        this.idEst=idEst;
    }

    public int getIdEst() {
        return idEst;
    }
    public JFXCheckBox getIsAplicable() {
        return isAplicable.get();
    }
    public SimpleObjectProperty<JFXCheckBox> isAplicableProperty() {
        return isAplicable;
    }
    public String getNombre() {
        return nombre.get();
    }
    public SimpleStringProperty nombreProperty() {
        return nombre;
    }
    public String getCarnet() {
        return carnet.get();
    }
    public SimpleStringProperty carnetProperty() {
        return carnet;
    }
    public String getEspecialidad() {
        return especialidad.get();
    }
    public SimpleStringProperty especialidadProperty() {
        return especialidad;
    }

}
