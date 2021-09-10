package Controlador;

import Auxiliar.Excepciones.AppException;
import Modelo.Directorio;
import Modelo.Etapa;
import Modelo.Tarea;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class Pantalla_Add_Nueva_Tarea_Controller implements Initializable
{

    @FXML
    private VBox vBox_Tarea;
    @FXML
    private JFXTextField textField_Nombre;
    @FXML
    private JFXListView<String> lis_participantes;
    @FXML
    private JFXButton buton_AnnadirTarea;

    private static Etapa etapa;
    private static Pantalla_Tutor_Controller padre;

    public static void setPadre(Pantalla_Tutor_Controller padre) {
        Pantalla_Add_Nueva_Tarea_Controller.padre = padre;
    }

    public static void setEtapa(Etapa etapa) {
        Pantalla_Add_Nueva_Tarea_Controller.etapa = etapa;
    }

    private static Stage stage;

    private Tarea tarea=new Tarea();
    private ArrayList<Integer> participantes;
    private ArrayList<Directorio> directorios;




    public static void setStage(Stage stage) {
        Pantalla_Add_Nueva_Tarea_Controller.stage = stage;
    }
    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            participantes=new ArrayList<>();
            lis_participantes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            directorios= Service_Locator.getInstance().getDirectorio_service().getAllActivos();
            ObservableList<String> list= FXCollections.observableArrayList();
            Iterator<Directorio> iterator=directorios.iterator();
            while (iterator.hasNext())
            {
                list.add(iterator.next().getNombreApellido());
            }
            lis_participantes.getItems().addAll(list);

            buton_AnnadirTarea.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event)
                {
                    try
                    {
                        if (!textField_Nombre.getText().equals(""))
                        {

                            tarea.setNombre(textField_Nombre.getText());
                            tarea.setEtapa(etapa.getId());
                            tarea.setFechaCumplimiento(null);
                            tarea.setCumplimiento(false);
                            tarea.setObservacion("");
                            tarea.setOtra(true);
                            tarea.setId(-1);

                            Iterator<Integer> it=lis_participantes.getSelectionModel().getSelectedIndices().iterator();
                            while (it.hasNext())
                                participantes.add(directorios.get(it.next()).getId());
                            Service_Locator.getInstance().getPlanificacion_adiestramiento_service().Create_Other_Tarea(tarea,participantes);
                            padre.LlenarTareas();
                            stage.close();
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION,"Información","Tarea añadida correctamente","DARKGREEN");
                        }
                        else throw new Exception("Formulario incompleto");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR,"Error", AppException.getJustMensaje(e.getMessage()),"DARKRED");
                    }
                }
            });






        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
