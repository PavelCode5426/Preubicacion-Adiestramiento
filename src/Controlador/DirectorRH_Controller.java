package Controlador;

import Auxiliar.Animaciones;
import Auxiliar.Area_JefeA_CantG;
import Auxiliar.Busquedas;
import Auxiliar.Revisar_Plan_Table;
import Modelo.Directorio;
import Modelo.Permiso;
import Modelo.PlanFamiliarizacion;
import Reportes.Visuales.GetPantalla_Reportes;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import Vista.IDraggedScene;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Shadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.ResourceBundle;

public class DirectorRH_Controller implements Initializable, IDraggedScene {
    @FXML
    private JFXTreeTableView<Area_JefeA_CantG> tableMAIN;
    @FXML
    private TreeTableColumn<Area_JefeA_CantG, String> areaCol;
    @FXML
    private TreeTableColumn<Area_JefeA_CantG, String> jefeCol;
    @FXML
    private TreeTableColumn<Area_JefeA_CantG, String> cantCol;


    @FXML
    private JFXTreeTableView<Revisar_Plan_Table> tabla;
    @FXML
    private TreeTableColumn<Revisar_Plan_Table, String> confectcol;
    @FXML
    private TreeTableColumn<Revisar_Plan_Table, String> cantactcol;
    @FXML
    private TreeTableColumn<Revisar_Plan_Table, String> anocol;
    @FXML
    private TreeTableColumn<Revisar_Plan_Table, String> notacol;
    @FXML
    private JFXButton revisarbtn;

    @FXML
    private MenuItem Ayuda;
    @FXML
    private MenuItem notfmf;
    @FXML
    private MenuItem mi_perfil;
    @FXML
    private MenuItem menuItem_ReporteTesisAp;

    @FXML
    private MenuItem menuItem_reporteEspecXArea;

    @FXML
    private MenuItem menuItem_ReporteAsistenciaPlanF;

    @FXML
    private JFXButton asigArea;

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


    public static void setStage(Stage stage) {
        DirectorRH_Controller.stage = stage;
    }

    public static void setIdDirector(int idDirector) {
        DirectorRH_Controller.idDirector = idDirector;
    }

    /*
        DECLARACIONES
         */
    private static Stage stage;
    private static int idDirector;
    private Directorio director;

    private boolean isRevisar;
    private boolean isAsignar;

    private Busquedas busquedas;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        onDraggedScene(PaneExterior);
        ConfigurarMinimizarMaximizar(PaneExterior,botonMinimizarVentana,botonCerrarVentana);

        try {

            busquedas=new Busquedas();

            areaCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Area_JefeA_CantG, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Area_JefeA_CantG, String> param) {
                    return param.getValue().getValue().areaProperty();
                }
            });
            jefeCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Area_JefeA_CantG, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Area_JefeA_CantG, String> param) {
                    return param.getValue().getValue().jefeProperty();
                }
            });
            cantCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Area_JefeA_CantG, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Area_JefeA_CantG, String> param) {
                    return param.getValue().getValue().cantGProperty();
                }
            });
            asigArea.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla.AsignarGraduador_Areas(DirectorRH_Controller.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            notfmf.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla.Pantalla_Notificaciones(notfmf);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            Ayuda.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    try {
                        GetPantalla.Ayuda(5);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            mi_perfil.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla.Pantalla_Perfil();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            menuItem_ReporteTesisAp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla_Reportes.Reporte_Est_TesisAplicable(-1,1);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            });
            menuItem_reporteEspecXArea.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla_Reportes.Reporte_EspecialidadXArea();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            });
            menuItem_ReporteAsistenciaPlanF.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla_Reportes.Reporte_AsistenciaActvidades();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            });

            int cant = Service_Locator.getInstance().getNotificacion_service().getAllNotificaciones(Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId()).size();
            if (cant > 0)
                notfmf.setText("Notificaciones: " + cant);
            else
                notfmf.setText("Sin Notificaciones");


            LlenarTablaMAIN();
            InstanciarAnimaciones();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            revisarbtn.setDisable(true);
            cantactcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Revisar_Plan_Table, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Revisar_Plan_Table, String> param) {
                    return param.getValue().getValue().cantgradProperty();
                }
            });
            confectcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Revisar_Plan_Table, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Revisar_Plan_Table, String> param) {
                    return param.getValue().getValue().nombreProperty();
                }
            });
            anocol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Revisar_Plan_Table, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Revisar_Plan_Table, String> param) {
                    return param.getValue().getValue().annoProperty();
                }
            });
            notacol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Revisar_Plan_Table, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Revisar_Plan_Table, String> param) {
                    return param.getValue().getValue().estadoProperty();
                }
            });
            revisarbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        PlanFamiliarizacion a = Service_Locator.getInstance().
                                getPlanificacion_familiarizacion_service().getPlanFamiliarizacionbyID(tabla.getSelectionModel().getSelectedItem().getValue().getId());
                        GetPantalla.PlandeFamiliarizacionERH(a.getId(), DirectorRH_Controller.this);
                        a.setRevisado(true);
                        Service_Locator.getInstance().getPlanificacion_familiarizacion_service().NuevoEditarPlanFamiliarizacion(a);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            tabla.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Revisar_Plan_Table>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Revisar_Plan_Table>> observable, TreeItem<Revisar_Plan_Table> oldValue, TreeItem<Revisar_Plan_Table> newValue) {
                    revisarbtn.setDisable(tabla.getSelectionModel().isEmpty());
                }
            });

            Iterator<Permiso> iterator = Service_Locator.getInstance().getLoggin_roles_service().getPermisosUsuario().iterator();
            while (iterator.hasNext()) {
                Permiso permiso = iterator.next();
                if (permiso.getNombre().equals("Revisar Plan de Familiarización"))
                    isRevisar = true;
                if (permiso.getNombre().equals("Asignación de graduados a áreas"))
                    isAsignar = true;
            }
            if (!isAsignar) {
                asigArea.setDisable(true);
                asigArea.disableProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        asigArea.setDisable(true);
                    }
                });
            }
            if (!isRevisar) {
                revisarbtn.setDisable(true);
                revisarbtn.disableProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        revisarbtn.setDisable(true);
                    }
                });



            }


            LlenarTabla();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void LlenarTablaMAIN() throws Exception {
        ResultSet x = Service_Locator.getInstance().getGraduado_service().Relacion_Graduados_Area();
        ObservableList<Area_JefeA_CantG> list = FXCollections.observableArrayList();
        int areaAux = 0;
        Directorio directAux = new Directorio();


        while (x.next()) {
            areaAux = x.getInt(2);
            directAux = busquedas.directorioJefeArea(areaAux);

            if (directAux != null) {

                directAux = busquedas.directorioJefeArea(areaAux);
                list.add(new Area_JefeA_CantG(x.getString(1), directAux.getNombreApellido(), x.getInt(3), directAux.getId()));
            } else {
                list.add(new Area_JefeA_CantG(x.getString(1), "Sin Jefe de Área", x.getInt(3), -1));
            }
        }

        final TreeItem<Area_JefeA_CantG> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        tableMAIN.setRoot(root);
        tableMAIN.setShowRoot(false);
    }

    private ObservableList<Revisar_Plan_Table> ListaDePlanes() throws Exception {
        ObservableList<Revisar_Plan_Table> lis = FXCollections.observableArrayList();
        ResultSet a = Service_Locator.getInstance().getPlanificacion_familiarizacion_service().getPlanesaRevisar();
        while (a.next()) {
            lis.add(new Revisar_Plan_Table(a.getString(1), new Integer(a.getInt(2)).toString(), a.getString(4), new Integer(a.getInt(3)).toString(), a.getInt(5)));
        }

        return lis;
    }

    public void LlenarTabla() throws Exception {
        TreeItem<Revisar_Plan_Table> item = new RecursiveTreeItem<>(ListaDePlanes(), RecursiveTreeObject::getChildren);
        tabla.setRoot(item);
        tabla.setShowRoot(false);
    }

    private void InstanciarAnimaciones() {
        Animaciones.AnimacionTipo1(revisarbtn);
        Animaciones.AnimacionTipo1(asigArea);
    }

}
