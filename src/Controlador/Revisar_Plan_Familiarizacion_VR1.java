package Controlador;

import Auxiliar.Animaciones;
import Auxiliar.Plan_Fam_VR1;
import Auxiliar.TuplaList;
import Modelo.*;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import Vista.IDraggedScene;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class Revisar_Plan_Familiarizacion_VR1 implements Initializable, IDraggedScene
{


    @FXML
    private JFXTreeTableView<Plan_Fam_VR1> plantable;
    @FXML
    private TreeTableColumn<Plan_Fam_VR1,String> actcol;
    @FXML
    private TreeTableColumn<Plan_Fam_VR1,String> respcol;
    @FXML
    private TreeTableColumn<Plan_Fam_VR1,String> lugcol;
    @FXML
    private TreeTableColumn<Plan_Fam_VR1,String> fechcol;
    @FXML
    private TreeTableColumn<Plan_Fam_VR1,String> horacol;
    @FXML
    private TreeTableColumn<Plan_Fam_VR1,String> desccol;
    @FXML
    private JFXButton aprob;
    @FXML
    private JFXButton rech;

    @FXML
    private AnchorPane paneTitulo;
    @FXML
    private JFXButton botonCerrarVentana;
    @FXML
    private JFXButton botonMinimizarVentana;
    @FXML
    private AnchorPane hbox;
    @FXML
    private AnchorPane PaneExterior;


    /*
    DECLARACIONES DE VARIABLES
     */
    private static Stage stage;
    private static PlanFamiliarizacion familiarizacion;
    private static Pantalla_Vicerector_Primero_Controller padre; // Pongo esta pantalla ya que al
    // final voy a utilizar los metodos de ella pa no pasar trabajo
    private ArrayList<TuplaList<Object>> Actividad_Plan;
    private ArrayList<Area> areas;


    public static void setStage(Stage stage) {
        Revisar_Plan_Familiarizacion_VR1.stage = stage;
    }

    public static void setPadre(Pantalla_Vicerector_Primero_Controller padre) {
        Revisar_Plan_Familiarizacion_VR1.padre = padre;
    }

    public static void setFamiliarizacion(PlanFamiliarizacion familiarizacion) {
        Revisar_Plan_Familiarizacion_VR1.familiarizacion = familiarizacion;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        onDraggedScene(PaneExterior);
        ConfigurarMinimizarMaximizar(PaneExterior,botonMinimizarVentana,botonCerrarVentana);

        try {
            fechcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Fam_VR1, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Fam_VR1, String> param) {
                    return param.getValue().getValue().fechaProperty();
                }
            });
            actcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Fam_VR1, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Fam_VR1, String> param) {
                    return param.getValue().getValue().actividadProperty();
                }
            });
            respcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Fam_VR1, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Fam_VR1, String> param) {
                    return param.getValue().getValue().responsableProperty();
                }
            });
            lugcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Fam_VR1, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Fam_VR1, String> param) {
                    return param.getValue().getValue().lugarProperty();
                }
            });
            horacol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Fam_VR1, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Fam_VR1, String> param) {
                    return param.getValue().getValue().horaProperty();
                }
            });
            desccol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Fam_VR1, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Fam_VR1, String> param) {
                    return param.getValue().getValue().descProperty();
                }
            });
            Actividad_Plan = Service_Locator.getInstance().getPlanificacion_familiarizacion_service().getActividades_Plan_No_Normalizado(familiarizacion.getId());
            areas=Service_Locator.getInstance().getArea_service().Areas_List();
            LlenarTablaActividades();

            if (familiarizacion.getAprobado()!=null)
            {
                aprob.setDisable(true);
                rech.setDisable(true);
            }
            aprob.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event)
                {
                    try {

                        familiarizacion.setAprobado(Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId());
                        familiarizacion.setRechazado(false);
                        familiarizacion.setRevisado(true);
                        Service_Locator.getInstance().getNotificacion_service().EnviarNotificacionToDirectorRecursosHumanos("Plan de Familiarización aprobado");
                        Service_Locator.getInstance().getNotificacion_service().EnviarNotificacion("Plan de Familiarización aprobado",familiarizacion.getConfeccionado());
                        Service_Locator.getInstance().getPlanificacion_familiarizacion_service().Aprobar_Desaprobar_PlanFamiliarizacion(familiarizacion);
                        padre.LlenarTablaPlanesFamiliarizacion();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION,"Información", "Plan de Familiarización aprobado","DARKGREEN");
                        stage.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });

            rech.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        familiarizacion.setAprobado(null);
                        familiarizacion.setRechazado(true);
                        familiarizacion.setRevisado(false);
                        Service_Locator.getInstance().getNotificacion_service().EnviarNotificacionToDirectorRecursosHumanos("Plan de Familiarización rechazado por el Vicerrector");
                        Service_Locator.getInstance().getNotificacion_service().EnviarNotificacion("Plan de Familiarización rechazado por el Vicerrector",familiarizacion.getConfeccionado());
                        Service_Locator.getInstance().getPlanificacion_familiarizacion_service().Aprobar_Desaprobar_PlanFamiliarizacion(familiarizacion);
                        GetPantalla.Pantalla_Enviar_Notifiacion("Observaciones del P.Familiarizacion",familiarizacion.getConfeccionado(),true);
                        padre.LlenarTablaPlanesFamiliarizacion();
                        stage.close();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION,"Información", "Plan de Familiarización rechazado","DARKGREEN");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });

            if (familiarizacion.getAprobado()!=null)
            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION,"Información", "Este Plan ya ha sido aprobado, no tendrá control sobre este","DARKGREEN");
            Animaciones.AnimacionTipo1(aprob);
            Animaciones.AnimacionTipo1(rech);



        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }






    private void LlenarTablaActividades()
    {
        Iterator<TuplaList<Object>> tuplaListIterator= Actividad_Plan.iterator();
        ObservableList<Plan_Fam_VR1> data= FXCollections.observableArrayList();
        while (tuplaListIterator.hasNext())
        {
            TuplaList<Object> next=tuplaListIterator.next();
            Actividad act= ((Actividad) next.getListaobjeto().get(0));
            ActividadesPlan actplan= ((ActividadesPlan) next.getListaobjeto().get(1));
            Plan_Fam_VR1 cell=new Plan_Fam_VR1(act.getNombre(),padre.DirectorioID(actplan.getResponsable()),AreaID(actplan.getArea()),actplan.getFecha().toString(),actplan.getHora().toString(),act.getDescripcion());
            data.add(cell);
        }
        final RecursiveTreeItem<Plan_Fam_VR1> item=new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);
        plantable.setRoot(item);
        plantable.setShowRoot(false);
    }

    private String AreaID(int id)
    {
        String nombre="";
        boolean is=false;
        Iterator<Area> iterator=areas.iterator();
        Area hold=null;
        while (iterator.hasNext())
        {
            hold=iterator.next();
            if (hold.getId()==id) {
                is = true;
                nombre=hold.getNombre();
            }
        }
        return nombre;
    }












}
