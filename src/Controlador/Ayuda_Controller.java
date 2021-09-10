package Controlador;

import Vista.IDraggedScene;
import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.reflect.generics.tree.Tree;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;


public class Ayuda_Controller implements Initializable, IDraggedScene {
    @FXML
    private AnchorPane anchorPaneExterior;

    @FXML
    private TreeView<String> treeView;
    @FXML
    private JFXButton botonCerrar;
    @FXML
    private Label label_TituloReporte;

    @FXML
    private Text text_EncabezadoReporte;
    @FXML
    private Text text_EncabezadoPlanFam;

    @FXML
    private ImageView foto_PlanF_2;


    //Declaraciones de ScrollPanes

    @FXML
    private ScrollPane vBoxIntro;

    @FXML
    private ScrollPane vbox_PlanFam;

    @FXML
    private ScrollPane vbox_Asignados;

    @FXML
    private ScrollPane vBoxPerfil;

    @FXML
    private ScrollPane vBoxReportes;

    @FXML
    private ScrollPane vBoxNotificaciones;
    @FXML
    private ScrollPane vBoxAsignacionDirectRH;
    @FXML
    private ScrollPane vbox_PlanFam_Especialista;
    @FXML
    private ScrollPane vBox_Estudiantes_Espec;
    @FXML
    private ScrollPane vbox_PlanFam_JefeArea;
    @FXML
    private ScrollPane vbox_PlanForm_Jefe;

    @FXML
    private ScrollPane vbox_PlanForm_Tuto;
    @FXML
    private ScrollPane vBoxEvaluacion;

    @FXML
    private AnchorPane paneTitulo;

    @FXML
    private HBox hbox;

    @FXML
    private JFXButton botonMinimizarVentana;

    @FXML
    private JFXButton botonCerrarVentana;


    //Declaraciones necesarias
    private static int rol;

    public static void setRol(int rol) {
        Ayuda_Controller.rol = rol;
    }

    private static Stage stage;

    public static void setStage(Stage stage) {
        Ayuda_Controller.stage = stage;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        onDraggedScene(anchorPaneExterior);
        //Poner pantalla linda
        ConfigurarMinimizarMaximizar(anchorPaneExterior,botonMinimizarVentana,botonCerrarVentana);


        switch (rol) {
            case 5:
                LLenarDirectorRH();
                break;
            case 6:
                LLenarEspecialistaRH();
                break;
            case 7:
                LlenarTreeView_Vicerrector();
                break;
            case 8:
                LlenarTutor();
                break;
            case 9:
                LlenarJefeArea();
                break;
        }


        treeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2 && treeView.getSelectionModel().getSelectedItem() != null && !treeView.getSelectionModel().getSelectedItem().getValue()
                        .equals("Perfil") && !treeView.getSelectionModel().getSelectedItem().getValue().equals("Reportes")) {

                    switch (treeView.getSelectionModel().getSelectedItem().getValue()) {
                        case "Plan de Familiarización":
                            if (rol == 7) {
                                text_EncabezadoPlanFam.setText("Usted como vicerrector de la CUJAE tiene la responsabilidad de revisar el Plan " +
                                        "de Familiarización que ya ha sido aprobado por el Director de Recursos Humanos y " +
                                        "darle su aprobación o no. En su pantalla principal usted podrá observar una tabla " +
                                        "con el registro histórico de los Planes de Familiarización realizados; si desea ver " +
                                        "con más detalle o revisar alguno que aún no haya revisado primero debe seleccionarlo " +
                                        "y presionar el botón de 'Revisar Plan de Familiarización' como se muestra a continuación:");
                                foto_PlanF_2.setImage(new Image("Vista/Imagenes/Screenshot (172).png"));
                            } else if (rol == 5) {
                                text_EncabezadoPlanFam.setText("Usted como Director de Recursos Humanos tiene la responsabilidad de revisar " +
                                        "el Plan de Familiarización que  ha sido realizado por un Especialista de Recursos Humanos y " +
                                        "darle su aprobación o no. En su pantalla principal usted podrá observar una tabla con el" +
                                        " registro histórico de los Planes de Familiarización realizados; si desea ver con más detalle " +
                                        "o revisar alguno que aún no haya revisado, primero debe seleccionarlo y presionar el botón de " +
                                        "'Revisar plan' como se muestra a continuación:");
                                foto_PlanF_2.setImage(new Image("Vista/Imagenes/Captura de pantalla (13).png"));
                            }
                            vbox_Asignados.setVisible(false);
                            vbox_PlanFam.setVisible(true);
                            vBoxIntro.setVisible(false);
                            vBoxPerfil.setVisible(false);
                            vBoxReportes.setVisible(false);
                            vBoxNotificaciones.setVisible(false);
                            vBoxAsignacionDirectRH.setVisible(false);
                            vbox_PlanFam_Especialista.setVisible(false);
                            vBox_Estudiantes_Espec.setVisible(false);
                            vbox_PlanFam_JefeArea.setVisible(true);
                            vbox_PlanForm_Jefe.setVisible(false);
                            vbox_PlanForm_Tuto.setVisible(false);
                            vBoxEvaluacion.setVisible(false);

                            break;

                        case "Gestión de Planes de Familiarización":
                            vbox_PlanFam.setVisible(false);
                            vBoxIntro.setVisible(false);
                            vBoxPerfil.setVisible(false);
                            vBoxReportes.setVisible(false);
                            vBoxNotificaciones.setVisible(false);
                            vbox_Asignados.setVisible(false);
                            vBoxAsignacionDirectRH.setVisible(false);
                            vbox_PlanFam_Especialista.setVisible(true);
                            vbox_PlanFam_JefeArea.setVisible(false);
                            vbox_PlanForm_Tuto.setVisible(false);
                            break;

                        case "Asignación de graduados":
                            vbox_PlanFam.setVisible(false);
                            vBoxIntro.setVisible(false);
                            vBoxPerfil.setVisible(false);
                            vBoxReportes.setVisible(false);
                            vBoxNotificaciones.setVisible(false);
                            vbox_PlanFam_Especialista.setVisible(false);
                            vBox_Estudiantes_Espec.setVisible(false);
                            vbox_PlanFam_JefeArea.setVisible(false);
                            vbox_PlanForm_Jefe.setVisible(false);
                            vbox_PlanForm_Tuto.setVisible(false);
                            vBoxEvaluacion.setVisible(false);

                            if (rol == 7) {
                                vbox_Asignados.setVisible(true);
                                vBoxAsignacionDirectRH.setVisible(false);
                            } else if (rol == 5) {
                                vbox_Asignados.setVisible(false);
                                vBoxAsignacionDirectRH.setVisible(true);
                            }

                            break;

                        case "Mi perfil":
                            vbox_PlanFam.setVisible(false);
                            vBoxIntro.setVisible(false);
                            vbox_Asignados.setVisible(false);
                            vBoxPerfil.setVisible(true);
                            vBoxReportes.setVisible(false);
                            vBoxNotificaciones.setVisible(false);
                            vBoxAsignacionDirectRH.setVisible(false);
                            vbox_PlanFam_Especialista.setVisible(false);
                            vBox_Estudiantes_Espec.setVisible(false);
                            vbox_PlanForm_Jefe.setVisible(false);
                            vbox_PlanForm_Tuto.setVisible(false);
                            vBoxEvaluacion.setVisible(false);
                            break;

                        case "Notificaciones":

                            vbox_PlanFam.setVisible(false);
                            vBoxIntro.setVisible(false);
                            vbox_Asignados.setVisible(false);
                            vBoxPerfil.setVisible(false);
                            vBoxReportes.setVisible(false);
                            vBoxNotificaciones.setVisible(true);
                            vBoxAsignacionDirectRH.setVisible(false);
                            vbox_PlanFam_JefeArea.setVisible(false);
                            vbox_PlanForm_Jefe.setVisible(false);
                            vbox_PlanForm_Tuto.setVisible(false);
                            vBoxEvaluacion.setVisible(false);

                            break;

                        case "Gestión de estudiantes":

                            vbox_PlanFam.setVisible(false);
                            vBoxIntro.setVisible(false);
                            vbox_Asignados.setVisible(false);
                            vBoxPerfil.setVisible(false);
                            vBoxReportes.setVisible(false);
                            vBoxNotificaciones.setVisible(false);
                            vBoxAsignacionDirectRH.setVisible(false);
                            vbox_PlanFam_Especialista.setVisible(false);
                            vBox_Estudiantes_Espec.setVisible(true);
                            vbox_PlanForm_Jefe.setVisible(false);
                            vbox_PlanForm_Tuto.setVisible(false);
                            vBoxEvaluacion.setVisible(false);

                            break;

                        case "Planes de adiestramiento":
                            vbox_PlanFam.setVisible(false);
                            vBoxIntro.setVisible(false);
                            vbox_Asignados.setVisible(false);
                            vBoxPerfil.setVisible(false);
                            vBoxReportes.setVisible(false);
                            vBoxNotificaciones.setVisible(false);
                            vBoxAsignacionDirectRH.setVisible(false);
                            vbox_PlanFam_Especialista.setVisible(false);
                            vBox_Estudiantes_Espec.setVisible(false);
                            vbox_PlanForm_Jefe.setVisible(true);
                            vbox_PlanForm_Tuto.setVisible(false);

                            break;

                        case "Planes de Adiestramiento":
                            vbox_PlanFam.setVisible(false);
                            vBoxIntro.setVisible(false);
                            vbox_Asignados.setVisible(false);
                            vBoxPerfil.setVisible(false);
                            vBoxReportes.setVisible(false);
                            vBoxNotificaciones.setVisible(false);
                            vBoxAsignacionDirectRH.setVisible(false);
                            vbox_PlanFam_Especialista.setVisible(false);
                            vBox_Estudiantes_Espec.setVisible(false);
                            vbox_PlanForm_Jefe.setVisible(false);
                            vbox_PlanForm_Tuto.setVisible(true);
                            vBoxEvaluacion.setVisible(false);

                            break;

                        case "Evaluar tareas":
                            vbox_PlanFam.setVisible(false);
                            vBoxIntro.setVisible(false);
                            vbox_Asignados.setVisible(false);
                            vBoxPerfil.setVisible(false);
                            vBoxReportes.setVisible(false);
                            vBoxNotificaciones.setVisible(false);
                            vBoxAsignacionDirectRH.setVisible(false);
                            vbox_PlanFam_Especialista.setVisible(false);
                            vBox_Estudiantes_Espec.setVisible(false);
                            vbox_PlanForm_Jefe.setVisible(false);
                            vbox_PlanForm_Tuto.setVisible(false);
                            vBoxEvaluacion.setVisible(true);

                            break;

                        default:
                            vbox_PlanFam.setVisible(false);
                            vBoxIntro.setVisible(false);
                            vbox_Asignados.setVisible(false);
                            vBoxPerfil.setVisible(false);
                            vBoxReportes.setVisible(true);
                            vBoxNotificaciones.setVisible(false);
                            vBoxAsignacionDirectRH.setVisible(false);
                            vbox_PlanFam_Especialista.setVisible(false);
                            vBox_Estudiantes_Espec.setVisible(false);
                            vbox_PlanFam_JefeArea.setVisible(false);
                            vbox_PlanForm_Jefe.setVisible(false);
                            vbox_PlanForm_Tuto.setVisible(false);
                            vBoxEvaluacion.setVisible(false);
                            if (treeView.getSelectionModel().getSelectedItem().getValue().equals("Estudiantes con tesis aplicable"))
                                Cargar_ReporteTesis();
                            else if (treeView.getSelectionModel().getSelectedItem().getValue().equals("Especialidades por área"))
                                Cargar_ReporteEspecialidades();
                            else if (treeView.getSelectionModel().getSelectedItem().getValue().equals("Asistencia al Plan de Familiarización"))
                                Cargar_ReporteActividadesPlanF();
                            else if (treeView.getSelectionModel().getSelectedItem().getValue().equals("Adiestrados de primer año"))
                                Cargar_ReporteAdiestrados();
                            else if (treeView.getSelectionModel().getSelectedItem().getValue().equals("Relación tutor-graduado-área"))
                                Cargar_ReporteTutoEstArea();
                            else if (treeView.getSelectionModel().getSelectedItem().getValue().equals("Plan de Adiestramiento"))
                                Cargar_Reporte_PlanAdiestramiento();
                            else if (treeView.getSelectionModel().getSelectedItem().getValue().equals("Estudiantes por centro de procedencia"))
                                CargarReporte_EstXCentro();
                            else if(treeView.getSelectionModel().getSelectedItem().getValue().equals("Estudiantes por especialidad"))
                                CargarReporte_EstXEspec();
                            break;

                    }

        /*          if (treeView.getSelectionModel().getSelectedItem().getValue().equals("Estudiantes con tesis aplicable")
                            || treeView.getSelectionModel().getSelectedItem().getValue().equals("Especialidades por área")
                            || treeView.getSelectionModel().getSelectedItem().getValue().equals("Asistencia al Plan de Familiarización")
                            || treeView.getSelectionModel().getSelectedItem().getValue().equals("Adiestrados de primer año")
                            || treeView.getSelectionModel().getSelectedItem().getValue().equals("Relación tutor-graduado-área")) {

                       }
*/

                }
            }

        });

        //Poner Pantalla linda
        botonCerrarVentana.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });

        botonMinimizarVentana.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                stage.toBack();
            }
        });
        botonCerrarVentana.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                botonCerrarVentana.setStyle("-fx-background-color: red");


            }
        });
        botonMinimizarVentana.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                botonMinimizarVentana.setStyle("-fx-background-color: blue");


            }
        });


        hbox.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                botonCerrarVentana.setStyle("-fx-background-color: #065325");
                botonMinimizarVentana.setStyle("-fx-background-color: #065325");

            }
        });
        paneTitulo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                botonCerrarVentana.setStyle("-fx-background-color: #065325");
                botonMinimizarVentana.setStyle("-fx-background-color: #065325");

            }
        });


    }


    public void LlenarTreeView_Vicerrector() {
        TreeItem<String> raiz = new TreeItem<>("Roles: ");


        TreeItem<String> nodoPlanFam = new TreeItem<>("Plan de Familiarización", getImagenIcono(2));
        TreeItem<String> nodoPlanAdiest = new TreeItem<>("Asignación de graduados", getImagenIcono(2));
        TreeItem<String> nodoReportes = new TreeItem<>("Reportes", getImagenIcono(1));

        TreeItem<String> nodoPerfil = new TreeItem<>("Perfil", getImagenIcono(3));
        TreeItem<String> nodoPerfilPersona = new TreeItem<>("Mi perfil", getImagenIcono(4));
        TreeItem<String> nodoNotificaciones = new TreeItem<>("Notificaciones", getImagenIcono(5));
        nodoPerfil.getChildren().addAll(nodoPerfilPersona, nodoNotificaciones);

        TreeItem<String> nodoReporte1 = new TreeItem<>("Estudiantes con tesis aplicable");
        TreeItem<String> nodoReporte2 = new TreeItem<>("Especialidades por área");
        TreeItem<String> nodoReporte3 = new TreeItem<>("Asistencia al Plan de Familiarización");
        TreeItem<String> nodoReporte4 = new TreeItem<>("Adiestrados de primer año");
        TreeItem<String> nodoReporte5 = new TreeItem<>("Relación tutor-graduado-área");


        nodoReportes.getChildren().addAll(nodoReporte1, nodoReporte2, nodoReporte3, nodoReporte4, nodoReporte5);
        raiz.getChildren().addAll(nodoPlanFam, nodoPlanAdiest, nodoPerfil, nodoReportes);

        treeView.setRoot(raiz);
        treeView.setShowRoot(false);
    }

    public void LLenarDirectorRH() {
        TreeItem<String> raiz = new TreeItem<>("Roles");

        TreeItem<String> nodoReportes = new TreeItem<>("Reportes", getImagenIcono(1));
        TreeItem<String> nodoReporte1 = new TreeItem<>("Estudiantes con tesis aplicable");
        TreeItem<String> nodoReporte2 = new TreeItem<>("Especialidades por área");
        TreeItem<String> nodoReporte3 = new TreeItem<>("Asistencia al Plan de Familiarización");

        nodoReportes.getChildren().addAll(nodoReporte1, nodoReporte2, nodoReporte3);

        TreeItem<String> nodoPerfil = new TreeItem<>("Perfil", getImagenIcono(3));
        TreeItem<String> nodoPerfilPersona = new TreeItem<>("Mi perfil", getImagenIcono(4));
        TreeItem<String> nodoNotificaciones = new TreeItem<>("Notificaciones", getImagenIcono(5));
        nodoPerfil.getChildren().addAll(nodoPerfilPersona, nodoNotificaciones);

        TreeItem<String> nodoAsignarGraduados = new TreeItem<>("Asignación de graduados", getImagenIcono(2));
        TreeItem<String> nodoPlanFam = new TreeItem<>("Plan de Familiarización", getImagenIcono(2));
        raiz.getChildren().addAll(nodoPlanFam, nodoAsignarGraduados, nodoPerfil, nodoReportes);
        treeView.setRoot(raiz);
        treeView.setShowRoot(false);

    }

    public void LLenarEspecialistaRH() {

        TreeItem<String> raiz = new TreeItem<>("Raiz");
        TreeItem<String> planF = new TreeItem<>("Gestión de Planes de Familiarización", getImagenIcono(2));
        TreeItem<String> estudiantesEspecRH = new TreeItem<>("Gestión de estudiantes", getImagenIcono(7));
        TreeItem<String> nodoReportes = new TreeItem<>("Reportes", getImagenIcono(1));

        TreeItem<String> nodoPerfil = new TreeItem<>("Perfil", getImagenIcono(3));
        TreeItem<String> nodoPerfilPersona = new TreeItem<>("Mi perfil", getImagenIcono(4));
        TreeItem<String> nodoNotificaciones = new TreeItem<>("Notificaciones", getImagenIcono(5));
        nodoPerfil.getChildren().addAll(nodoPerfilPersona, nodoNotificaciones);

        TreeItem<String> nodoReporte1 = new TreeItem<>("Estudiantes con tesis aplicable");
        TreeItem<String> nodoReporte3 = new TreeItem<>("Estudiantes por especialidad");
        TreeItem<String> nodoReporte4 = new TreeItem<>("Estudiantes por centro de procedencia");

        nodoReportes.getChildren().addAll(nodoReporte1,nodoReporte3, nodoReporte4);
        raiz.getChildren().addAll(planF, estudiantesEspecRH, nodoPerfil, nodoReportes);

        treeView.setRoot(raiz);
        treeView.setShowRoot(false);

    }

    public void LlenarTutor() {
        TreeItem<String> raiz = new TreeItem<>("Raiz");
        TreeItem<String> nodoPlanForm = new TreeItem<>("Planes de Adiestramiento", getImagenIcono(7));
        TreeItem<String> nodoReportes = new TreeItem<>("Reportes", getImagenIcono(1));
        TreeItem<String> nodoEvaluar = new TreeItem<>("Evaluar tareas", getImagenIcono(6));

        TreeItem<String> nodoPerfil = new TreeItem<>("Perfil", getImagenIcono(3));
        TreeItem<String> nodoPerfilPersona = new TreeItem<>("Mi perfil", getImagenIcono(4));
        TreeItem<String> nodoNotificaciones = new TreeItem<>("Notificaciones", getImagenIcono(5));
        nodoPerfil.getChildren().addAll(nodoPerfilPersona, nodoNotificaciones);

        TreeItem<String> nodoReporte1 = new TreeItem<>("Estudiantes con tesis aplicable");
        TreeItem<String> nodoReporte2 = new TreeItem<>("Plan de Adiestramiento");
        TreeItem<String> nodoReporte3 = new TreeItem<>("Estudiantes por especialidad");
        TreeItem<String> nodoReporte4 = new TreeItem<>("Estudiantes por centro de procedencia");


        nodoReportes.getChildren().addAll(nodoReporte1, nodoReporte2, nodoReporte3, nodoReporte4);
        raiz.getChildren().addAll(nodoPlanForm, nodoEvaluar, nodoPerfil, nodoReportes);
        treeView.setRoot(raiz);
        treeView.setShowRoot(false);

    }

    public void LlenarJefeArea() {

        TreeItem<String> raiz = new TreeItem<>("Raiz");
        TreeItem<String> nodoPlanF = new TreeItem<>("Plan de Familiarización", getImagenIcono(2));
        TreeItem<String> nodoPlanForm = new TreeItem<>("Planes de adiestramiento", getImagenIcono(7));
        TreeItem<String> nodoReportes = new TreeItem<>("Reportes", getImagenIcono(1));

        TreeItem<String> nodoPerfil = new TreeItem<>("Perfil", getImagenIcono(3));
        TreeItem<String> nodoPerfilPersona = new TreeItem<>("Mi perfil", getImagenIcono(4));
        TreeItem<String> nodoNotificaciones = new TreeItem<>("Notificaciones", getImagenIcono(5));
        nodoPerfil.getChildren().addAll(nodoPerfilPersona, nodoNotificaciones);

        TreeItem<String> nodoReporte1 = new TreeItem<>("Estudiantes con tesis aplicable");
        TreeItem<String> nodoReporte3 = new TreeItem<>("Estudiantes por especialidad");
        TreeItem<String> nodoReporte4 = new TreeItem<>("Estudiantes por centro de procedencia");


        nodoReportes.getChildren().addAll(nodoReporte1,nodoReporte3, nodoReporte4);
        raiz.getChildren().addAll(nodoPlanF, nodoPlanForm, nodoPerfil, nodoReportes);
        treeView.setRoot(raiz);
        treeView.setShowRoot(false);

    }



    public ImageView getImagenIcono(int tipoTreeItem) {

        Image image = null;
        ImageView imagenRetorno = new ImageView();
        imagenRetorno.setFitHeight(20);
        imagenRetorno.setFitWidth(20);

        if (tipoTreeItem == 1) {
            //Es un reporte
            image = new Image("Vista/Imagenes/icons8_Cashbook_32.png");
        } else if (tipoTreeItem == 2) {
            //Es un plan familiarizacion
            image = new Image("Vista/Imagenes/Report Card_50px.png");

        } else if (tipoTreeItem == 3) {
            //Es un perfil
            image = new Image("Vista/Imagenes/Profile_White_50px.png");
        } else if (tipoTreeItem == 4) {
            //Es un mi perfil
            image = new Image("Vista/Imagenes/Male User_White_50px.png");

        } else if (tipoTreeItem == 5) {
            //Es una notificacion
            image = new Image("Vista/Imagenes/Notification_White_50px.png");
        } else if (tipoTreeItem == 6) {
            //Es evaluar tarea
            image = new Image("Vista/Imagenes/Edit_50px.png");
        }
        else if(tipoTreeItem==7)
            //Es un plan adiestramiento
            image = new Image("Vista/Imagenes/Test Passed_50px.png");

        imagenRetorno.setImage(image);

        return imagenRetorno;
    }



    public void Cargar_ReporteTesis() {
        label_TituloReporte.setText("Reporte de los estudiantes con tesis aplicable a la CUJAE");
        text_EncabezadoReporte.setText("Este reporte le permitirá tener una relación de todos los graduados que usted atiende" +
                " desde su cargo, que tienen tesis aplicable a la institución. Para acceder a él solo " +
                "debe seleccionar en la barra de menú la opción 'Reportes' y allí seleccionar la opción 'Estudiantes " +
                "con tesis aplicable', como se mostrará a continuación:");

    }

    public void Cargar_ReporteEspecialidades() {
        label_TituloReporte.setText("Reporte de la cantidad de graduados de cada especialidad por área");
        text_EncabezadoReporte.setText("Este reporte le permitirá tener una relación de la cantidad de graduados por cada " +
                "especialidad presente en cada área. Para acceder a él solo debe seleccionar en la barra de menú la opción 'Reportes' " +
                "y allí seleccionar la opción 'Graduados por especialidad por área', como se mostrará a continuación:");
    }

    public void Cargar_ReporteActividadesPlanF() {
        label_TituloReporte.setText("Reporte de la asistencia al Plan de Familiarización");
        text_EncabezadoReporte.setText("Este reporte le permitirá tener una relación del porciento de asistencia " +
                "a las actividades del Plan de Familiarización. Para acceder a él solo debe seleccionar en la barra de menú la opción 'Reportes' " +
                "y allí seleccionar la opción 'Asistencia a las actividades del Plan de Familiarización', como se mostrará a continuación:");
    }

    public void Cargar_ReporteAdiestrados() {
        label_TituloReporte.setText("Reporte de los adiestrados de primer año");
        text_EncabezadoReporte.setText("Este reporte le permitirá conocer información importante de los graduados que se encuentran en primer año de adiestramiento" +
                ", puede obtener la información por área, especialidad o ambos. Para acceder a él solo debe seleccionar en la barra de menú la opción 'Reportes' " +
                "y allí seleccionar la opción 'Adiestrados de primer año', como se mostrará a continuación: ");
    }

    public void Cargar_ReporteTutoEstArea() {
        label_TituloReporte.setText("Reporte de tutor-graduado-área-jefe de área");
        text_EncabezadoReporte.setText("Este reporte le permitirá conocer la relación del tutor, área y jefe de área(en caso de existir)" +
                "por cada adiestrado.  Para acceder a él solo debe seleccionar en la barra de menú la opción 'Reportes' " +
                "y allí seleccionar la opción 'Relación tutor-graduado-área', como se mostrará a continuación:");
    }


    public void Cargar_Reporte_PlanAdiestramiento() {
        label_TituloReporte.setText("Reporte Plan de Adiestramiento:");
        text_EncabezadoReporte.setText("Este reporte le permitirá conocer de manera resumida todas tareas organizadas por etapas del Plan de Adiestramiento" +
                "de cada tutorado.  Para acceder a él solo debe seleccionar en la barra de menú la opción 'Reportes' " +
                "y allí seleccionar la opción 'Plan de Adiestramiento', como se mostrará a continuación:");
    }

    public void CargarReporte_EstXEspec() {
        label_TituloReporte.setText("Reporte estudiantes por especialidad: ");
        text_EncabezadoReporte.setText("Este reporte le permitirá tener una relación de todos los graduados que usted atiende" +
                " desde su cargo, distribuidos por especialidad. Para acceder a él solo " +
                "debe seleccionar en la barra de menú la opción 'Reportes' y allí seleccionar la opción 'Graduados " +
                "por especialidad', como se mostrará a continuación:");
    }

    public void CargarReporte_EstXCentro() {
        label_TituloReporte.setText("Reporte estudiantes por procedencia: ");
        text_EncabezadoReporte.setText("Este reporte le permitirá tener una relación de todos los graduados que usted atiende" +
                " desde su cargo, distribuidos por centro de procedencia. Para acceder a él solo " +
                "debe seleccionar en la barra de menú la opción 'Reportes' y allí seleccionar la opción 'Graduados " +
                "por procedencia', como se mostrará a continuación:");
    }
}
