package Auxiliar;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


public class Estudiante_Data_Color_Adiestramiento_JA extends RecursiveTreeObject<Estudiante_Data_Color_Adiestramiento_JA>
{
    private SimpleObjectProperty<Label> nombre;
    private SimpleObjectProperty<Label> carnet;
    private SimpleObjectProperty<Label> especialidad;
    private SimpleObjectProperty<Label> lugar;
    private SimpleObjectProperty<Label> estado;
    private int idEstudiante;
    private int idPlanAdiestramiento;


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

    public Label getEstado() {
        return estado.get();
    }

    public SimpleObjectProperty<Label> estadoProperty() {
        return estado;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public int getIdPlanAdiestramiento() {
        return idPlanAdiestramiento;
    }

    public Estudiante_Data_Color_Adiestramiento_JA(String nom, String ci, String esp, String lug, String estado, int idEst, int idPlanA)
    {
        nombre=new SimpleObjectProperty<>(new Label(nom));
        carnet=new SimpleObjectProperty<>(new Label(ci));
        especialidad=new SimpleObjectProperty<>(new Label(esp));
        lugar=new SimpleObjectProperty<>(new Label(lug));
       this.estado=new SimpleObjectProperty<>(new Label(estado));
        idEstudiante=idEst;
        idPlanAdiestramiento=idPlanA;

        Color color=Color.ORANGE;
        if (estado.equals("Aprobado"))
            color=Color.GREEN;
        else if (estado.equals("N/A"))
            color=Color.RED;

        SetColor(color);



    }


    private void SetColor(Color color)
    {
        nombre.getValue().setTextFill(color);
        especialidad.getValue().setTextFill(color);
        carnet.getValue().setTextFill(color);
        lugar.getValue().setTextFill(color);
        estado.getValue().setTextFill(color);
    }
}
