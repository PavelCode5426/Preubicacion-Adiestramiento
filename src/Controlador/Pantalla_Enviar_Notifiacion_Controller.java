package Controlador;

import Auxiliar.FiltrarTablas;
import Modelo.Directorio;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import Vista.IDraggedScene;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.sun.istack.internal.Nullable;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.security.DigestException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Pantalla_Enviar_Notifiacion_Controller implements Initializable, IDraggedScene
{

    @FXML
    private Label titulo;
    @FXML
    private JFXComboBox<String> buscar;
    @FXML
    private JFXTextArea sms;
    @FXML
    private JFXButton acept;
    @FXML
    private JFXButton cancel;
    @FXML
    private JFXCheckBox todos;
    @FXML
    private AnchorPane pane;


    ///////Declaraciones de Variables
    private String titulo_Pane;
    private Integer remitente;
    private boolean exclusiva;
    private ArrayList<Directorio> directorios;


    private static Stage stage=null;

    public static void setStage(Stage stage) {
        Pantalla_Enviar_Notifiacion_Controller.stage = stage;
    }

    public Pantalla_Enviar_Notifiacion_Controller(@Nullable String titulo_Pane, Integer remitente, boolean exclusiva) {
        this.titulo_Pane = titulo_Pane;
        this.remitente = remitente;
        this.exclusiva = exclusiva;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        onDraggedScene(pane);
        try {

            directorios = Service_Locator.getInstance().getDirectorio_service().getAllActivos();
            LlenarCombobox();
            titulo.setText(titulo_Pane);
            if (exclusiva)
            {
                buscar.setDisable(true);
                todos.setDisable(true);
            }
            buscar.getEditor().setOnKeyTyped(new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent event) {
                    String text=buscar.getEditor().getText();
                    String e=event.getCharacter();
                    if (e.equals("\b")||e.equals("\t"))
                        e="";
                    text+=e;
                    LlenarCombobox();
                    if (!text.equals(""))
                    {
                        buscar.show();
                        buscar.setItems(FiltrarCombox(text));
                    }
                }
            });

            todos.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    buscar.setDisable(todos.isSelected());
                }
            });

            acept.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try
                    {
                        if (validacion()) {
                            if (todos.isSelected())
                                Service_Locator.getInstance().getNotificacion_service().EnviarNotificacionMasiva(sms.getText());
                            else
                                Service_Locator.getInstance().getNotificacion_service().EnviarNotificacion(sms.getText(), directorioByName(buscar.getSelectionModel().getSelectedItem()).getId());
                            stage.close();
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION,"Información","Noticación enviada correctamente","DARKGREEN");
                        }
                        else throw new Exception("Formulario Incompleto.");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR,"Error",e.getMessage(),"DARKRED");
                    }

                }
            });

            cancel.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                }
            });



        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private Directorio directorioByName(String name)    {
        Iterator<Directorio> iterator=directorios.iterator();
        Directorio hold=null;
        boolean is=false;
        while (iterator.hasNext()&&!is)
        {
            hold=iterator.next();
            if (hold.getNombreApellido().equals(name))
                is=true;
        }
        return hold;
    }
    private void LlenarCombobox()
    {
        Iterator<Directorio> iterator=directorios.iterator();
        Directorio hold=null;
        int miId=Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId();
        buscar.getItems().clear();
        if (remitente==null)
            while (iterator.hasNext())
            {
                hold=iterator.next();
                if (hold.getId()!=miId)
                    buscar.getItems().add(hold.getNombreApellido());
            }
        else
        {
            while (iterator.hasNext()) {
                hold = iterator.next();
                if (hold.getId() == remitente.intValue())
                    buscar.getItems().add(hold.getNombreApellido());
            }
            buscar.getSelectionModel().select(0);
        }
        buscar.getItems().sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
    }
    private ObservableList<String> FiltrarCombox(String tex)    {
        FiltrarTablas<String> tablas=new FiltrarTablas<>();
        Predicate<String> predicate=new Predicate<String>() {
            @Override
            public boolean test(String s)
            {
                boolean is=true;
                if (s.toLowerCase().startsWith(tex.toLowerCase()))
                    is=false;
                return is;
            }
        };
        return tablas.FiltrarTabla(buscar.getItems(),predicate);
    }
    private boolean validacion()    {
        boolean is=true;
        if (sms.getText().equals("")||buscar.getSelectionModel().isEmpty())
            is=false;
        return is;
    }








}
