package Controlador;

import Auxiliar.Parent_Controller_Auxiliar;
import Auxiliar.Tupla;
import Modelo.Directorio;
import Modelo.Notificacion;
import Servicio.Repository;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import Vista.IDraggedScene;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.ResourceBundle;

public class Pantalla_Notificaciones_Controller implements Initializable, IDraggedScene
{
    @FXML
    private AnchorPane PaneExterior;
    @FXML
    private VBox panel_Noti;
    @FXML
    private JFXButton selc_btn;
    @FXML
    private JFXButton del_btn;
    @FXML
    private JFXButton enviar;
    @FXML
    private JFXButton botonCerrarVentana;


    private  MenuItem menuItem;

    public Pantalla_Notificaciones_Controller(MenuItem menuItem)
    {
        this.menuItem = menuItem;
    }

    private ArrayList<Notificacion_Pane_Controller> controllers=new ArrayList<>();

    private static Stage stage;
    private ArrayList<Tupla<Notificacion>> notificacions;
    private Directorio directorio;
    private boolean isSelecteAll =false;
    public static void setStage(Stage stage)
    {
        Pantalla_Notificaciones_Controller.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        onDraggedScene(PaneExterior);
        ConfigurarMinimizarMaximizar(PaneExterior,null,botonCerrarVentana);

        try
        {
            directorio= Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser();
            LlenarNotificaciones();
            MostrarNotificaciones();

            panel_Noti.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    BorrarCorrect();
                }
            });
            del_btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event)
                {
                    try
                    {
                        Delete_Notificacion();
                        MostrarNotificaciones();
                        selc_btn.setText("Seleccionar Todo");
                        del_btn.setText("Borrar");
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION,"Información","Notifiaciones borradas correctamente","DARKGREEN");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            selc_btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        if (!isSelecteAll)
                            selc_btn.setText("Deseleccionar Todo");
                        else
                            selc_btn.setText("Seleccionar Todo");
                        SelectAll();

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }


                }


            });

            enviar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event)
                {
                    try {
                        GetPantalla.Pantalla_Enviar_Notifiacion("Enviar notificación",null,false);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            });


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void LlenarNotificaciones()throws Exception
    {
        notificacions=new ArrayList<>();
        Iterator<Notificacion>  notificacionIterator=Service_Locator.getInstance().getNotificacion_service().getAllNotificaciones(directorio.getId()).iterator();
        while (notificacionIterator.hasNext())
        {
            Notificacion next = notificacionIterator.next();
            notificacions.add(new Tupla<Notificacion>(0,next));
        }
    }
    private void MostrarNotificaciones()
    {
        panel_Noti.getChildren().clear();
        Iterator<Tupla<Notificacion>>iterator=notificacions.iterator();
        controllers=new ArrayList<>();
        while (iterator.hasNext())
        {
            Tupla<Notificacion> next=iterator.next();
            if (next.getOperacion()==0)
            {
                try
                {
                    Parent_Controller_Auxiliar<Notificacion_Pane_Controller> hold=GetPantalla.Get_Notificacion_Pane(next.getObjeto().getMensaje());
                    controllers.add(hold.getController());
                    panel_Noti.getChildren().add(hold.getParent());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        int cant=panel_Noti.getChildren().size();
        if (cant!=0)
        menuItem.setText("Notificaciones: "+cant);
        else menuItem.setText("Sin Notificaciones");

    }
    private void Delete_Notificacion()throws Exception
    {
        ListIterator<Node> iterator=panel_Noti.getChildren().listIterator();
        int cantBorrados=0;
        while (iterator.hasNext())
        {
            JFXCheckBox next= ((JFXCheckBox)((HBox) iterator.next()).getChildren().get(0));
            if (next.isSelected())
            {
                cantBorrados++;
                Service_Locator.getInstance().getNotificacion_service().RemoveNotificacion(notificacions.remove(iterator.nextIndex()-cantBorrados).getObjeto().getId());
            }
        }
    }
    private void SelectAll()throws Exception
    {
        isSelecteAll=!isSelecteAll;
        ListIterator<Node> iterator=panel_Noti.getChildren().listIterator();
        while (iterator.hasNext())
        {
            ((JFXCheckBox) ((HBox) iterator.next()).getChildren().get(0)).setSelected(isSelecteAll);
        }
        BorrarCorrect();
    }
    private void BorrarCorrect()
    {
        int cont=0;
        Iterator<Notificacion_Pane_Controller>iterator=controllers.iterator();
        while (iterator.hasNext())
            if (iterator.next().isSelected())
                cont++;
        if (cont!=0) {
            del_btn.setText("Borrar (" + cont + ")");
            selc_btn.setText("Deseleccionar Todo");
            isSelecteAll=true;
        }
        else
            del_btn.setText("Borrar");
    }

}
