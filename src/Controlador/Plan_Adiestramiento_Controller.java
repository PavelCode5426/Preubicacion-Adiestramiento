package Controlador;

import Auxiliar.Animaciones;
import Auxiliar.Excepciones.AppException;
import Auxiliar.Plan_Adiestramiento;
import Auxiliar.TuplaList;
import Modelo.*;
import Servicio.Planificacion_Adiestramiento_Service;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class Plan_Adiestramiento_Controller implements Initializable, IDraggedScene {

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
    private JFXTabPane jfxTabPane;
    @FXML
    private Tab tab_Etapa1;
    @FXML
    private JFXTextField textFieldObjetivo;
    @FXML
    private JFXTreeTableView<Plan_Adiestramiento> tablaDeTareas;
    @FXML
    private TreeTableColumn<Plan_Adiestramiento, String> columna_nombreTarea;
    @FXML
    private TreeTableColumn<Plan_Adiestramiento, String> columna_Participante;
    @FXML
    private TreeTableColumn<Plan_Adiestramiento, String> columna_Responsable;
    @FXML
    private JFXButton boton_Nueva;
    @FXML
    private JFXButton boton_Editar;
    @FXML
    private JFXButton boton_Eliminar;
    @FXML
    private VBox vBox_Tarea;
    @FXML
    private JFXTextField textField_Nombre;
    @FXML
    private JFXListView<String> lis_participantes;
    @FXML
    private JFXDatePicker datePicker_FechaInicio;
    @FXML
    private JFXDatePicker datePicker_FechaFin;
    @FXML
    private JFXButton buton_AnnadirTarea;
    @FXML
    private Tab tab_Etapa11;
    @FXML
    private JFXTextField textFieldObjetivo1;
    @FXML
    private JFXTreeTableView<Plan_Adiestramiento> tablaDeTareas1;
    @FXML
    private TreeTableColumn<Plan_Adiestramiento, String> columna_nombreTarea1;
    @FXML
    private TreeTableColumn<Plan_Adiestramiento, String> columna_Participante1;
    @FXML
    private TreeTableColumn<Plan_Adiestramiento, String> columna_Responsable1;
    @FXML
    private JFXButton boton_Nueva1;
    @FXML
    private JFXButton boton_Editar1;
    @FXML
    private JFXButton boton_Eliminar1;
    @FXML
    private VBox vBox_Tarea1;
    @FXML
    private JFXTextField textField_Nombre1;
    @FXML
    private JFXListView<String> lis_participantes1;
    @FXML
    private JFXDatePicker datePicker_FechaInicio1;
    @FXML
    private JFXDatePicker datePicker_FechaFin1;
    @FXML
    private JFXButton buton_AnnadirTarea1;
    @FXML
    private Tab tab_Etapa111;
    @FXML
    private JFXTextField textFieldObjetivo11;
    @FXML
    private JFXTreeTableView<Plan_Adiestramiento> tablaDeTareas11;
    @FXML
    private TreeTableColumn<Plan_Adiestramiento, String> columna_nombreTarea11;
    @FXML
    private TreeTableColumn<Plan_Adiestramiento, String> columna_Participante11;
    @FXML
    private TreeTableColumn<Plan_Adiestramiento, String> columna_Responsable11;
    @FXML
    private JFXButton boton_Nueva11;
    @FXML
    private JFXButton boton_Editar11;
    @FXML
    private JFXButton boton_Eliminar11;
    @FXML
    private VBox vBox_Tarea11;
    @FXML
    private JFXTextField textField_Nombre11;
    @FXML
    private JFXListView<String> lis_participantes11;
    @FXML
    private JFXDatePicker datePicker_FechaInicio11;
    @FXML
    private JFXDatePicker datePicker_FechaFin11;
    @FXML
    private JFXButton buton_AnnadirTarea11;
    @FXML
    private Tab tab_Etapa1111;
    @FXML
    private JFXTextField textFieldObjetivo111;
    @FXML
    private JFXTreeTableView<Plan_Adiestramiento> tablaDeTareas111;
    @FXML
    private TreeTableColumn<Plan_Adiestramiento, String> columna_nombreTarea111;
    @FXML
    private TreeTableColumn<Plan_Adiestramiento, String> columna_Participante111;
    @FXML
    private TreeTableColumn<Plan_Adiestramiento, String> columna_Responsable111;
    @FXML
    private JFXButton boton_Nueva111;
    @FXML
    private JFXButton boton_Editar111;
    @FXML
    private JFXButton boton_Eliminar111;
    @FXML
    private VBox vBox_Tarea111;
    @FXML
    private JFXTextField textField_Nombre111;
    @FXML
    private JFXListView<String> lis_participantes111;
    @FXML
    private JFXDatePicker datePicker_FechaInicio111;
    @FXML
    private JFXDatePicker datePicker_FechaFin111;
    @FXML
    private JFXButton buton_AnnadirTarea111;
    @FXML
    private Label nomb_tutorado;
    @FXML
    private JFXButton accept;
    @FXML
    private JFXButton cancel;




/*
Componentes de la Proroga
 */

    @FXML
    private Tab tab_Etapa11111;
    @FXML
    private JFXTextField textFieldObjetivo1111;
    @FXML
    private JFXTreeTableView<Plan_Adiestramiento> tablaDeTareas1111;
    @FXML
    private TreeTableColumn<Plan_Adiestramiento, String> columna_nombreTarea1111;
    @FXML
    private TreeTableColumn<Plan_Adiestramiento, String> columna_Participante1111;
    @FXML
    private TreeTableColumn<Plan_Adiestramiento, String> columna_Responsable1111;
    @FXML
    private JFXButton boton_Nueva1111;
    @FXML
    private JFXButton boton_Editar1111;
    @FXML
    private JFXButton boton_Eliminar1111;
    @FXML
    private VBox vBox_Tarea1111;
    @FXML
    private JFXTextField textField_Nombre1111;
    @FXML
    private JFXListView<String> lis_participantes1111;
    @FXML
    private JFXDatePicker datePicker_FechaInicio1111;
    @FXML
    private JFXDatePicker datePicker_FechaFin1111;
    @FXML
    private JFXButton buton_AnnadirTarea1111;
    @FXML

    private Tab tab_Etapa111111;
    @FXML
    private JFXTextField textFieldObjetivo11111;
    @FXML
    private JFXTreeTableView<Plan_Adiestramiento> tablaDeTareas11111;
    @FXML
    private TreeTableColumn<Plan_Adiestramiento, String> columna_nombreTarea11111;
    @FXML
    private TreeTableColumn<Plan_Adiestramiento, String> columna_Participante11111;
    @FXML
    private TreeTableColumn<Plan_Adiestramiento, String> columna_Responsable11111;
    @FXML
    private JFXButton boton_Nueva11111;
    @FXML
    private JFXButton boton_Editar11111;
    @FXML
    private JFXButton boton_Eliminar11111;
    @FXML
    private VBox vBox_Tarea11111;
    @FXML
    private JFXTextField textField_Nombre11111;
    @FXML
    private JFXListView<String> lis_participantes11111;
    @FXML
    private JFXDatePicker datePicker_FechaInicio11111;
    @FXML
    private JFXDatePicker datePicker_FechaFin11111;
    @FXML
    private JFXButton buton_AnnadirTarea11111;
    @FXML











    /*
    Declaraciones de Variables
     */
    private static Stage stage;

    private ArrayList<Directorio> directorios;
    private ArrayList<Etapa> etapas;
    private ObservableList<String> list;
    private PlanAdiestramiento miPlan;
    private ArrayList<TuplaList<Object>> tuplaListsActividadesParticipantes;
    private Estudiante estudiante;


    private static Integer idEstud;
    private static Integer idPlan;
    private static Pantalla_Tutor_Controller padre;
    private static boolean isProrroga;


    public static void setPadre(Pantalla_Tutor_Controller padre) {
        Plan_Adiestramiento_Controller.padre = padre;
    }

    public static void setIdEstud(Integer idEstud) {
        Plan_Adiestramiento_Controller.idEstud = idEstud;
    }

    public static void setIdPlan(Integer idPlan) {
        Plan_Adiestramiento_Controller.idPlan = idPlan;
    }

    public static void setStage(Stage stage) {
        Plan_Adiestramiento_Controller.stage = stage;
    }

    public static void setIsProrroga(boolean isProrroga) {
        Plan_Adiestramiento_Controller.isProrroga = isProrroga;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        onDraggedScene(PaneExterior);
        ConfigurarMinimizarMaximizar(PaneExterior,botonMinimizarVentana,botonCerrarVentana);



        try {
            estudiante = Service_Locator.getInstance().getGraduado_service().getGraduado(idEstud);
            Configtables();
            if (idPlan != null) {
                miPlan = Service_Locator.getInstance().getPlanificacion_adiestramiento_service().getPlanAdiestramientoID(idPlan);
              /*  if (miPlan.getAprobado()!=null &&!isProrroga)
                {
                    //Desactivar Botones
                    buton_AnnadirTarea.setDisable(true);
                    buton_AnnadirTarea1.setDisable(true);
                    buton_AnnadirTarea11.setDisable(true);
                    buton_AnnadirTarea111.setDisable(true);
                    buton_AnnadirTarea1111.setDisable(true);
                    buton_AnnadirTarea11111.setDisable(true);



                    boton_Editar.setDisable(true);
                    boton_Editar1.setDisable(true);
                    boton_Editar11.setDisable(true);
                    boton_Editar111.setDisable(true);
                    boton_Editar1111.setDisable(true);
                    boton_Editar11111.setDisable(true);

                    boton_Eliminar.setDisable(true);
                    boton_Eliminar1.setDisable(true);
                    boton_Eliminar11.setDisable(true);
                    boton_Eliminar111.setDisable(true);
                    boton_Eliminar1111.setDisable(true);
                    boton_Eliminar11111.setDisable(true);

                    accept.disableProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            accept.setDisable(true);
                        }
                    });
                    accept.setDisable(true);

                    Alert alert=new Alert(Alert.AlertType.INFORMATION,"Este Plan No Puede Ser Editado Ya Que Fue Aprobado");
                    alert.showAndWait();
                }
               */
                etapas = Service_Locator.getInstance().getPlanificacion_adiestramiento_service().getEtapas_fromPlan(miPlan.getId());
                tuplaListsActividadesParticipantes = Service_Locator.getInstance().getPlanificacion_adiestramiento_service().getTareas_and_participantes(idPlan);

                if (isProrroga && miPlan.getAprobado() != null) {
                    tab_Etapa1.setDisable(true);
                    tab_Etapa11.setDisable(true);
                    tab_Etapa111.setDisable(true);
                    tab_Etapa1111.setDisable(true);
                    jfxTabPane.getSelectionModel().select(4);
                    if (etapas.size() == 4)
                        for (int x = 4; x < 6; x++) {
                            Etapa etapa = new Etapa(-1 * x, miPlan.getId(), x + 1, null, null, "", null);
                            etapas.add(etapa);
                           }
                } else if (etapas.size() == 6) {
                    tab_Etapa1.setDisable(true);
                    tab_Etapa11.setDisable(true);
                    tab_Etapa111.setDisable(true);
                    tab_Etapa1111.setDisable(true);
                    jfxTabPane.getSelectionModel().select(4);
                    if (miPlan.getAprobado() != null) {
                        buton_AnnadirTarea1111.setDisable(true);
                        buton_AnnadirTarea1111.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                buton_AnnadirTarea1111.setDisable(true);
                            }
                        });
                        buton_AnnadirTarea11111.setDisable(true);
                        buton_AnnadirTarea11111.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                buton_AnnadirTarea11111.setDisable(true);
                            }
                        });


                        boton_Editar1111.setDisable(true);
                        boton_Editar1111.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                boton_Editar1111.setDisable(true);
                            }
                        });
                        boton_Editar11111.setDisable(true);
                        boton_Editar11111.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                boton_Editar11111.setDisable(true);
                            }
                        });


                        boton_Eliminar1111.setDisable(true);
                        boton_Eliminar1111.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                boton_Eliminar1111.setDisable(true);
                            }
                        });
                        boton_Eliminar11111.setDisable(true);
                        boton_Eliminar11111.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                boton_Eliminar11111.setDisable(true);
                            }
                        });


                        accept.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                accept.setDisable(true);
                            }
                        });
                        accept.setDisable(true);

                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Este plan no puede ser editado ya que fue aprobado", "DARKGREEN");
                    }
                } else if (etapas.size() == 4) {
                    tab_Etapa111111.setDisable(true);
                    tab_Etapa11111.setDisable(true);
                    if (miPlan.getAprobado() != null) {
                        buton_AnnadirTarea.setDisable(true);
                        buton_AnnadirTarea.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                buton_AnnadirTarea.setDisable(true);
                            }
                        });
                        buton_AnnadirTarea1.setDisable(true);
                        buton_AnnadirTarea1.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                buton_AnnadirTarea1.setDisable(true);
                            }
                        });
                        buton_AnnadirTarea11.setDisable(true);
                        buton_AnnadirTarea11.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                buton_AnnadirTarea11.setDisable(true);
                            }
                        });
                        buton_AnnadirTarea111.setDisable(true);
                        buton_AnnadirTarea111.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                buton_AnnadirTarea1111.setDisable(true);
                            }
                        });


                        boton_Editar.setDisable(true);
                        boton_Editar.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                boton_Editar.setDisable(true);
                            }
                        });
                        boton_Editar1.setDisable(true);
                        boton_Editar1.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                boton_Editar1.setDisable(true);
                            }
                        });
                        boton_Editar11.setDisable(true);
                        boton_Editar11.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                boton_Editar11.setDisable(true);
                            }
                        });
                        boton_Editar111.setDisable(true);
                        boton_Editar111.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                boton_Editar111.setDisable(true);
                            }
                        });


                        boton_Eliminar.setDisable(true);
                        boton_Eliminar.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                boton_Eliminar.setDisable(true);
                            }
                        });
                        boton_Eliminar1.setDisable(true);
                        boton_Eliminar1.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                boton_Eliminar1.setDisable(true);
                            }
                        });
                        boton_Eliminar11.setDisable(true);
                        boton_Eliminar11.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                boton_Eliminar11.setDisable(true);
                            }
                        });
                        boton_Eliminar111.setDisable(true);
                        boton_Eliminar111.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                boton_Eliminar111.setDisable(true);
                            }
                        });

                        accept.disableProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                accept.setDisable(true);
                            }
                        });
                        accept.setDisable(true);

                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Este plan no puede ser editado ya que fue aprobado", "DARKGREEN");
                    }
                }

            } else {
                miPlan = new PlanAdiestramiento(-1, null, LocalDate.now().getYear(), Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId(), estudiante.getId(), false);
                tuplaListsActividadesParticipantes = new ArrayList<>();
                etapas = new ArrayList<>();
                for (int x = 0; x < 4; x++) {
                    Etapa etapa = new Etapa(-1 * x, miPlan.getId(), x + 1, null, null, "", null);
                    etapas.add(etapa);
                  }
                tab_Etapa111111.setDisable(true);
                tab_Etapa11111.setDisable(true);
            }

            nomb_tutorado.setText(nomb_tutorado.getText() + " " + estudiante.getNombreApellidos());
            directorios = Service_Locator.getInstance().getDirectorio_service().getDirectorio();
            list = FXCollections.observableArrayList();
            ListIterator<Directorio> directorioListIterator = directorios.listIterator();
            int id = Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId();
            while (directorioListIterator.hasNext()) {
                Directorio directorio = directorioListIterator.next();
                if (directorio.isActivo() && directorio.getId() != id)
                    list.add(directorio.getNombreApellido());
            }
            list.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareToIgnoreCase(o2);
                }
            });
            LlenarListas(0);
            LlenarTabla(-1);
            InstanciateAnimations();

            buton_AnnadirTarea.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        if (isTareaRepetida()) {
                            Añadir_Nueva_Tarea();
                            lis_participantes.getSelectionModel().clearSelection();
                            textField_Nombre.setText("");
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Tarea añadida correctamente", "DARKGREEN");
                        } else
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "Tarea Repetida", "DARKRED");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }

                }
            });
            boton_Editar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Editar_Tarea();
                        lis_participantes.getSelectionModel().clearSelection();
                        textField_Nombre.setText("");
                        LlenarTabla(jfxTabPane.getSelectionModel().getSelectedIndex());
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Tarea editada correctamente", "DARKGREEN");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            boton_Eliminar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Elimiar_Tarea();
                        lis_participantes.getSelectionModel().clearSelection();
                        textField_Nombre.setText("");
                        LlenarTabla(jfxTabPane.getSelectionModel().getSelectedIndex());
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Tarea eliminada correctamente", "DARKGREEN");

                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            buton_AnnadirTarea1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        if (isTareaRepetida()) {
                            Añadir_Nueva_Tarea();
                            textField_Nombre1.setText("");
                            lis_participantes1.getSelectionModel().clearSelection();
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Tarea añadida correctamente", "DARKGREEN");
                        } else
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "Tarea Repetida", "DARKRED");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error",AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            boton_Editar1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Editar_Tarea();
                        lis_participantes1.getSelectionModel().clearSelection();
                        textField_Nombre1.setText("");
                        LlenarTabla(jfxTabPane.getSelectionModel().getSelectedIndex());
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Tarea Editada Correctamente", "DARKGREEN");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            boton_Eliminar1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Elimiar_Tarea();
                        lis_participantes1.getSelectionModel().clearSelection();
                        textField_Nombre1.setText("");
                        LlenarTabla(jfxTabPane.getSelectionModel().getSelectedIndex());
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Tarea Eliminada Correctamente", "DARKGREEN");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            buton_AnnadirTarea11.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        if (isTareaRepetida()) {
                            Añadir_Nueva_Tarea();
                            textField_Nombre11.setText("");
                            lis_participantes11.getSelectionModel().clearSelection();
                            LlenarTabla(jfxTabPane.getSelectionModel().getSelectedIndex());
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Tarea añadida correctamente", "DARKGREEN");
                        } else
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "Tarea Repetida", "DARKRED");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            boton_Editar11.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Editar_Tarea();
                        textField_Nombre11.setText("");
                        lis_participantes11.getSelectionModel().clearSelection();
                        LlenarTabla(jfxTabPane.getSelectionModel().getSelectedIndex());
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Tarea editada correctamente", "DARKGREEN");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            boton_Eliminar11.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Elimiar_Tarea();
                        textField_Nombre11.setText("");
                        lis_participantes11.getSelectionModel().clearSelection();
                        LlenarTabla(jfxTabPane.getSelectionModel().getSelectedIndex());
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Tarea Eliminada Correctamente", "DARKGREEN");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error",AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            buton_AnnadirTarea111.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        if (isTareaRepetida()) {
                            Añadir_Nueva_Tarea();
                            textField_Nombre111.setText("");
                            lis_participantes111.getSelectionModel().clearSelection();
                            LlenarTabla(jfxTabPane.getSelectionModel().getSelectedIndex());
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Tarea añadida correctamente", "DARKGREEN");
                        } else
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "Tarea Repetida", "DARKRED");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            boton_Editar111.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Editar_Tarea();
                        textField_Nombre111.setText("");
                        lis_participantes111.getSelectionModel().clearSelection();
                        LlenarTabla(jfxTabPane.getSelectionModel().getSelectedIndex());
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Tarea editada correctamente", "DARKGREEN");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            boton_Eliminar111.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Elimiar_Tarea();
                        textField_Nombre111.setText("");
                        lis_participantes111.getSelectionModel().clearSelection();
                        LlenarTabla(jfxTabPane.getSelectionModel().getSelectedIndex());
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Tarea eliminada correctamente", "DARKGREEN");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error",AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            buton_AnnadirTarea1111.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        if (isTareaRepetida()) {
                            Añadir_Nueva_Tarea();
                            textField_Nombre1111.setText("");
                            lis_participantes1111.getSelectionModel().clearSelection();
                            LlenarTabla(jfxTabPane.getSelectionModel().getSelectedIndex());
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Tarea añadida correctamente", "DARKGREEN");
                        } else
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "Tarea Repetida", "DARKRED");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "ERROR", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            boton_Editar1111.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Editar_Tarea();
                        textField_Nombre1111.setText("");
                        lis_participantes1111.getSelectionModel().clearSelection();
                        LlenarTabla(jfxTabPane.getSelectionModel().getSelectedIndex());
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Tarea editada correctamente", "DARKGREEN");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            boton_Eliminar1111.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Elimiar_Tarea();
                        textField_Nombre1111.setText("");
                        lis_participantes1111.getSelectionModel().clearSelection();
                        LlenarTabla(jfxTabPane.getSelectionModel().getSelectedIndex());
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Tarea Eliminada Correctamente", "DARKGREEN");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            buton_AnnadirTarea11111.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        if (isTareaRepetida()) {
                            Añadir_Nueva_Tarea();
                            textField_Nombre11111.setText("");
                            lis_participantes11111.getSelectionModel().clearSelection();
                            LlenarTabla(jfxTabPane.getSelectionModel().getSelectedIndex());
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Tarea Añadida Correctamente", "DARKGREEN");
                        } else
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "Tarea Repetida", "DARKRED");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error",AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            boton_Editar11111.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Editar_Tarea();
                        textField_Nombre11111.setText("");
                        lis_participantes11111.getSelectionModel().clearSelection();
                        LlenarTabla(jfxTabPane.getSelectionModel().getSelectedIndex());
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Tarae Editada Correctamente", "DARKGREEN");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });
            boton_Eliminar11111.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Elimiar_Tarea();
                        textField_Nombre11111.setText("");
                        lis_participantes11111.getSelectionModel().clearSelection();
                        LlenarTabla(jfxTabPane.getSelectionModel().getSelectedIndex());
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Tarea eliminada correctamente", "DARKGREEN");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });


            cancel.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                }
            });
            accept.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        SaveData_250();
                        padre.ActualizarEstu();
                        padre.LlenarTablaGraduados();
                        padre.LlenarTablaTutorados();
                        padre.ListarTutorados();
                        stage.close();
                        if (!isProrroga)
                            Service_Locator.getInstance().getNotificacion_service().EnviarNotificacionToJefeArea(estudiante.getArea(), "Plan de Adiestramiento del estudiante " + estudiante.getNombreApellidos() + " listo para revisar");
                        else
                            Service_Locator.getInstance().getNotificacion_service().EnviarNotificacionToJefeArea(estudiante.getArea(), "Prorroga del Plan de Adiestramiento del Estudiante " + estudiante.getNombreApellidos() + " listo para revisar");
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Plan creado correctamente", "DARKGREEN");

                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                }
            });


            tablaDeTareas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Plan_Adiestramiento>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Plan_Adiestramiento>> observable, TreeItem<Plan_Adiestramiento> oldValue, TreeItem<Plan_Adiestramiento> newValue) {
                    try {
                        if (!tablaDeTareas.getSelectionModel().isEmpty())
                            DesfragmentarObjeto();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            tablaDeTareas1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Plan_Adiestramiento>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Plan_Adiestramiento>> observable, TreeItem<Plan_Adiestramiento> oldValue, TreeItem<Plan_Adiestramiento> newValue) {
                    try {
                        if (!tablaDeTareas1.getSelectionModel().isEmpty())
                            DesfragmentarObjeto();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            tablaDeTareas11.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Plan_Adiestramiento>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Plan_Adiestramiento>> observable, TreeItem<Plan_Adiestramiento> oldValue, TreeItem<Plan_Adiestramiento> newValue) {
                    try {
                        if (!tablaDeTareas11.getSelectionModel().isEmpty())
                            DesfragmentarObjeto();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            tablaDeTareas111.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Plan_Adiestramiento>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Plan_Adiestramiento>> observable, TreeItem<Plan_Adiestramiento> oldValue, TreeItem<Plan_Adiestramiento> newValue) {
                    try {
                        if (!tablaDeTareas111.getSelectionModel().isEmpty())
                            DesfragmentarObjeto();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            tablaDeTareas1111.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Plan_Adiestramiento>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Plan_Adiestramiento>> observable, TreeItem<Plan_Adiestramiento> oldValue, TreeItem<Plan_Adiestramiento> newValue) {
                    try {
                        if (!tablaDeTareas1111.getSelectionModel().isEmpty())
                            DesfragmentarObjeto();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            tablaDeTareas11111.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Plan_Adiestramiento>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Plan_Adiestramiento>> observable, TreeItem<Plan_Adiestramiento> oldValue, TreeItem<Plan_Adiestramiento> newValue) {
                    try {
                        if (!tablaDeTareas11111.getSelectionModel().isEmpty())
                            DesfragmentarObjeto();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });


            lis_participantes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lis_participantes1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lis_participantes11.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lis_participantes111.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lis_participantes1111.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lis_participantes11111.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        } catch (Exception e) {

            e.printStackTrace();
        }

    }


    /*
    Metodos Basicos
     */
    private void Configtables() {
        columna_nombreTarea.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String> param) {
                return param.getValue().getValue().tareaProperty();
            }
        });
        columna_Responsable.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String> param) {
                return param.getValue().getValue().responsableProperty();
            }
        });
        columna_Participante.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String> param) {
                return param.getValue().getValue().participanteProperty();
            }
        });
        columna_nombreTarea1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String> param) {
                return param.getValue().getValue().tareaProperty();
            }
        });
        columna_Responsable1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String> param) {
                return param.getValue().getValue().responsableProperty();
            }
        });
        columna_Participante1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String> param) {
                return param.getValue().getValue().participanteProperty();
            }
        });
        columna_nombreTarea11.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String> param) {
                return param.getValue().getValue().tareaProperty();
            }
        });
        columna_Responsable11.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String> param) {
                return param.getValue().getValue().responsableProperty();
            }
        });
        columna_Participante11.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String> param) {
                return param.getValue().getValue().participanteProperty();
            }
        });
        columna_nombreTarea111.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String> param) {
                return param.getValue().getValue().tareaProperty();
            }
        });
        columna_Responsable111.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String> param) {
                return param.getValue().getValue().responsableProperty();
            }
        });
        columna_Participante111.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String> param) {
                return param.getValue().getValue().participanteProperty();
            }
        });
        columna_nombreTarea1111.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String> param) {
                return param.getValue().getValue().tareaProperty();
            }
        });
        columna_Responsable1111.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String> param) {
                return param.getValue().getValue().responsableProperty();
            }
        });
        columna_Participante1111.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String> param) {
                return param.getValue().getValue().participanteProperty();
            }
        });
        columna_nombreTarea11111.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String> param) {
                return param.getValue().getValue().tareaProperty();
            }
        });
        columna_Responsable11111.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String> param) {
                return param.getValue().getValue().responsableProperty();
            }
        });
        columna_Participante11111.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Plan_Adiestramiento, String> param) {
                return param.getValue().getValue().participanteProperty();
            }
        });


        tablaDeTareas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Plan_Adiestramiento>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<Plan_Adiestramiento>> observable, TreeItem<Plan_Adiestramiento> oldValue, TreeItem<Plan_Adiestramiento> newValue) {
                boolean is = tablaDeTareas.getSelectionModel().isEmpty();
                boton_Editar.setDisable(is);
                boton_Eliminar.setDisable(is);
            }
        });
        tablaDeTareas1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Plan_Adiestramiento>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<Plan_Adiestramiento>> observable, TreeItem<Plan_Adiestramiento> oldValue, TreeItem<Plan_Adiestramiento> newValue) {
                boolean is = tablaDeTareas1.getSelectionModel().isEmpty();
                boton_Editar1.setDisable(is);
                boton_Eliminar1.setDisable(is);
            }
        });
        tablaDeTareas11.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Plan_Adiestramiento>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<Plan_Adiestramiento>> observable, TreeItem<Plan_Adiestramiento> oldValue, TreeItem<Plan_Adiestramiento> newValue) {
                boolean is = tablaDeTareas11.getSelectionModel().isEmpty();
                boton_Editar11.setDisable(is);
                boton_Eliminar11.setDisable(is);
            }
        });
        tablaDeTareas111.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Plan_Adiestramiento>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<Plan_Adiestramiento>> observable, TreeItem<Plan_Adiestramiento> oldValue, TreeItem<Plan_Adiestramiento> newValue) {
                boolean is = tablaDeTareas111.getSelectionModel().isEmpty();
                boton_Editar111.setDisable(is);
                boton_Eliminar111.setDisable(is);
            }
        });
        tablaDeTareas1111.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Plan_Adiestramiento>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<Plan_Adiestramiento>> observable, TreeItem<Plan_Adiestramiento> oldValue, TreeItem<Plan_Adiestramiento> newValue) {
                boolean is = tablaDeTareas1111.getSelectionModel().isEmpty();
                boton_Editar1111.setDisable(is);
                boton_Eliminar1111.setDisable(is);
            }
        });
        tablaDeTareas11111.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Plan_Adiestramiento>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<Plan_Adiestramiento>> observable, TreeItem<Plan_Adiestramiento> oldValue, TreeItem<Plan_Adiestramiento> newValue) {
                boolean is = tablaDeTareas11111.getSelectionModel().isEmpty();
                boton_Editar11111.setDisable(is);
                boton_Eliminar11111.setDisable(is);
            }
        });


        textField_Nombre.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                buton_AnnadirTarea.setDisable(textField_Nombre.getText().length() == 0);

            }
        });
        textField_Nombre1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                buton_AnnadirTarea1.setDisable(textField_Nombre1.getText().length() == 0);

            }
        });
        textField_Nombre11.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                buton_AnnadirTarea11.setDisable(textField_Nombre11.getText().length() == 0);

            }
        });
        textField_Nombre111.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                buton_AnnadirTarea111.setDisable(textField_Nombre111.getText().length() == 0);

            }
        });
        textField_Nombre1111.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                buton_AnnadirTarea1111.setDisable(textField_Nombre1111.getText().length() == 0);

            }
        });
        textField_Nombre11111.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                buton_AnnadirTarea11111.setDisable(textField_Nombre11111.getText().length() == 0);

            }
        });


       /* textField_Nombre.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                String text=textField_Nombre.getText();
                String e=event.getCharacter();
                if (e.equals("\t")||e.equals("\b"))
                    e="";
                text+=e;
                if (text.equals(""))
                    buton_AnnadirTarea.setDisable(true);
                else
                    buton_AnnadirTarea.setDisable(false);
            }
        });
        textField_Nombre1.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                String text=textField_Nombre1.getText();
                String e=event.getCharacter();
                if (e.equals("\t")||e.equals("\b"))
                    e="";
                text+=e;
                if (text.equals(""))
                    buton_AnnadirTarea1.setDisable(true);
                else
                    buton_AnnadirTarea1.setDisable(false);
            }
        });
        textField_Nombre11.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                String text=textField_Nombre11.getText();
                String e=event.getCharacter();
                if (e.equals("\t")||e.equals("\b"))
                    e="";
                text+=e;
                if (text.equals(""))
                    buton_AnnadirTarea11.setDisable(true);
                else
                    buton_AnnadirTarea11.setDisable(false);
            }
        });
        textField_Nombre111.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                String text=textField_Nombre111.getText();
                String e=event.getCharacter();
                if (e.equals("\t")||e.equals("\b"))
                    e="";
                text+=e;
                if (text.equals(""))
                    buton_AnnadirTarea111.setDisable(true);
                else
                    buton_AnnadirTarea111.setDisable(false);
            }
        });
        textField_Nombre1111.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                String text=textField_Nombre1111.getText();
                String e=event.getCharacter();
                if (e.equals("\t")||e.equals("\b"))
                    e="";
                text+=e;
                if (text.equals(""))
                    buton_AnnadirTarea1111.setDisable(true);
                else
                    buton_AnnadirTarea1111.setDisable(false);
            }
        });
        textField_Nombre11111.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                String text=textField_Nombre11111.getText();
                String e=event.getCharacter();
                if (e.equals("\t")||e.equals("\b"))
                    e="";
                text+=e;
                if (text.equals(""))
                    buton_AnnadirTarea11111.setDisable(true);
                else
                    buton_AnnadirTarea11111.setDisable(false);
            }
        });*/




    }

    private Directorio IDDirectorio(String Nombre) {
        Directorio hold = null;
        boolean is = false;
        Iterator<Directorio> iterator = directorios.iterator();
        while (iterator.hasNext() && !is) {
            hold = iterator.next();
            if (hold.getNombreApellido().equals(Nombre)) {
                is = true;

            }
        }
        return hold;
    }

    private Directorio Directorio(int ID) {
        Directorio hold = null;
        boolean is = false;
        Iterator<Directorio> iterator = directorios.iterator();
        while (iterator.hasNext()) {
            hold = iterator.next();
            if (hold.getId() == ID) {
                is = true;

            }
        }
        return hold;
    }

    private void LlenarListas(int lista) {
        switch (lista) {
            case 1:
                lis_participantes.getItems().clear();
                lis_participantes.getItems().addAll(list);
                break;
            case 2:
                lis_participantes1.getItems().clear();
                lis_participantes1.getItems().addAll(list);
                break;
            case 3:
                lis_participantes11.getItems().clear();
                lis_participantes11.getItems().addAll(list);
                break;
            case 4:
                lis_participantes111.getItems().clear();
                lis_participantes111.getItems().addAll(list);
                break;
            case 5:
                lis_participantes1111.getItems().clear();
                lis_participantes1111.getItems().addAll(list);
                break;
            case 6:
                lis_participantes11111.getItems().clear();
                lis_participantes11111.getItems().addAll(list);
                break;
            default:
                LlenarListas(1);
                LlenarListas(2);
                LlenarListas(3);
                LlenarListas(4);
                LlenarListas(5);
                LlenarListas(6);
                break;
        }
    }

    private boolean Validar(int etapa) {
        Effect effect = new DropShadow(0.9, Color.DARKRED);
        textField_Nombre1.setEffect(null);
        textField_Nombre11.setEffect(null);
        textField_Nombre111.setEffect(null);
        textField_Nombre1111.setEffect(null);
        textField_Nombre11111.setEffect(null);
        textField_Nombre.setEffect(null);

        boolean is = true;
        switch (etapa) {
            case 1:
                if (textField_Nombre1.getText().equals("")) {
                    is = false;
                    textField_Nombre1.setEffect(effect);
                }
                break;
            case 0:
                if (textField_Nombre.getText().equals("")) {
                    is = false;
                    textField_Nombre.setEffect(effect);
                }
                break;
            case 2:
                if (textField_Nombre11.getText().equals("")) {
                    is = false;
                    textField_Nombre11.setEffect(effect);
                }
                break;
            case 3:
                if (textField_Nombre111.getText().equals("")) {
                    is = false;
                    textField_Nombre111.setEffect(effect);
                }
                break;
            case 4:
                if (textField_Nombre1111.getText().equals("")) {
                    is = false;
                    textField_Nombre1111.setEffect(effect);
                }
                break;
            case 5:
                if (textField_Nombre11111.getText().equals("")) {
                    is = false;
                    textField_Nombre11111.setEffect(effect);
                }
                break;

        }
        return is;
    }

    private boolean ValidarEtapas() throws Exception {
        boolean is = true;
        Effect effect = new DropShadow(0.5, Color.DARKRED);
        textFieldObjetivo.setEffect(null);
        textFieldObjetivo1.setEffect(null);
        textFieldObjetivo11.setEffect(null);
        textFieldObjetivo111.setEffect(null);

        datePicker_FechaInicio.setEffect(null);
        datePicker_FechaInicio1.setEffect(null);
        datePicker_FechaInicio11.setEffect(null);
        datePicker_FechaInicio111.setEffect(null);

        datePicker_FechaFin.setEffect(null);
        datePicker_FechaFin1.setEffect(null);
        datePicker_FechaFin11.setEffect(null);
        datePicker_FechaFin111.setEffect(null);

        if (textFieldObjetivo.getText().equals("")) {
            textFieldObjetivo.setEffect(effect);
            is = false;
        }
        if (textFieldObjetivo1.getText().equals("")) {
            textFieldObjetivo1.setEffect(effect);
            is = false;
        }
        if (textFieldObjetivo11.getText().equals("")) {
            textFieldObjetivo11.setEffect(effect);
            is = false;
        }
        if (textFieldObjetivo111.getText().equals("")) {
            textFieldObjetivo111.setEffect(effect);
            is = false;
        }

        if (datePicker_FechaInicio.getValue() == null) {
            is = false;
            datePicker_FechaInicio.setEffect(effect);
        }
        if (datePicker_FechaInicio1.getValue() == null) {
            is = false;
            datePicker_FechaInicio1.setEffect(effect);
        }
        if (datePicker_FechaInicio11.getValue() == null) {
            is = false;
            datePicker_FechaInicio11.setEffect(effect);
        }
        if (datePicker_FechaInicio111.getValue() == null) {
            is = false;
            datePicker_FechaInicio111.setEffect(effect);
        }

        if (datePicker_FechaFin.getValue() == null) {
            is = false;
            datePicker_FechaFin.setEffect(effect);
        }
        if (datePicker_FechaFin1.getValue() == null) {
            is = false;
            datePicker_FechaFin1.setEffect(effect);
        }
        if (datePicker_FechaFin11.getValue() == null) {
            is = false;
            datePicker_FechaFin11.setEffect(effect);
        }
        if (datePicker_FechaFin111.getValue() == null) {
            is = false;
            datePicker_FechaFin111.setEffect(effect);
        }


        if (!is)
            throw new Exception("Formuario Incompleto");


        if (!datePicker_FechaInicio.getValue().isBefore(datePicker_FechaFin.getValue())) {
            is = false;
            datePicker_FechaInicio.setEffect(effect);
            datePicker_FechaFin.setEffect(effect);
        }
        if (!datePicker_FechaFin.getValue().isBefore(datePicker_FechaInicio1.getValue())) {
            is = false;
            datePicker_FechaInicio1.setEffect(effect);
            datePicker_FechaFin.setEffect(effect);
        }
        if (!datePicker_FechaInicio1.getValue().isBefore(datePicker_FechaFin1.getValue())) {
            is = false;
            datePicker_FechaInicio1.setEffect(effect);
            datePicker_FechaFin1.setEffect(effect);
        }
        if (!datePicker_FechaFin1.getValue().isBefore(datePicker_FechaInicio11.getValue())) {
            is = false;
            datePicker_FechaInicio11.setEffect(effect);
            datePicker_FechaFin1.setEffect(effect);
        }
        if (!datePicker_FechaInicio11.getValue().isBefore(datePicker_FechaFin11.getValue())) {
            is = false;
            datePicker_FechaInicio11.setEffect(effect);
            datePicker_FechaFin11.setEffect(effect);
        }
        if (!datePicker_FechaFin11.getValue().isBefore(datePicker_FechaInicio111.getValue())) {
            is = false;
            datePicker_FechaInicio111.setEffect(effect);
            datePicker_FechaFin11.setEffect(effect);
        }
        if (!datePicker_FechaInicio111.getValue().isBefore(datePicker_FechaFin111.getValue())) {
            is = false;
            datePicker_FechaInicio111.setEffect(effect);
            datePicker_FechaFin111.setEffect(effect);
        }


        if (!is)
            throw new Exception("Fechas incorrectas");

        if (tablaDeTareas.getRoot().getChildren().size() == 0 || tablaDeTareas1.getRoot().getChildren().size() == 0 || tablaDeTareas11.getRoot().getChildren().size() == 0 || tablaDeTareas111.getRoot().getChildren().size() == 0)
            throw new Exception("Etapas sin Tareas");

        else if (etapas.size() == 6)
            if (!ValidarEtapasProrroga())
                is = false;

        return is;
    }

    private boolean ValidarEtapasProrroga() throws Exception {
        boolean is = true;
        Effect effect = new DropShadow(0.5, Color.DARKRED);
        textFieldObjetivo1111.setEffect(null);
        textFieldObjetivo11111.setEffect(null);

        datePicker_FechaInicio1111.setEffect(null);
        datePicker_FechaInicio11111.setEffect(null);

        datePicker_FechaFin1111.setEffect(null);
        datePicker_FechaFin11111.setEffect(null);

        if (textFieldObjetivo1111.getText().equals("")) {
            textFieldObjetivo1111.setEffect(effect);
            is = false;
        }
        if (textFieldObjetivo11111.getText().equals("")) {
            textFieldObjetivo11111.setEffect(effect);
            is = false;
        }

        if (datePicker_FechaInicio1111.getValue() == null) {
            is = false;
            datePicker_FechaInicio1111.setEffect(effect);
        }
        if (datePicker_FechaInicio11111.getValue() == null) {
            is = false;
            datePicker_FechaInicio11111.setEffect(effect);
        }

        if (datePicker_FechaFin1111.getValue() == null) {
            is = false;
            datePicker_FechaFin1111.setEffect(effect);
        }
        if (datePicker_FechaFin11111.getValue() == null) {
            is = false;
            datePicker_FechaFin11111.setEffect(effect);
        }

        if (!is)
            throw new Exception("Formulario Incorrecto");

        if (!datePicker_FechaFin111.getValue().isBefore(datePicker_FechaInicio1111.getValue())) {
            is = false;
            datePicker_FechaFin111.setEffect(effect);
            datePicker_FechaInicio1111.setEffect(effect);
        }
        if (!datePicker_FechaInicio1111.getValue().isBefore(datePicker_FechaFin1111.getValue())) {
            is = false;
            datePicker_FechaInicio1111.setEffect(effect);
            datePicker_FechaFin1111.setEffect(effect);
        }
        if (!datePicker_FechaFin1111.getValue().isBefore(datePicker_FechaInicio11111.getValue())) {
            is = false;
            datePicker_FechaFin1111.setEffect(effect);
            datePicker_FechaInicio11111.setEffect(effect);
        }
        if (!datePicker_FechaInicio11111.getValue().isBefore(datePicker_FechaFin11111.getValue())) {
            is = false;
            datePicker_FechaInicio11111.setEffect(effect);
            datePicker_FechaFin11111.setEffect(effect);

        }

        if (!is)
            throw new Exception("Fechas incorrectas");

        if (tablaDeTareas11111.getRoot().getChildren().size() == 0 || tablaDeTareas1111.getRoot().getChildren().size() == 0)
            throw new Exception("Etapas sin Tareas");

        return is;
    }


    private void LlenarTabla(int pos) {
        ObservableList<Plan_Adiestramiento> list = FXCollections.observableArrayList();
        switch (pos) {
            case 0:
                list.addAll(FilasTablas(etapas.get(0).getId()));
                final RecursiveTreeItem<Plan_Adiestramiento> item = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
                tablaDeTareas.setRoot(item);
                tablaDeTareas.setShowRoot(false);
                break;
            case 1:
                list.addAll(FilasTablas(etapas.get(1).getId()));
                final RecursiveTreeItem<Plan_Adiestramiento> item2 = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
                tablaDeTareas1.setRoot(item2);
                tablaDeTareas1.setShowRoot(false);
                break;
            case 2:
                list.addAll(FilasTablas(etapas.get(2).getId()));
                final RecursiveTreeItem<Plan_Adiestramiento> item3 = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
                tablaDeTareas11.setRoot(item3);
                tablaDeTareas11.setShowRoot(false);
                break;
            case 3:
                list.addAll(FilasTablas(etapas.get(3).getId()));
                final RecursiveTreeItem<Plan_Adiestramiento> item4 = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
                tablaDeTareas111.setRoot(item4);
                tablaDeTareas111.setShowRoot(false);
                break;
            case 4:
                list.addAll(FilasTablas(etapas.get(4).getId()));
                final RecursiveTreeItem<Plan_Adiestramiento> item5 = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
                tablaDeTareas1111.setRoot(item5);
                tablaDeTareas1111.setShowRoot(false);
                break;
            case 5:
                list.addAll(FilasTablas(etapas.get(5).getId()));
                final RecursiveTreeItem<Plan_Adiestramiento> item6 = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
                tablaDeTareas11111.setRoot(item6);
                tablaDeTareas11111.setShowRoot(false);
                break;
            default:
                LlenarTabla(0);
                LlenarTabla(1);
                LlenarTabla(2);
                LlenarTabla(3);
                if (etapas.size() > 4) {
                    LlenarTabla(4);
                    LlenarTabla(5);
                }
                if (etapas.get(0).getId() > 0) {

                    Etapa etapa = etapas.get(0);
                    textFieldObjetivo.setText(etapa.getObjetivo());
                    datePicker_FechaInicio.setValue(etapa.getFechaInicio().toLocalDate());
                    datePicker_FechaFin.setValue(etapa.getFechaFinal().toLocalDate());

                    etapa = etapas.get(1);
                    textFieldObjetivo1.setText(etapa.getObjetivo());
                    datePicker_FechaInicio1.setValue(etapa.getFechaInicio().toLocalDate());
                    datePicker_FechaFin1.setValue(etapa.getFechaFinal().toLocalDate());

                    etapa = etapas.get(2);
                    textFieldObjetivo11.setText(etapa.getObjetivo());
                    datePicker_FechaInicio11.setValue(etapa.getFechaInicio().toLocalDate());
                    datePicker_FechaFin11.setValue(etapa.getFechaFinal().toLocalDate());

                    etapa = etapas.get(3);
                    textFieldObjetivo111.setText(etapa.getObjetivo());
                    datePicker_FechaInicio111.setValue(etapa.getFechaInicio().toLocalDate());
                    datePicker_FechaFin111.setValue(etapa.getFechaFinal().toLocalDate());
                    if (etapas.size() > 4 && etapas.get(4).getId() > 0) {
                        etapa = etapas.get(4);
                        textFieldObjetivo1111.setText(etapa.getObjetivo());
                        datePicker_FechaInicio1111.setValue(etapa.getFechaInicio().toLocalDate());
                        datePicker_FechaFin1111.setValue(etapa.getFechaFinal().toLocalDate());

                        etapa = etapas.get(5);
                        textFieldObjetivo11111.setText(etapa.getObjetivo());
                        datePicker_FechaInicio11111.setValue(etapa.getFechaInicio().toLocalDate());
                        datePicker_FechaFin11111.setValue(etapa.getFechaFinal().toLocalDate());
                    }
                }

                break;
        }
    }

    private ArrayList<TuplaList<Object>> getAct_from_etapa(int etapaId) {
        ArrayList<TuplaList<Object>> lists = new ArrayList<>();
        TuplaList<Object> hold;
        Iterator<TuplaList<Object>> it = tuplaListsActividadesParticipantes.iterator();
        while (it.hasNext()) {
            hold = it.next();
            if (((Tarea) hold.getListaobjeto().get(0)).getEtapa() == etapaId)
                lists.add(hold);
        }
        return lists;
    }

    private void Añadir_Nueva_Tarea() throws Exception {
        tuplaListsActividadesParticipantes.add(Nueva_Tarea());
        LlenarTabla(jfxTabPane.getSelectionModel().getSelectedIndex());
    }

    private void Elimiar_Tarea() throws Exception {
        int pos = jfxTabPane.getSelectionModel().getSelectedIndex();
        int et = etapas.get(pos).getId();
        /*if (pos==0)
        {
            GetTarea(tablaDeTareas.getSelectionModel().getSelectedIndex(),et).setOperacion(-1);
        }
        else if (pos==1)
        {
            GetTarea(tablaDeTareas1.getSelectionModel().getSelectedIndex(),et).setOperacion(-1);
        }
        else if(pos==2)
        {
            GetTarea(tablaDeTareas11.getSelectionModel().getSelectedIndex(),et).setOperacion(-1);
        }
        else if(pos==3)
        {
            GetTarea(tablaDeTareas111.getSelectionModel().getSelectedIndex(),et).setOperacion(-1);
        }*/
        GetTareaV250().setOperacion(-1);
    }

    private void Editar_Tarea() throws Exception {
        int pos = jfxTabPane.getSelectionModel().getSelectedIndex();
        int et = etapas.get(pos).getId();
        TuplaList<Object> qwe = null;
        /*if (pos==0)
        {
            qwe= GetTarea(tablaDeTareas.getSelectionModel().getSelectedIndex(),et);

        }
        else if (pos==1)
        {
            qwe=GetTarea(tablaDeTareas1.getSelectionModel().getSelectedIndex(),et);

        }
        else if(pos==2)
        {
            qwe= GetTarea(tablaDeTareas11.getSelectionModel().getSelectedIndex(),et);

        }
        else if(pos==3)
        {
            qwe=GetTarea(tablaDeTareas111.getSelectionModel().getSelectedIndex(),et);
        }*/
        qwe = GetTareaV250();
        qwe.setOperacion(1);
        TuplaList<Object> a = Nueva_Tarea();
        ((Tarea) qwe.getListaobjeto().get(0)).setNombre(((Tarea) a.getListaobjeto().get(0)).getNombre());
        while (qwe.getListaobjeto().size() > 1) {
            qwe.getListaobjeto().remove(1);
        }
        while (a.getListaobjeto().size() > 1) {
            qwe.getListaobjeto().add(a.getListaobjeto().remove(1));
        }

    }

    private void DesfragmentarObjeto() throws Exception {
        TuplaList<Object> tarea = GetTareaV250();
        switch (jfxTabPane.getSelectionModel().getSelectedIndex()) {
            case 0:
                ListIterator<Object> listIterator = tarea.getListaobjeto().listIterator();
                textField_Nombre.setText(((Tarea) listIterator.next()).getNombre());
                lis_participantes.getSelectionModel().clearSelection();
                while (listIterator.hasNext()) {
                    lis_participantes.getSelectionModel().select(((Directorio) listIterator.next()).getNombreApellido());
                }

                break;
            case 1:
                ListIterator<Object> listIterator2 = tarea.getListaobjeto().listIterator();
                textField_Nombre1.setText(((Tarea) listIterator2.next()).getNombre());
                lis_participantes1.getSelectionModel().clearSelection();
                while (listIterator2.hasNext()) {
                    lis_participantes1.getSelectionModel().select(((Directorio) listIterator2.next()).getNombreApellido());
                }
                break;
            case 2:
                ListIterator<Object> listIterator3 = tarea.getListaobjeto().listIterator();
                textField_Nombre11.setText(((Tarea) listIterator3.next()).getNombre());
                lis_participantes11.getSelectionModel().clearSelection();
                while (listIterator3.hasNext()) {
                    lis_participantes11.getSelectionModel().select(((Directorio) listIterator3.next()).getNombreApellido());
                }
                break;
            case 3:
                ListIterator<Object> listIterator4 = tarea.getListaobjeto().listIterator();
                textField_Nombre111.setText(((Tarea) listIterator4.next()).getNombre());
                lis_participantes111.getSelectionModel().clearSelection();
                while (listIterator4.hasNext()) {
                    lis_participantes111.getSelectionModel().select(((Directorio) listIterator4.next()).getNombreApellido());
                }
                break;
            case 4:
                ListIterator<Object> listIterator5 = tarea.getListaobjeto().listIterator();
                textField_Nombre1111.setText(((Tarea) listIterator5.next()).getNombre());
                lis_participantes1111.getSelectionModel().clearSelection();
                while (listIterator5.hasNext()) {
                    lis_participantes1111.getSelectionModel().select(((Directorio) listIterator5.next()).getNombreApellido());
                }
                break;
            case 5:
                ListIterator<Object> listIterator6 = tarea.getListaobjeto().listIterator();
                textField_Nombre11111.setText(((Tarea) listIterator6.next()).getNombre());
                lis_participantes11111.getSelectionModel().clearSelection();
                while (listIterator6.hasNext()) {
                    lis_participantes11111.getSelectionModel().select(((Directorio) listIterator6.next()).getNombreApellido());
                }
                break;
        }
    }


    private TuplaList<Object> GetTareaV250() throws Exception {
        int Etapa = etapas.get(jfxTabPane.getSelectionModel().getSelectedIndex()).getId();
        Plan_Adiestramiento adiestramiento = null;
        switch (jfxTabPane.getSelectionModel().getSelectedIndex()) {
            case 0:
                adiestramiento = tablaDeTareas.getSelectionModel().getSelectedItem().getValue();
                break;
            case 1:
                adiestramiento = tablaDeTareas1.getSelectionModel().getSelectedItem().getValue();
                break;
            case 2:
                adiestramiento = tablaDeTareas11.getSelectionModel().getSelectedItem().getValue();
                break;
            case 3:
                adiestramiento = tablaDeTareas111.getSelectionModel().getSelectedItem().getValue();
                break;
            case 4:
                adiestramiento = tablaDeTareas1111.getSelectionModel().getSelectedItem().getValue();
                break;
            case 5:
                adiestramiento = tablaDeTareas11111.getSelectionModel().getSelectedItem().getValue();
                break;
        }

        TuplaList<Object> objectTuplaList = null;
        boolean is = false;
        Iterator<TuplaList<Object>> iterator = tuplaListsActividadesParticipantes.iterator();
        while (iterator.hasNext() && !is) {
            objectTuplaList = iterator.next();
            if (((Tarea) objectTuplaList.getListaobjeto().get(0)).getNombre().equals(adiestramiento.getTarea()) && ((Tarea) objectTuplaList.getListaobjeto().get(0)).getEtapa() == Etapa && objectTuplaList.getOperacion() > -1)
                is = true;
        }
        if (!is) {
            throw new Exception("Fila no Encontrada");
        }
        return objectTuplaList;
    }

    private TuplaList<Object> Nueva_Tarea() throws Exception {
        Tarea NEW = null;
        int pos = jfxTabPane.getSelectionModel().getSelectedIndex();
       int et = etapas.get(pos).getId();
        TuplaList<Object> q = new TuplaList<>(2);
        if (Validar(pos)) {
            if (pos == 0)
                NEW = new Tarea(pos * -1, textField_Nombre.getText(), et,
                        false, null, "", false);
            else if (pos == 1)
                NEW = new Tarea(pos * -1, textField_Nombre1.getText(), et,
                        false, null, "", false);
            else if (pos == 2)
                NEW = new Tarea(pos * -1, textField_Nombre11.getText(), et,
                        false, null, "", false);
            else if (pos == 3)
                NEW = new Tarea(pos * -1, textField_Nombre111.getText(), et,
                        false, null, "", false);
            else if (pos == 4)
                NEW = new Tarea(pos * -1, textField_Nombre1111.getText(), et,
                        false, null, "", false);
            else if (pos == 5)
                NEW = new Tarea(pos * -1, textField_Nombre11111.getText(), et,
                        false, null, "", false);

            q.getListaobjeto().add(NEW);
            Iterator<String> it = null;
            if (pos == 0)
                it = lis_participantes.getSelectionModel().getSelectedItems().iterator();
            else if (pos == 1)
                it = lis_participantes1.getSelectionModel().getSelectedItems().iterator();
            else if (pos == 2)
                it = lis_participantes11.getSelectionModel().getSelectedItems().iterator();
            else if (pos == 3)
                it = lis_participantes111.getSelectionModel().getSelectedItems().iterator();
            else if (pos == 4)
                it = lis_participantes1111.getSelectionModel().getSelectedItems().iterator();
            else if (pos == 5)
                it = lis_participantes11111.getSelectionModel().getSelectedItems().iterator();
            while (it.hasNext()) {
                q.getListaobjeto().add(IDDirectorio(it.next()));
            }

        } else throw new Exception("Formulario Incompleto");

        return q;
    }

    private ObservableList<Plan_Adiestramiento> FilasTablas(int etapaID) {
        ObservableList<Plan_Adiestramiento> list = FXCollections.observableArrayList();
        TuplaList<Object> tuplaList = null;
        Tarea T;
        Iterator<TuplaList<Object>> iterator = tuplaListsActividadesParticipantes.iterator();
        while (iterator.hasNext()) {
            tuplaList = iterator.next();
            T = ((Tarea) tuplaList.getListaobjeto().get(0));
            if (tuplaList.getOperacion() > -1 && T.getEtapa() == etapaID) {
                String q = "";
                int e = tuplaList.getListaobjeto().size() - 1;
                while (e > 0) {
                    q += ((Directorio) tuplaList.getListaobjeto().get(e--)).getNombreApellido();
                    if (e > 0)
                        q += ",";
                    else
                        q += ".";
                }
                if (q.equals(""))
                    q = "Sin Participantes";
                list.add(new Plan_Adiestramiento(T.getNombre(), Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getNombreApellido(), q, T.getId()));
            }
        }
        return list;
    }

    private void CrearEtapasYActividades() throws Exception {
        Etapa a = null;
        int q = 1;
        Iterator<Etapa> etapaIterator = etapas.iterator();
        while (etapaIterator.hasNext()) {
            a = etapaIterator.next();
            a.setPlan(miPlan.getId());
            q = a.getNombre();
            switch (q) {
                case 1:
                    a.setObjetivo(textFieldObjetivo.getText());
                    a.setFechaFinal(Date.valueOf(datePicker_FechaFin.getValue().toString()));
                    a.setFechaInicio(Date.valueOf(datePicker_FechaInicio.getValue().toString()));
                    break;
                case 2:
                    a.setObjetivo(textFieldObjetivo1.getText());
                    a.setFechaFinal(Date.valueOf(datePicker_FechaFin1.getValue().toString()));
                    a.setFechaInicio(Date.valueOf(datePicker_FechaInicio1.getValue().toString()));
                    break;
                case 3:
                    a.setObjetivo(textFieldObjetivo11.getText());
                    a.setFechaFinal(Date.valueOf(datePicker_FechaFin11.getValue().toString()));
                    a.setFechaInicio(Date.valueOf(datePicker_FechaInicio11.getValue().toString()));
                    break;
                case 4:
                    a.setObjetivo(textFieldObjetivo111.getText());
                    a.setFechaFinal(Date.valueOf(datePicker_FechaFin111.getValue().toString()));
                    a.setFechaInicio(Date.valueOf(datePicker_FechaInicio111.getValue().toString()));
                    break;
                case 5:
                    a.setObjetivo(textFieldObjetivo1111.getText());
                    a.setFechaFinal(Date.valueOf(datePicker_FechaFin1111.getValue().toString()));
                    a.setFechaInicio(Date.valueOf(datePicker_FechaInicio1111.getValue().toString()));
                    break;
                case 6:
                    a.setObjetivo(textFieldObjetivo11111.getText());
                    a.setFechaFinal(Date.valueOf(datePicker_FechaFin11111.getValue().toString()));
                    a.setFechaInicio(Date.valueOf(datePicker_FechaInicio11111.getValue().toString()));
                    break;
            }
        }

        Planificacion_Adiestramiento_Service service = Service_Locator.getInstance().getPlanificacion_adiestramiento_service();
        service.Create_Update_Etapas(etapas);
        ArrayList<Etapa> newEtapas = service.getEtapas_fromPlan(miPlan.getId());
        Iterator<TuplaList<Object>> iterator = tuplaListsActividadesParticipantes.iterator();
        while (iterator.hasNext()) {
            TuplaList<Object> next = iterator.next();
            int elid = ((Tarea) next.getListaobjeto().get(0)).getId();
            if (elid <= 0)
                ((Tarea) next.getListaobjeto().get(0)).setEtapa(newEtapas.get(elid * -1).getId());

            ArrayList<Integer> integers = new ArrayList<>();
            Iterator<Object> objectIterator = next.getListaobjeto().iterator();
            while (objectIterator.hasNext()) {
                Object w = objectIterator.next();
                if (!(w instanceof Tarea))
                    integers.add(((Directorio) w).getId());
            }
            if (next.getOperacion() >= 1)
                service.Create_Update_Actividad_Participantes(((Tarea) next.getListaobjeto().get(0)), integers);
            else if (next.getOperacion() == -1)
                service.Delete_Actividad(((Tarea) next.getListaobjeto().get(0)));
        }
    }

    private void SaveData_250() throws Exception {
        if (isProrroga)
            miPlan.setAprobado(null);
            miPlan.setRevisado(false);
        if (ValidarEtapas())
        {
            miPlan = Service_Locator.getInstance().getPlanificacion_adiestramiento_service().Create_Update_PlanAdiestramiento(miPlan);
            CrearEtapasYActividades();
        } else throw new Exception("Etapas Incompletas");
    }

    private int IndexofDirector(int id) {
        int index = -1;
        boolean is = false;
        ListIterator<Directorio> iterator = directorios.listIterator();
        while (iterator.hasNext() && !is) {
            index++;
            if (iterator.next().getId() == id) {
                is = true;
            }
        }
        return index;
    }


    private void InstanciateAnimations() {

        Animaciones.AnimacionTipo1(buton_AnnadirTarea);
        Animaciones.AnimacionTipo1(buton_AnnadirTarea1);
        Animaciones.AnimacionTipo1(buton_AnnadirTarea11);
        Animaciones.AnimacionTipo1(buton_AnnadirTarea111);
        Animaciones.AnimacionTipo1(buton_AnnadirTarea1111);
        Animaciones.AnimacionTipo1(buton_AnnadirTarea11111);

        Animaciones.AnimacionTipo1(boton_Editar);
        Animaciones.AnimacionTipo1(boton_Editar1);
        Animaciones.AnimacionTipo1(boton_Editar11);
        Animaciones.AnimacionTipo1(boton_Editar111);
        Animaciones.AnimacionTipo1(boton_Editar1111);
        Animaciones.AnimacionTipo1(boton_Editar11111);

        Animaciones.AnimacionTipo1(boton_Eliminar);
        Animaciones.AnimacionTipo1(boton_Eliminar1);
        Animaciones.AnimacionTipo1(boton_Eliminar11);
        Animaciones.AnimacionTipo1(boton_Eliminar111);
        Animaciones.AnimacionTipo1(boton_Eliminar1111);
        Animaciones.AnimacionTipo1(boton_Eliminar11111);


        Animaciones.AnimacionTipo1(accept);
        Animaciones.AnimacionTipo1(cancel);
    }

    public boolean isTareaRepetida() {
        boolean is = true;
        String text = "";
        switch (jfxTabPane.getSelectionModel().getSelectedIndex()) {
            case 0:
                text = textField_Nombre.getText();
                break;
            case 1:
                text = textField_Nombre1.getText();
                break;
            case 2:
                text = textField_Nombre11.getText();
                break;
            case 3:
                text = textField_Nombre111.getText();
                break;
            case 4:
                text = textField_Nombre1111.getText();
                break;
            case 5:
                text = textField_Nombre11111.getText();
                break;
        }
        Iterator<TuplaList<Object>> iterator = tuplaListsActividadesParticipantes.iterator();
        while (iterator.hasNext()) {
            if (((Tarea) iterator.next().getListaobjeto().get(0)).getNombre().equals(text))
                is = false;
        }
        return is;
    }


}