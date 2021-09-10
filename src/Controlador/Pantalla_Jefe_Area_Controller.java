package Controlador;

import Auxiliar.Estudiante_Data_Color_Adiestramiento_JA;
import Auxiliar.Estudiante_Data_Color_Familiarizacion_JA;
import Auxiliar.FiltrarTablas;
import Modelo.Directorio;
import Modelo.Permiso;
import Reportes.Visuales.GetPantalla_Reportes;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import Vista.IDraggedScene;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Pantalla_Jefe_Area_Controller implements Initializable, IDraggedScene
{
    @FXML
    private Tab tab_PlanAdiestram;
    @FXML
    private JFXTextField buscar_a;
    @FXML
    private JFXTreeTableView<Estudiante_Data_Color_Adiestramiento_JA> tabla_adiestramiento;
    @FXML
    private TreeTableColumn<Estudiante_Data_Color_Adiestramiento_JA, Label> colum_nombre_a;
    @FXML
    private TreeTableColumn<Estudiante_Data_Color_Adiestramiento_JA, Label> colum_carnet_a;
    @FXML
    private TreeTableColumn<Estudiante_Data_Color_Adiestramiento_JA, Label> column_esp_a;
    @FXML
    private TreeTableColumn<Estudiante_Data_Color_Adiestramiento_JA, Label> colum_lugar_a;
    @FXML
    private TreeTableColumn<Estudiante_Data_Color_Adiestramiento_JA, Label> colum_estado_a;
    @FXML
    private JFXButton review_plan_adiestramiento;
    @FXML
    private Tab tab_AsistenciaPlanFam;
    @FXML
    private JFXTextField buscar_f;
    @FXML
    private JFXTreeTableView<Estudiante_Data_Color_Familiarizacion_JA> tabla_familiarizacion;
    @FXML
    private TreeTableColumn<Estudiante_Data_Color_Familiarizacion_JA,Label> colum_nombre_f;
    @FXML
    private TreeTableColumn<Estudiante_Data_Color_Familiarizacion_JA,Label> colum_canet_f;
    @FXML
    private TreeTableColumn<Estudiante_Data_Color_Familiarizacion_JA,Label> colum_especialidad_f;
    @FXML
    private TreeTableColumn<Estudiante_Data_Color_Familiarizacion_JA,Label> colum_lugar_f;
    @FXML
    private TreeTableColumn<Estudiante_Data_Color_Familiarizacion_JA,Label> colum_porciento_f;
    @FXML
    private JFXButton asis_eval_plan;
    @FXML
    private MenuItem notfmf;
    @FXML
    private MenuItem mi_perfil;
    @FXML
    private MenuItem menu_Ayuda;

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
    private MenuItem menuItem_ReporteTesisAp;
    @FXML
    private MenuItem menu_Reporte_EstXEspec;
    @FXML
    private MenuItem menu_Reporte_EstXProc;



    /**
     *Declaraciones de Variables
     */



    ObservableList<Estudiante_Data_Color_Adiestramiento_JA> PlanesAdiestramientoEst;
    ObservableList<Estudiante_Data_Color_Familiarizacion_JA> PlanesFamiliarizacionEst;
    private static Stage stage;
    private boolean isPermisoRevisarPlanes=false;
    private boolean isPermisoEvaluar=false;



    public static void setStage(Stage stage) {
        Pantalla_Jefe_Area_Controller.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)    {

        //Poner Pantalla linda
        ConfigurarMinimizarMaximizar(PaneExterior,botonMinimizarVentana,botonCerrarVentana);
        onDraggedScene(PaneExterior);


        try {

            colum_canet_f.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Familiarizacion_JA, Label>, ObservableValue<Label>>() {
                @Override
                public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Familiarizacion_JA, Label> param) {
                    return param.getValue().getValue().carnetProperty();
                }
            });
            colum_carnet_a.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Adiestramiento_JA, Label>, ObservableValue<Label>>() {
                @Override
                public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Adiestramiento_JA, Label> param) {
                    return param.getValue().getValue().carnetProperty();
                }
            });
            colum_nombre_f.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Familiarizacion_JA, Label>, ObservableValue<Label>>() {
                @Override
                public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Familiarizacion_JA, Label> param) {
                    return param.getValue().getValue().nombreProperty();
                }
            });
            colum_nombre_a.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Adiestramiento_JA, Label>, ObservableValue<Label>>() {
                @Override
                public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Adiestramiento_JA, Label> param) {
                    return param.getValue().getValue().nombreProperty();
                }
            });
            colum_especialidad_f.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Familiarizacion_JA, Label>, ObservableValue<Label>>() {
                @Override
                public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Familiarizacion_JA, Label> param) {
                    return param.getValue().getValue().especialidadProperty();
                }
            });
            column_esp_a.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Adiestramiento_JA, Label>, ObservableValue<Label>>() {
                @Override
                public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Adiestramiento_JA, Label> param) {
                    return param.getValue().getValue().especialidadProperty();
                }
            });
            colum_lugar_f.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Familiarizacion_JA, Label>, ObservableValue<Label>>() {
                @Override
                public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Familiarizacion_JA, Label> param) {
                    return param.getValue().getValue().lugarProperty();
                }
            });
            colum_lugar_a.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Adiestramiento_JA, Label>, ObservableValue<Label>>() {
                @Override
                public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Adiestramiento_JA, Label> param) {
                    return param.getValue().getValue().lugarProperty();
                }
            });
            colum_porciento_f.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Familiarizacion_JA, Label>, ObservableValue<Label>>() {
                @Override
                public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Familiarizacion_JA, Label> param) {
                    return param.getValue().getValue().asistenciaProperty();
                }
            });
            colum_estado_a.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Adiestramiento_JA, Label>, ObservableValue<Label>>() {
                @Override
                public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Estudiante_Data_Color_Adiestramiento_JA, Label> param) {
                    return param.getValue().getValue().estadoProperty();
                }
            });

            Iterator<Permiso> iterator=Service_Locator.getInstance().getLoggin_roles_service().getPermisosUsuario().iterator();
            while (iterator.hasNext()&&!(isPermisoEvaluar&&isPermisoRevisarPlanes))
            {
                Permiso permiso=iterator.next();
                if (permiso.getNombre().equals("Aprobar Plan de Formación Complementaria"))
                    isPermisoEvaluar=true;
                if (permiso.getNombre().equals("Evaluar y dar asistencia al Plan de Familiarización"))
                    isPermisoRevisarPlanes=true;
               }
            if (!isPermisoEvaluar)
                asis_eval_plan.disableProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        asis_eval_plan.setDisable(true);
                    }
                });
            if (!isPermisoRevisarPlanes)
                review_plan_adiestramiento.disableProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        review_plan_adiestramiento.setDisable(true);
                    }
                });


            buscar_a.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    try {
                        String text = buscar_a.getText();
                        String e = event.getCharacter();
                        if (e.equals("\t") || e.equals("\b"))
                            e = "";
                        text += e;
                        Llenar_Tabla_Adiestramiento();
                        if (!text.equals(""))
                            FiltrarEstudiantes_Adiestramiento(text);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }



                }
            });
            buscar_f.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    try {
                        String text = buscar_f.getText();
                        String e = event.getCharacter();
                        if (e.equals("\t") || e.equals("\b"))
                            e = "";
                        text += e;
                        Llenar_Tabla_Familiarizacion();
                        if (!text.equals(""))
                            FiltrarEstudiantes_Familiarizacion(text);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            });
            tabla_adiestramiento.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Estudiante_Data_Color_Adiestramiento_JA>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Estudiante_Data_Color_Adiestramiento_JA>> observable, TreeItem<Estudiante_Data_Color_Adiestramiento_JA> oldValue, TreeItem<Estudiante_Data_Color_Adiestramiento_JA> newValue)
                {
                    review_plan_adiestramiento.setDisable(tabla_adiestramiento.getSelectionModel().isEmpty());
                }
            });
            tabla_familiarizacion.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Estudiante_Data_Color_Familiarizacion_JA>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Estudiante_Data_Color_Familiarizacion_JA>> observable, TreeItem<Estudiante_Data_Color_Familiarizacion_JA> oldValue, TreeItem<Estudiante_Data_Color_Familiarizacion_JA> newValue) {
                    asis_eval_plan.setDisable(tabla_familiarizacion.getSelectionModel().isEmpty());
                }
            });

            review_plan_adiestramiento.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event) {
                    try
                    {
                        GetPantalla.Revisar_Plan_Adiestramiento(tabla_adiestramiento.getSelectionModel().getSelectedItem().getValue().getIdPlanAdiestramiento(),Pantalla_Jefe_Area_Controller.this);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            });

            asis_eval_plan.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Estudiante_Data_Color_Familiarizacion_JA a=tabla_familiarizacion.getSelectionModel().getSelectedItem().getValue();
                        GetPantalla.Evaluar_Plan_Familiarizacion_Estudiante(a.getIdEstudiante(),a.getIdInner(),Pantalla_Jefe_Area_Controller.this);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
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
            menu_Ayuda.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla.Ayuda(9);
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




            Llenar_Tabla_Adiestramiento();
            Llenar_Tabla_Familiarizacion();




            menuItem_ReporteTesisAp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla_Reportes.Reporte_Est_TesisAplicable((Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId()),9);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            menu_Reporte_EstXEspec.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla_Reportes.Reporte_EstX_Espec((Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId()),9);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            menu_Reporte_EstXProc.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla_Reportes.Reporte_Est_X_Procedencia(Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId(),9);
                    } catch (Exception e) {
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



    public void Llenar_Tabla_Adiestramiento() throws Exception    {
        int Area= Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getArea();
        ResultSet resultSet=Service_Locator.getInstance().getGraduado_service().Relacion_Graduados_Plan_Adiestramiento_From_Area(Area);
        PlanesAdiestramientoEst= FXCollections.observableArrayList();
        while (resultSet.next())
        {
            boolean revisado=resultSet.getBoolean(6);
            int aprob=resultSet.getInt(5);

            if (aprob!=0)
                PlanesAdiestramientoEst.add(new Estudiante_Data_Color_Adiestramiento_JA(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),"Aprobado",resultSet.getInt(7),resultSet.getInt(8)));
            else if (aprob==0 && revisado)
                PlanesAdiestramientoEst.add(new Estudiante_Data_Color_Adiestramiento_JA(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),"Rechazado",resultSet.getInt(7),resultSet.getInt(8)));
            else
                PlanesAdiestramientoEst.add(new Estudiante_Data_Color_Adiestramiento_JA(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),"N/A",resultSet.getInt(7),resultSet.getInt(8)));

        }
        final TreeItem<Estudiante_Data_Color_Adiestramiento_JA> item=new RecursiveTreeItem<>(PlanesAdiestramientoEst, RecursiveTreeObject::getChildren);
        tabla_adiestramiento.setShowRoot(false);
        tabla_adiestramiento.setRoot(item);
    }

    public void Llenar_Tabla_Familiarizacion()throws Exception  {
        PlanesFamiliarizacionEst=FXCollections.observableArrayList();
        ResultSet resultSet=Service_Locator.getInstance().getGraduado_service().Relacion_Graduados_Plan_Familiarizacion_From_Area(Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getArea());
        while (resultSet.next()) {
            PlanesFamiliarizacionEst.add(new Estudiante_Data_Color_Familiarizacion_JA(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getDouble(5),resultSet.getInt(6),resultSet.getInt(7)));
        }
        final TreeItem<Estudiante_Data_Color_Familiarizacion_JA> item=new RecursiveTreeItem<>(PlanesFamiliarizacionEst, RecursiveTreeObject::getChildren);
        tabla_familiarizacion.setShowRoot(false);
        tabla_familiarizacion.setRoot(item);
    }
    private void FiltrarEstudiantes_Adiestramiento(String text)  {
        Predicate<Estudiante_Data_Color_Adiestramiento_JA> predicate=new Predicate<Estudiante_Data_Color_Adiestramiento_JA>() {
            @Override
            public boolean test(Estudiante_Data_Color_Adiestramiento_JA estudiante_data_color_adiestramiento_ja) {
                boolean is=true;
                if (estudiante_data_color_adiestramiento_ja.getNombre().getText().toLowerCase().startsWith(text.toLowerCase()))
                    is=false;
                return is;
            }
        };
        FiltrarTablas<Estudiante_Data_Color_Adiestramiento_JA> filtrarTablas=new FiltrarTablas<>();
        final TreeItem<Estudiante_Data_Color_Adiestramiento_JA> item=new RecursiveTreeItem<>(filtrarTablas.FiltrarTabla(PlanesAdiestramientoEst,predicate), RecursiveTreeObject::getChildren);
        tabla_adiestramiento.setShowRoot(false);
        tabla_adiestramiento.setRoot(item);
    }
    private void FiltrarEstudiantes_Familiarizacion(String text)  {
        Predicate<Estudiante_Data_Color_Familiarizacion_JA> predicate=new Predicate<Estudiante_Data_Color_Familiarizacion_JA>() {
            @Override
            public boolean test(Estudiante_Data_Color_Familiarizacion_JA estudiante_data_color_adiestramiento_ja) {
                boolean is=true;
                if (estudiante_data_color_adiestramiento_ja.getNombre().getText().toLowerCase().startsWith(text.toLowerCase()))
                    is=false;
                return is;
            }
        };
        FiltrarTablas<Estudiante_Data_Color_Familiarizacion_JA> filtrarTablas=new FiltrarTablas<>();
        final TreeItem<Estudiante_Data_Color_Familiarizacion_JA> item=new RecursiveTreeItem<>(filtrarTablas.FiltrarTabla(PlanesFamiliarizacionEst,predicate), RecursiveTreeObject::getChildren);
        tabla_familiarizacion.setShowRoot(false);
        tabla_familiarizacion.setRoot(item);
    }






}
