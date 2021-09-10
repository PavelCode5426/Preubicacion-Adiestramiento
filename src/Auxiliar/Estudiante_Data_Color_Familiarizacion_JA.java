package Auxiliar;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Estudiante_Data_Color_Familiarizacion_JA extends RecursiveTreeObject<Estudiante_Data_Color_Familiarizacion_JA>
{
    private SimpleObjectProperty<Label> nombre;
    private SimpleObjectProperty<Label> carnet;
    private SimpleObjectProperty<Label> especialidad;
    private SimpleObjectProperty<Label> lugar;
    private SimpleObjectProperty<Label> asistencia;
    private int idEstudiante;
    private int idInner;

    public Estudiante_Data_Color_Familiarizacion_JA(String nom, String ci, String esp, String lug, double asist, int idEst, int idPlanF)
    {
        nombre=new SimpleObjectProperty<>(new Label(nom));
        carnet=new SimpleObjectProperty<>(new Label(ci));
        especialidad=new SimpleObjectProperty<>(new Label(esp));
        lugar=new SimpleObjectProperty<>(new Label(lug));
        asistencia=new SimpleObjectProperty<>(new Label(Double.toString(asist)));
        idEstudiante=idEst;
        idInner =idPlanF;

        if (asist>=20)
            SetColor(Color.DARKRED);
        else if (asist>=10)
            SetColor(Color.DARKORANGE);
        else
            SetColor(Color.DARKGREEN);
    }

    public Label getNombre() {
        return nombre.get();
    }

    public SimpleObjectProperty<Label> nombreProperty() {
        return nombre;
    }

    public Label getCarnet() {
        return carnet.get();
    }

    public SimpleObjectProperty<Label> carnetProperty() {
        return carnet;
    }

    public Label getEspecialidad() {
        return especialidad.get();
    }

    public SimpleObjectProperty<Label> especialidadProperty() {
        return especialidad;
    }

    public Label getLugar() {
        return lugar.get();
    }

    public SimpleObjectProperty<Label> lugarProperty() {
        return lugar;
    }

    public Label getAsistencia() {
        return asistencia.get();
    }

    public SimpleObjectProperty<Label> asistenciaProperty() {
        return asistencia;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public int getIdInner() {
        return idInner;
    }

    private void SetColor(Color color)
    {
        nombre.getValue().setTextFill(color);
        especialidad.getValue().setTextFill(color);
        carnet.getValue().setTextFill(color);
        lugar.getValue().setTextFill(color);
        asistencia.getValue().setTextFill(color);
    }

}
