package Controlador;

import Auxiliar.Excepciones.AppException;
import Modelo.Tarea;
import Servicio.Repository;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import Vista.IDraggedScene;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Pantalla_Evaluar_Tara_Controller implements Initializable, IDraggedScene
{

    @FXML
    private Label nomlab;
    @FXML
    private Label partlab;
    @FXML
    private JFXCheckBox check;
    @FXML
    private JFXDatePicker datepick;
    @FXML
    private JFXTextArea obs;
    @FXML
    private JFXButton acep;
    @FXML
    private JFXButton cancel;

    @FXML
    private AnchorPane PaneExterior;

    /*
    Declaraciones
     */

    private static Tarea tarea;
    private static Stage stage;
    private static Pantalla_Tutor_Controller padre;


    public static void setPadre(Pantalla_Tutor_Controller padre) {
        Pantalla_Evaluar_Tara_Controller.padre = padre;
    }

    public static void setTarea(Tarea tarea)
    {
        Pantalla_Evaluar_Tara_Controller.tarea = tarea;
    }
    public static void setStage(Stage stage) {
        Pantalla_Evaluar_Tara_Controller.stage = stage;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        onDraggedScene(PaneExterior);

            try {
            nomlab.setText(nomlab.getText() + " " + tarea.getNombre());
            check.setSelected(tarea.getFechaCumplimiento()!=null);
            partlab.setText(partlab.getText() + " " + Service_Locator.getInstance().getPlanificacion_adiestramiento_service().getParticipantesfromTarea_SQL(tarea.getId()));
            datepick.setDisable(true);
            obs.setText(tarea.getObservacion());
            if (check.isSelected())
            datepick.setValue(tarea.getFechaCumplimiento().toLocalDate());
            else datepick.setValue(LocalDate.now());
            cancel.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                }
            });
            acep.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Update_Tarea();
                        padre.LlenarTareas();
                        stage.close();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION,"Información","Tarea evaluada correctamente","DARKGREEN");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR,"Error", AppException.getJustMensaje(e.getMessage()),"DARKRED");
                    }

                }
            });


            check.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    datepick.setDisable(!check.isSelected());
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void Update_Tarea()throws Exception
    {
        tarea.setCumplimiento(check.isSelected());
        if (check.isSelected())
            tarea.setFechaCumplimiento(Date.valueOf(datepick.getValue()));
        if(datepick.getValue()==null && check.isSelected())
            throw new Exception("Formulario Incompleto");
        tarea.setObservacion(obs.getText());

        Service_Locator.getInstance().getPlanificacion_adiestramiento_service().Update_Tarea(tarea);
    }











}
