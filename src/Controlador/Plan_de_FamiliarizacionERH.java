package Controlador;

import Auxiliar.*;
import Auxiliar.Excepciones.AppException;
import Modelo.*;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import Vista.IDraggedScene;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.oracle.webservices.internal.api.EnvelopeStyleFeature;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;

public class Plan_de_FamiliarizacionERH implements Initializable, IDraggedScene {
    @FXML
    private JFXTextField actividadtf;
    @FXML
    private JFXComboBox<String> esp;
    @FXML
    private JFXComboBox<String> proc;
    @FXML
    private JFXDatePicker fechadp;
    @FXML
    private JFXTimePicker horatp;
    @FXML
    private JFXTextArea descripciontf;
    @FXML
    private JFXButton addbtn;
    @FXML
    private JFXTreeTableView<Plan_Familiarizacion> table;
    @FXML
    private TreeTableColumn<Plan_Familiarizacion, String> actcol;
    @FXML
    private TreeTableColumn<Plan_Familiarizacion, String> respcol;
    @FXML
    private TreeTableColumn<Plan_Familiarizacion, String> lugarcol;
    @FXML
    private TreeTableColumn<Plan_Familiarizacion, String> fechacol;
    @FXML
    private TreeTableColumn<Plan_Familiarizacion, String> holacol;
    @FXML
    private JFXButton finishbtn;
    @FXML
    private JFXButton editbtn;
    @FXML
    private JFXButton delbtn;
    @FXML
    private AnchorPane panel;
    @FXML
    private Spinner<Integer> annospinner;
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

    public static void setStage(Stage stage) {
        Plan_de_FamiliarizacionERH.stage = stage;
    }

    private static Stage stage;
    private ArrayList<Directorio> responsables;
    private ArrayList<Area> lugares;
    private static EspecialistaRH_Controller especialistaRH_controller;
    private static DirectorRH_Controller directorRH_controller;


    public static void setEspecialistaRH_controller(EspecialistaRH_Controller especialistaRH_controller) {
        Plan_de_FamiliarizacionERH.especialistaRH_controller = especialistaRH_controller;
    }

    public static void setDirectorRH_controller(DirectorRH_Controller directorRH_controller) {
        Plan_de_FamiliarizacionERH.directorRH_controller = directorRH_controller;
    }

    private static int idPlan;

    public static void setIdPlan(int idPlan) {
        Plan_de_FamiliarizacionERH.idPlan = idPlan;
    }

    private PlanFamiliarizacion Pfamiliarizacion;
    private ArrayList<TuplaList<Object>> filanonormalizada = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        onDraggedScene(panel);
        ConfigurarMinimizarMaximizar(panel, botonMinimizarVentana, botonCerrarVentana);

        try {

            if (idPlan != -1) {
                Pfamiliarizacion = Service_Locator.getInstance().getPlanificacion_familiarizacion_service().getPlanFamiliarizacionbyID(idPlan);
                filanonormalizada = Service_Locator.getInstance().getPlanificacion_familiarizacion_service().getActividades_Plan_No_Normalizado(Pfamiliarizacion.getId());

                annospinner.setDisable(true);
                editbtn.setDisable(true);
                delbtn.setDisable(true);

                if (Pfamiliarizacion.getAprobado() != null) {
                    finishbtn.setDisable(true);
                    addbtn.setDisable(true);
                    GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Este Plan ya fue aprobado, usted no podrá realizar acción alguna sobre este.", "DARKGREEN");
                }
            } else
                Pfamiliarizacion = null;


            lugares = Service_Locator.getInstance().getArea_service().Areas_List();
            responsables = Service_Locator.getInstance().getDirectorio_service().getDirectorio();

            actcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Familiarizacion, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Familiarizacion, String> param) {
                    return param.getValue().getValue().actividadProperty();
                }
            });
            respcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Familiarizacion, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Familiarizacion, String> param) {
                    return param.getValue().getValue().responsableProperty();
                }
            });
            lugarcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Familiarizacion, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Familiarizacion, String> param) {
                    return param.getValue().getValue().lugarProperty();
                }
            });
            fechacol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Familiarizacion, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Familiarizacion, String> param) {
                    return param.getValue().getValue().fechaProperty();
                }
            });
            holacol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Familiarizacion, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Familiarizacion, String> param) {
                    return param.getValue().getValue().horaProperty();
                }
            });

            table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Plan_Familiarizacion>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Plan_Familiarizacion>> observable, TreeItem<Plan_Familiarizacion> oldValue, TreeItem<Plan_Familiarizacion> newValue) {
                    DisableAll(false);
                    if (table.getSelectionModel().isEmpty()) {
                        editbtn.setDisable(true);
                        delbtn.setDisable(true);
                    } else if (Pfamiliarizacion == null || Pfamiliarizacion.getAprobado() == null) {
                        UnSetData();
                        editbtn.setDisable(false);
                        delbtn.setDisable(false);
                    } else if (Pfamiliarizacion.getAprobado() != null) {
                        UnSetData();
                        editbtn.setDisable(true);
                        delbtn.setDisable(true);
                        addbtn.setDisable(true);
                    }
                }
            });

            LlenarTabla_V250();
            LlenarCombobox(3);
            InstaciarAnimaciones();

            addbtn.setOnAction(
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                NuevaActividad_V250();
                                LlenarTabla_V250();
                                ClearAll();
                                GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Actividad añadida correctamente", "DARKGREEN");
                                finishbtn.setDisable(false);
                            } catch (Exception e) {
                                e.printStackTrace();
                                GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error en la Base de Datos", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                            }


                        }
                    }
            );
            editbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        EditarActividad_V250();
                        ClearAll();
                        LlenarTabla_V250();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Actividad editada correctamente", "DARKGREEN");
                        finishbtn.setDisable(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error en la Base de Datos",AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }

                }
            });
            delbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Eliminar_V250();
                        LlenarTabla_V250();
                        ClearAll();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Actividad eliminada correctamente", "DARKGREEN");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error en la Base de Datos",AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            finishbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        // if (Service_Locator.getInstance().getPlanificacion_familiarizacion_service().getPlanFamiliarizacion(annospinner.getValue())!=null && Pfamiliarizacion==null && directorRH_controller==null)
                        //    throw new Exception("Ya existe un Plan para es año");

                        if (Pfamiliarizacion == null)
                            Pfamiliarizacion = new PlanFamiliarizacion(-1, annospinner.getValue(), Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId(), null, false, false);

                        if (directorRH_controller != null) {
                            Pfamiliarizacion.setAnno(annospinner.getValue());
                            Pfamiliarizacion.setRevisado(true);
                            Pfamiliarizacion.setRechazado(false);
                            Service_Locator.getInstance().getNotificacion_service().EnviarNotifiacionToVicerrector("Plan de Familiarización listo para revisar");
                            SaveData_V250();
                            Service_Locator.getInstance().getNotificacion_service().EnviarNotificacion("Plan de Familiarización revisado por Director de Recursos Humanos", Pfamiliarizacion.getConfeccionado());
                            GetPantalla.Pantalla_Enviar_Notifiacion("Deficiencias del P.Familiarización", Pfamiliarizacion.getConfeccionado(), true);
                            directorRH_controller.LlenarTabla();
                        } else if (Pfamiliarizacion.getAprobado() == null) {

                            Pfamiliarizacion.setRevisado(false);
                            Pfamiliarizacion.setRechazado(false);
                            Pfamiliarizacion.setAnno(annospinner.getValue());
                            SaveData_V250();

                            Service_Locator.getInstance().getPlanificacion_familiarizacion_service().DeleteActividadesFromPlanFamiliarizacion();
                            Service_Locator.getInstance().getPlanificacion_familiarizacion_service().DeletePlanFamiliarizacion();
                            especialistaRH_controller.LlenarTablaPlanesFamiliarizacion();
                            Service_Locator.getInstance().getNotificacion_service().EnviarNotificacionToDirectorRecursosHumanos("Plan de Familiarización realiazado");
                        }
                        stage.close();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Plan gestionado correctamente", "DARKGREEN");

                    } catch (Exception e) {
                        // TODO: 5/6/2020 Araaa akiii
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error en la Base de Datos", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            annospinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(LocalDate.now().getYear(), LocalDate.now().getYear() + 10));
            if (Pfamiliarizacion != null)
                annospinner.getValueFactory().setValue(Pfamiliarizacion.getAnno());

            proc.getEditor().setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    String e = event.getCharacter();
                    if (e.equals("\t") || e.equals("\b"))
                        e = "";
                    final String text = proc.getEditor().getText() + e;
                    LlenarCombobox(2);
                    if (!text.equals("")) {
                        proc.show();
                        proc.getItems().removeIf(new Predicate<String>() {
                            @Override
                            public boolean test(String s) {
                                boolean is = true;
                                if (s.toLowerCase().startsWith(text.toLowerCase()))
                                    is = false;
                                return is;
                            }
                        });
                    }
                }
            });
            esp.getEditor().setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    String e = event.getCharacter();
                    if (e.equals("\t") || e.equals("\b"))
                        e = "";
                    final String text = esp.getEditor().getText() + e;
                    LlenarCombobox(1);
                    if (!text.equals("")) {
                        esp.show();
                        esp.getItems().removeIf(new Predicate<String>() {
                            @Override
                            public boolean test(String s) {
                                boolean is = true;
                                if (s.toLowerCase().startsWith(text.toLowerCase()))
                                    is = false;
                                return is;
                            }
                        });
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error en la Base de Datos", e.getMessage(), "DARKRED");
        }
    }


    private void DisableAll(boolean is) {
        actividadtf.setDisable(is);
        esp.setDisable(is);
        proc.setDisable(is);
        fechadp.setDisable(is);
        horatp.setDisable(is);
        descripciontf.setDisable(is);
    }

    private void LlenarCombobox(int cb) {
        try {
            switch (cb) {
                case 1:
                    esp.getItems().clear();
                    Iterator<Directorio> x = responsables.iterator();
                    while (x.hasNext()) {
                        Directorio o = x.next();
                        if (o.isActivo())
                            esp.getItems().add(o.getNombreApellido());
                    }
                    break;
                case 2:
                    proc.getItems().clear();
                    Iterator<Area> x2 = lugares.iterator();
                    while (x2.hasNext())
                        proc.getItems().add(x2.next().getNombre());
                    break;
                default:
                    LlenarCombobox(1);
                    LlenarCombobox(2);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean Validacion() throws Exception {
        boolean is = true;
        Effect effect = new DropShadow(0.5, Color.DARKRED);
        actividadtf.setEffect(null);
        esp.setEffect(null);
        fechadp.setEffect(null);
        proc.setEffect(null);
        descripciontf.setEffect(null);
        horatp.setEffect(null);
        if (actividadtf.getText().equals("")) {
            actividadtf.setEffect(effect);
            is = false;
        }
        if (esp.getSelectionModel().getSelectedIndex() == -1) {
            esp.setEffect(effect);
            is = false;
        }

        if (proc.getSelectionModel().getSelectedIndex() == -1) {
            proc.setEffect(effect);
            is = false;
        }
        if (fechadp.getValue() == null) {
            fechadp.setEffect(effect);
            is = false;
        }
        if (horatp.getValue() == null) {
            horatp.setEffect(effect);
            is = false;
        }
        if (descripciontf.getText().equals("")) {
            descripciontf.setEffect(effect);
            is = false;
        }

        if (!is)
            throw new Exception("Formulario Incompleto");
        return is;
    }

    private void ClearAll() {
        actividadtf.setText("");
        esp.getSelectionModel().clearSelection();
        proc.getSelectionModel().clearSelection();
        horatp.setValue(LocalTime.of(horatp.getValue().getHour() + 1, horatp.getValue().getMinute()));
        descripciontf.setText("");
        editbtn.setDisable(true);
        delbtn.setDisable(true);
    }

    private int IDResponsable(String Nombre) {
        int id = -1;
        boolean is = false;
        Iterator<Directorio> iterator = responsables.iterator();
        Directorio hold = null;
        while (iterator.hasNext()) {
            hold = iterator.next();
            if (hold.getNombreApellido().equals(Nombre)) {
                is = true;
                id = hold.getId();
            }
        }
        return id;
    }

    private int IDArea(String Nombre) {
        int id = -1;
        boolean is = false;
        Iterator<Area> iterator = lugares.iterator();
        Area hold = null;
        while (iterator.hasNext()) {
            hold = iterator.next();
            if (hold.getNombre().equals(Nombre)) {
                is = true;
                id = hold.getId();
            }
        }
        return id;
    }

    private String Resposable(int ID) {
        String nombre = "";
        boolean is = false;
        Iterator<Directorio> iterator = responsables.iterator();
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

    private String Area(int ID) {
        String nombre = "";
        boolean is = false;
        Iterator<Area> iterator = lugares.iterator();
        Area hold = null;
        while (iterator.hasNext() && !is) {
            hold = iterator.next();
            if (hold.getId() == ID) {
                is = true;
                nombre = hold.getNombre();
            }
        }
        return nombre;
    }

    public void SaveData_V250() throws Exception {
        Iterator<TuplaList<Object>> it = filanonormalizada.iterator();
        TuplaList<Object> hold;
        PlanFamiliarizacion planFam = null;

        if (filanonormalizada.size() == 0) {
            throw new Exception("Plan sin actividades.");
        }

        Service_Locator.getInstance().getPlanificacion_familiarizacion_service().NuevoEditarPlanFamiliarizacion(Pfamiliarizacion);
        planFam = Service_Locator.getInstance().getPlanificacion_familiarizacion_service().getPlanFamiliarizacion(Pfamiliarizacion.getAnno());

        while (it.hasNext()) {
            hold = it.next();
            if (hold.getOperacion() == -1) {
                Service_Locator.getInstance().getPlanificacion_familiarizacion_service().RemovePlanFamiliarizacionActividades(((Actividad) hold.getListaobjeto().get(0)), ((ActividadesPlan) hold.getListaobjeto().get(1)));
            } else if (hold.getOperacion() > 0) {
                ActividadesPlan plan = ((ActividadesPlan) hold.getListaobjeto().get(1));
                System.out.println("Actividad "+plan.getAct());
                plan.setPlan(planFam.getId());
                Service_Locator.getInstance().getPlanificacion_familiarizacion_service().NuevoEditarPlanFamiliarizacionActividades(((Actividad) hold.getListaobjeto().get(0)), plan);
            }

        }
    }

    public void LlenarTabla_V250() {
        ObservableList<Plan_Familiarizacion> list = FXCollections.observableArrayList();
        Iterator<TuplaList<Object>> iterator = filanonormalizada.iterator();
        TuplaList<Object> hold;
        Plan_Familiarizacion row;
        Actividad actividad;
        ActividadesPlan actividadesPlan;
        while (iterator.hasNext()) {
            hold = iterator.next();
            actividad = ((Actividad) hold.getListaobjeto().get(0));
            actividadesPlan = ((ActividadesPlan) hold.getListaobjeto().get(1));
            if (hold.getOperacion() >= 0) {
                row = new Plan_Familiarizacion(actividad.getNombre(), Resposable(actividadesPlan.getResponsable()), Area(actividadesPlan.getArea()), actividadesPlan.getFecha().toString(), actividadesPlan.getHora().toString(), actividad.getId());
                list.add(row);
            }
        }
        final TreeItem<Plan_Familiarizacion> treeItem = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        table.setRoot(treeItem);
        table.setShowRoot(false);
    }

    private void NuevaActividad_V250() throws Exception {
        if (Validacion()) {
            TuplaList<Object> t = new TuplaList<>(2);
            t.getListaobjeto().add(new Actividad(-1, actividadtf.getText(), descripciontf.getText()));
            t.getListaobjeto().add(new ActividadesPlan(-1, Date.valueOf(fechadp.getValue().toString()), -1, -1, IDResponsable(esp.getValue()), Time.valueOf(horatp.getValue()), IDArea(proc.getValue())));


            if (isActividadAñadida(t))
                throw new Exception("Esta actividad ya esta en el Plan de Familiarizacion");
            else
                filanonormalizada.add(t);
        }

    }

    private void EditarActividad_V250() throws Exception {
        if (Validacion() && getSelectedRow() > -1) {
            int pos = getSelectedRow();
            Actividad q = (Actividad) filanonormalizada.get(pos).getListaobjeto().get(0);
            ActividadesPlan q1 = (ActividadesPlan) filanonormalizada.get(pos).getListaobjeto().get(1);
            q.set(new Actividad(q.getId(), actividadtf.getText(), descripciontf.getText()));
            q1.set(new ActividadesPlan(q1.getId(), Date.valueOf(fechadp.getValue().toString()), q.getId(), -1, IDResponsable(esp.getValue()), Time.valueOf(horatp.getValue()), IDArea(proc.getValue())));
            filanonormalizada.get(pos).setOperacion(1);
        }
    }

    private void Eliminar_V250() {
        if (getSelectedRow() > -1) {
            filanonormalizada.get(getSelectedRow()).setOperacion(-1);
        }
    }

    private int getSelectedRow() {
        int selected = -1;
        if (!table.getSelectionModel().isEmpty()) {
            String c = table.getSelectionModel().getSelectedItem().getValue().getActividad();
            ListIterator<TuplaList<Object>> iterator = filanonormalizada.listIterator();
            while (iterator.hasNext() && selected == -1)
                if (((Actividad) iterator.next().getListaobjeto().get(0)).getNombre().equals(c)) {
                    System.out.println(iterator.nextIndex());
                    selected = iterator.nextIndex() - 1;
                }
        }
        System.out.println("Fila seleccionada la numero" + selected);
        return selected;
    }

    private void UnSetData() {
        TuplaList<Object> tuplaList = filanonormalizada.get(getSelectedRow());
        Actividad tarea = ((Actividad) tuplaList.getListaobjeto().get(0));
        ActividadesPlan actividadesPlan = ((ActividadesPlan) tuplaList.getListaobjeto().get(1));


        actividadtf.setText(tarea.getNombre());
        descripciontf.setText(tarea.getDescripcion());
        fechadp.setValue(actividadesPlan.getFecha().toLocalDate());
        horatp.setValue(actividadesPlan.getHora().toLocalTime());
        esp.getSelectionModel().select(Resposable(actividadesPlan.getResponsable()));
        proc.getSelectionModel().select(Area(actividadesPlan.getArea()));
    }

    private boolean isActividadAñadida(TuplaList<Object> actividad) {
        boolean is = false;
        Iterator<TuplaList<Object>> iterator = filanonormalizada.iterator();
        while (iterator.hasNext()) {
            TuplaList<Object> next = iterator.next();
            if (next.getOperacion() >= 0 && ((Actividad) next.getListaobjeto().get(0)).getNombre().equals(((Actividad) actividad.getListaobjeto().get(0)).getNombre()))
                is = true;
        }
        return is;
    }


    private void InstaciarAnimaciones() {
        Animaciones.AnimacionTipo1(delbtn);
        Animaciones.AnimacionTipo1(addbtn);
        Animaciones.AnimacionTipo1(editbtn);
        Animaciones.AnimacionTipo1(finishbtn);
    }

}
