package Controlador;

import Auxiliar.Animaciones;
import Auxiliar.Excepciones.AppException;
import Auxiliar.FiltrarTablas;
import Auxiliar.Graduado_Data;
import Auxiliar.Plan_Fam_Data;
import Modelo.Estudiante;
import Modelo.Permiso;
import Modelo.PlanFamiliarizacion;
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
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.rmi.ServerError;
import java.sql.ResultSet;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Predicate;


public class EspecialistaRH_Controller implements Initializable, IDraggedScene {
    @FXML
    private JFXTreeTableView<Plan_Fam_Data> tableplan;
    @FXML
    private TreeTableColumn<Plan_Fam_Data, String> nomcol;
    @FXML
    private TreeTableColumn<Plan_Fam_Data, String> actcol;
    @FXML
    private TreeTableColumn<Plan_Fam_Data, String> estcol;
    @FXML
    private JFXButton newplabtn;
    @FXML
    private JFXButton ediplanbtn;
    @FXML
    private JFXButton elimplanbtn;
    @FXML
    private JFXTreeTableView<Graduado_Data> tablegrad;
    @FXML
    private TreeTableColumn<Graduado_Data, String> nomapcol;
    @FXML
    private TreeTableColumn<Graduado_Data, String> cicol;
    @FXML
    private TreeTableColumn<Graduado_Data, String> especol;
    @FXML
    private TreeTableColumn<Graduado_Data, String> areacol;
    @FXML
    private TreeTableColumn<Graduado_Data, String> annocol;
    @FXML
    private JFXButton newgradbtn;
    @FXML
    private JFXButton edigradbtn;
    @FXML
    private JFXButton elimgradbtn;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXButton imporgradbtn;
    @FXML
    private JFXTextField buscartf;
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
    private MenuItem menuItem_Reporte1;
    @FXML
    private MenuItem menu_Reporte_EstXEspec;
    @FXML
    private MenuItem menu_Reporte_EstXProc;

    @FXML
    private MenuItem menu_Ayuda;


    /*
    Declaraciones
     */
    private static Stage stage;
    private ObservableList<Graduado_Data> list_grad;
    private boolean permisoNewPlan = false;
    private boolean permisoNewEstudiante = false;


    public static void setStage(Stage stage) {
        EspecialistaRH_Controller.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Pa que la pantalla se mueva
        onDraggedScene(pane);
        ConfigurarMinimizarMaximizar(pane,botonMinimizarVentana,botonCerrarVentana);


        try {
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
            areacol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Data, String> param) {
                    return param.getValue().getValue().areaProperty();
                }
            });
            annocol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Data, String> param) {
                    return param.getValue().getValue().annoProperty();
                }
            });
            nomcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Fam_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Fam_Data, String> param) {
                    return param.getValue().getValue().nombreProperty();
                }
            });
            actcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Fam_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Fam_Data, String> param) {
                    return param.getValue().getValue().cantgradProperty();
                }
            });
            estcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Fam_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Fam_Data, String> param) {
                    return param.getValue().getValue().estadoProperty();
                }
            });
            edigradbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        try {
                            if (!tablegrad.getSelectionModel().isEmpty()) {
                                Estudiante estudiante = Service_Locator.getInstance().getGraduado_service().getGraduado(tablegrad.getSelectionModel().getSelectedItem().getValue().getId());
                                if (!estudiante.isCujaenno())
                                    GetPantalla.NuevoEstudiante(estudiante);
                                else
                                    GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "Este sistema no puede cambiar esta información", "DARKRED");
                            }

                        } catch (Exception e) {
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error",  AppException.getJustMensaje(e.getMessage()), "DARKRED");
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error",  AppException.getJustMensaje(e.getMessage()), "DARKRED");
                        e.printStackTrace();
                    }
                }
            });
            elimgradbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        int eliminar = GetPantalla.panel_De_Notificacion_Confirmar( "Está seguro que desea eliminar a ese estudiante?", "Confirmación", "#065325");
                        if (eliminar == 1) {
                            EliminarGraduado();
                            LlenarTablaGraduados();
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Graduado eliminado correctamente", "DARKGREEN");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error",  AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }

                }
            });
            imporgradbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla.Pantalla_Importar_Graduados(EspecialistaRH_Controller.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });




            InstanciarAnimaciones();
            LlenarTablaGraduados();
            LlenarTablaPlanesFamiliarizacion();
            newgradbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla.NuevoEstudiante(null);
                        tableplan.getSelectionModel().clearSelection();
                        ediplanbtn.setDisable(true);
                        elimplanbtn.setDisable(true);
                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                }
            });
            elimplanbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        int eliminar = GetPantalla.panel_De_Notificacion_Confirmar( "Está seguro que desea eliminar  ese Plan de Familiarización?", "Confirmación", "#065325");
                        if (eliminar == 1) {

                            EliminarPlanFamiliarizacion();
                            LlenarTablaPlanesFamiliarizacion();
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Plan de Familiarización eliminado correctamente", "DARKGREEN");
                        }
                    } catch (Exception e) {
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                        e.printStackTrace();
                    }
                }
            });
            newplabtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla.PlandeFamiliarizacionERH(-1, EspecialistaRH_Controller.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            ediplanbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        int id = tableplan.getSelectionModel().getSelectedItem().getValue().getIdPla();
                        System.out.println("ID Plan====" + id);
                        PlanFamiliarizacion familiarizacion = Service_Locator.getInstance().
                                getPlanificacion_familiarizacion_service().getPlanFamiliarizacionbyID(id);
                        if (familiarizacion != null) {
                            GetPantalla.PlandeFamiliarizacionERH(familiarizacion.getId(), EspecialistaRH_Controller.this);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error",  AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            tableplan.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Plan_Fam_Data>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Plan_Fam_Data>> observable, TreeItem<Plan_Fam_Data> oldValue, TreeItem<Plan_Fam_Data> newValue) {
                    if (tableplan.getSelectionModel().isEmpty()) {
                        ediplanbtn.setDisable(true);
                        elimplanbtn.setDisable(true);
                    } else {
                        ediplanbtn.setDisable(false);
                        elimplanbtn.setDisable(false);
                    }
                }
            });
            tablegrad.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Graduado_Data>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Graduado_Data>> observable, TreeItem<Graduado_Data> oldValue, TreeItem<Graduado_Data> newValue) {
                    if (tablegrad.getSelectionModel().isEmpty()) {
                        edigradbtn.setDisable(true);
                        elimgradbtn.setDisable(true);
                    } else {
                        edigradbtn.setDisable(false);
                        elimgradbtn.setDisable(false);
                    }
                }
            });
            buscartf.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    try {
                        String e = event.getCharacter();
                        if (e.equals("\b"))
                            e = "";
                        String key = buscartf.getText() + e;
                        LlenarTablaGraduados();
                        if (!key.equals(""))
                            FiltrarTablas(key.toLowerCase());
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
            menu_Reporte_EstXEspec.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla_Reportes.Reporte_EstX_Espec((Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId()), -1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            menu_Reporte_EstXProc.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla_Reportes.Reporte_Est_X_Procedencia(Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId(), -1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });


            menu_Ayuda.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        GetPantalla.Ayuda(6);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

            Estudiante_Controller.setEspecialistaRH_controller(this);
            ListIterator<Permiso> permisosIt = Service_Locator.getInstance().getLoggin_roles_service().getPermisosUsuario().listIterator();
            while (permisosIt.hasNext()) {
                Permiso permiso = permisosIt.next();
                if (permiso.getNombre().equals("Ingresar Plan de Familiarización"))
                    permisoNewPlan = true;
                if (permiso.getNombre().equals("Ingresar los graduados que no son de la CUJAE"))
                    permisoNewEstudiante = true;
            }
            if (!permisoNewPlan) {
                newplabtn.setDisable(true);
                elimplanbtn.setDisable(true);
                ediplanbtn.setDisable(true);
                newplabtn.disableProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        newplabtn.setDisable(true);
                    }
                });
                ediplanbtn.disableProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        ediplanbtn.setDisable(true);
                    }
                });
                elimplanbtn.disableProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        elimplanbtn.setDisable(true);
                    }
                });
            }
            if (!permisoNewEstudiante) {
                newgradbtn.setDisable(true);
                edigradbtn.setDisable(true);
                elimgradbtn.setDisable(true);
                imporgradbtn.setDisable(true);

                imporgradbtn.disableProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        imporgradbtn.setDisable(true);
                    }
                });
                newgradbtn.disableProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        newgradbtn.setDisable(true);
                    }
                });
                edigradbtn.disableProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        edigradbtn.setDisable(true);
                    }
                });
                elimgradbtn.disableProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        elimgradbtn.setDisable(true);
                    }
                });
            }


            int cant = Service_Locator.getInstance().getNotificacion_service().getAllNotificaciones(Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId()).size();
            if (cant > 0)
                notfmf.setText("Notificaciones: " + cant);
            else
                notfmf.setText("Sin Notificaciones");

            menuItem_Reporte1.setOnAction(new EventHandler<ActionEvent>() {
                                              @Override
                                              public void handle(ActionEvent event) {

                                                  try {
                                                      GetPantalla_Reportes.Reporte_Est_TesisAplicable(-1, 1);
                                                  } catch (Exception e) {
                                                      e.printStackTrace();
                                                  }
                                              }
                                          }
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void LlenarTablaGraduados() throws Exception {
        ResultSet retorno = Service_Locator.getInstance().getGraduado_service().Graduados_sin_terminar(false);
        ResultSet retorno2 = Service_Locator.getInstance().getGraduado_service().Graduados_sin_terminar(true);
        list_grad = FXCollections.observableArrayList();
        boolean is1 = retorno.next();
        boolean is2 = retorno2.next();
        while (is1 || is2) {
            if (is1)
                list_grad.add(
                        new Graduado_Data(retorno.getString(1)
                                , retorno.getString(2)
                                , retorno.getString(3)
                                , retorno.getString(4)
                                , new Integer(retorno.getInt(5)).toString(), retorno.getInt(6)));
            if (is2)
                list_grad.add(
                        new Graduado_Data(retorno2.getString(1)
                                , retorno2.getString(2)
                                , retorno2.getString(3)
                                , retorno2.getString(4)
                                , new Integer(retorno2.getInt(5)).toString(), retorno2.getInt(6)));
            is1 = retorno.next();
            is2 = retorno2.next();
        }
        final TreeItem<Graduado_Data> item = new RecursiveTreeItem<>(list_grad, RecursiveTreeObject::getChildren);
        tablegrad.setRoot(item);
        tablegrad.setShowRoot(false);
    }

    public void LlenarTablaPlanesFamiliarizacion() throws Exception {
        ResultSet retorno = Service_Locator.getInstance().getPlanificacion_familiarizacion_service().Planes_Familiarizacion_Logged();
        ObservableList<Plan_Fam_Data> array = FXCollections.observableArrayList();
        while (retorno.next()) {
            array.add(
                    new Plan_Fam_Data(retorno.getString(1)
                            , new Integer(retorno.getInt(2)).toString()
                            , retorno.getString(4), retorno.getInt(3)));
        }
        final TreeItem<Plan_Fam_Data> item = new RecursiveTreeItem<>(array, RecursiveTreeObject::getChildren);
        tableplan.setRoot(item);
        tableplan.setShowRoot(false);
    }

    public void EliminarPlanFamiliarizacion() throws Exception {
        if (!tableplan.getSelectionModel().isEmpty()) {
            int num = tableplan.getSelectionModel().getSelectedItem().getValue().getIdPla();
            PlanFamiliarizacion familiarizacion = Service_Locator.getInstance().getPlanificacion_familiarizacion_service().getPlanFamiliarizacionbyID(num);
            if (familiarizacion.getAprobado() != null)
                throw new Exception("Este Plan ya fue aprobado.");
            Service_Locator.getInstance().getPlanificacion_familiarizacion_service().RemovePlanFamiliarizacion(familiarizacion.getId());
        }
    }

    public void EliminarGraduado() throws Exception {
        if (!tablegrad.getSelectionModel().isEmpty()) {
            Estudiante estudiante = Service_Locator.getInstance().getGraduado_service().getGraduado(tablegrad.getSelectionModel().getSelectedItem().getValue().getId());
            if (estudiante.isCujaenno()) {
                ResultSet set = Service_Locator.getInstance().getGraduado_service().get_Estudiante_FROM_SECUJAE_BY_CARNET(estudiante.getNidentidad());
                set.next();
                Service_Locator.getInstance().getGraduado_service().Set_Adiestramiento_FROM_SECUJAE(set.getInt(1), false);
                Service_Locator.getInstance().getGraduado_service().deleteGraduado(estudiante);
            } else if (Service_Locator.getInstance().getPlanificacion_adiestramiento_service().getPlanAdiestramientofromEstudiante(estudiante.getId()) == null)
                Service_Locator.getInstance().getGraduado_service().deleteGraduado(estudiante);

            else
                throw new Exception("Este estudiante esta siendo adiestrado. No puede ser borrado");
        } else
            throw new Exception("Seleccione un estudiante");
    }

    public void FiltrarTablas(String nom) {
        FiltrarTablas<Graduado_Data> filtrarTablas = new FiltrarTablas();
        Predicate<Graduado_Data> predicate = new Predicate<Graduado_Data>() {
            @Override
            public boolean test(Graduado_Data graduado_data) {
                boolean is = true;
                if (graduado_data.getNombre().toLowerCase().startsWith(nom))
                    is = false;
                return is;
            }
        };
        final RecursiveTreeItem<Graduado_Data> filt = new RecursiveTreeItem<>(filtrarTablas.FiltrarTabla(list_grad, predicate), RecursiveTreeObject::getChildren);
        tablegrad.setRoot(filt);

    }

    private void InstanciarAnimaciones() {
        Animaciones.AnimacionTipo1(newplabtn);
        Animaciones.AnimacionTipo1(ediplanbtn);
        Animaciones.AnimacionTipo1(elimplanbtn);
        Animaciones.AnimacionTipo1(imporgradbtn);
        Animaciones.AnimacionTipo1(newgradbtn);
        Animaciones.AnimacionTipo1(edigradbtn);
        Animaciones.AnimacionTipo1(elimgradbtn);
    }


}
