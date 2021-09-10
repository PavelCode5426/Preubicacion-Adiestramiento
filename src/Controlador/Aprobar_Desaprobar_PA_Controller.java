package Controlador;

import Auxiliar.TuplaList;
import Modelo.Directorio;
import Modelo.Etapa;
import Modelo.PlanAdiestramiento;
import Modelo.Tarea;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import Vista.IDraggedScene;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.*;

public class Aprobar_Desaprobar_PA_Controller implements Initializable, IDraggedScene
{
    @FXML
    private AnchorPane PaneExterior;
    @FXML
    private Tab pane;
    @FXML
    private Label obj;
    @FXML
    private Label ini;
    @FXML
    private Label end;
    @FXML
    private JFXTreeTableView<Estudiante_Tarea> table;
    @FXML
    private TreeTableColumn<Estudiante_Tarea,String> tarea;
    @FXML
    private TreeTableColumn<Estudiante_Tarea,String> part;
    @FXML
    private Tab pane1;
    @FXML
    private Label obj1;
    @FXML
    private Label ini1;
    @FXML
    private Label end1;
    @FXML
    private JFXTreeTableView<Estudiante_Tarea> table1;
    @FXML
    private TreeTableColumn<Estudiante_Tarea,String> tarea1;
    @FXML
    private TreeTableColumn<Estudiante_Tarea,String> part1;
    @FXML
    private Tab pane11;
    @FXML
    private Label obj11;
    @FXML
    private Label ini11;
    @FXML
    private Label end11;
    @FXML
    private JFXTreeTableView<Estudiante_Tarea> table11;
    @FXML
    private TreeTableColumn<Estudiante_Tarea,String> tarea11;
    @FXML
    private TreeTableColumn<Estudiante_Tarea,String> part11;
    @FXML
    private Tab pane111;
    @FXML
    private Label obj111;
    @FXML
    private Label ini111;
    @FXML
    private Label end111;
    @FXML
    private JFXTreeTableView<Estudiante_Tarea> table111;
    @FXML
    private TreeTableColumn<Estudiante_Tarea,String> tarea111;
    @FXML
    private TreeTableColumn<Estudiante_Tarea,String> part111;
    @FXML
    private Tab pane1111;
    @FXML
    private Label obj1111;
    @FXML
    private Label ini1111;
    @FXML
    private Label end1111;
    @FXML
    private JFXTreeTableView<Estudiante_Tarea> table1111;
    @FXML
    private TreeTableColumn<Estudiante_Tarea,String> tarea1111;
    @FXML
    private TreeTableColumn<Estudiante_Tarea,String> part1111;
    @FXML
    private Tab pane11111;
    @FXML
    private Label obj11111;
    @FXML
    private Label ini11111;
    @FXML
    private Label end11111;
    @FXML
    private JFXTreeTableView<Estudiante_Tarea> table11111;
    @FXML
    private TreeTableColumn<Estudiante_Tarea,String> tarea11111;
    @FXML
    private TreeTableColumn<Estudiante_Tarea,String> part11111;
    @FXML
    private Label resp;
    @FXML
    private JFXButton acept;
    @FXML
    private JFXButton rechazar;
    @FXML
    private JFXTabPane tabPane;

    @FXML
    private AnchorPane paneTitulo;
    @FXML
    private JFXButton botonCerrarVentana;
    @FXML
    private JFXButton botonMinimizarVentana;
    @FXML
    private AnchorPane hbox;

    /*
    Declaraciones
     */
    private static int IDPlan;
    private static Stage stage;
    private static Pantalla_Jefe_Area_Controller padre;
    private PlanAdiestramiento planAdiestramiento;
    private LinkedList<Etapa> etapas;
    private ArrayList<TuplaList<Object>> tareas_participantes;
    private boolean isProrroga=false;

    public static void setPadre(Pantalla_Jefe_Area_Controller padre) {
        Aprobar_Desaprobar_PA_Controller.padre = padre;
    }

    public static void setStage(Stage stage) {
        Aprobar_Desaprobar_PA_Controller.stage = stage;
    }

    public static void setIDPlan(int IDPlan) {
        Aprobar_Desaprobar_PA_Controller.IDPlan = IDPlan;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

       //Pa que la pantalla se mueva
        onDraggedScene(PaneExterior);
        ConfigurarMinimizarMaximizar(PaneExterior,botonMinimizarVentana,botonCerrarVentana);


        try
        {
            planAdiestramiento=Service_Locator.getInstance().getPlanificacion_adiestramiento_service().getPlanAdiestramientoID(IDPlan);
            etapas=new LinkedList<>(Service_Locator.getInstance().getPlanificacion_adiestramiento_service().getEtapas_fromPlan(planAdiestramiento.getId()));
            tareas_participantes = Service_Locator.getInstance().getPlanificacion_adiestramiento_service().getTareas_and_participantes(planAdiestramiento.getId());
            if (etapas.size()==6)
                isProrroga=true;
            if (isProrroga)
            {
                pane.setDisable(true);
                pane1.setDisable(true);
                pane11.setDisable(true);
                pane111.setDisable(true);
                tabPane.getSelectionModel().select(4);
            }
            else
            {
                tabPane.getSelectionModel().select(0);
                pane1111.setDisable(true);
                pane11111.setDisable(true);
            }

            tarea.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String> param) {
                    return param.getValue().getValue().tareaProperty();
                }
            });
            part.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String> param) {
                    return param.getValue().getValue().participanteProperty();
                }
            });
            tarea1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String> param) {
                    return param.getValue().getValue().tareaProperty();
                }
            });
            part1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String> param) {
                    return param.getValue().getValue().participanteProperty();
                }
            });
            tarea11.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String> param) {
                    return param.getValue().getValue().tareaProperty();
                }
            });
            part11.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String> param) {
                    return param.getValue().getValue().participanteProperty();
                }
            });
            tarea111.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String> param) {
                    return param.getValue().getValue().tareaProperty();
                }
            });
            part111.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String> param) {
                    return param.getValue().getValue().participanteProperty();
                }
            });
            tarea1111.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String> param) {
                    return param.getValue().getValue().tareaProperty();
                }
            });
            part1111.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String> param) {
                    return param.getValue().getValue().participanteProperty();
                }
            });
            tarea11111.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String> param) {
                    return param.getValue().getValue().tareaProperty();
                }
            });
            part11111.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Estudiante_Tarea, String> param) {
                    return param.getValue().getValue().participanteProperty();
                }
            });

            if (planAdiestramiento.getAprobado()!=null)
            {
               GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION,"Información","Este Plan de Adiestramiento ya fue aprobado,no podrá" +
                       "realizar cambios sobre él","#065325");
                acept.setDisable(true);
                rechazar.setDisable(true);
            }
            LLenarTablas_Datos();
            acept.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try
                    {
                        planAdiestramiento.setRevisado(true);
                        planAdiestramiento.setAprobado(Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId());
                        Service_Locator.getInstance().getPlanificacion_adiestramiento_service().Create_Update_PlanAdiestramiento(planAdiestramiento);
                        Service_Locator.getInstance().getNotificacion_service().EnviarNotificacion("Plan de Adiestramiento aceptado",planAdiestramiento.getTutor());
                        padre.Llenar_Tabla_Adiestramiento();
                        stage.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            rechazar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try
                    {
                        planAdiestramiento.setRevisado(true);
                        planAdiestramiento.setAprobado(null);
                        Service_Locator.getInstance().getPlanificacion_adiestramiento_service().Create_Update_PlanAdiestramiento(planAdiestramiento);
                        Service_Locator.getInstance().getNotificacion_service().EnviarNotificacion("Plan de Adiestramiento rechazado",planAdiestramiento.getTutor());
                        GetPantalla.Pantalla_Enviar_Notifiacion("Motivo del rechazo",planAdiestramiento.getTutor(),true);
                        padre.Llenar_Tabla_Adiestramiento();
                        stage.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });



        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metodos Basicos
    Etapa BuscarEtapaByID(int id)    {
        Etapa etapa=null;
        Iterator<Etapa> etapaIterator=etapas.iterator();
        boolean is=false;
        while (etapaIterator.hasNext()&&!is)
        {
            etapa=etapaIterator.next();
            if (etapa.getId()==id)
                is=true;
        }
        if (!is)
            etapa=null;
        return etapa;
    }
    private void LLenarTablas_Datos() throws Exception    {
        ObservableList<Estudiante_Tarea> tab1= FXCollections.observableArrayList();
        ObservableList<Estudiante_Tarea> tab2= FXCollections.observableArrayList();
        ObservableList<Estudiante_Tarea> tab3= FXCollections.observableArrayList();
        ObservableList<Estudiante_Tarea> tab4= FXCollections.observableArrayList();
        ObservableList<Estudiante_Tarea> tab5= FXCollections.observableArrayList();
        ObservableList<Estudiante_Tarea> tab6= FXCollections.observableArrayList();

        Iterator<TuplaList<Object>> iterator=tareas_participantes.iterator();
        resp.setText(resp.getText()+Service_Locator.getInstance().getDirectorio_service().getDirectorio(planAdiestramiento.getTutor()).getNombreApellido());
        Iterator<Etapa> etapaIterator=null;
        Etapa nextEtapa=null;
        TuplaList<Object> next=null;
        if (!isProrroga)
        {


            while (iterator.hasNext()) {
                next = iterator.next();
                Tarea tarea = ((Tarea) next.getListaobjeto().remove(0));
                String part = "";
                if (BuscarEtapaByID(tarea.getEtapa()).getNombre() == 1)
                {
                    while (next.getListaobjeto().size() > 0) {
                        part += ((Directorio) next.getListaobjeto().remove(0)).getNombreApellido();
                        if (next.getListaobjeto().size() == 0)
                            part += ".";
                        else
                            part += ",";
                    }
                    if (part.equals(""))
                        part="Sin Participantes";
                    tab1.add(new Estudiante_Tarea(tarea.getNombre(), part));
                }
                if (BuscarEtapaByID(tarea.getEtapa()).getNombre() == 2) {
                    while (next.getListaobjeto().size() > 0) {
                        part += ((Directorio) next.getListaobjeto().remove(0)).getNombreApellido();
                        if (next.getListaobjeto().size() == 0)
                            part += ".";
                        else
                            part += ",";
                    }
                    if (part.equals(""))
                        part="Sin Participantes";
                    tab2.add(new Estudiante_Tarea(tarea.getNombre(), part));
                }
                if (BuscarEtapaByID(tarea.getEtapa()).getNombre() == 3) {
                    while (next.getListaobjeto().size() > 0) {
                        part += ((Directorio) next.getListaobjeto().remove(0)).getNombreApellido();
                        if (next.getListaobjeto().size() == 0)
                            part += ".";
                        else
                            part += ",";
                    }
                    if (part.equals(""))
                        part="Sin Participantes";
                    tab3.add(new Estudiante_Tarea(tarea.getNombre(), part));
                }
                if (BuscarEtapaByID(tarea.getEtapa()).getNombre() == 4) {
                    while (next.getListaobjeto().size() > 0) {
                        part += ((Directorio) next.getListaobjeto().remove(0)).getNombreApellido();
                        if (next.getListaobjeto().size() == 0)
                            part += ".";
                        else
                            part += ",";
                    }
                    if (part.equals(""))
                        part="Sin Participantes";
                    tab4.add(new Estudiante_Tarea(tarea.getNombre(), part));
                }
            }
            final TreeItem<Estudiante_Tarea> item1=new RecursiveTreeItem<>(tab1,RecursiveTreeObject::getChildren);
            final TreeItem<Estudiante_Tarea> item2=new RecursiveTreeItem<>(tab2,RecursiveTreeObject::getChildren);
            final TreeItem<Estudiante_Tarea> item3=new RecursiveTreeItem<>(tab3,RecursiveTreeObject::getChildren);
            final TreeItem<Estudiante_Tarea> item4=new RecursiveTreeItem<>(tab4,RecursiveTreeObject::getChildren);

            table.setShowRoot(false);
            table1.setShowRoot(false);
            table11.setShowRoot(false);
            table111.setShowRoot(false);

            table.setRoot(item1);
            table1.setRoot(item2);
            table11.setRoot(item3);
            table111.setRoot(item4);

            etapaIterator=etapas.iterator();
            nextEtapa=etapaIterator.next();

            obj.setText(obj.getText()+nextEtapa.getObjetivo());
            ini.setText(ini.getText()+nextEtapa.getFechaInicio().toString());
            end.setText(end.getText()+nextEtapa.getFechaFinal().toString());

            nextEtapa=etapaIterator.next();

            obj1.setText(obj1.getText()+nextEtapa.getObjetivo());
            ini1.setText(ini1.getText()+nextEtapa.getFechaInicio().toString());
            end1.setText(end1.getText()+nextEtapa.getFechaFinal().toString());

            nextEtapa=etapaIterator.next();

            obj11.setText(obj11.getText()+nextEtapa.getObjetivo());
            ini11.setText(ini11.getText()+nextEtapa.getFechaInicio().toString());
            end11.setText(end11.getText()+nextEtapa.getFechaFinal().toString());

            nextEtapa=etapaIterator.next();

            obj111.setText(obj111.getText()+nextEtapa.getObjetivo());
            ini111.setText(ini111.getText()+nextEtapa.getFechaInicio().toString());
            end111.setText(end111.getText()+nextEtapa.getFechaFinal().toString());

        }
        else
        {
            while (iterator.hasNext()) {
                next = iterator.next();
                Tarea tarea = ((Tarea) next.getListaobjeto().remove(0));
                String part = "";

                if (BuscarEtapaByID(tarea.getEtapa()).getNombre() == 5) {
                    while (next.getListaobjeto().size() > 0) {
                        part += ((Directorio) next.getListaobjeto().remove(0)).getNombreApellido();
                        if (next.getListaobjeto().size() == 0)
                            part += ".";
                        else
                            part += ",";
                    }
                    if (part.equals(""))
                        part="Sin Participantes";
                    tab5.add(new Estudiante_Tarea(tarea.getNombre(), part));
                }
                if (BuscarEtapaByID(tarea.getEtapa()).getNombre() == 6) {
                    while (next.getListaobjeto().size() > 0) {
                        part += ((Directorio) next.getListaobjeto().remove(0)).getNombreApellido();
                        if (next.getListaobjeto().size() == 0)
                            part += ".";
                        else
                            part += ",";
                    }
                    if (part.equals(""))
                        part="Sin Participantes";
                    tab6.add(new Estudiante_Tarea(tarea.getNombre(), part));
                }
            }
            final TreeItem<Estudiante_Tarea> item5=new RecursiveTreeItem<>(tab5,RecursiveTreeObject::getChildren);
            final TreeItem<Estudiante_Tarea> item6=new RecursiveTreeItem<>(tab6,RecursiveTreeObject::getChildren);

            table1111.setShowRoot(false);
            table11111.setShowRoot(false);

            table1111.setRoot(item5);
            table11111.setRoot(item6);

            etapaIterator=etapas.descendingIterator();

            nextEtapa=etapaIterator.next();

            obj11111.setText(obj11111.getText()+nextEtapa.getObjetivo());
            ini11111.setText(ini11111.getText()+nextEtapa.getFechaInicio().toString());
            end11111.setText(end11111.getText()+nextEtapa.getFechaFinal().toString());


            nextEtapa=etapaIterator.next();

            obj1111.setText(obj1111.getText()+nextEtapa.getObjetivo());
            ini1111.setText(ini1111.getText()+nextEtapa.getFechaInicio().toString());
            end1111.setText(end1111.getText()+nextEtapa.getFechaFinal().toString());



        }








    }









    private class Estudiante_Tarea extends RecursiveTreeObject<Estudiante_Tarea>
    {
        private SimpleStringProperty tarea;
        private SimpleStringProperty participante;


        public Estudiante_Tarea(String  tarea, String participante) {
            this.tarea = new SimpleStringProperty(tarea);
            this.participante = new SimpleStringProperty(participante);
        }

        public String getTarea() {
            return tarea.get();
        }

        public SimpleStringProperty tareaProperty() {
            return tarea;
        }

        public String getParticipante() {
            return participante.get();
        }

        public SimpleStringProperty participanteProperty() {
            return participante;
        }
    }
}
