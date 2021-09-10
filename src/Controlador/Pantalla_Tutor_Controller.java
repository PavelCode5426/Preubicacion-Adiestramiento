package Controlador;

import Auxiliar.Animaciones;
import Auxiliar.Excepciones.AppException;
import Auxiliar.FiltrarTablas;
import Auxiliar.Graduado_Data;
import Auxiliar.Tarea_Data;
import Modelo.*;
import Reportes.Visuales.GetPantalla_Reportes;
import Servicio.Repository;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import Vista.IDraggedScene;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.sun.istack.internal.NotNull;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Pantalla_Tutor_Controller implements Initializable, IDraggedScene
{

    @FXML
    private JFXTabPane jfxtappane;
    @FXML
    private VBox plan_pane;
    @FXML
    private JFXTreeTableView<Graduado_Data> table_graduado;
    @FXML
    private TreeTableColumn<Graduado_Data,String > nomapcol;
    @FXML
    private TreeTableColumn<Graduado_Data,String > cicol;
    @FXML
    private TreeTableColumn<Graduado_Data,String > especol;
    @FXML
    private TreeTableColumn<Graduado_Data,String > annocol;
    @FXML
    private JFXButton addPlan;


    @FXML
    private VBox plan_pane1;
    @FXML
    private JFXTreeTableView<Graduado_Data_Color> table_graduado1;
    @FXML
    private TreeTableColumn<Graduado_Data_Color,Label > nomapcol1;
    @FXML
    private TreeTableColumn<Graduado_Data_Color,Label > cicol1;
    @FXML
    private TreeTableColumn<Graduado_Data_Color,Label > especol1;
    @FXML
    private TreeTableColumn<Graduado_Data_Color,Label > annocol1;
    @FXML
    private TreeTableColumn<Graduado_Data_Color, Label> column_aprobadopor;
    @FXML
    private TreeTableColumn<Graduado_Data_Color, Label> colum_estado;
    @FXML
    private JFXButton editPlan;
    @FXML
    private JFXButton delPlan;




    @FXML
    private VBox plan_pane11;
    @FXML
    private Label nomEt;
    @FXML
    private Text obEt;
    @FXML
    private JFXCheckBox habilitareval;
    @FXML
    private JFXComboBox<String> evaletapa;
    @FXML
    private JFXTreeTableView<Tarea_Data> tablaEtapa;
    @FXML
    private TreeTableColumn<Tarea_Data,String > nomAct;
    @FXML
    private TreeTableColumn<Tarea_Data,String > fecAct;
    @FXML
    private TreeTableColumn<Tarea_Data,String > obsAct;
    @FXML
    private TreeTableColumn<Tarea_Data,String > estAct;
    @FXML
    private JFXButton addTareaExt;
    @FXML
    private JFXButton evaltarea;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton prorog;
    @FXML
    private JFXListView<String> list;


    @FXML
    private JFXTextField buscartf;
    @FXML
    private JFXTextField buscartf1;
    @FXML
    private JFXTextField buscartf2;

    @FXML
    private MenuItem notfmf;
    @FXML
    private MenuItem mi_perfil;

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
    @FXML
    private MenuItem menuItem_ReportePA;
    @FXML
    private MenuItem menuItem_ReporteTesisAp;
    @FXML
    private MenuItem menuItem_TutoradosXEsp;
    @FXML
    private MenuItem menu_Ayuda;
    @FXML
    private MenuItem menuItem_TutoradosXProc;

    /*
    DECLARACIONES DE VARIABLES
    */

    private ArrayList<Especialidad> especialidads;
    private static Stage stage;
    private Etapa etapa;
    private ArrayList<Tarea> tareas;
    private ArrayList<Estudiante> estudiantes;

    /*
    DECLARACIONES DE OBSERVABLES
     */
    private ObservableList<Graduado_Data> graduado_data;
    private ObservableList<Graduado_Data_Color> tutorado;



    public static void setStage(Stage stage) {
        Pantalla_Tutor_Controller.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Poner Pantalla linda
        ConfigurarMinimizarMaximizar(PaneExterior,botonMinimizarVentana,botonCerrarVentana);
        onDraggedScene(PaneExterior);

       try {
            especialidads=Service_Locator.getInstance().getEspecialidad_service().Especialidades_List();
            estudiantes=Service_Locator.getInstance().getDirectorio_service().getTutorados();



            nomapcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Data, String> param) {
                    return param.getValue().getValue().nombreProperty();
                }
            });
            cicol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Data, String> param) {
                    return param.getValue().getValue().ciProperty();
                }
            });
            especol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Data, String> param) {
                    return param.getValue().getValue().especialidadProperty();
                }
            });
            annocol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Data, String> param) {
                    return param.getValue().getValue().annoProperty();
                }
            });

            nomapcol1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Data_Color, Label>, ObservableValue<Label>>() {
                @Override
                public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Graduado_Data_Color, Label> param) {
                    return param.getValue().getValue().nombreProperty();
                }
            });
            cicol1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Data_Color, Label>, ObservableValue<Label>>() {
                @Override
                public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Graduado_Data_Color, Label> param) {
                    return param.getValue().getValue().ciProperty();
                }
            });

            especol1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Data_Color, Label>, ObservableValue<Label>>() {
                @Override
                public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Graduado_Data_Color, Label> param) {
                    return param.getValue().getValue().especialidadProperty();
                }
            });
            annocol1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Data_Color, Label>, ObservableValue<Label>>() {
                @Override
                public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Graduado_Data_Color, Label> param) {
                    return param.getValue().getValue().annoPlanProperty();
                }
            });

            colum_estado.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Data_Color, Label>, ObservableValue<Label>>() {
                @Override
                public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Graduado_Data_Color, Label> param) {
                    return param.getValue().getValue().estadoPlanProperty();
                }
            });
            column_aprobadopor.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Data_Color, Label>, ObservableValue<Label>>() {
                @Override
                public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Graduado_Data_Color, Label> param) {
                    return param.getValue().getValue().aprobadoporProperty();
                }
            });
            nomAct.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Tarea_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Tarea_Data, String> param) {
                    return param.getValue().getValue().nombreProperty();
                }
            });
            obsAct.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Tarea_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Tarea_Data, String> param) {
                    return param.getValue().getValue().observProperty();
                }
            });
            fecAct.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Tarea_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Tarea_Data, String> param) {
                    return param.getValue().getValue().fechaCumpProperty();
                }
            });
            estAct.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Tarea_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Tarea_Data, String> param) {
                    return param.getValue().getValue().estadoProperty();
                }
            });

            tablaEtapa.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Tarea_Data>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Tarea_Data>> observable, TreeItem<Tarea_Data> oldValue, TreeItem<Tarea_Data> newValue) {
                    evaltarea.setDisable(tablaEtapa.getSelectionModel().isEmpty());
                }
            });


            evaletapa.getItems().addAll("Excelente","Bien","Regular","Mal");
            LlenarTablaGraduados();
            LlenarTablaTutorados();
            ListarTutorados();
            InstanciarAnimaciones();
            addPlan.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try
                    {
                        if (!table_graduado.getSelectionModel().isEmpty())
                        {
                            int idGrad=table_graduado.getSelectionModel().getSelectedItem().getValue().getId();
                            GetPantalla.Plan_Adiestramiento(idGrad,null,false,Pantalla_Tutor_Controller.this);
                        }
                        else throw new Exception("Seleccione un estudiante");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR,"Error",AppException.getJustMensaje(e.getMessage()),"DARKRED");
                    }
                }
            });
            list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
                {
                    try {
                        LlenarTareas();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            editPlan.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (!table_graduado1.getSelectionModel().isEmpty()) {
                        try {
                            int Grad, plan;
                            Grad = table_graduado1.getSelectionModel().getSelectedItem().getValue().getIdEstud();
                            ArrayList<Etapa> etapas = Service_Locator.getInstance().getPlanificacion_adiestramiento_service().get_Etapas_from_Estudiante(Grad);
                            plan=etapas.get(0).getPlan();
                            if (etapas.size()==4)
                                GetPantalla.Plan_Adiestramiento(Grad, plan,false,Pantalla_Tutor_Controller.this);
                            else
                                GetPantalla.Plan_Adiestramiento(Grad, plan,false,Pantalla_Tutor_Controller.this);
                        } catch (Exception e) {
                            e.printStackTrace();
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR,"Error",AppException.getJustMensaje(e.getMessage()),"DARKRED");
                        }
                    }
                }
            });
            prorog.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (!table_graduado1.getSelectionModel().isEmpty()) {
                        try {
                            int Grad, plan;
                            Grad = table_graduado1.getSelectionModel().getSelectedItem().getValue().getIdEstud();
                            plan=table_graduado1.getSelectionModel().getSelectedItem().getValue().getIdPlan();
                            GetPantalla.Plan_Adiestramiento(Grad, plan,true,Pantalla_Tutor_Controller.this);
                        } catch (Exception e) {
                            e.printStackTrace();
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR,"Error",AppException.getJustMensaje(e.getMessage()),"DARKRED");
                        }
                    }
                }
            });
            delPlan.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event)
                {
                    if (!table_graduado1.getSelectionModel().isEmpty()) {
                        try {
                            int plan=table_graduado1.getSelectionModel().getSelectedItem().getValue().getIdPlan();
                            int eliminar=GetPantalla.panel_De_Notificacion_Confirmar("Está seguro que desea eliminar el plan de ese estudiante?","Confirmación","Darkgreen");
                            if(eliminar==1) {

                                Service_Locator.getInstance().getPlanificacion_adiestramiento_service().RemovePlanAdiestramientoById(plan);
                                LlenarTablaTutorados();
                                LlenarTablaGraduados();
                                ListarTutorados();
                                GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Plan eliminado correctamente", "DARKGREEN");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR,"Error", AppException.getJustMensaje(e.getMessage()),"DARKRED");
                        }
                    }
                }
            });
            notfmf.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                         GetPantalla.Pantalla_Notificaciones(notfmf);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            mi_perfil.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla.Pantalla_Perfil();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            });

           table_graduado.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Graduado_Data>>() {
               @Override
               public void changed(ObservableValue<? extends TreeItem<Graduado_Data>> observable, TreeItem<Graduado_Data> oldValue, TreeItem<Graduado_Data> newValue) {
                   addPlan.setDisable(table_graduado.getSelectionModel().isEmpty());
               }
           });
            table_graduado1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Graduado_Data_Color>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Graduado_Data_Color>> observable, TreeItem<Graduado_Data_Color> oldValue, TreeItem<Graduado_Data_Color> newValue) {
                    if (!table_graduado1.getSelectionModel().isEmpty()) {
                        editPlan.setDisable(false);
                        delPlan.setDisable(false);
                        prorog.setDisable(true);
                        try {
                            int id=table_graduado1.getSelectionModel().getSelectedItem().getValue().getIdEstud();
                            if (Service_Locator.getInstance().getGraduado_service().NecesitaProrroga(id))
                                prorog.setDisable(false);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                    else
                    {
                        editPlan.setDisable(true);
                        delPlan.setDisable(true);
                        prorog.setDisable(true);
                    }
                }
            });
            addTareaExt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla.Pantalla_Add_Otra_Tarae(etapa,Pantalla_Tutor_Controller.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            evaltarea.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event)
                {
                    try
                    {
                        Tarea tarea=getTareaToEvaluate();
                        GetPantalla.Pantalla_Evaluar_Tarea(tarea,Pantalla_Tutor_Controller.this);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            evaletapa.setDisable(true);
            save.setDisable(true);
            habilitareval.setSelected(false);
            habilitareval.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    evaletapa.setDisable(!habilitareval.isSelected());
                    evaletapa.getSelectionModel().clearSelection();
                }
            });
            evaletapa.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    save.setDisable(evaletapa.getSelectionModel().isEmpty());
                }
            });
            save.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try
                    {
                        if (habilitareval.isSelected())
                        {
                            Evaluar_Etapa();
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION,"Información","Etapa evaluada correctamente","DARKGREEN");
                        }
                        else throw new Exception("Habilite la Evaluacion de la Etapa");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR,"ERROR",AppException.getJustMensaje(e.getMessage()),"DARKGREEN");
                    }
                }
            });



            buscartf.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    try {
                        String e=event.getCharacter();
                        if (e.equals("\b"))
                            e="";
                        String key=buscartf.getText()+e;

                        LlenarTablaGraduados();
                        if (!key.equals(""))
                            FiltrarTablas(key.toLowerCase());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            });
            buscartf1.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    try {
                        String e=event.getCharacter();
                        if (e.equals("\b"))
                            e="";
                        String key=buscartf1.getText()+e;

                        LlenarTablaTutorados();
                        if (!key.equals(""))
                            FiltrarTablas(key.toLowerCase());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            buscartf2.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event)
                {
                     String e=event.getCharacter();
                    if (e.equals("\b"))
                        e="";
                    String key=buscartf2.getText()+e;

                    ListarTutorados();
                    if (!key.equals(""))
                        FiltrarTablas(key.toLowerCase());
                }
            });


            menu_Ayuda.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla.Ayuda(8);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });


            int cant=Service_Locator.getInstance().getNotificacion_service().getAllNotificaciones(Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId()).size();
            if (cant>0)
                notfmf.setText("Notificaciones: "+cant);
                else
                    notfmf.setText("Sin Notificaciones");




            menuItem_ReportePA.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla_Reportes.Reporte_Plan_Adiestramiento();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });


            menuItem_ReporteTesisAp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla_Reportes.Reporte_Est_TesisAplicable(Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId(),8);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

            menuItem_TutoradosXEsp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla_Reportes.Reporte_EstX_Espec(Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId(),8);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

            menuItem_TutoradosXProc.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla_Reportes.Reporte_Est_X_Procedencia(Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId(),8);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LlenarTablaGraduados() throws Exception
    {
        ArrayList<Estudiante> retorno= Service_Locator.getInstance().getGraduado_service().getGraduadofromArea(Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getArea());
        graduado_data= FXCollections.observableArrayList();
        Iterator<Estudiante> estudianteIterator=retorno.iterator();
        while (estudianteIterator.hasNext()) {
            Estudiante next = estudianteIterator.next();
            graduado_data.addAll(new Graduado_Data(next.getNombreApellidos(),next.getNidentidad(),Especialidad(next.getEspecialidad()),"",next.getAnnoIngreso().toString(),next.getId()));
        }
        final TreeItem<Graduado_Data> item=new RecursiveTreeItem<>(graduado_data, RecursiveTreeObject::getChildren);
        table_graduado.setRoot(item);
        table_graduado.setShowRoot(false);
    }
    public void LlenarTablaTutorados() throws Exception
    {
        tutorado= FXCollections.observableArrayList();
        ResultSet resultSet=Service_Locator.getInstance().getDirectorio_service().getTutoradosInfo();
        while (resultSet.next()) {
            int IDTutorado=resultSet.getInt(7);
            int IDPlan=resultSet.getInt(8);
            String estado=resultSet.getString(5);
            if (estado==null)
                estado="Sin Aprobar";
            else
                estado="Aprobado por: "+estado;
            boolean revisado=resultSet.getBoolean(6);
            String rev="Revisado";
            if (!revisado)
                rev="Sin Revisar";
            Color color=Color.ORANGE;
            double prom=Service_Locator.getInstance().getPlanificacion_adiestramiento_service().PromedioNotasPlanAdiestramiento(IDPlan);
            boolean is=estado.equals("Sin Aprobar");
            if (is)
                color=Color.RED;
            else if (!is&&Service_Locator.getInstance().getGraduado_service().NecesitaProrroga(IDTutorado))
                color=Color.RED;
            else if (!is&&(prom>3||prom==0))
                color=Color.GREEN;

            tutorado.add(new Graduado_Data_Color(IDPlan,IDTutorado,resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),estado,rev,color));
        }
        final TreeItem<Graduado_Data_Color> item=new RecursiveTreeItem<>(tutorado, RecursiveTreeObject::getChildren);
        table_graduado1.setRoot(item);
        table_graduado1.setShowRoot(false);
    }
    public void LlenarTareas()throws Exception
    {
        int IDEstudiante=-1;
        if (/*list.getItems().size()>0&&*/!list.getSelectionModel().isEmpty())
        {
            IDEstudiante=estudiantes.get(list.getSelectionModel().getSelectedIndex()).getId();
            ArrayList<Etapa> etapas = Service_Locator.getInstance().getPlanificacion_adiestramiento_service().get_Etapas_from_Estudiante(IDEstudiante);
            Iterator<Etapa> etapaIterator = etapas.iterator();
            boolean is = false;
            while (etapaIterator.hasNext() && !is)
            {
                etapa = etapaIterator.next();
                  if (etapa.getNota() == null) {
                    is = true;

                }
            }
            tareas = Service_Locator.getInstance().getPlanificacion_adiestramiento_service().get_Tareas_from_Etapas(etapa.getId());
            ObservableList<Tarea_Data> list = FXCollections.observableArrayList();
            Iterator<Tarea> iterator = tareas.iterator();
            while (iterator.hasNext()) {
                Tarea next = iterator.next();
                Tarea_Data tarea_data = new Tarea_Data(next.getNombre(), "Sin Cumplir", "Sin Observacion", "Sin Cumplir",next.getId());
                if (next.isCumplimiento()) {
                    tarea_data.setEstado("Cumplida");
                    tarea_data.setFechaCump(next.getFechaCumplimiento().toString());
                    tarea_data.setObserv(next.getObservacion());
                } else
                    tarea_data.setObserv(next.getObservacion());
                list.add(tarea_data);
            }
            if (etapa.getNota()==null)
            {
                final RecursiveTreeItem<Tarea_Data> item = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
                tablaEtapa.setRoot(item);
                tablaEtapa.setShowRoot(false);
                nomEt.setText("Etapa Numero : " + etapa.getNombre());
                obEt.setText("Objetivo: " + etapa.getObjetivo());
                habilitareval.setDisable(false);
                habilitareval.setSelected(false);
                evaltarea.setDisable(true);
                evaletapa.getSelectionModel().clearSelection();
                addTareaExt.setDisable(false);
            }
            else
            {
                GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION,"Información","La última etapa de este plan ha sido evaluada con una nota promedio de "+Service_Locator.getInstance().getPlanificacion_adiestramiento_service().PromedioNotasPlanAdiestramiento(etapa.getPlan()),"DARKGREEN");
                ObservableList<Tarea_Data> list2 = FXCollections.observableArrayList();
                final RecursiveTreeItem<Tarea_Data> item = new RecursiveTreeItem<>(list2, RecursiveTreeObject::getChildren);
                tablaEtapa.setRoot(item);
                tablaEtapa.setShowRoot(false);
                nomEt.setText("Etapa Numero : N/A");
                obEt.setText("Objetivo: N/A");
                habilitareval.setDisable(true);
                habilitareval.setSelected(false);
                evaletapa.getSelectionModel().clearSelection();
                evaltarea.setDisable(true);
                addTareaExt.setDisable(true);
            }
        }
        else
        {
            ObservableList<Tarea_Data> list = FXCollections.observableArrayList();
            final RecursiveTreeItem<Tarea_Data> item = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
            tablaEtapa.setRoot(item);
            tablaEtapa.setShowRoot(false);
            nomEt.setText("Etapa Numero : N/A");
            obEt.setText("Objetivo: N/A");
            habilitareval.setDisable(true);
            evaltarea.setDisable(true);
            habilitareval.setSelected(false);
            evaletapa.getSelectionModel().clearSelection();
            addTareaExt.setDisable(true);
        }
    }
    public void ListarTutorados()
    {
        list.getItems().clear();
        Iterator<Estudiante> estudianteIterator=estudiantes.iterator();
        while (estudianteIterator.hasNext())
            list.getItems().add(estudianteIterator.next().getNombreApellidos());
    }
    private String Especialidad(int ID)
    {

        String es="";
        boolean is=false;
        Iterator<Especialidad> especialidadIterator=especialidads.iterator();
        while (especialidadIterator.hasNext() &&!is) {
            Especialidad next = especialidadIterator.next();
            if (ID==next.getId())
            {
                is=true;
                es=next.getNombre();
            }
        }
        return es;
    }
    private Tarea getTareaToEvaluate()throws Exception
    {
        Tarea tarea=null;
        if (!tablaEtapa.getSelectionModel().isEmpty())
        {
            Tarea_Data data=tablaEtapa.getSelectionModel().getSelectedItem().getValue();
            Iterator<Tarea> iterator=tareas.iterator();
            boolean is=false;
            while (iterator.hasNext()&&!is)
            {
                tarea=iterator.next();
                if (tarea.getId()==data.getIdTarea())
                    is=true;
            }
        }
        else
            throw new Exception("Seleccione una Tarea");
        return tarea;
    }
    private void Evaluar_Etapa() throws Exception
    {
        int nota=2;
        if (evaletapa.getSelectionModel().getSelectedItem().equals("Excelente"))
            nota=5;
        else if (evaletapa.getSelectionModel().getSelectedItem().equals("Bien"))
            nota=4;
        else if (evaletapa.getSelectionModel().getSelectedItem().equals("Regular"))
            nota=3;
        etapa.setNota(nota);
        evaletapa.getSelectionModel().clearSelection();
        new Repository<Etapa>(new Etapa()).Update(etapa.getId(),etapa);
        evaletapa.setDisable(true);
        save.setDisable(true);
        habilitareval.setSelected(false);
        int index=list.getSelectionModel().getSelectedIndex();
        estudiantes=Service_Locator.getInstance().getDirectorio_service().getTutorados();
        String a=list.getSelectionModel().getSelectedItem();
        ListarTutorados();
        if (list.getItems().contains(a))
            list.getSelectionModel().select(a);
        LlenarTablaTutorados();
        list.getSelectionModel().selectPrevious();
        list.getSelectionModel().select(index);
    }
    public Estudiante getGraduadoBy(String ci)
    {
        Iterator<Estudiante> estudianteIterator=estudiantes.iterator();
        Estudiante estudiante=null;
        boolean is=false;
        while (estudianteIterator.hasNext()&&is)
        {
            estudiante=estudianteIterator.next();
            if (estudiante.getNidentidad().equals(ci))
                is=true;
        }
        return estudiante;
    }
    public void FiltrarTablas(String nom)
    {
        switch (jfxtappane.getSelectionModel().getSelectedIndex())
        {
            case 0:
                FiltrarTablas<Graduado_Data> filtrarTablas=new FiltrarTablas();
                Predicate<Graduado_Data> predicate=new Predicate<Graduado_Data>() {
                    @Override
                    public boolean test(Graduado_Data graduado_data) {
                        boolean is=true;
                        if (graduado_data.getNombre().toLowerCase().startsWith(nom))
                            is=false;
                        return is;
                    }
                };
                final RecursiveTreeItem<Graduado_Data> filt=new RecursiveTreeItem<>(filtrarTablas.FiltrarTabla(graduado_data,predicate),RecursiveTreeObject::getChildren);
                table_graduado.setRoot(filt);
                break;
            case 1:
                FiltrarTablas<Graduado_Data_Color> filtrarTablas2=new FiltrarTablas();
                Predicate<Graduado_Data_Color> predicate2=new Predicate<Graduado_Data_Color>() {
                    @Override
                    public boolean test(Graduado_Data_Color graduado_data) {
                        boolean is=true;
                        if (graduado_data.getNombre().getText().toLowerCase().startsWith(nom.toLowerCase()))
                            is=false;
                        return is;
                    }
                };
                final RecursiveTreeItem<Graduado_Data_Color> filt2=new RecursiveTreeItem<>(filtrarTablas2.FiltrarTabla(tutorado,predicate2),RecursiveTreeObject::getChildren);
                table_graduado1.setRoot(filt2);
                break;
            case 2:
                list.getItems().removeIf(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        boolean is=true;
                        if (s.startsWith(nom))
                            is=false;
                        return is;
                    }
                });
                break;
        }
    }

    public void ActualizarEstu() throws Exception
    {
        estudiantes=Service_Locator.getInstance().getDirectorio_service().getTutorados();
    }

    private void InstanciarAnimaciones()
    {
         Animaciones.AnimacionTipo1(addPlan);
        Animaciones.AnimacionTipo1(prorog);
        Animaciones.AnimacionTipo1(editPlan);
        Animaciones.AnimacionTipo1(delPlan);
        Animaciones.AnimacionTipo1(evaletapa);
        Animaciones.AnimacionTipo1(evaltarea);
        Animaciones.AnimacionTipo1(addTareaExt);
        Animaciones.AnimacionTipo1(save);
    }

    private class Graduado_Data_Color extends RecursiveTreeObject<Graduado_Data_Color>
    {
        int idPlan;
        int idEstud;
        private SimpleObjectProperty<Label> nombre;
        private SimpleObjectProperty<Label> ci;
        private SimpleObjectProperty<Label> especialidad;
        private SimpleObjectProperty<Label> annoPlan;
        private SimpleObjectProperty<Label> aprobadopor;
        private SimpleObjectProperty<Label> estadoPlan;

        public Graduado_Data_Color(int idPlan, int idEstud, String nombre, String ci, String especialidad, int annoPlan, String aprobadopor, String estadoPlan, Color color) {
            this.idPlan = idPlan;
            this.idEstud = idEstud;
            this.nombre = new SimpleObjectProperty<>(new Label(nombre));
            this.ci = new SimpleObjectProperty<>(new Label(ci));
            this.especialidad = new SimpleObjectProperty<>(new Label(especialidad));
            this.annoPlan = new SimpleObjectProperty<>(new Label(new Integer(annoPlan).toString()));
            this.aprobadopor = new SimpleObjectProperty<>(new Label(aprobadopor));
            this.estadoPlan = new SimpleObjectProperty<>(new Label(estadoPlan));
            SetColor(color);
        }

        public void SetColor(Color color)
        {
            nombre.getValue().setTextFill(color);
            ci.getValue().setTextFill(color);
            especialidad.getValue().setTextFill(color);
            annoPlan.getValue().setTextFill(color);
            aprobadopor.getValue().setTextFill(color);
            estadoPlan.getValue().setTextFill(color);

            /*
            int tam=15;
            nombre.getValue().setFont(Font.font("System", FontWeight.EXTRA_BOLD,tam));
            ci.getValue().setFont(Font.font("System", FontWeight.EXTRA_BOLD,tam));
            especialidad.getValue().setFont(Font.font("System", FontWeight.EXTRA_BOLD,tam));
            annoPlan.getValue().setFont(Font.font("System", FontWeight.EXTRA_BOLD,tam));
            aprobadopor.getValue().setFont(Font.font("System", FontWeight.EXTRA_BOLD,tam));
            estadoPlan.getValue().setFont(Font.font("System", FontWeight.EXTRA_BOLD,tam));
             */
        }

        public int getIdPlan() {
            return idPlan;
        }

        public int getIdEstud() {
            return idEstud;
        }

        public Label getNombre() {
            return nombre.get();
        }

        public SimpleObjectProperty<Label> nombreProperty() {
            return nombre;
        }

        public Label getCi() {
            return ci.get();
        }

        public SimpleObjectProperty<Label> ciProperty() {
            return ci;
        }

        public Label getEspecialidad() {
            return especialidad.get();
        }

        public SimpleObjectProperty<Label> especialidadProperty() {
            return especialidad;
        }

        public Label getAnnoPlan() {
            return annoPlan.get();
        }

        public SimpleObjectProperty<Label> annoPlanProperty() {
            return annoPlan;
        }

        public Label getAprobadopor() {
            return aprobadopor.get();
        }

        public SimpleObjectProperty<Label> aprobadoporProperty() {
            return aprobadopor;
        }

        public Label getEstadoPlan() {
            return estadoPlan.get();
        }

        public SimpleObjectProperty<Label> estadoPlanProperty() {
            return estadoPlan;
        }
    }






}
