package Controlador;

import Auxiliar.*;
import Modelo.*;
import Reportes.Visuales.GetPantalla_Reportes;
import Servicio.Graduado_Service;
import Servicio.PreasignacionArea_Service;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import Vista.IDraggedScene;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Pantalla_Vicerector_Primero_Controller implements Initializable, IDraggedScene {

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
    private MenuItem menuItem_reporteEspecXArea;

    @FXML
    private MenuItem menuItem_ReporteAsistenciaPlanF;

    @FXML
    private MenuItem menuItem_ReporteAdiestrados_Primero;
    @FXML
    private MenuItem menuItem_Reporte_Tutor_Est_Jefe;


    @FXML
    private JFXTreeTableView<Conformado_Estado_Data> especialistastable;
    @FXML
    private TreeTableColumn<Conformado_Estado_Data, String> annocol;
    @FXML
    private TreeTableColumn<Conformado_Estado_Data, String> confcol;
    @FXML
    private TreeTableColumn<Conformado_Estado_Data, String> aprobpor;
    @FXML
    private TreeTableColumn<Conformado_Estado_Data, String> estadocol;
    @FXML
    private JFXButton revisarplan;
    @FXML
    private MenuItem Ayuda;
    @FXML
    private MenuItem notfmf;
    @FXML
    private MenuItem mi_perfil;

    //Parte de Aprobar Asignaciones

    @FXML
    private JFXTextField area_tf;
    @FXML
    private JFXTextField buscar_estudiante;
    @FXML
    private JFXTreeTableView<Area_JefeA_CantG> cantArea_table;
    @FXML
    private TreeTableColumn<Area_JefeA_CantG, String> colum_nombreArea;
    @FXML
    private TreeTableColumn<Area_JefeA_CantG, String> colum_cantGrad;
    @FXML
    private TreeTableColumn<Area_JefeA_CantG, String> colum_jefeArea;
    @FXML
    private JFXTreeTableView<Graduado_Info> asignados_table;
    @FXML
    private TreeTableColumn<Graduado_Info, String> colum_nombGrad;
    @FXML
    private TreeTableColumn<Graduado_Info, String> colum_carnet_grad;
    @FXML
    private TreeTableColumn<Graduado_Info, String> colum_areaAsig;
    @FXML
    private TreeTableColumn<Graduado_Info, String> colum_especiGrad;
    @FXML
    private TreeTableColumn<Graduado_Info, String> colum_lugarProc;
    @FXML
    private JFXButton aprobar_asig;
    @FXML
    private JFXButton rechaz_asig;

    private ArrayList<PlanFamiliarizacion> PlanesFamiliarizacion;
    private ArrayList<Directorio> directorios;
    private static Stage stage;
    private ObservableList<Area_JefeA_CantG> area_jefeA_cantGObservableList = FXCollections.observableArrayList();
    private ObservableList<Graduado_Info> graduado_infoObservableList = FXCollections.observableArrayList();
    private ArrayList<Area> area;


    private boolean isAprobarPF = false;
    private boolean isAprobarAG = false;
    private Busquedas busquedas;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        onDraggedScene(PaneExterior);
        ConfigurarMinimizarMaximizar(PaneExterior,botonMinimizarVentana,botonCerrarVentana);


        try {
            busquedas = new Busquedas();

            PlanesFamiliarizacion = Service_Locator.getInstance().getPlanificacion_familiarizacion_service().getAllPlan_Familiarizacion();
            directorios = busquedas.getDirectorios();
            annocol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Conformado_Estado_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Conformado_Estado_Data, String> param) {
                    return param.getValue().getValue().annoProperty();
                }
            });
            aprobpor.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Conformado_Estado_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Conformado_Estado_Data, String> param) {
                    return param.getValue().getValue().aprobadoProperty();
                }
            });
            confcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Conformado_Estado_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Conformado_Estado_Data, String> param) {
                    return param.getValue().getValue().conformadoProperty();
                }
            });
            estadocol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Conformado_Estado_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Conformado_Estado_Data, String> param) {
                    return param.getValue().getValue().estadoProperty();
                }
            });

            colum_nombreArea.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Area_JefeA_CantG, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Area_JefeA_CantG, String> param) {
                    return param.getValue().getValue().areaProperty();
                }
            });
            colum_cantGrad.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Area_JefeA_CantG, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Area_JefeA_CantG, String> param) {
                    return param.getValue().getValue().cantGProperty();
                }
            });
            colum_jefeArea.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Area_JefeA_CantG, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Area_JefeA_CantG, String> param) {
                    return param.getValue().getValue().jefeProperty();
                }
            });

            colum_nombGrad.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Info, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Info, String> param) {
                    return param.getValue().getValue().nombreProperty();
                }
            });
            colum_carnet_grad.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Info, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Info, String> param) {
                    return param.getValue().getValue().ciProperty();
                }
            });

            colum_areaAsig.setCellValueFactory(param -> param.getValue().getValue().areaProperty());
            colum_lugarProc.setCellValueFactory(param -> param.getValue().getValue().lugarProcProperty());
            colum_especiGrad.setCellValueFactory(param -> param.getValue().getValue().especialidadProperty());

            area = busquedas.getAreas();

            LlenarTablaPlanesFamiliarizacion();

            especialistastable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Conformado_Estado_Data>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Conformado_Estado_Data>> observable, TreeItem<Conformado_Estado_Data> oldValue, TreeItem<Conformado_Estado_Data> newValue) {
                    revisarplan.setDisable(especialistastable.getSelectionModel().isEmpty());
                }
            });
            revisarplan.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        PlanFamiliarizacion familiarizacion = getPlanSelected();
                        if (familiarizacion.isRevisado())
                            GetPantalla.Revisar_Plan_Familiarizacion(Pantalla_Vicerector_Primero_Controller.this, familiarizacion);
                        else {
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "El plan no ha sido revisado por el Director de Recursos Humanos", "DARKRED");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            Ayuda.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla.Ayuda(7);
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
            Animaciones.AnimacionTipo1(revisarplan);

            area_jefeA_cantGObservableList.addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    boolean is = area_jefeA_cantGObservableList.isEmpty();
                    aprobar_asig.setDisable(is);
                    rechaz_asig.setDisable(is);
                }
            });

            LlenarTablaCantidad_en_Area();
            LlenarTablaPreasignados_en_Area();


            buscar_estudiante.setOnKeyTyped(event ->
            {
                try {
                    String text = buscar_estudiante.getText();
                    String e = event.getCharacter();
                    if (e.equals("\b") || e.equals("\t"))
                        e = "";
                    text += e;
                    String a = area_tf.getText();
                    LlenarTablaPreasignados_en_Area();
                    if (!text.equals(""))
                        FiltrarGraduados(text, a);
                    else if (!a.equals(""))
                        FiltrarAreas(a, "");


                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            area_tf.setOnKeyTyped(event ->
            {
                try {
                    String text = area_tf.getText();
                    String e = event.getCharacter();
                    if (e.equals("\b") || e.equals("\t"))
                        e = "";
                    text += e;
                    String est = buscar_estudiante.getText();
                    LlenarTablaCantidad_en_Area();
                    LlenarTablaPreasignados_en_Area();
                    if (!text.equals(""))
                        FiltrarAreas(text, est);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            rechaz_asig.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        aprobar_asig.setDisable(true);
                        rechaz_asig.setDisable(true);
                        Service_Locator.getInstance().getNotificacion_service().EnviarNotificacionToDirectorRecursosHumanos("Preasignación de graduados rechazada");
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Preasignación de graduados rechazada", "DARKGREEN");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            aprobar_asig.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        aprobar_asig.setDisable(true);
                        rechaz_asig.setDisable(true);
                        Service_Locator.getInstance().getNotificacion_service().EnviarNotificacionToDirectorRecursosHumanos("Preasignación de graduados aprobado");
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Preasignación de graduados aprobada", "DARKGREEN");
                        AceptarUbicacionGraduados();
                        LlenarTablaCantidad_en_Area();
                        LlenarTablaPreasignados_en_Area();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            if (graduado_infoObservableList.size() == 0) {
                rechaz_asig.setDisable(true);
                aprobar_asig.setDisable(true);
            }


            Iterator<Permiso> iterator = Service_Locator.getInstance().getLoggin_roles_service().getPermisosUsuario().iterator();
            while (iterator.hasNext()) {
                Permiso permiso = iterator.next();
                if (permiso.getNombre().equals("Aprobar asignación de graduados a áreas"))
                    isAprobarAG = true;
                if (permiso.getNombre().equals("Aprobar Plan de Familiarización"))
                    isAprobarPF = true;
            }
            if (!isAprobarPF) {
                revisarplan.setDisable(true);
                revisarplan.disableProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        revisarplan.setDisable(true);
                    }
                });
            }
            if (!isAprobarAG) {
                aprobar_asig.setDisable(true);
                aprobar_asig.disableProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        aprobar_asig.setDisable(true);
                    }
                });
                rechaz_asig.setDisable(true);
                rechaz_asig.disableProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        rechaz_asig.setDisable(true);
                    }
                });
            }


            if (!graduado_infoObservableList.isEmpty()) {
                rechaz_asig.setDisable(false);
                aprobar_asig.setDisable(false);
            }


            int cant = Service_Locator.getInstance().getNotificacion_service().getAllNotificaciones(Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId()).size();
            if (cant > 0)
                notfmf.setText("Notificaciones: " + cant);
            else
                notfmf.setText("Sin Notificaciones");




            menuItem_ReporteTesisAp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla_Reportes.Reporte_Est_TesisAplicable(-1,1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            menuItem_reporteEspecXArea.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla_Reportes.Reporte_EspecialidadXArea();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            menuItem_ReporteAsistenciaPlanF.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla_Reportes.Reporte_AsistenciaActvidades();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

            menuItem_ReporteAdiestrados_Primero.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla_Reportes.Reporte_Adiestrados_Primero();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

            menuItem_Reporte_Tutor_Est_Jefe.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla_Reportes.Reporte_Tutor_Est_JefeArea();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Metodos Basicos
    public String DirectorioID(int ID) {
        String nombre = "N/A";
        boolean is = false;
        Iterator<Directorio> iterator = directorios.iterator();
        Directorio hold = null;
        while (iterator.hasNext()) {
            hold = iterator.next();
            if (hold.getId() == ID) {
                is = true;
                nombre = hold.getNombreApellido();
            }
        }
        return nombre;
    }

    public static void setStage(Stage stage) {
        Pantalla_Vicerector_Primero_Controller.stage = stage;
    }

    //Metodos de Plan de Familiarizacion
    public void LlenarTablaPlanesFamiliarizacion() throws Exception {
        ObservableList<Conformado_Estado_Data> data = FXCollections.observableArrayList();
        Iterator<PlanFamiliarizacion> iterator = PlanesFamiliarizacion.iterator();
        while (iterator.hasNext()) {
            PlanFamiliarizacion next = iterator.next();
            String estado = "";
            if (next.isRechazado())
                estado = "Rechazado";
            else if (next.getAprobado() != null)
                estado = "Aprobado";
            else if (next.isRevisado())
                estado = "Revisado por Dir. Recursos Humanos";
            else if (!next.isRevisado())
                estado = "Sin Revisar por Dir. Recursos Humanos";


            int idAprob;
            if (next.getAprobado() == null)
                idAprob = 0;
            else idAprob = next.getAprobado().intValue();
            Conformado_Estado_Data estado_data = new Conformado_Estado_Data(DirectorioID(next.getConfeccionado()), DirectorioID(idAprob), estado, String.valueOf(next.getAnno()), next.getId());
            data.add(estado_data);

        }
        final RecursiveTreeItem<Conformado_Estado_Data> item = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);
        especialistastable.setRoot(item);
        especialistastable.setShowRoot(false);
    }

    private PlanFamiliarizacion getPlanSelected() {
        PlanFamiliarizacion pos = null;
        ListIterator<PlanFamiliarizacion> iterator = PlanesFamiliarizacion.listIterator();
        Conformado_Estado_Data value = especialistastable.getSelectionModel().getSelectedItem().getValue();
        boolean is = false;
        while (iterator.hasNext() && !is) {
            pos = iterator.next();
            if (pos.getAnno() == Integer.parseInt(value.getAnno()))
                is = true;
        }
        return pos;
    }

    //Metodos de Asignacion de Graduados
    private void LlenarTablaCantidad_en_Area() throws Exception {
        area_jefeA_cantGObservableList = FXCollections.observableArrayList();
        int areaAux = 0;
        Directorio directAux =null;

        ResultSet resultSet = Service_Locator.getInstance().getGraduado_service().Relacion_Graduados_Preubicado_Area();
        while (resultSet.next()) {
            areaAux = resultSet.getInt(2);
            directAux = busquedas.directorioJefeArea(areaAux);

            if (directAux != null) {
                directAux = busquedas.directorioJefeArea(areaAux);
                area_jefeA_cantGObservableList.add(new Area_JefeA_CantG(resultSet.getString(1), directAux.getNombreApellido(), resultSet.getInt(3), directAux.getId()));
            } else {
                area_jefeA_cantGObservableList.add(new Area_JefeA_CantG(resultSet.getString(1), "Sin Jefe de Área", resultSet.getInt(3), -1));
            }
        }

        final TreeItem<Area_JefeA_CantG> item = new RecursiveTreeItem<>(area_jefeA_cantGObservableList, RecursiveTreeObject::getChildren);
        cantArea_table.setShowRoot(false);
        cantArea_table.setRoot(item);
    }

    private void LlenarTablaPreasignados_en_Area() throws Exception {

        graduado_infoObservableList = FXCollections.observableArrayList();
        ResultSet resultSet = Service_Locator.getInstance().getGraduado_service().Preubicacion_Graduados_Info();
        while (resultSet.next()) {
            graduado_infoObservableList.add(new Graduado_Info(resultSet.getString(1)
                    , resultSet.getString(2)
                    , resultSet.getString(3)
                    , resultSet.getString(4)
                    , resultSet.getString(5)
                    , resultSet.getInt(6)));
        }
        final TreeItem<Graduado_Info> item = new RecursiveTreeItem<>(graduado_infoObservableList, RecursiveTreeObject::getChildren);
        asignados_table.setShowRoot(false);
        asignados_table.setRoot(item);
    }

    private void FiltrarAreas(String text, String est) {
        FiltrarTablas<Area_JefeA_CantG> filtrarTablas = new FiltrarTablas<>();
        Predicate<Area_JefeA_CantG> predicate = new Predicate<Area_JefeA_CantG>() {

            @Override
            public boolean test(Area_JefeA_CantG s) {
                boolean is = true;
                if (s.areaProperty().get().toLowerCase().contains(text.toLowerCase()))
                    is = false;
                return is;
            }
        };
        final TreeItem<Area_JefeA_CantG> item = new RecursiveTreeItem<>(filtrarTablas.FiltrarTabla(area_jefeA_cantGObservableList, predicate), RecursiveTreeObject::getChildren);
        cantArea_table.setRoot(item);
        if (!est.equals(""))
            FiltrarGraduados(est, text);
        else
            FiltrarGraduadosArea(text);
    }

    private void FiltrarGraduados(String text, String area) {
        FiltrarTablas<Graduado_Info> filtrarTablas = new FiltrarTablas<>();
        Predicate<Graduado_Info> predicate = new Predicate<Graduado_Info>() {
            @Override
            public boolean test(Graduado_Info s) {
                boolean is = true;
                if (s.getNombre().toLowerCase().contains(text.toLowerCase()))
                    is = false;
                return is;
            }
        };
        graduado_infoObservableList = filtrarTablas.FiltrarTabla(graduado_infoObservableList, predicate);
        FiltrarGraduadosArea(area);
    }

    private void FiltrarGraduadosArea(String area) {
        FiltrarTablas<Graduado_Info> filtrarTablas = new FiltrarTablas<>();
        Predicate<Graduado_Info> predicate = new Predicate<Graduado_Info>() {
            @Override
            public boolean test(Graduado_Info s) {
                boolean is = true;
                if (s.getArea().toLowerCase().startsWith(area.toLowerCase()))
                    is = false;
                return is;
            }
        };
        final TreeItem<Graduado_Info> item = new RecursiveTreeItem<>(filtrarTablas.FiltrarTabla(graduado_infoObservableList, predicate), RecursiveTreeObject::getChildren);
        asignados_table.setRoot(item);
    }

    private void AceptarUbicacionGraduados() throws Exception {
        PreasignacionArea_Service service = Service_Locator.getInstance().getPreasignacionArea_service();
        Iterator<AreaPreasignada> iterator = Service_Locator.getInstance().getPreasignacionArea_service().getAllAreasPreasignadas().iterator();
        while (iterator.hasNext()) {
            AreaPreasignada next = iterator.next();
            service.UbicarEstudiante(next.getEstudiante(), next.getArea());
            service.Remove(next.getId());
        }


    }


    private class Graduado_Info extends RecursiveTreeObject<Graduado_Info> {

        private int id;
        private StringProperty nombre;
        private StringProperty ci;
        private StringProperty especialidad;
        private StringProperty area;
        private StringProperty lugarProc;

        public int getId() {
            return id;
        }

        public Graduado_Info(String nom, String ci, String area, String esp, String lugar, int id) {
            this.nombre = new SimpleStringProperty(nom);
            this.ci = new SimpleStringProperty(ci);
            this.especialidad = new SimpleStringProperty(esp);
            this.area = new SimpleStringProperty(area);
            this.lugarProc = new SimpleStringProperty(lugar);
            this.id = id;
        }

        public String getNombre() {
            return nombre.get();
        }

        public StringProperty nombreProperty() {
            return nombre;
        }

        public String getCi() {
            return ci.get();
        }

        public StringProperty ciProperty() {
            return ci;
        }

        public String getEspecialidad() {
            return especialidad.get();
        }

        public StringProperty especialidadProperty() {
            return especialidad;
        }

        public String getArea() {
            return area.get();
        }

        public StringProperty areaProperty() {
            return area;
        }

        public String getLugarProc() {
            return lugarProc.get();
        }

        public StringProperty lugarProcProperty() {
            return lugarProc;
        }
    }

}
